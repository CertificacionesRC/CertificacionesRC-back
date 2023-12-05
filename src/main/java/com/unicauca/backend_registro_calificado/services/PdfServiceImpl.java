package com.unicauca.backend_registro_calificado.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class PdfServiceImpl implements IPdfService{

    @Override
    public ResponseEntity<byte[]> downloadPDFFile(Integer IdRegistroCalificado) throws IOException {

        InputStream docxInputStream = new FileInputStream("src/main/resources/files/prueba.docx");

        try (XWPFDocument document = new XWPFDocument(docxInputStream);

             OutputStream pdfOutputStream = new FileOutputStream("src/main/resources/files/prueba.pdf");) {
            Document pdfDocument = new Document();
            PdfWriter.getInstance(pdfDocument, pdfOutputStream);
            pdfDocument.open();

            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph paragraph : paragraphs) {
                pdfDocument.add(new Paragraph(paragraph.getText()));
            }

            pdfDocument.close();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        return null;

    }
}
