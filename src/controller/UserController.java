package controller;

import dao.GUsersDao;
import entity.GUserEntity;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
 */
@Controller
public class UserController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String loginUserGet(HttpServletRequest request, @RequestParam("userId") String userId, @RequestParam("password") String password, ModelMap modelMap) {

        GUserEntity guserEntity = new GUserEntity();
        GUsersDao gUsersDao = new GUsersDao();
        guserEntity.setUserId(userId);
        guserEntity.setUserPassword(password);
        GUserEntity result = gUsersDao.login(guserEntity);
        //TODO 判断是否为管理员进入管理后台或者显示正常页面
        if (result != null) {
            modelMap.addAttribute("user", result);
            return "true";
        } else
            return "false";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginUserPost(HttpServletRequest request, @RequestBody GUserEntity userEntity, ModelMap modelMap) {

        if (userEntity.getUserId() != null && userEntity.getUserPassword() != null)
            loginUserGet(request, userEntity.getUserId(), userEntity.getUserPassword(), modelMap);

    }

    @RequestMapping("/register")
    @ResponseBody
    public String RegisterUser(@RequestParam("userId") String userId, @RequestParam("password") String password, ModelMap modelMap) {
        GUserEntity gUsersEntity = new GUserEntity();
        GUsersDao gUsersDao = new GUsersDao();
        gUsersEntity.setUserId(userId);
        gUsersEntity.setUserPassword(password);

        boolean result = gUsersDao.add(gUsersEntity);
        if (result) {
            return "success";
        } else
            return "error";

    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteUser(@RequestParam("userId") String userId, @RequestParam("password") String password, ModelMap modelMap) {
        GUserEntity gUsersEntity = new GUserEntity();
        GUsersDao gUsersDao = new GUsersDao();
        gUsersEntity.setUserId(userId);
        gUsersEntity.setUserPassword(password);
        boolean result = gUsersDao.delete(gUsersEntity);
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

