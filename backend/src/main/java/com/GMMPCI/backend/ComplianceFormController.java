package com.GMMPCI.backend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceFormController {

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateComplianceForm(@RequestBody ComplianceFormData formData) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        document.add(new Paragraph("GARDEN OF MEMORIES MEMORIAL PARK & CHAPELS, INC."));
        document.add(new Paragraph("COMPLIANCE REQUIREMENTS"));
        document.add(new Paragraph("Job Order No.: " + formData.getJobOrderNo()));
        
        // Accessing nameOfDeceased fields safely
        ComplianceFormData.NameOfDeceased deceased = formData.getNameOfDeceased();
        if (deceased != null) {
            document.add(new Paragraph("Name of Deceased: " + deceased.getFirstName() + " " +
                    deceased.getMiddleName() + " " + deceased.getSurname()));
        } else {
            document.add(new Paragraph("Name of Deceased: Not provided"));
        }

        document.add(new Paragraph("Date of Interment: " + formData.getDateOfInterment()));
        document.add(new Paragraph("Day/Time: " + formData.getDayTime()));
        document.add(new Paragraph("Location: " + formData.getLocation()));
        document.add(new Paragraph("Remarks: " + formData.getRemarks()));
        // Add remaining fields as needed

        document.close();

        byte[] pdfBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "compliance_form.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
