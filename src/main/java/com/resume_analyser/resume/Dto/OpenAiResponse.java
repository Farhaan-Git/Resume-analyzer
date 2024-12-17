package com.resume_analyser.resume.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
public class OpenAiResponse {
    private List<Choice> choices;

    @Data
    public static class Choice {
        private String text;
        private int index;
        private Map<String, Object> logprobs;
        private String finish_reason;
    }
}
