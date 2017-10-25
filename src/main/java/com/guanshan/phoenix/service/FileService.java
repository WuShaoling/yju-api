package com.guanshan.phoenix.service;


import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.response.ResUploadImage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {

    ResUploadImage uploadImage(MultipartFile file) throws ApplicationErrorException;

    String uploadMarkdown(MultipartFile file) throws ApplicationErrorException;

    String uploadReport(MultipartFile file) throws ApplicationErrorException;

    void downloadImage(String fileName, HttpServletResponse response) throws ApplicationErrorException;

    void downloadMarkdown(String fileName, HttpServletResponse response) throws ApplicationErrorException;

    void downloadReport(String fileName, HttpServletResponse response) throws ApplicationErrorException;
}
