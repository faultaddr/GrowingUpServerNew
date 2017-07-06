package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import entity.GArticleEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by panyu on 2017/5/26.
 */
@Controller
public class ContentController {


    @RequestMapping("/person")
    public JSONArray showAllPerson(){
        return null;

    }
    @RequestMapping(value = "/addArticle",method= RequestMethod.POST)
    public String addArticle(HttpServletResponse response, @RequestBody GArticleEntity gArticleEntity, HttpServletRequest request, ModelMap modelMap){
        ArticleService articleService=new ArticleService();
        boolean result=articleService.add(gArticleEntity);
        if(result)
            return "error";
        else
            return "success";
    }
    @RequestMapping(value="/deleteArticle",method = RequestMethod.POST)
    public String deleteArticle(HttpServletResponse response,@RequestBody GArticleEntity gArticleEntity,HttpServletRequest request,ModelMap modelMap){
        ArticleService articleService=new ArticleService();
        boolean result=articleService.delete(gArticleEntity);
        if(result)
            return "error";
        else
            return "success";
    }
    @RequestMapping(value="/updateArticle",method = RequestMethod.POST)
    public String updateArticle(HttpServletResponse response,@RequestBody GArticleEntity gArticleEntity,HttpServletRequest request,ModelMap modelMap){
        ArticleService articleService=new ArticleService();
        boolean result=articleService.update(gArticleEntity);
        if(result)
            return "error";
        else
            return "success";
    }
    @RequestMapping(value="/showArticle",method = RequestMethod.POST)
    public void showArticle(HttpServletResponse response,HttpServletRequest request,ModelMap modelMap){
        ArticleService articleService=new ArticleService();
        List list=articleService.cursor();
        String jsonArray= JSON.toJSONString(list);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.write(jsonArray);
    }
}
