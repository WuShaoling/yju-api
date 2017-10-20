package com.guanshan.phoenix.service;

import com.guanshan.phoenix.enums.ResourceTypeEnum;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadFile(MultipartFile file, ResourceTypeEnum type);
}
