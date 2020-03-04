package com.taianting.springboot.controller;

import com.taianting.springboot.model.Jiaoshi;
import com.taianting.springboot.model.Xuexiao;
import com.taianting.springboot.service.JiaoshiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shengjin Tong
 * @date 2020/2/18 - 3:17 下午
 */
@RestController
public class JiaoshiController {
    @Autowired
    private JiaoshiService jiaoshiService;

    //查找所有的教师
    @CrossOrigin
    @GetMapping(value = "api/manage/jiaoshi/getAll")
    @ResponseBody
    public Map<String, Object> getAllJiaoshi(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String,String>> jiaoshi = jiaoshiService.getAllJiaoshi();
        if(jiaoshi == null){
            resultMap.put("code",201);
            resultMap.put("msg","没有教师信息");
        } else {
            resultMap.put("code", 200);
            resultMap.put("msg", "查找成功");
            resultMap.put("result", jiaoshi);
        }
        System.out.println("resultMap:"+resultMap);
        return resultMap;
    }

    //增加教师
    @CrossOrigin
    @RequestMapping(value = "api/manage/jiaoshi/insertJiaoshi")
    @ResponseBody
    public Map<String, Object> insertJiaoshi(Jiaoshi jiaoshi,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map<String, Object> resultMap = new HashMap<>();
        Integer xuexiao_id = jiaoshi.getXuexiao_id();
        String jiaoshi_xingming = jiaoshi.getJiaoshi_xingming();
        String jiaoshi_shoujihaoma = jiaoshi.getJiaoshi_shoujihaoma();
        Jiaoshi jiaoshi1 = new Jiaoshi(jiaoshi_xingming,jiaoshi_shoujihaoma,xuexiao_id);
        int flag = jiaoshiService.insertJiaoshi(jiaoshi1);
        if(flag != -1){
            resultMap.put("code",200);
            resultMap.put("msg","添加成功！");
            resultMap.put("result", jiaoshi1);
        } else {
            resultMap.put("code", 201);
            resultMap.put("msg", "添加失败！");
        }
        System.out.println("resultMap:"+resultMap);
        return resultMap;
    }

    //软删除单条记录
    @CrossOrigin
    @RequestMapping(value = "api/manage/jiaoshi/deleteSingleJiaoshi")
    @ResponseBody
    public Map<String, Object> deleteSingleJiaoshi (Jiaoshi jiaoshi, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin","*");
        Map<String, Object> resultMap = new HashMap<>();
        int jiaoshi_id = jiaoshi.getJiaoshi_id();
        int flag = jiaoshiService.deleteSingleJiaoshi(jiaoshi_id);
        if(flag != -1){
            resultMap.put("code",200);
            resultMap.put("msg","删除成功!");
            resultMap.put("result", jiaoshi_id);
        } else {
            resultMap.put("code", 201);
            resultMap.put("msg", "删除失败！");
        }
        System.out.println("resultMap:"+resultMap);
        return resultMap;
    }

    //更改单条记录
    @CrossOrigin
    @RequestMapping(value = "api/manage/jiaoshi/updateSingleJiaoshi")
    @ResponseBody
    public Map<String, Object> updateSingleJiaoshi (Jiaoshi jiaoshi, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin","*");
        Map<String, Object> resultMap = new HashMap<>();
        int jiaoshi_id = jiaoshi.getJiaoshi_id();
        String jiaoshi_xingming = jiaoshi.getJiaoshi_xingming();
        String jiaoshi_shoujihaoma = jiaoshi.getJiaoshi_shoujihaoma();
        Jiaoshi jiaoshi1 = new Jiaoshi(jiaoshi_id, jiaoshi_xingming, jiaoshi_shoujihaoma);
        System.out.println(jiaoshi1);
        int flag = jiaoshiService.updateSingleJiaoshi(jiaoshi1);
        if(flag != -1){
            resultMap.put("code",200);
            resultMap.put("msg","更改成功!");
            resultMap.put("result", jiaoshi1);
        } else {
            resultMap.put("code", 201);
            resultMap.put("msg", "更改失败！");
        }
        System.out.println("resultMap:"+resultMap);
        return resultMap;
    }
}