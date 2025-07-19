package com.ncloud.ai;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/Api")
public class CampingApiController {

    @PostMapping("/AiApi")
    public ResponseEntity<Map<String, String>> askAI(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");
        System.out.println("111111111111111111111111111");
        System.out.println("userMessage : " + userMessage);
        // Python API 호출
        String pythonUrl = "http://49.50.131.0:8000/chat";
        RestTemplate restTemplate = new RestTemplate();

        // 요청 바디 구성
        Map<String, String> pythonRequest = new HashMap<>();
        
        pythonRequest.put("message", userMessage);
        System.out.println(pythonRequest.get(pythonRequest));
        try {
            ResponseEntity<Map> pythonResponse = restTemplate.postForEntity(
                pythonUrl,
                pythonRequest,
                Map.class
            );

            // Python 응답 파싱
            String aiResponse = (String) pythonResponse.getBody().get("response");

            System.out.println("aiResponse : " + aiResponse);
            // 클라이언트로 응답 전달
            Map<String, String> result = new HashMap<>();
            result.put("response", aiResponse);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("response", "AI 응답 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}

