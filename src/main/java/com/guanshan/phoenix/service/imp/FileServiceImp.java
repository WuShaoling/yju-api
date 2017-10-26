package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.FileService;
import com.guanshan.phoenix.webdomain.response.ResUploadImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class FileServiceImp implements FileService {

    @Value("${file.baseUrl}")
    private String baseUrl;

    @Value("${file.baseDir}")
    private String baseDir;

    @Value("${file.imageDir}")
    private String imageDir;

    @Value("${file.markdownDir}")
    private String markdownDir;

    @Value("${file.reportDir}")
    private String reportDir;


    @Override
    public ResUploadImage uploadImage(MultipartFile file) throws ApplicationErrorException {
        String uploadFilePath = file.getOriginalFilename();
        String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1, uploadFilePath.length());

        if (!uploadFileSuffix.equals("jpg") && !uploadFileSuffix.equals("jpeg") && !uploadFileSuffix.equals("bmp") && !uploadFileSuffix.equals("png")) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotImage);
        }

        String targetDir = baseDir + imageDir;

        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        saveFile(file, targetDir);

        ResUploadImage resUploadImage = new ResUploadImage();
        resUploadImage.setUrl(baseUrl + "/image/" + file.getOriginalFilename());

        File image = new File(targetDir + "/" + file.getOriginalFilename());
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(image));
            resUploadImage.setName(file.getOriginalFilename());
            resUploadImage.setWidth(bufferedImage.getWidth());
            resUploadImage.setHeight(bufferedImage.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resUploadImage;
    }

    @Override
    public String uploadMarkdown(MultipartFile file) throws ApplicationErrorException {
        String uploadFilePath = file.getOriginalFilename();
        String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1, uploadFilePath.length());

        if (!uploadFileSuffix.equals("md")) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotMarkdown);
        }

        String targetDir = baseDir + markdownDir;

        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        saveFile(file, targetDir);


        return baseUrl + "/markdown/" + file.getOriginalFilename();
    }

    @Override
    public String uploadReport(MultipartFile file) throws ApplicationErrorException {
        String uploadFilePath = file.getOriginalFilename();
        String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1, uploadFilePath.length());

        if (!uploadFileSuffix.equals("pdf") &&
                !uploadFileSuffix.equals("doc") &&
                !uploadFileSuffix.equals("docx")) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotMarkdown);
        }

        String targetDir = baseDir + reportDir;

        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        saveFile(file, targetDir);


        return baseUrl + "/report/" + file.getOriginalFilename();
    }

    @Override
    public void downloadImage(String fileName, HttpServletResponse response) throws ApplicationErrorException {

        try {
            getFile(fileName, response, baseDir + imageDir);
        } catch (Exception e) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotExist);
        }

    }

    @Override
    public void downloadMarkdown(String fileName, HttpServletResponse response) throws ApplicationErrorException {

        try {
            getFile(fileName, response, baseDir + markdownDir);
        } catch (Exception e) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotExist);
        }
    }

    @Override
    public void downloadReport(String fileName, HttpServletResponse response) throws ApplicationErrorException {

        try {
            getFile(fileName, response, baseDir + reportDir);
        } catch (Exception e) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotExist);
        }
    }


    private void saveFile(MultipartFile file, String dir) throws ApplicationErrorException {

        String uploadFilePath = file.getOriginalFilename();
        String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
        String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1, uploadFilePath.length());

        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fis = (FileInputStream) file.getInputStream();
            fos = new FileOutputStream(new File(dir + "/"
                    + uploadFileName
                    + ".")
                    + uploadFileSuffix);
            byte[] temp = new byte[1024];
            int i = fis.read(temp);
            while (i != -1) {
                fos.write(temp, 0, temp.length);
                fos.flush();
                i = fis.read(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getFile(String fileName, HttpServletResponse response, String dir) throws ApplicationErrorException {

        final String filePath = dir + "/" + fileName;

        File file = new File(filePath);

        if (!file.exists()) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotExist);
        }

        try {
            // fix chinese filename problem
            String filename = new String(fileName.getBytes("gbk"), "iso-8859-1");
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}