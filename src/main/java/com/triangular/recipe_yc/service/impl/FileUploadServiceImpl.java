package com.triangular.recipe_yc.service.impl;

import com.google.protobuf.ServiceException;
import com.triangular.recipe_yc.service.FileUploadService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${YC_API_KEY}")
    String apiKey;

    @Value("${YC_UPLOAD_FUNCTION_URL}")
    String uploadFunctionUrl;

    public FileUploadServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    RestTemplate restTemplate;

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        try {
            String fileContentBase64 = Base64.getEncoder().encodeToString(file.getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Api-Key " + apiKey);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("file_content", fileContentBase64);
            requestBody.put("file_name", fileName);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity response = restTemplate.postForEntity(uploadFunctionUrl, requestEntity, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ServiceException("Ошибка функции: " + response.getStatusCode());
            }

            return response.getBody().toString();


        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
