package com.guanshan.phoenix.excel;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.excel.domain.ExcelStudent;
import com.guanshan.phoenix.excel.domain.ExcelTeacher;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/22.
 */

public class ExcelUtil {

    public static ExcelStudent studentExcelAnalysis(MultipartFile file) throws IOException, ApplicationErrorException {
        ExcelStudent excelStudent = new ExcelStudent();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            List<ExcelStudent.ExcelStudentElement> excelStudentElementList = new ArrayList<>();
            for (int rowNum=1; rowNum<=sheet.getLastRowNum(); rowNum++) {
                ExcelStudent.ExcelStudentElement element = new ExcelStudent().new ExcelStudentElement();
                Row row = sheet.getRow(rowNum);
                element.setStudentNum(new Integer(new Double(row.getCell(0).toString()).intValue()).toString());
                element.setStudentName(row.getCell(1).toString());
                element.setGender(new Double(row.getCell(2).toString()).intValue());
                excelStudentElementList.add(element);
            }

            excelStudent.setExcelStudentElementList(excelStudentElementList);
            return excelStudent;

        } catch ( Exception e) {
            throw new ApplicationErrorException(ErrorCode.InvalidExcelFileFormat);
        }
    }

    public static ExcelTeacher teacherExcelAnalysis(MultipartFile file) throws ApplicationErrorException, IOException {
        ExcelTeacher excelTeacher = new ExcelTeacher();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            List<ExcelTeacher.ExcelTeacherElement> excelTeacherElementList = new ArrayList<>();
            for (int rowNum=1; rowNum<=sheet.getLastRowNum(); rowNum++) {
                ExcelTeacher.ExcelTeacherElement element = new ExcelTeacher().new ExcelTeacherElement();
                Row row = sheet.getRow(rowNum);
                element.setTeacherNum(new Integer(new Double(row.getCell(0).toString()).intValue()).toString());
                element.setTeacherName(row.getCell(1).toString());
                element.setGender(new Double(row.getCell(2).toString()).intValue());
                element.setTeacherTitle(new Double(row.getCell(3).toString()).intValue());
                element.setTeacherContact(row.getCell(4).toString());
                excelTeacherElementList.add(element);
            }

            excelTeacher.setExcelTeacherElementList(excelTeacherElementList);
            return excelTeacher;

        } catch ( Exception e) {
            throw new ApplicationErrorException(ErrorCode.InvalidExcelFileFormat);
        }
    }
}
