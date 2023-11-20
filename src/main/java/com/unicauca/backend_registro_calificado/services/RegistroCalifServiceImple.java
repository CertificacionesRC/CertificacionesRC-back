package com.unicauca.backend_registro_calificado.services;

import com.unicauca.backend_registro_calificado.domain.ObservacionDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.domain.Response;
import com.unicauca.backend_registro_calificado.domain.UsuarioDTO;
import com.unicauca.backend_registro_calificado.model.Observacion;
import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import com.unicauca.backend_registro_calificado.repository.IObservacionRepository;
import com.unicauca.backend_registro_calificado.repository.IProgramaAcademicoRepo;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class RegistroCalifServiceImple implements IRegistroCalificadoService{

    private static final Logger logger = LoggerFactory.getLogger(RegistroCalifServiceImple.class);
    private final ModelMapper modelMapper;
    private final IRegistroCalifRepository iRegistroCalifRepository;
    private final IObservacionRepository iObservacionRepository;

    private final IProgramaAcademicoRepo iProgramaAcademicoRepo;

    public RegistroCalifServiceImple(ModelMapper modelMapper, IRegistroCalifRepository registroCalifRepository, IObservacionRepository observacionRepository, IProgramaAcademicoRepo iProgramaAcademicoRepo) {
        this.modelMapper = modelMapper;
        this.iRegistroCalifRepository = registroCalifRepository;
        this.iObservacionRepository = observacionRepository;
        this.iProgramaAcademicoRepo = iProgramaAcademicoRepo;
    }



    @Override
    public Response<RegistroCalificadoDTO> createRegistroCalificado(RegistroCalificadoDTO registroCalificadoDTO) {

        logger.debug("Init createRegistroCalificado: {}", registroCalificadoDTO.toString());

        Response<RegistroCalificadoDTO> response = new Response<>();

        RegistroCalificado registroCalificado = modelMapper.map(registroCalificadoDTO, RegistroCalificado.class);

        RegistroCalificadoDTO registroCalificadoDTO1 = modelMapper.map(iRegistroCalifRepository.save(registroCalificado), RegistroCalificadoDTO.class);

        System.out.println("Miremos esto: " + registroCalificadoDTO1.toString());

        response.setStatus(200);
        response.setUserMessage("Registro Calificado creado exitosamente");
        response.setDeveloperMessage("Registro Calificado creado exitosamente");
        response.setMoreInfo("localhost:8080/api/registroCalificado");
        response.setErrorCode("");
        response.setData(registroCalificadoDTO1);
        logger.debug("Finish createRegistroCalificado Business");

        return response;
    }
    /**
     * Este método permite actualizar el estado del documento cuando se aprueba o se rechaza
     * @param objObservacion es la observación realizada por el administrador
     * @param estado indica si es rechazado o aprobado
     * @return un mensaje indicando si la operación se realizó con éxito
     */
    @Override
    public ResponseEntity<?> updateStateRegistroCalificado(ObservacionDTO objObservacion, EstadoRegistroCal estado) {
        if(objObservacion!=null){
            Observacion objObservacionEnt = modelMapper.map(objObservacion, Observacion.class);
            RegistroCalificado objR = objObservacionEnt.getRegistroCalificado();
            objR.setEstado(estado);
            iRegistroCalifRepository.save(objR);
            iObservacionRepository.save(objObservacionEnt);

            return new ResponseEntity("Operación realizada con exito", HttpStatus.OK);
        }
        return new ResponseEntity("LA OPERACION NO SE HA PODIDO REALIZAR", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Response<List<RegistroCalificadoDTO>> findAllByEstado(EstadoRegistroCal estado) {
        System.out.println("estado "+estado);
        List<RegistroCalificado> lstRegistroCal = iRegistroCalifRepository.findByEstado(estado);
        Response<List<RegistroCalificadoDTO>> response = new Response<>();
        if(lstRegistroCal.isEmpty()){
            response.setStatus(404);
            response.setUserMessage("No se encontraron los registros calificados");
            response.setDeveloperMessage("No se encontraron los registros calificados");
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByEstado");
            response.setData(null);
        }else{
            List<RegistroCalificadoDTO> registrosCalificadosDTO= lstRegistroCal.stream().map(registroCalificado ->  modelMapper.map(registroCalificado, RegistroCalificadoDTO.class)).collect(Collectors.toList());
            response.setStatus(200);
            response.setUserMessage("Registros calificados encontrados con éxito");
            response.setDeveloperMessage("Registros calificados encontrados con éxito");
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByEstado");
            response.setData(registrosCalificadosDTO);
        }
        return response;
    }

    @Override
    public Response<List<RegistroCalificadoDTO>> findAllByDate(String fechaInicio, String fechaFin) throws ParseException {
        System.out.println("fecha inicio: "+fechaInicio);
        System.out.println("fecha fin: "+fechaFin);

        List<RegistroCalificado> lstRegistroCal = iRegistroCalifRepository.findAll();
        Response<List<RegistroCalificadoDTO>> response = new Response<>();
        if(lstRegistroCal.isEmpty()){
            response.setStatus(404);
            response.setUserMessage("No se encontraron los registros calificados");
            response.setDeveloperMessage("No se encontraron los registros calificados");
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByDate");
            response.setData(null);
        }else{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date inicio = dateFormat.parse(fechaInicio);
            Date fin = dateFormat.parse(fechaFin);
            System.out.println("Inicio "+inicio.getTime());
            System.out.println("Fin "+fin.getTime());
            System.out.println("pruebaa 0"+lstRegistroCal.get(0).getFecha_creacion().getTime());
            System.out.println("pruebaa 1"+lstRegistroCal.get(1).getFecha_creacion().getTime());
            System.out.println("pruebaa 2"+lstRegistroCal.get(2).getFecha_creacion().getTime());

            lstRegistroCal=lstRegistroCal.stream()
                    .filter(registroCalificado -> registroCalificado.getFecha_creacion().equals(new Date(inicio.getTime())) ||
                            registroCalificado.getFecha_creacion().after(new Date(inicio.getTime())) &&
                                    registroCalificado.getFecha_creacion().before(new Date(fin.getTime())) ||
                            registroCalificado.getFecha_creacion().equals(new Date(fin.getTime())))
                    .collect(Collectors.toList());


            List<RegistroCalificadoDTO> lstRegistroDTO = lstRegistroCal.stream().map(registroCalificado -> modelMapper.map(registroCalificado, RegistroCalificadoDTO.class)).collect(Collectors.toList());
            response.setStatus(200);
            response.setUserMessage("Registros calificados encontrados con éxito");
            response.setDeveloperMessage("Registros calificados encontrados con éxito");
            response.setMoreInfo("http://localhost:8081/api/registrocalificado/findAllByDate");
            response.setData(lstRegistroDTO);
        }
        return response;
    }

    /**
     * Este método permite crear un documento de word con la información de los registros calificados
     * @throws IOException
     */

    @Override
    public ResponseEntity<byte[]> downloadWordFile() throws IOException {

        //llamamos a la funcion de crear documento
        this.CreatefileDocx();

        //ruta del archivo
        String filepath = "C:\\Users\\Windows 10\\Documents\\202302\\proyecto2\\prueba.docx";

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
    }


    public static void CreatefileDocx() throws IOException {

        //el String se reemplaza con la inormacion de tynyeditor de cada item

        String htmlText = "<h1>Facultad de...</h1>\n" +
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
                "    <h2>Secretaria General</h2>";
        //crear el documento word
        XWPFDocument document = new XWPFDocument();

        //vamos a tratar la respuesta de la vista y buscamos las etiquetas p strong list table img etc
        //convertimos la respueta del front a un formato de arbol y nodos con la libreria Jsoup
        Document doc = Jsoup.parse(htmlText);
        Element body = doc.body();

        //portadaFile(document);

        for (Element element : body.children()){
            //funcion que va aprocesar cada nodo del arbol html
            processElement(element, document);
        }

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
        Document doc2 = Jsoup.parse(htmlText);
        Element bodyy = doc2.body();
        paragraph.createRun().setText(bodyy.text());

        //intentamos guardar el documento word
        try {
            document.write(new FileOutputStream("C:\\Users\\Windows 10\\Documents\\202302\\proyecto2\\prueba.docx"));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    private static void portadaFile(XWPFDocument document){

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        // Establecemos el tamaño de fuente para que coincida con un encabezado h1
        run.setFontSize(24);

        // Agregamos el texto de la etiqueta al párrafo
        run.setText("Facultad de " );

        // Agregamos un salto de línea después del párrafo
        document.createParagraph().createRun().addBreak();

        run.setText("Condiciones de Calidad");

        document.createParagraph().createRun().addBreak();

        //long id = 1;
    }
*/

    private static void processElement(Element element, XWPFDocument document) throws IOException {

        String tagName = element.tagName();

        if ("p".equals(tagName)) {
            processParagraph(element, document);  //metodo que crea parrafos
        } else if ("ul".equals(tagName) || "ol".equals(tagName)) {
            //processList(element, document, paragraph); //metodo que procesa listas
        } else if ("img".equals(tagName)) {
            processImage(element, document);
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


    private static void processImage(Element element, XWPFDocument document) throws IOException {
        // Extract the image source URL from the element
        String imageUrl = element.attr("src");

        String rutaLocal = "C:\\Users\\Windows 10\\Documents\\202302\\proyecto2\\unicauca.jpg";

        Path origen = Path.of(rutaLocal);

        // Verificar si el archivo de origen existe
        if (Files.exists(origen)) {
            // Copiar el archivo al destino especificado
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(Files.probeContentType(origen));
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            insertarImagen(run, document, origen, rutaLocal);

            //Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        } else {
            throw new IOException("El archivo de origen no existe en la ruta especificada.");
        }
    }

    private static void insertarImagen(XWPFRun run, XWPFDocument document, Path imagePath, String rutaImagen) throws IOException {
        // Obtener el contenido de la imagen como bytes
        try (FileInputStream imageStream = new FileInputStream(rutaImagen)) {
            run.addPicture(imageStream, XWPFDocument.PICTURE_TYPE_JPEG, "imagen", Units.toEMU(200), Units.toEMU(200));
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



    private static void agregarEntradaIndice(XWPFDocument document, String entrada) {
        // Crear un párrafo para una entrada del índice
        XWPFParagraph indexParagraph = document.createParagraph();
        XWPFRun indexRun = indexParagraph.createRun();
        indexRun.setText(entrada);
    }




}


