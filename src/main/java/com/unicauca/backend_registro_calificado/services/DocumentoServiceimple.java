package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.model.Configuraciones;
import com.unicauca.backend_registro_calificado.model.ProgramaAcademico;
import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.model.SubItem;
import com.unicauca.backend_registro_calificado.repository.IConfiguracionesRepo;
import com.unicauca.backend_registro_calificado.repository.IObservacionRepository;
import com.unicauca.backend_registro_calificado.repository.IProgramaAcademicoRepo;
import com.unicauca.backend_registro_calificado.repository.IRegistroCalifRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
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
    private final IProgramaAcademicoRepo iProgramaAcademicoRepo;

    private final IConfiguracionesRepo iConfiguracionesRepo;

    public DocumentoServiceimple(ModelMapper modelMapper, IRegistroCalifRepository iRegistroCalifRepository, IObservacionRepository iObservacionRepository,
                                 IProgramaAcademicoRepo iProgramaAcademicoRepo, IConfiguracionesRepo iConfiguracionesRepo) {
        this.modelMapper = modelMapper;
        this.iRegistroCalifRepository = iRegistroCalifRepository;
        this.iObservacionRepository = iObservacionRepository;
        this.iProgramaAcademicoRepo = iProgramaAcademicoRepo;
        this.iConfiguracionesRepo = iConfiguracionesRepo;
    }

    @Override
    public ResponseEntity<byte[]> downloadWordFile(Integer IdRegistroCalificado) throws IOException {
        //id del registro calificado
        String idregistro = String.valueOf(IdRegistroCalificado);

        RegistroCalificado registroCal = this.iRegistroCalifRepository.findById(idregistro).get();

        ProgramaAcademico ProgramaAcade = this.iProgramaAcademicoRepo.findById(registroCal.getProgramaAcademico().getId());

        List<Configuraciones> configuraciones = this.iConfiguracionesRepo.findAll();


        //llamamos a la funcion de crear documento
        this.CreatefileDocx(ProgramaAcade,configuraciones);

        //ruta del archivo
        String filepath = "C:\\Users\\stive\\OneDrive\\Documentos\\Documentos prueba proyecto 2\\PruebaRegistro.docx";

        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);

        //configuramos los encabezados de respuesta
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "PruebaRegistro.docx");

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
    }

    public static void CreatefileDocx(ProgramaAcademico ProgramaAcade, List<Configuraciones> configuraciones ) throws IOException {

        //el String se reemplaza con la inormacion de tynyeditor de cada item

        String htmlText = "<h1>Tabla X informacion del programa</h1>\n" +
                "    <table>\n" +
                "    <tr>\n" +
                "    <td> 1 </td>\n" +
                "    <td><strong> Denominacion </strong></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td> 2 </td>\n" +
                "    <td><a href=\"https://www.ejemplo.com\">Dato 7</a></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td> 3 </td>\n" +
                "    <td><em> Vigencia (para renovaciones) </em></td>\n" +
                "    <td> 7 8 o 10 años </td>\n" +
                "    </tr>\n" +
                "    </table>";
        //crear el documento word
        XWPFDocument document = new XWPFDocument();
        portadaFile(document, ProgramaAcade,configuraciones );
        //vamos a tratar la respuesta de la vista y buscamos las etiquetas p strong list table img etc
        //convertimos la respueta del front a un formato de arbol y nodos con la libreria Jsoup
        Document doc = Jsoup.parse(htmlText);
        Element body = doc.body();

        Element tableElement = body.select("table").first();

        for (Element element : body.children()){
            //funcion que va aprocesar cada nodo del arbol html
            processElement(element, document);
        }

        //crearIndice(document);

        /*XWPFParagraph p = document.createParagraph();

        String encodedURI = URLEncoder.encode("#my_bookmark");
        XWPFHyperlinkRun hyperlinkRun = p.createHyperlinkRun(encodedURI);
        //hyperlinkRun.setText("Click here to go to the bookmark!");

        XWPFParagraph indexParagraph = document.createParagraph();
        indexParagraph.addRun(hyperlinkRun);
        XWPFRun indexRun = indexParagraph.createRun();
        //indexRun.setText("my_bookmark");
        XWPFParagraph paragraph = document.createParagraph();

        //pasear el html y argegar al docuemtno word
        Document doc2 = Jsoup.parse(htmlText);
        Element bodyy = doc2.body();
        paragraph.createRun().setText(bodyy.text());*/

        //intentamos guardar el documento word
        try {
            document.write(new FileOutputStream("C:\\Users\\stive\\OneDrive\\Documentos\\Documentos prueba proyecto 2\\PruebaRegistro.docx"));
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

        procesarTitulo(4, document, Tprograma );

        procesarTitulo(0, document, "Popayan");

        LocalDate fechaActual = LocalDate.now();

        // Obtener el año, mes y día por separado
        String anio = String.valueOf(fechaActual.getYear());
        Month mes = fechaActual.getMonth();
        String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        procesarTitulo(0, document, nombreMes);

        procesarTitulo(0, document, anio);

        System.out.println("configuraciones: "+configuraciones.get(0).getNombreVariable());


        for (int i = 0; i < configuraciones.size(); i++) {
            if(configuraciones.get(i).getNombreVariable().equals("rector")){
                procesarTitulo(0, document,configuraciones.get(i).getContenido());
                procesarTitulo(2, document,configuraciones.get(i).getNombreVariable());
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
        }else if("table".equals(tagName)) {
            processTable(element, document);
        }
        //para procesar sub elementos de ese elemento
        for (Element child : element.children()) {
            processElement(child, document);
        }
    }

    public static void processElementTable(Element element, XWPFTableCell tableCell) {
        // Obtener el texto del elemento
        String text = element.text();

        // Crear un nuevo párrafo en la celda
        XWPFParagraph paragraph = tableCell.addParagraph();

        // Procesar las etiquetas adicionales dentro del elemento
        for (Node child : element.childNodes()) {
            if (child instanceof TextNode) {
                // Agregar el texto sin formato al párrafo
                paragraph.createRun().setText(((TextNode) child).text());
            } else if (child instanceof Element) {
                // Procesar las etiquetas adicionales dentro del elemento
                processElementTable((Element) child, tableCell);
            }
        }

        // Agregar el texto al párrafo si no se ha procesado como etiqueta adicional
        if (paragraph.getRuns().isEmpty()) {
            //paragraph.createRun().setText(text);
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

        String rutaLocal = "C:\\Users\\stive\\OneDrive\\Imágenes\\Logo Whalen Gold Fit 1.jpg";

        Path origen = Path.of(rutaLocal);

        // Verificar si el archivo de origen existe
        if (Files.exists(origen)) {
            // Copiar el archivo al destino especificado
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(Files.probeContentType(origen));
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

    public static void processTable(Element element, XWPFDocument document) {
        // Create a new table in the Word document
        XWPFTable table = document.createTable();

        // Get the table rows from the HTML element
        Elements rows = element.select("tr");
        System.out.println("Rows: " + rows);

        for(Element row : rows) {
            // Crear fila en Word
            XWPFTableRow tableRow = table.createRow();
            // Obtener celdas de esta fila
            Elements cells = row.select("td");
            System.out.println("Cells: " + cells);
            for(Element cell : cells) {

                // Crear celda en Word
                XWPFTableCell tableCell = tableRow.createCell();
                // Procesar el contenido de la celda
                processElementTable(cell, tableCell);
                // Configurar celda
                //tableCell.setText(cell.text());
            }
        }

        // Iterate over the rows
        /*for (Element row : rows) {
            // Create a new table row in the Word document
            XWPFTableRow tableRow = table.createRow();
            System.out.println("Table row: " + tableRow);

            // Get the table cells from the HTML row
            Elements cells = row.select("td");

        }*/
    }

    private static void agregarEntradaIndice(XWPFDocument document, String entrada) {
        // Crear un párrafo para una entrada del índice
        XWPFParagraph indexParagraph = document.createParagraph();
        XWPFRun indexRun = indexParagraph.createRun();
        indexRun.setText(entrada);
    }
}
