package com.resume_analyser.resume.Controller;

import com.resume_analyser.resume.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/api-health")
public class ApiHealth {


    @GetMapping("")
    public ResponseEntity<Object> getApiHealth (){
        return ResponseUtil.getResponse("api-healthy", HttpStatus.OK);
    }
}
