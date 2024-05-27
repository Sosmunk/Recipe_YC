package com.triangular.recipe_yc.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadFile(MultipartFile file, String fileName);
}
