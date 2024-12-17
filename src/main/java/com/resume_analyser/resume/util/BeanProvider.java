package com.resume_analyser.resume.util;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class BeanProvider {

    @Bean
    public PDFTextStripper pdfTextStripper() throws IOException {
        return new PDFTextStripper();
    }
}
