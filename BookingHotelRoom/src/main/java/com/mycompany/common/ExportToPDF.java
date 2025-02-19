/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.common;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


/**
 *
 * @author lminh
 */
public class ExportToPDF {
    
    
    
    public static void main(String[] args) {
        String file = "test.pdf";
        try {
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(writer);
            pdfDocument.setDefaultPageSize(PageSize.A5);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("This is a test"));
            document.close();

        } catch (Exception e) {
        }
    }
    
    
}
