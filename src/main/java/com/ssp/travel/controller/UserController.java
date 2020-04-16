package com.ssp.travel.controller;

import com.ssp.travel.entity.Result;
import com.ssp.travel.entity.User;
import com.ssp.travel.service.UserService;
import com.ssp.travel.utils.CreateImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    
    
    
    @RequestMapping("/login")
    public Result login(@RequestBody User user,HttpServletRequest request){
        Result result = new Result();
        log.info("user"+user);
        try {
            User loginUser = userService.login(user);
            //登录成功之后保存用户的标记 redis   userId : loginUser
            request.getServletContext().setAttribute(loginUser.getId(),loginUser);
            result.setMsg("登录成功").setUserId(loginUser.getId());
        }catch (Exception e){
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }
    
    /**
     * 用户注册
     *
     * @param code
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(String code, String key, @RequestBody User user, HttpServletRequest request) {
        log.info("接收的验证码：" + code);
        log.info("接收的验证码的key：" + key);
        log.info("接收user对象：" + user);
        Result result = new Result();
        //验证验证码
        String keycode = (String) request.getServletContext().getAttribute(key);
        log.info(keycode);
        try {
            if (code.equalsIgnoreCase(keycode)) {
                //注册用户
                userService.register(user);
                result.setMsg("注册成功！");
            } else {
                throw new RuntimeException("验证码错误");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;
    }

    /**
     * 生成验证码
     *
     * @param request
     * @throws IOException
     */
    @GetMapping("/getImage")
    public Map<String,String> getImage( HttpServletRequest request) throws IOException {
        Map<String,String> result = new HashMap<>();
        //获取验证码
        CreateImageCode imageCode = new CreateImageCode(100, 30, 5, 20);
        String code = imageCode.getCode();
        //验证码存入session
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key,code);
        //生成图片
        BufferedImage buffImg = imageCode.getBuffImg();
        //进行base64的编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(buffImg,"png",bos);
        String s = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key",key);
        result.put("image",s);
        return result;
    }

}
