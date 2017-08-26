package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.GUsersDao;
import entity.GUserEntity;
import entity.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import util.RSAUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

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
    public void loginUserGet(HttpServletRequest request, @RequestParam("userId") String userId, @RequestParam("password") String password, ModelMap modelMap, HttpServletResponse response) {

        GUserEntity guserEntity = new GUserEntity();
        GUsersDao gUsersDao = new GUsersDao();
        guserEntity.setUserId(userId);
        guserEntity.setUserPassword(password);
        GUserEntity result = gUsersDao.login(guserEntity);
        HttpSession session=request.getSession();


        //TODO 判断是否为管理员进入管理后台或者显示正常页面
        if (result != null) {
            session.setAttribute("user",result);
            String jsonString = JSON.toJSONString(result);
            System.out.print(jsonString);
            PrintWriter pw =null;
            try {
                pw =response .getWriter();
                pw.write(jsonString);
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                pw.close();
            }

        } else
            try {
                PrintWriter pw=response.getWriter();
                pw.write("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginUserPost(HttpServletRequest request, @RequestBody User userEntity, ModelMap modelMap,HttpServletResponse response) {
        System.out.println(userEntity.getUserId());
        System.out.println(userEntity.getUserPassword());
        if (userEntity.getUserId() != null && userEntity.getUserPassword() != null)
            loginUserGet(request, userEntity.getUserId(), userEntity.getUserPassword(), modelMap,response);
        else{
            try {
                PrintWriter pw=response.getWriter();
                pw.write("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public void RegisterUser(@RequestBody GUserEntity gUserEntity ,ModelMap modelMap,HttpServletResponse response) {

        GUsersDao gUsersDao = new GUsersDao();
        boolean result = gUsersDao.add(gUserEntity);
        if (result) {
            TeacherController.printMessage(response,"true");
        } else
            TeacherController.printMessage(response,"false");

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

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public void updateUser(ModelMap modelMap,HttpServletResponse response,HttpServletRequest request ,@RequestBody GUserEntity gUserEntity ) {
        GUsersDao gUsersDao = new GUsersDao();
        System.out.print(gUserEntity.getUserId());
        boolean result = gUsersDao.update(gUserEntity);
        System.out.print(result);
        TeacherController.printMessage(response,"true");

    }


    @RequestMapping("/showAllUser")
    public void showUser(HttpServletResponse response,HttpServletRequest request){
        GUsersDao gUsersDao=new GUsersDao();
        List <GUserEntity> list=gUsersDao.list();
        String jsonArray = JSON.toJSONString(list);
        TeacherController.printMessage(response,jsonArray);
    }
}

