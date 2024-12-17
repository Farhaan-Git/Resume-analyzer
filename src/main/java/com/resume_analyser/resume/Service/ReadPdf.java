package com.resume_analyser.resume.Service;

import com.resume_analyser.resume.Dto.JobDescription;
import com.resume_analyser.resume.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service

public class ReadPdf {

    private final PDFTextStripper pdfTextStripper;
    private final GetScoreResume getScoreResume;

    public ResponseEntity<Object> returnPdfText (MultipartFile file, JobDescription jobDescription){
        String pdfData = "null";
        if(!"application/pdf".equals(file.getContentType())){
            ResponseUtil.getResponse("Wrong file exentension!", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
        try{
            PDDocument document = Loader.loadPDF(file.getBytes());
            pdfData = pdfTextStripper.getText(document);
            document.close();
            return getScoreResume.getScoreResume(pdfData, jobDescription.getContent());
        }
        catch (Exception e){
            ResponseUtil.getResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return getScoreResume.getScoreResume(pdfData, jobDescription.getContent());
    }
}
