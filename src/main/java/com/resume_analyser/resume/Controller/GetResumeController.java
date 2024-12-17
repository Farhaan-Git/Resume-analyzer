package com.resume_analyser.resume.Controller;

import com.resume_analyser.resume.Dto.JobDescription;
import com.resume_analyser.resume.Service.ReadPdf;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("api/v1/upload")
@RequiredArgsConstructor
public class GetResumeController {

    private final ReadPdf readPdf;                  //requestpart is used to handle both multipart file and raw json in form/data
    @PostMapping("")
    public ResponseEntity<Object> uploadResume(@RequestPart("file")MultipartFile resumeFile, @RequestPart("jobDescription") JobDescription jobDescription){
        return readPdf.returnPdfText(resumeFile,jobDescription);
    }

}
