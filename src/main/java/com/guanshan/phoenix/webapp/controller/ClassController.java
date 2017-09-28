package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.service.ClassService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
import com.guanshan.phoenix.webapp.webdomain.WebClass;
import com.guanshan.phoenix.webapp.webdomain.WebClassDetail;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @ApiOperation(value = "获取班级信息详情")
    @GetMapping("detail")
    public ResponseMessage<List<WebClassDetail>> getClassDetail(@RequestParam("classId") Integer classId) {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                classService.getClassDetail(classId));
    }

    @ApiOperation(value = "获取所有班级信息")
    @GetMapping("all")
    public ResponseMessage<List<WebClass>> getALlClassesInfo() {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                classService.getAllClassesInfo());
    }
}
