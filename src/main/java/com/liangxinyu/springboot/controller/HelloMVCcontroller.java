package com.liangxinyu.springboot.controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/test")
public class HelloMVCcontroller {
    @RequestMapping ("/hello")
    public String hello(Model model){
        model.addAttribute("mav","Helo,Spring");
        return "example/text";
    }
    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET,produces = "MediaType.APPLICATION_JSON_VALUE"+";charset=utf-8")
    public String getProduct(@PathVariable("id")String id){
        return "";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "application/json")
    public Model getModel(@PathVariable String id,Model model){
        return model;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST,consumes = "application/json")
    public Model addModel(@RequestBody  Model model){
        return model;
    }
    /**
     * Description:获取路径中的id值获取文章
     */
        @GetMapping("article/{id}")
        public ModelAndView getArtice(@PathVariable("id") String id){
            ModelAndView modelAndView= new ModelAndView("/SHOW");
            modelAndView.addObject("id",id);
            return modelAndView;
        }
    /**
     *获取路径中得参数
     * http://localhost/user/?username=liangxinyu
     */
    @RequestMapping("/addUser")
    public String addUser(String username){
        return username;
    }

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest httpServletRequest){
        System.out.println("name:"+httpServletRequest.getParameter("username"));
        return "/index";
    }
    @RequestMapping(value = "adduser",method = {RequestMethod.POST})
    @ResponseBody
    public void saveUser(@RequestBody List<User> userList){

    }
    long I=System.currentTimeMillis();
    //新建日期对象
    Date date = new Date(I);
    //转换日期输出格式
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    String nyr = dateFormat.format(date);
    private static String UPLOADED_FOLDER="/UPLOAD/img";
    @PostMapping("/upload")
    @ResponseBody
    //注意，cheditor上传的是upload字段
    public Map<String,Object> singleFileUpload(@RequestParam("upload") MultipartFile file,RedirectAttributes redirectAttributes){
        Map<String,Object> map = new HashMap<String,Object>();
        if(file.getOriginalFilename().endsWith(".jpg")||file.getOriginalFilename().endsWith(".png")||file.getOriginalFilename().endsWith(",gif")){

            try {
                byte[] bytes = file.getBytes();
                String S = nyr +Math.random()+file.getOriginalFilename();
                Path path = Paths.get(UPLOADED_FOLDER+S);
                Files.write(path,bytes);
                map.put("uploaded",1);
                map.put("fileName",S);
                map.put("url","/UPLOAD/imgs/"+S);
                map.put("mesaage","上传成功");
                return map;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            map.put("uploaded",0);
            map.put("fileName",file.getOriginalFilename());
            map.put("url","/img/"+file.getOriginalFilename());
            map.put("message","图片后缀只支持png,jpg,gif,请检查!");
            return map;
        }
        return map;
    }

}
