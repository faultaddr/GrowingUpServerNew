package controller;

import dao.GUsersDao;
import entity.GUserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by panyu on 2017/5/24.
 */
@Controller

public class UserController {
    @RequestMapping("/login")
    @ResponseBody
    public String loginUser(@RequestParam("userId")String userId,@RequestParam("password")String password, ModelMap modelMap){
        GUserEntity guserEntity=new GUserEntity();
        GUsersDao gUsersDao=new GUsersDao();
        guserEntity.setUserId(userId);
        guserEntity.setUserPassword(password);
        Boolean result=gUsersDao.login(guserEntity);
        //TODO 判断是否为管理员进入管理后台或者显示正常页面
        if(result){
            return "success";
        }else
            return "error";
    }

    @RequestMapping("/register")
    public String RegisterUser(ModelMap modelMap){

        return "register" ;

    }
    @RequestMapping("/delete")
    public String deleteUser(ModelMap modelMap){

    return "delete";
    }
    @RequestMapping("/updateUser")
    public String updateUser(ModelMap modelMap){
return "update";
    }
    }

