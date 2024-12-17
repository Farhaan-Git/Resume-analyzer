package com.resume_analyser.resume.Service;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume_analyser.resume.Dto.OpenAiRequest;
import com.resume_analyser.resume.Dto.OpenAiResponse;
import com.resume_analyser.resume.Dto.ResumeAnalysisResult;
import com.resume_analyser.resume.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class GetScoreResume {


    @Value("${openai.api.key}")
    private String API_KEY;

    @Value("${openai.api.url}")
    private String API_URL;

    public ResponseEntity<Object> getScoreResume(String resumeContent,String jobDescriptionContent){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + API_KEY);
        httpHeaders.set("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();

        String prompt = String.format(
                "Analyze the given resume and job description, and provide the following response in JSON format:\n" +
                        "\n" +
                        "{\n" +
                        "    \"closenessScoreInPercentage\": 85,\n" +
                        "    \"missingRequirements\": [\"Skill 1\", \"Skill 2\"],\n" +
                        "    \"suggestions\": [\"Add more details about project X\", \"Include certifications related to Y\"]\n" +
                        "}\n" +
                        "\n" +
                        "Resume:\n" +
                        "%s\n" +
                        "\n" +
                        "Job Description:\n" +
                        "%s", resumeContent, jobDescriptionContent);

        OpenAiRequest openAiRequest = new OpenAiRequest();
        openAiRequest.setPrompt(prompt);

        HttpEntity<OpenAiRequest> entity = new HttpEntity<>(openAiRequest,httpHeaders);
        ResponseEntity<OpenAiResponse> response = restTemplate.postForEntity(API_URL,entity, OpenAiResponse.class);

        if((!(response.getStatusCode() == HttpStatus.OK))|| response.getBody().getChoices().isEmpty()){
            return ResponseUtil.getResponse("Gen AI response Error!", HttpStatus.SERVICE_UNAVAILABLE);
        }

        ResumeAnalysisResult result = responseParser(response.getBody().getChoices().getFirst().getText());
        return ResponseUtil.getResponseWithData("Resume Analysis is Sucessfull",result,HttpStatus.OK);
    }

    private ResumeAnalysisResult responseParser(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse, ResumeAnalysisResult.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse OpenAI response", e);
        }
    }

}
