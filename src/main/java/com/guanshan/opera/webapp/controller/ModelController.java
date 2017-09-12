package com.guanshan.opera.webapp.controller;

import com.guanshan.opera.webapp.service.ModelService;
import com.guanshan.opera.webapp.shared.entity.Model;
import com.guanshan.opera.webapp.shared.util.codec.Const;
import com.guanshan.opera.webapp.shared.util.codec.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by e on 2017/8/30.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/model")
public class ModelController {

    @Autowired
    public ModelService modelService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseMessage<Integer> insertModel(@RequestBody Map req){
            ResponseMessage<Integer> result = new ResponseMessage();
            if(req.get("name")==null){
                result.setErrorCode(Const.FAIL);
                result.setMessage("name should not be null");
                return result;
            }

//            Model model = new Model((String) req.get("name"));
//            int newId = modelService.save((String) req.get("name"));
//            result.setData(newId);
            result.setErrorCode(Const.SUCCESS);
            result.setMessage("insert success");
            return result;
    }
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ResponseMessage<List<Model>> findAll(){
        ResponseMessage<List<Model>> result = new ResponseMessage();
       result.setErrorCode(Const.SUCCESS);
       result.setMessage("success");
       return result;
    }

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResponseMessage<Model> findOne(@RequestParam("name") String name){
        ResponseMessage<Model> result = new ResponseMessage();
        result.setData(modelService.findOne(name));
        result.setErrorCode(Const.SUCCESS);
        result.setMessage("success");
        return result;
    }


}
