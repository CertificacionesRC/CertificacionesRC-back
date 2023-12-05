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

import java.io.*;
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

        String idregistro = String.valueOf(IdRegistroCalificado);

        RegistroCalificado registroCal = this.iRegistroCalifRepository.findById(idregistro).get();
        ProgramaAcademico ProgramaAcade = this.iProgramaAcademicoRepo.findById(registroCal.getProgramaAcademico().getId());

        List<Configuraciones> configuraciones = this.iConfiguracionesRepo.findAll();

        String filepath = "src/main/resources/files/prueba.docx";

        try {

            this.CreatefileDocx(ProgramaAcade,configuraciones);
            File file = new File(filepath);
            FileInputStream fileInputStream = new FileInputStream(file);

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

        String htmlText_item1 = "<h1>Documento Institucional</h1>\n" +
                "<p><b>1. Denominación del Programa</b></p>\n" +
                "<p>La institución deberá especificar la denominación o nombre del programa, en correspondencia con el título que se va a otorgar, el nivel de formación, los contenidos curriculares del programa y el perfil del egresado; lo anterior de acuerdo con la normatividad vigente.</p>\n" +
                "<p>Los programas técnicos profesionales y tecnológicos deben adoptar denominaciones que correspondan con las competencias propias de su campo de conocimiento, de tal manera que su denominación sea diferenciable y permita una clara distinción de las ocupaciones, disciplinas y profesiones.</p>\n" +
                "<p>Los programas de especialización deben definir denominaciones que correspondan al área específica de estudio. En el caso de los programas de maestría y doctorado podrán adoptar una denominación disciplinar o interdisciplinar.</p>\n" +
                "<p>Parágrafo. Las denominaciones no existentes en el Sistema Nacional de Información de la Educación Superior -SNIES deberán incluir una argumentación desde el (los) campo(s) del conocimiento y desde la pertinencia con las necesidades del país y de las regiones, en concordancia con el campo de ocupación, las normas que regulan el ejercicio de la profesión y el marco nacional de cualificaciones. Se podrá tener en cuenta referentes internacionales como los dados por: nomenclatura internacional de la Organización de las Naciones Unidas para la Educación, la Ciencia y la Cultura -UNESCO, estándares internacionales los campos de ciencia y tecnología, Clasificación Internacional Uniforme de Ocupaciones -CIUO, en inglés ISCO, entre otras.</p>";

        String htmlText_tabla = "<table>\n" +
                "    <tr>\n" +
                "    <td><b> 1 </b></td>\n" +
                "    <td><b>Denominacion</b></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td><b> 2 </b></td>\n" +
                "    <td> Titulo que otorga </td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td><b> 3 </b></td>\n" +
                "    <td> Norma interna de creacion </td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td><b> 4 </b></td>\n" +
                "    <td> Codigo SNIES <i> (para renovaciones) </i></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td><b> 5 </b></td>\n" +
                "    <td><b> Vigencia </b>  <i> (para renovaciones) </i></td>\n" +
                "    <td><i> 7, 8 o 10 años </i></td>\n" +
                "    </tr>\n" +
                "    </table>";

        XWPFDocument document = new XWPFDocument();
        //crea la portada del documento
        portadaFile(document, ProgramaAcade,configuraciones );
        //crea la tabla de contenido
        createTableOfContents(document);
        generarContenidoItems(document);

        try {
            document.write(new FileOutputStream("src/main/resources/files/prueba.docx"));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generarContenidoItems(XWPFDocument document) throws IOException {

        document.createParagraph().createRun().addBreak(BreakType.PAGE);

        List Items = getItems();

        for (Object item : Items) {

            ItemDTO itemDTO = (ItemDTO) item;

            String contenidoItem = itemDTO.getContenido();

            if (contenidoItem != null && !contenidoItem.isEmpty()) {
                Document doc = Jsoup.parse(contenidoItem);
                Element arbolContenido = doc.body();

                for (Element element : arbolContenido.children()){
                    processElement(element, document);
                }
            }

            List<SubItem> subItemsList = itemDTO.getSubItems();

            for (SubItem subItem : subItemsList) {
                String contenidoSubItem = subItem.getContenido();
                if(contenidoSubItem != null && !contenidoSubItem.isEmpty()){
                    Document doc = Jsoup.parse(contenidoSubItem);
                    Element arbolContenido = doc.body();
                    for (Element element : arbolContenido.children()){
                        processElement(element, document);
                    }
                }
            }
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

        String anio = String.valueOf(fechaActual.getYear());
        Month mes = fechaActual.getMonth();
        String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        procesarTitulo(0, document, nombreMes);
        procesarTitulo(2, document, anio);

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
        } else if ("h1".equals(tagName) ){
            processh1(element, document);
        }else if("h2".equals(tagName)){
            processh2(element, document);
        }else if("table".equals(tagName)) {
            processTable(element, document);
        }

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
        run.setBold(true);
        run.setText(element.text());
        document.createParagraph().createRun().addBreak();
    }

    private static void processParagraph(Element element, XWPFDocument document) {

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.text());

    }


    private static void processImage(int saltosLinea, XWPFDocument document) throws IOException {

        String rutaLocal = "src/main/resources/files/unicauca.jpg";
        Path origen = Path.of(rutaLocal);

        if (Files.exists(origen)) {

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            insertarImagen(run, document, origen, rutaLocal);

            for(int i=saltosLinea;i>0;i--) {
                document.createParagraph().createRun().addBreak();
            }

        } else {
            throw new IOException("El archivo de origen no existe en la ruta especificada.");
        }
    }

    private static void insertarImagen(XWPFRun run, XWPFDocument document, Path imagePath, String rutaImagen) throws IOException {

        try (FileInputStream imageStream = new FileInputStream(rutaImagen)) {
            run.addPicture(imageStream, XWPFDocument.PICTURE_TYPE_JPEG, "imagen", Units.toEMU(100), Units.toEMU(100));
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

    }

    private static void createTableOfContents(XWPFDocument document){

        document.createParagraph().createRun().addBreak(BreakType.PAGE);
        procesarTitulo(1,document,"Tabla de contenido");
        List Items = getItems();

        int mainIndex = 1;
        for (Object item : Items) {
            ItemDTO itemDTO = (ItemDTO) item;
            String nombreItem = mainIndex + ". " + itemDTO.getNombre();
            XWPFParagraph paragraph_title = document.createParagraph();
            XWPFRun run_title = paragraph_title.createRun();
            run_title.setText(nombreItem);

            int subIndex = 1;
            List<SubItem> subItemsList = itemDTO.getSubItems();
            for (SubItem subItem : subItemsList) {

                String nombreSubItem = "    " + mainIndex + "." + subIndex + ". " + subItem.getNombre();
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
    private static void crearIndice(XWPFDocument document) {

        XWPFParagraph titleParagraph = document.createParagraph();
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setBold(true);
        titleRun.setText("Tabla de contenido");

        XWPFParagraph p = document.createParagraph();
        XWPFHyperlinkRun hyperlinkRun = p.createHyperlinkRun("my_bookmark");
        hyperlinkRun.setText("Click here to go to the bookmark!");

        XWPFParagraph indexParagraph = document.createParagraph();
        indexParagraph.addRun(hyperlinkRun);
        XWPFRun indexRun = indexParagraph.createRun();
        indexRun.setText("nuevo parrafo con link");
        document.createParagraph().createRun().addBreak(BreakType.PAGE);

    }

    private static void agregarEntradaIndice(XWPFDocument document, String entrada) {

        XWPFParagraph indexParagraph = document.createParagraph();
        XWPFRun indexRun = indexParagraph.createRun();
        indexRun.setText(entrada);

    }
    private static void processTable(Element element, XWPFDocument document) {

        XWPFTable table = document.createTable();
        Elements rows = element.select("tr");

        for(Element row : rows) {

            XWPFTableRow tableRow = table.createRow();
            Elements cells = row.select("td");

            for(Element cell : cells) {

                XWPFTableCell tableCell = tableRow.createCell();
                processElementTable(cell, tableCell);
                applyCellStyles(cell, tableCell);
            }
        }
    }
    private static void processElementTable(Element cellElement, XWPFTableCell tableCell) {

        if (cellElement.children().isEmpty()) {
            XWPFParagraph paragraph = tableCell.addParagraph();
            paragraph.createRun().setText(cellElement.text());
        } else {
            XWPFParagraph paragraph = tableCell.addParagraph();
            StringBuilder textBuilder = new StringBuilder();

            for (Node child : cellElement.childNodes()) {

                if (child instanceof TextNode)
                    textBuilder.append(((TextNode) child).text());
            }

            paragraph.createRun().setText(textBuilder.toString());

            for (Element child : cellElement.children()) {

                if ("b".equals(child.tagName()))
                    applyBoldStyle(child, paragraph);
                else if ("i".equals(child.tagName()))
                    applyItalicStyle(child, paragraph);
                else if ("p".equals(child.tagName()))
                    applyParagraph(child, paragraph);
            }
        }
    }
    private static void applyCellStyles(Element cell, XWPFTableCell tableCell) {

        XWPFParagraph paragraph = tableCell.getParagraphs().get(0);
        XWPFRun run = paragraph.createRun();

        if (cell.select("b").size() > 0)
            run.setBold(true);
        if (cell.select("i").size() > 0)
            run.setItalic(true);
        int runIndex = paragraph.getRuns().indexOf(run);
        paragraph.removeRun(runIndex);

    }

    private static void applyBoldStyle(Element boldElement, XWPFParagraph paragraph) {

        XWPFRun run = paragraph.createRun();
        run.setText(boldElement.text());
        run.setBold(true);

    }
    private static void applyItalicStyle(Element italicElement, XWPFParagraph paragraph) {

        XWPFRun run = paragraph.createRun();
        run.setText(italicElement.text());
        run.setItalic(true);

    }

    private static void applyParagraph(Element element, XWPFParagraph paragraph ) {

        XWPFRun run = paragraph.createRun();
        run.setText(element.text());

    }

    public static void CreatefileDocx(){

        XWPFDocument document = new XWPFDocument();
        createTableOfContents(document);

        try {

            document.write(new FileOutputStream("/Users/alvarodanieleraso/Desktop/output.docx"));
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



