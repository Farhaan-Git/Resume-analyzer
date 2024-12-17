package com.resume_analyser.resume.Dto;
import lombok.Data;

import java.util.List;

@Data
public class ResumeAnalysisResult {
    private byte closenessScoreInPercentage;
    private List<String> missingRequirements;
    private List<String> suggestions;
}
