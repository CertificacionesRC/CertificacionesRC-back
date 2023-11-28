package com.unicauca.backend_registro_calificado.controllers;

import com.unicauca.backend_registro_calificado.domain.ItemDTO;
import com.unicauca.backend_registro_calificado.domain.SubItemDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.model.Item;
import com.unicauca.backend_registro_calificado.domain.ObservacionDTO;
import com.unicauca.backend_registro_calificado.domain.RegistroCalificadoDTO;
import com.unicauca.backend_registro_calificado.model.enums.EstadoRegistroCal;
import com.unicauca.backend_registro_calificado.services.IDocumentoService;
import com.unicauca.backend_registro_calificado.services.IRegistroCalificadoService;
import com.unicauca.backend_registro_calificado.services.IitemService;
import org.apache.poi.xwpf.usermodel.*;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.unicauca.backend_registro_calificado.domain.Response;

import java.text.ParseException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.unicauca.backend_registro_calificado.model.SubItem;


@RestController
@RequestMapping("/registrocalificado")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistroCalificadoController {

    private final IRegistroCalificadoService registroCalificadoBusiness;
    private final IDocumentoService documentoService;

    public RegistroCalificadoController(IRegistroCalificadoService registroCalificadoBusiness, IDocumentoService documentoService) {
    private static IitemService iitemService;

    @Autowired
    public RegistroCalificadoController(IitemService iitemService, IRegistroCalificadoService registroCalificadoBusiness) {
        this.iitemService = iitemService;
        this.registroCalificadoBusiness = registroCalificadoBusiness;
        this.documentoService = documentoService;
    }

    @PostMapping()
    public Response<RegistroCalificadoDTO> createRegistro(@RequestBody RegistroCalificadoDTO registroCalificadoDTO) {
        return this.registroCalificadoBusiness.createRegistroCalificado(registroCalificadoDTO);
    }
    @Secured("ADMIN")
    @PostMapping("/updateStateRegistroCalificado")
    public ResponseEntity<?> updateStateRegistroCalificado(@RequestBody ObservacionDTO observacion, @RequestParam EstadoRegistroCal estado) {
        return this.registroCalificadoBusiness.updateStateRegistroCalificado(observacion, estado);
    }
    @Secured("ADMIN")
    @GetMapping("/findAllByEstado")
    public Response<List<RegistroCalificadoDTO>> findAllByEstado(@RequestParam EstadoRegistroCal estado){
        return this.registroCalificadoBusiness.findAllByEstado(estado);
    }

    @Secured("ADMIN")
    @GetMapping("/findAllByDate")
    public Response<List<RegistroCalificadoDTO>> findAllByDate(@RequestParam String fechaInicio, @RequestParam String fechaFin) {
        try {
            return this.registroCalificadoBusiness.findAllByDate(fechaInicio, fechaFin);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Secured("COORDINADOR")
    @GetMapping("/findAll")
    public Response<List<RegistroCalificadoDTO>> findAll() {
        return this.registroCalificadoBusiness.findAll();
    }

    @Secured("COORDINADOR")
    @GetMapping("/findAllByProgramaAcademico/{programaId}")
    public Response<List<RegistroCalificadoDTO>> findAllByProgramaAcademico(@PathVariable Long programaId) {
        return this.registroCalificadoBusiness.findAllByProgramaAcademico(programaId);
    }
    @Secured("COORDINADOR")
    @GetMapping("/findRegistroCalificadoById/{autor}")
    public Response<RegistroCalificadoDTO> findRegistroCalificadoByAutor(@PathVariable String autor) {
        return this.registroCalificadoBusiness.findRegistroCalificadoByAutor(autor);
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


    /*public static List<SubItemDTO> getSubItemByIdItem(){
        List<SubItemDTO> listaItems = iitemService.findAllItem();
        return listaItems;
    }*/

    public static List<ItemDTO> getItems(){
        List<ItemDTO> listaItems = iitemService.findAllItem();
        return listaItems;
    }

    private static void processElement(Element element, XWPFDocument document) {

        String tagName = element.tagName();

        if ("p".equals(tagName)) {
            processParagraph(element, document);  //metodo que crea parrafos
        } else if ("ul".equals(tagName) || "ol".equals(tagName)) {
            //processList(element, document, paragraph); //metodo que procesa listas
        } else if ("img".equals(tagName)) {
            //processImage(element, document, paragraph); //metodo que procesa imagenes
        }

        //para procesar sub elementos de ese elemento
        for (Element child : element.children()) {
            processElement(child, document);
        }
    }

    private static void processParagraph(Element element, XWPFDocument document) {
        //creamos el parrafo y se lo agregarmos al documento
        //obtenemos el texto de la etiqueta
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.text());
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

    }
}
