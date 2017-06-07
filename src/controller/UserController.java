package controller;

import dao.GUsersDao;
import entity.GUserEntity;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.RSAUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by panyu on 2017/5/24.
 * 实现login()使用RSA 加密通信
 * 实现注册
 * 实现用户删除
 * 实现用户信息更新
 *
 */
@Controller

public class UserController {
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String loginUser(HttpServletRequest request, ModelMap modelMap) {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGen.initialize(1024);
        KeyPair kp= keyPairGen.generateKeyPair();
        RSAPublicKey pubk = (RSAPublicKey) kp.getPublic();//生成公钥
        RSAPrivateKey prik= (RSAPrivateKey) kp.getPrivate();//生成私钥
        String publicKeyExponent = pubk.getPublicExponent().toString(16);//16进制
        String publicKeyModulus = pubk.getModulus().toString(16);//16进制
        modelMap.addAttribute("pubexponent", publicKeyExponent);//保存公钥指数
        modelMap.addAttribute("pubmodules", publicKeyModulus);//保存公钥系数
        request.getSession().setAttribute("prik", prik);
        return "login";
    }
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String loginUser(HttpServletRequest request,@RequestParam("userId") String userId, @RequestParam("password") String password, ModelMap modelMap) {
        RSAPrivateKey prik = (RSAPrivateKey)request.getSession().getAttribute("prik");
        StringBuilder pwd = new StringBuilder();
        try {
            pwd.append(RSAUtils.decryptByPrivateKey(password, prik)).reverse();//反转获得的字符串
        } catch (Exception e) {
            e.printStackTrace();
        }


        GUserEntity guserEntity = new GUserEntity();
        GUsersDao gUsersDao = new GUsersDao();
        guserEntity.setUserId(userId);
        guserEntity.setUserPassword(pwd.toString());
        GUserEntity result = gUsersDao.login(guserEntity);
        //TODO 判断是否为管理员进入管理后台或者显示正常页面
        if (result!=null) {
            modelMap.addAttribute("user", result);
            return "success";
        } else
            return "error";
    }

    @RequestMapping("/register")
    @ResponseBody
    public String RegisterUser(@RequestParam("userId")String userId,@RequestParam("password")String password, ModelMap modelMap) {
        GUserEntity gUsersEntity=new GUserEntity();
        GUsersDao gUsersDao=new GUsersDao();
        gUsersEntity.setUserId(userId);
        gUsersEntity.setUserPassword(password);
        
        boolean result=gUsersDao.add(gUsersEntity);
        if (result) {
            return "success";
        } else
            return "error";

    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteUser(@RequestParam("userId")String userId,@RequestParam("password")String password,ModelMap modelMap) {
        GUserEntity gUsersEntity=new GUserEntity();
        GUsersDao gUsersDao=new GUsersDao();
        gUsersEntity.setUserId(userId);
        gUsersEntity.setUserPassword(password);
        boolean result=gUsersDao.delete(gUsersEntity);
        if (result) {
            return "success";
        } else
            return "error";

    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(ModelMap modelMap) {
        return "update";
    }
}

