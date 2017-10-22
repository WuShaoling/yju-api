package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImp implements FileService {

    @Value("${file.baseUrl}")
    private String baseUrl;

    @Value("${file.imageDir}")
    private String imageDir;

    @Value("${file.markdownDir}")
    private String markdownDir;

    @Override
    public String uploadFile(MultipartFile file, ResourceTypeEnum type) {

        String uploadDir = "";
        switch (type) {
            case IMAGE:
                uploadDir = imageDir;
                break;
            case MARKDOWN:
                uploadDir = markdownDir;
                break;
        }

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String filename = file.getOriginalFilename();
        File serverFile = new File(uploadDir + filename);
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "null";
        }

        return baseUrl + uploadDir + filename;
    }
}
