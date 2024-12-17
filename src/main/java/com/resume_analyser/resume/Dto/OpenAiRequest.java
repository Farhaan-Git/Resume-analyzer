package com.resume_analyser.resume.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenAiRequest {
    private final String model = "gpt-3.5-turbo";
    private String prompt;
    private final int max_tokens = 1000;
    private final double temperature = 0.7;
    private final double top_p = 0.7;
}
