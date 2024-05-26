package com.triangular.recipe_yc.web.controller;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.triangular.recipe_yc.web.annotation.ApiV1;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

@ApiV1
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileUploadController {

    AWSLambda awsLambda;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("image") MultipartFile file) {
        try {
            String fileContentBase64 = Base64.getEncoder().encodeToString(file.getBytes());
            String fileName = file.getOriginalFilename();

            String payload = String.format("{\"file_content\": \"%s\", \"file_name\": \"%s\"}",
                    fileContentBase64, fileName);

            System.out.println(payload);

            InvokeRequest invokeRequest = new InvokeRequest()
                    .withFunctionName("upload-test")
                    .withPayload(payload);
            InvokeResult invokeResult = awsLambda.invoke(invokeRequest);
            return ResponseEntity.ok(invokeResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}