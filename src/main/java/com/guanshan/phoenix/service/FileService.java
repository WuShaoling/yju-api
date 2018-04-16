package com.guanshan.phoenix.service;


import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.response.ResUploadImage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {

    ResUploadImage uploadImage(MultipartFile file) throws ApplicationErrorException, IOException;

    String uploadMarkdown(MultipartFile file) throws ApplicationErrorException, IOException;

    String uploadReport(MultipartFile file) throws ApplicationErrorException, IOException;

    void downloadImage(String fileName, HttpServletResponse response) throws ApplicationErrorException, IOException;

    void downloadMarkdown(String fileName, HttpServletResponse response) throws ApplicationErrorException;

    void downloadReport(String path, String fileName, HttpServletResponse response) throws ApplicationErrorException;
}
