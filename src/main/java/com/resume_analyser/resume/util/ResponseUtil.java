package com.resume_analyser.resume.util;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static ResponseEntity<Object> getResponseWithData(String message, Object obj, HttpStatus httpStatus){
        Map<String,Object> hashmap = new HashMap<>();
        hashmap.put("message",message);
        hashmap.put("data",obj);
        return new ResponseEntity<>(hashmap,httpStatus);
    }

    public static ResponseEntity<Object> getResponse(String message, HttpStatus httpStatus){
        Map<String,Object> hashmap = new HashMap<>();
        hashmap.put("message",message);
        return new ResponseEntity<>(hashmap,httpStatus);
    }
}
