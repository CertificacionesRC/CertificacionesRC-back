package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.model.Configuraciones;
import com.unicauca.backend_registro_calificado.model.ProgramaAcademico;
import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.model.SubItem;
import com.unicauca.backend_registro_calificado.repository.IConfiguracionesRepo;
import com.unicauca.backend_registro_calificado.repository.IObservacionRepository;
import com.unicauca.backend_registro_calificado.repository.IProgramaAcademicoRepository;
import com.unicauca.backend_registro_calificado.repository.IRegistroCalifRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class DocumentoServiceimple implements IDocumentoService {

    private static final Logger logger = LoggerFactory.getLogger(RegistroCalifServiceImple.class);
    private final ModelMapper modelMapper;
    private final IRegistroCalifRepository iRegistroCalifRepository;
    private final IObservacionRepository iObservacionRepository;
    private final IProgramaAcademicoRepository iProgramaAcademicoRepo;

    private final IConfiguracionesRepo iConfiguracionesRepo;

    private static IitemService iitemService;

    public DocumentoServiceimple(ModelMapper modelMapper, IRegistroCalifRepository iRegistroCalifRepository, IObservacionRepository iObservacionRepository,
                                 IProgramaAcademicoRepository iProgramaAcademicoRepository, IConfiguracionesRepo iConfiguracionesRepo, IitemService iitemService) {
        this.modelMapper = modelMapper;
        this.iRegistroCalifRepository = iRegistroCalifRepository;
        this.iObservacionRepository = iObservacionRepository;
        this.iProgramaAcademicoRepo = iProgramaAcademicoRepository;
        this.iConfiguracionesRepo = iConfiguracionesRepo;
        this.iitemService = iitemService;
    }

    @Override
    public ResponseEntity<byte[]> downloadWordFile(Integer IdRegistroCalificado) throws IOException {
        //id del registro calificado
        String idregistro = String.valueOf(IdRegistroCalificado);

        RegistroCalificado registroCal = this.iRegistroCalifRepository.findById(idregistro).get();

        ProgramaAcademico ProgramaAcade = this.iProgramaAcademicoRepo.findById(registroCal.getProgramaAcademico().getId());

        List<Configuraciones> configuraciones = this.iConfiguracionesRepo.findAll();

        // Ruta donde se guardará el archivo Word dentro de la carpeta resources
        String filepath = "src/main/resources/files/prueba.docx";

        try {

            this.CreatefileDocx(ProgramaAcade,configuraciones);
            File file = new File(filepath);
            FileInputStream fileInputStream = new FileInputStream(file);

            //configuramos los encabezados de respuesta
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "registroCalificado.docx");

            //lee el contenido del archivo en un array de bytes
            byte[] fileContent = new byte[(int) file.length()];
            fileInputStream.read(fileContent);

            //cerramos el archivo
            fileInputStream.close();

            //devuelve el archivo como una respuesta http
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileContent);
        }catch (Exception e) {
            logger.error("Error en el archivo: " + e.getMessage());
            return null;
        }
    }

    public static void CreatefileDocx(ProgramaAcademico ProgramaAcade, List<Configuraciones> configuraciones ) throws IOException {

        //el String se reemplaza con la inormacion de tynyeditor de cada item

        /*String htmlText = "<h1>Facultad de...</h1>\n" +
                "    <h2>Condiciones de Calidad</h2>\n" +
                "    <img src=\"C:\\Users\\Windows 10\\Documents\\202302\\proyecto2\\unicauca.jpg\">\n" +
                "    <p>Programa de (pregrado/posgrado)...</p>\n" +
                "    <p>Popayán</p>\n" +
                "    <p>(mes) 2023</p>\n" +
                "\n" +
                "    <h1>Deibar René Hurtado Herrera</h1>\n" +
                "    <h2>Rector</h2>\n" +
                "\n" +
                "    <h1>Aida Patricia González Nieva</h1>\n" +
                "    <h2>Vicerrectora Académica</h2>\n" +
                "    \n" +
                "    <h1>Jorge Enrique Barrera Moreno</h1>\n" +
                "    <h2>Vicerrector Administrativo</h2>\n" +
                "\n" +
                "    <h1>Francisco José Pino Correa</h1>\n" +
                "    <h2>Vicerrector de Investigaciones</h2>\n" +
                "\n" +
                "    <h1>César Alfaro Mosquera Dorado</h1>\n" +
                "    <h2>Vicerrector de Cultura y Bienestar</h2>\n" +
                "\n" +
                "    <h1>Laura Ismenia Castellanos Vivas</h1>\n" +
                "    <h2>Secretaria General</h2>";*/
        //crear el documento word
        XWPFDocument document = new XWPFDocument();
        portadaFile(document, ProgramaAcade,configuraciones );
        //vamos a tratar la respuesta de la vista y buscamos las etiquetas p strong list table img etc
        //convertimos la respueta del front a un formato de arbol y nodos con la libreria Jsoup
        //Document doc = Jsoup.parse(htmlText);
        //Element body = doc.body();

        /*for (Element element : body.children()){
            //funcion que va aprocesar cada nodo del arbol html
            processElement(element, document);
        }*/

        //crearIndice(document);

        XWPFParagraph p = document.createParagraph();

        String encodedURI = URLEncoder.encode("#my_bookmark");
        XWPFHyperlinkRun hyperlinkRun = p.createHyperlinkRun(encodedURI);
        //hyperlinkRun.setText("Click here to go to the bookmark!");

        XWPFParagraph indexParagraph = document.createParagraph();
        indexParagraph.addRun(hyperlinkRun);
        XWPFRun indexRun = indexParagraph.createRun();
        //indexRun.setText("my_bookmark");
        XWPFParagraph paragraph = document.createParagraph();

        //pasear el html y argegar al docuemtno word
        //Document doc2 = Jsoup.parse(htmlText);
        //Element bodyy = doc2.body();
        //paragraph.createRun().setText(bodyy.text());

        //intentamos guardar el documento word
        try {
            document.write(new FileOutputStream("src/main/resources/files/prueba.docx"));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void portadaFile(XWPFDocument document, ProgramaAcademico ProgramaAcade, List<Configuraciones> configuraciones ) throws IOException {

        processImage(3,document);

        procesarTitulo(3, document, "Facultad de Electronica y Telecomunicaciones");

        procesarTitulo(0, document, "Condiciones de Calidad");

        String Tprograma = "Programa de " + ProgramaAcade.getTipo() + ": "+ ProgramaAcade.getNombre();

        procesarTitulo(7, document, Tprograma );

        procesarTitulo(0, document, "Popayan");

        LocalDate fechaActual = LocalDate.now();

        // Obtener el año, mes y día por separado
        String anio = String.valueOf(fechaActual.getYear());
        Month mes = fechaActual.getMonth();
        String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        procesarTitulo(0, document, nombreMes);

        procesarTitulo(2, document, anio);

        System.out.println("configuraciones: "+configuraciones.get(0).getNombreVariable());

        for (int i = 0; i < configuraciones.size(); i++) {
            if(configuraciones.get(i).getNombreVariable().equals("rector")){
                procesarTitulo(0, document,configuraciones.get(i).getContenido());
                procesarTitulo(1, document,configuraciones.get(i).getNombreVariable());
            }
            if(configuraciones.get(i).getNombreVariable().equals("vicerrector_academico")){
                procesarTitulo(0, document,configuraciones.get(i).getContenido());
                procesarTitulo(2, document,configuraciones.get(i).getNombreVariable());
            }
            if(configuraciones.get(i).getNombreVariable().equals("vicerrector_administrativo")){
                procesarTitulo(0, document,configuraciones.get(i).getContenido());
                procesarTitulo(2, document,configuraciones.get(i).getNombreVariable());
            }
            if(configuraciones.get(i).getNombreVariable().equals("vicerrector_investigaciones")){
                procesarTitulo(0, document,configuraciones.get(i).getContenido());
                procesarTitulo(2, document,configuraciones.get(i).getNombreVariable());
            }
            if(configuraciones.get(i).getNombreVariable().equals("vicerrector_cultura_bienestar")){
                procesarTitulo(0, document,configuraciones.get(i).getContenido());
                procesarTitulo(2, document,configuraciones.get(i).getNombreVariable());
            }
            if(configuraciones.get(i).getNombreVariable().equals("secretaria_general")){
                procesarTitulo(0, document,configuraciones.get(i).getContenido());
                procesarTitulo(2, document,configuraciones.get(i).getNombreVariable());
            }
        }
    }

    private static void procesarTitulo(int saltoslinea,XWPFDocument document, String texto){
        // Creamos el párrafo y le asignamos el estilo de encabezado 2
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        // Establecemos el tamaño de fuente para que coincida con un encabezado h2
        run.setFontSize(14); // Puedes ajustar el tamaño según tus preferencias

        // Establecemos el formato del texto (puedes agregar más formato según sea necesario)
        run.setBold(true);

        // Agregamos el texto de la etiqueta al párrafo
        run.setText(texto);
        // Agregamos un salto de línea después del párrafo
        for(int i=saltoslinea;i>0;i--) {
            document.createParagraph().createRun().addBreak();
        }
    }

    private static void processElement(Element element, XWPFDocument document) throws IOException {

        String tagName = element.tagName();

        if ("p".equals(tagName)) {
            processParagraph(element, document);  //metodo que crea parrafos
        } else if ("ul".equals(tagName) || "ol".equals(tagName)) {
            //processList(element, document, paragraph); //metodo que procesa listas
        } else if ("img".equals(tagName)) {
            processImage(1,document);
            //processImage(element, document, paragraph); //metodo que procesa imagenes

        } else if ("h1".equals(tagName) ){
            processh1(element, document);
            //processParagraph(element, document);
        }else if("h2".equals(tagName)){
            processh2(element, document);
        }
        //para procesar sub elementos de ese elemento
        for (Element child : element.children()) {
            processElement(child, document);
        }
    }


    private static void processh1(Element element, XWPFDocument document) {
        // Creamos el párrafo y le asignamos el estilo de encabezado 1
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        // Establecemos el tamaño de fuente para que coincida con un encabezado h1
        run.setFontSize(24);

        // Agregamos el texto de la etiqueta al párrafo
        run.setText(element.text());

        // Agregamos un salto de línea después del párrafo
        document.createParagraph().createRun().addBreak();
    }


    private static void processh2(Element element, XWPFDocument document) {
        // Creamos el párrafo y le asignamos el estilo de encabezado 2
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        // Establecemos el tamaño de fuente para que coincida con un encabezado h2
        run.setFontSize(18); // Puedes ajustar el tamaño según tus preferencias

        // Establecemos el formato del texto (puedes agregar más formato según sea necesario)
        run.setBold(true);

        // Agregamos el texto de la etiqueta al párrafo
        run.setText(element.text());

        // Agregamos un salto de línea después del párrafo
        document.createParagraph().createRun().addBreak();
    }

    private static void processParagraph(Element element, XWPFDocument document) {
        //creamos el parrafo y se lo agregarmos al documento
        //obtenemos el texto de la etiqueta
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.text());
    }


    private static void processImage(int saltosLinea, XWPFDocument document) throws IOException {
        // Extract the image source URL from the element

        String rutaLocal = "src/main/resources/files/unicauca.jpg";

        Path origen = Path.of(rutaLocal);

        // Verificar si el archivo de origen existe
        if (Files.exists(origen)) {
            // Copiar el archivo al destino especificado
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            //run.setText(Files.probeContentType(origen));
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            insertarImagen(run, document, origen, rutaLocal);
            for(int i=saltosLinea;i>0;i--) {
                document.createParagraph().createRun().addBreak();
            }
            //Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        } else {
            throw new IOException("El archivo de origen no existe en la ruta especificada.");
        }
    }

    private static void insertarImagen(XWPFRun run, XWPFDocument document, Path imagePath, String rutaImagen) throws IOException {
        // Obtener el contenido de la imagen como bytes
        try (FileInputStream imageStream = new FileInputStream(rutaImagen)) {
            run.addPicture(imageStream, XWPFDocument.PICTURE_TYPE_JPEG, "imagen", Units.toEMU(100), Units.toEMU(100));
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para crear la tabla de contenido
    private static void createTableOfContents(XWPFDocument document){
        //llamamos a funcion que trae los items
        List Items = getItems();
        //System.out.println("estos son los items");
        //System.out.println(Items);

        //recorremos los items y agregamos al documento
        int mainIndex = 1;

        for (Object item : Items) {
            //System.out.println("este es el item");
            //System.out.println(item);

            //System.out.println("este es el nombre del item");
            ItemDTO itemDTO = (ItemDTO) item;

            String nombreItem = mainIndex + ". " + itemDTO.getNombre();
            System.out.println(nombreItem);

            //creamos el parrafo y se lo agregarmos al documento
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(nombreItem);

            //creamos los subitems
            int subIndex = 1;
            List<SubItem> subItemsList = itemDTO.getSubItems();

            //iteramos la listade subitems
            for (SubItem subItem : subItemsList) {

                SubItem padreDeItem = subItem.getParentSubItem();

                System.out.println("este es el subitem: " + subItem.getNombre());

                System.out.println("tiene items: ");
                System.out.println(padreDeItem);

                //para mapear items de (3) tercer nivel
                /*if(padreDeItem == null){
                    System.out.println("el sub item" + mainIndex +":"+ subIndex + "tiene items" );
                }else{
                    System.out.println("el sub item" + mainIndex +":"+ subIndex + "no tiene items" );
                }*/

                String nombreSubItem = "    " + mainIndex + "." + subIndex + ". " + subItem.getNombre();
                //System.out.println(nombreSubItem);

                //creamos el parrafo y se lo agregarmos al documento
                XWPFParagraph paragraph2 = document.createParagraph();
                XWPFRun run2 = paragraph2.createRun();
                run2.setText(nombreSubItem);

                subIndex++;
            }

            mainIndex++;
        }
    }

    public static List<ItemDTO> getItems(){
        List<ItemDTO> listaItems = iitemService.findAllItem();
        return listaItems;
    }


    //metodo para crear la tabla de contenido
    private static void crearIndice(XWPFDocument document) {
        // Crear un párrafo para el título del índice
        XWPFParagraph titleParagraph = document.createParagraph();
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setBold(true);
        titleRun.setText("Tabla de contenido");



        XWPFParagraph p = document.createParagraph();
        XWPFHyperlinkRun hyperlinkRun = p.createHyperlinkRun("my_bookmark");
        hyperlinkRun.setText("Click here to go to the bookmark!");

        // Add the hyperlink to the paragraph
        //p.addRun(hyperlinkRun);

        XWPFParagraph indexParagraph = document.createParagraph();
        indexParagraph.addRun(hyperlinkRun);
        XWPFRun indexRun = indexParagraph.createRun();
        indexRun.setText("nuevo parrafo con link");


        // Crear un párrafo para cada entrada del índice
        /*agregarEntradaIndice(document, "1. Denominación del Programa");
        agregarEntradaIndice(document, "  1.1. Información básica del programa");
        agregarEntradaIndice(document, "  1.2. Análisis de la coherencia entre la denominación, el título a otorgar, el nivel de formación, los contenidos curriculares y el perfil de egreso");
        agregarEntradaIndice(document, "2. Justificación del Programa");
        agregarEntradaIndice(document, "  2.1. Estado de la oferta de educación en el área del programa a nivel nacional y global");
        agregarEntradaIndice(document, "  2.2. Necesidades de la región y del país");
        agregarEntradaIndice(document, "  2.3. Justificación de atributos o factores que constituyen los rasgos distintivos del programa");
*/

        /*CTP ctP = p.getCTP();
        CTSimpleField toc = ctP.addNewFldSimple();
        toc.setInstr("TOC \\h");
        toc.setDirty(STOnOff1.ON);*/


        // Agregar un salto de página después del índice
        document.createParagraph().createRun().addBreak(BreakType.PAGE);
    }

    /*@GetMapping("/getDocumento")
    public ResponseEntity<byte[]> downloadWordFile() throws IOException {

        //llamamos a la funcion de crear documento
        this.CreatefileDocx();

        //ruta del archivo
        String filepath = "/Users/alvarodanieleraso/Desktop/prueba_download_file.docx";

        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);

        //configuramos los encabezados de respuesta
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "registroCalificado.docx");

        //lee el contenido del archivo en un array de bytes
        byte[] fileContent = new byte[(int)file.length()];
        fileInputStream.read(fileContent);

        //cerramos el archivo
        fileInputStream.close();

        //devuelve el archivo como una respuesta http
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileContent);

    }*/

    private static void agregarEntradaIndice(XWPFDocument document, String entrada) {
        // Crear un párrafo para una entrada del índice
        XWPFParagraph indexParagraph = document.createParagraph();
        XWPFRun indexRun = indexParagraph.createRun();
        indexRun.setText(entrada);
    }

    public static void CreatefileDocx(){

        //el String se reemplaza con la inormacion de tynyeditor de cada item
        //String htmlText = "<p>Esta es otra prueba</p>";

        //crear el documento word
        XWPFDocument document = new XWPFDocument();

        //cremos la tabla de contenido
        //traemos los items y subItems de la base de datos
        createTableOfContents(document);

        //vamos a tratar la respuesta de la vista y buscamos las etiquetas p strong list table img etc
        //convertimos la respueta del front a un formato de arbol y nodos con la libreria Jsoup
        //Document doc = Jsoup.parse(htmlText);
        //Element body = doc.body();

        //for (Element element : body.children()){
        //funcion que va aprocesar cada nodo del arbol html
        //  processElement(element, document);
        //}

        //crearIndice(document);

        //creacion de marcador verificar para tabla de contenido //todo
        /*XWPFParagraph p = document.createParagraph();

        String encodedURI = URLEncoder.encode("#my_bookmark");
        XWPFHyperlinkRun hyperlinkRun = p.createHyperlinkRun(encodedURI);
        hyperlinkRun.setText("Click here to go to the bookmark!");

        XWPFParagraph indexParagraph = document.createParagraph();
        indexParagraph.addRun(hyperlinkRun);
        XWPFRun indexRun = indexParagraph.createRun();
        indexRun.setText("my_bookmark");*/


        //XWPFParagraph paragraph = document.createParagraph();

        //pasear el html y argegar al docuemtno word
        //Document doc2 = Jsoup.parse(htmlText);
        //Element bodyy = doc2.body();
        //paragraph.createRun().setText(bodyy.text());

        //intentamos guardar el documento word
        try {
            document.write(new FileOutputStream("/Users/alvarodanieleraso/Desktop/output.docx"));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
