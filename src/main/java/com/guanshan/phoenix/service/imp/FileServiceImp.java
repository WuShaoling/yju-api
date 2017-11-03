package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.Utility;
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
import java.util.Date;

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
    public ResUploadImage uploadImage(MultipartFile file) throws ApplicationErrorException, IOException {
        String uploadFilePath = file.getOriginalFilename();

        if (!uploadFilePath.endsWith(".jpg") &&
                !uploadFilePath.endsWith(".jpeg") &&
                !uploadFilePath.endsWith(".bmp") &&
                !uploadFilePath.endsWith(".png")) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotImage);
        }

        String targetDir = baseDir + imageDir;

        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String newFileName = saveFile(file, targetDir);

        ResUploadImage resUploadImage = new ResUploadImage();
        resUploadImage.setUrl(baseUrl + "/image/" + newFileName);

        File image = new File(targetDir + "/" + newFileName);

        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(image));
            resUploadImage.setName(file.getOriginalFilename());
            resUploadImage.setWidth(bufferedImage.getWidth());
            resUploadImage.setHeight(bufferedImage.getHeight());
        }catch (FileNotFoundException e){
            throw new ApplicationErrorException(ErrorCode.FileIsNotExist);
        }

        return resUploadImage;
    }

    @Override
    public String uploadMarkdown(MultipartFile file) throws ApplicationErrorException, IOException {
        String uploadFilePath = file.getOriginalFilename();

        if (!uploadFilePath.endsWith(".md")) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotMarkdown);
        }

        String targetDir = baseDir + markdownDir;

        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String newFileName = saveFile(file, targetDir);


        return baseUrl + "/markdown/" + newFileName;
    }

    @Override
    public String uploadReport(MultipartFile file) throws ApplicationErrorException, IOException {
        String uploadFilePath = file.getOriginalFilename();

        if (!uploadFilePath.endsWith(".pdf") &&
                !uploadFilePath.endsWith(".doc") &&
                !uploadFilePath.endsWith(".docx")) {
            throw new ApplicationErrorException(ErrorCode.InvalidReportType);
        }

        String currentUserName = Utility.getCurrentUserName();

        String targetDir = baseDir + reportDir + File.separator + currentUserName;

        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String newFileName = saveFile(file, targetDir);


        return String.format("%s%s/%s/%s", baseUrl, reportDir, currentUserName, newFileName);
    }

    @Override
    public void downloadImage(String fileName, HttpServletResponse response) throws ApplicationErrorException, IOException {

        getFile(fileName, response, baseDir + imageDir);
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
    public void downloadReport(String path, String fileName, HttpServletResponse response) throws ApplicationErrorException {

        try {
            getFile(fileName, response, baseDir + reportDir + File.separator + path);
        } catch (Exception e) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotExist);
        }
    }


    private String saveFile(MultipartFile file, String dir) throws IOException {

        String uploadFilePath = file.getOriginalFilename();
        String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.lastIndexOf('.'));
        String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.lastIndexOf('.') + 1);

        uploadFileName = String.format("%s_%s", uploadFileName, Utility.formatDate(new Date(), Utility.longDateFormat));

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
                i = fis.read(temp);
            }
            fos.flush();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }

        return uploadFileName + '.' + uploadFileSuffix;
    }

    private void getFile(String fileName, HttpServletResponse response, String dir) throws ApplicationErrorException, IOException {

        final String filePath = dir + "/" + fileName;

        File file = new File(filePath);

        if (!file.exists()) {
            throw new ApplicationErrorException(ErrorCode.FileIsNotExist);
        }

        // fix chinese filename problem
        String filename = new String(fileName.getBytes("gbk"), "iso-8859-1");
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");

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
        } finally {
            if (bis != null) {
                bis.close();
            }
        }
    }
}