package com.unicauca.backend_registro_calificado.controllers;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.services.IRegistroCalificadoService;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.select.Elements;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unicauca.backend_registro_calificado.domain.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;




@RestController
@RequestMapping("/registrocalificado")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistroCalificadoController {

    private final IRegistroCalificadoService registroCalificadoBusiness;

    public RegistroCalificadoController(IRegistroCalificadoService registroCalificadoBusiness) {
        this.registroCalificadoBusiness = registroCalificadoBusiness;
    }

    @PostMapping()
    public Response<RegistroCalificadoDTO> createRegistro(@RequestBody RegistroCalificadoDTO registroCalificadoDTO) {
        return this.registroCalificadoBusiness.createRegistroCalificado(registroCalificadoDTO);
    }


    public static void CreatefileDocx(){

        //el String se reemplaza con la inormacion de tynyeditor de cada item
        String htmlText = "<table>\n" +
                "        <thead>\n" +
                "            <tr>\n" +
                "                <th>Encabezado 1</th>\n" +
                "                <th>Encabezado 2</th>\n" +
                "                <th>Encabezado 3</th>\n" +
                "            </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td>Dato 1</td>\n" +
                "                <td>Dato 2</td>\n" +
                "                <td>Dato 3</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Dato 4</td>\n" +
                "                <td>Dato 5</td>\n" +
                "                <td>Dato 6</td>\n" +
                "            </tr>\n" +
                "            <!-- Agrega más filas según sea necesario -->\n" +
                "        </tbody>\n" +
                "    </table>";

        //crear el documento word
        XWPFDocument document = new XWPFDocument();

        //vamos a tratar la respuesta de la vista y buscamos las etiquetas p strong list table img etc
        //convertimos la respueta del front a un formato de arbol y nodos con la libreria Jsoup
        Document doc = Jsoup.parse(htmlText);
        Element body = doc.body();

        for (Element element : body.children()){
            //funcion que va aprocesar cada nodo del arbol html
            processElement(element, document);
        }


        //crearIndice(document);

        XWPFParagraph p = document.createParagraph();

        String encodedURI = URLEncoder.encode("#my_bookmark");
        XWPFHyperlinkRun hyperlinkRun = p.createHyperlinkRun(encodedURI);
        hyperlinkRun.setText("Click here to go to the bookmark!");

        XWPFParagraph indexParagraph = document.createParagraph();
        indexParagraph.addRun(hyperlinkRun);
        XWPFRun indexRun = indexParagraph.createRun();
        indexRun.setText("my_bookmark");


        XWPFParagraph paragraph = document.createParagraph();

        //pasear el html y argegar al docuemtno word
        Document doc2 = Jsoup.parse(htmlText);
        Element bodyy = doc2.body();
        paragraph.createRun().setText(bodyy.text());

        //intentamos guardar el documento word
        try {
            document.write(new FileOutputStream("C:\\Users\\stive\\OneDrive\\Documentos\\Documentos prueba proyecto 2\\PruebaRegistro.docx"));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*private static void processElement(Element element, XWPFDocument document) {

        String tagName = element.tagName();

        if ("p".equals(tagName)) {
            processParagraph(element, document);  //metodo que crea parrafos
        } else if ("ul".equals(tagName) || "ol".equals(tagName)) {
            //processList(element, document, paragraph); //metodo que procesa listas
        } else if ("img".equals(tagName)) {
            //processImage(element, document, paragraph); //metodo que procesa imagenes
        }else if ("table".equals(tagName)) {
            processTable(element, document);
        }

        //para procesar sub elementos de ese elemento
        for (Element child : element.children()) {
            processElement(child, document);
        }
    }*/

    private static void processElement(Element element, Object container) {
        String tagName = element.tagName();

        if ("p".equals(tagName)) {
            processParagraph(element, container);
        } else if ("ul".equals(tagName) || "ol".equals(tagName)) {
            //processList(element, container); // ajustar este método también
        } else if ("img".equals(tagName)) {
            //processImage(element, container); // ajustar este método también
        } else if ("table".equals(tagName)) {
            processTable(element, container);
        }

        // Para procesar subelementos de ese elemento
        for (Element child : element.children()) {
            processElement(child, container);
        }
    }

    private static void processTable(Element tableElement, Object container) {
        if (container instanceof XWPFDocument) {
            // Crear una nueva tabla en el documento
            XWPFTable table = ((XWPFDocument) container).createTable();

            // Procesar filas (tr)
            for (Element rowElement : tableElement.select("tr")) {
                XWPFTableRow row = table.createRow();
                // Procesar celdas (th o td)
                Elements cells = rowElement.select("th, td");
                for (int i = 0; i < cells.size(); i++) {
                    XWPFTableCell cell = row.getCell(i);
                    processElement(cells.get(i), cell);
                }
            }
        } /*else if (container instanceof XWPFTableCell) {
            // Aquí manejarías la lógica específica de la celda de la tabla, si es necesario
        }*/
    }

    /*private static void processTable(Element tableElement, XWPFDocument document) {
        // Crear una nueva tabla en el documento
        XWPFTable table = document.createTable();

        // Procesar filas (tr)
        for (Element rowElement : tableElement.select("tr")) {
            XWPFTableRow row = table.createRow();
            // Procesar celdas (th o td)
            Elements cells = rowElement.select("th, td");
            for (int i = 0; i < cells.size(); i++) {
                XWPFTableCell cell = row.getCell(i);
                processElement(cells.get(i), cell);
            }
        }
    }*/

    // Procesar elementos dentro de las celdas
    /*private static void processParagraph(Element element, XWPFDocument document) {
        //creamos el parrafo y se lo agregarmos al documento
        //obtenemos el texto de la etiqueta
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.text());
    }*/
    private static void processParagraph(Element element, Object container) {
        if (container instanceof XWPFDocument) {
            // Crear un nuevo párrafo y agregarlo al documento
            XWPFParagraph paragraph = ((XWPFDocument) container).createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(element.text());
        } else if (container instanceof XWPFTableCell) {
            // Agregar texto a la celda de la tabla
            XWPFTableCell cell = (XWPFTableCell) container;
            cell.setText(element.text());
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


    @GetMapping("/getDocumento")
    public ResponseEntity<byte[]> downloadWordFile() throws IOException {

        //llamamos a la funcion de crear documento
        this.CreatefileDocx();

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


}