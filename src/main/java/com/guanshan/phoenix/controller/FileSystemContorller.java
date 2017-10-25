package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.FileService;
import com.guanshan.phoenix.webdomain.response.ResUploadImage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class FileSystemContorller {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传Image")
    @PostMapping(value = "/admin/course/experiment/piclib")
    public ResponseMessage<ResUploadImage> uploadImage(@RequestParam("file") MultipartFile file) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(fileService.uploadImage(file));
    }

    @ApiOperation(value = "上传Markdown")
    @PostMapping(value = "/admin/course/experiment/markdown")
    public ResponseMessage<String> uploadMarkdown(@RequestParam("file") MultipartFile file) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(fileService.uploadMarkdown(file));
    }

    @ApiOperation(value = "下载Image")
    @GetMapping(value = "/image/{fileName:.+}")
    public ResponseMessage downloadImage(@PathVariable("fileName") String fileName, HttpServletResponse response) throws ApplicationErrorException {
        fileService.downloadImage(fileName, response);
        return new ResponseMessage.Success<>();
    }

    @ApiOperation(value = "下载Markdown")
    @GetMapping(value = "/markdown/{fileName:.+}")
    public ResponseMessage downloadMarkdown(@PathVariable("fileName") String fileName, HttpServletResponse response) throws ApplicationErrorException {
        fileService.downloadMarkdown(fileName, response);
        return new ResponseMessage.Success<>();
    }
}
