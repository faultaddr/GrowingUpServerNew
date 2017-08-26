package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import entity.GArticleEntity;
import entity.GFeedbackEntity;
import entity.GPersonEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.ArticleService;
import service.FeedBackService;
import service.PersonService;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.MidiChannel;
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


    @RequestMapping("/showPerson")
    public void showAllPerson(HttpServletResponse response) {
        PersonService personService = new PersonService();
        List list = personService.cursor("select * from g_person", GPersonEntity.class);
        String jsonArray = JSONArray.toJSONString(list);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.write(jsonArray);
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(HttpServletResponse response, @RequestBody GPersonEntity gPersonEntity, HttpServletRequest request, ModelMap modelMap) {
        PersonService personService = new PersonService();
        boolean result = personService.add(gPersonEntity);
        if (result)
            return "/jsp/success.jsp";
        else
            return "jsp/error.jsp";
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public String addArticle(HttpServletResponse response, @RequestBody GArticleEntity gArticleEntity, HttpServletRequest request, ModelMap modelMap) {
        ArticleService articleService = new ArticleService();
        boolean result = articleService.add(gArticleEntity);
        if (result)
            return "/jsp/success.jsp";
        else
            return "/jsp/error.jsp";
    }

    @RequestMapping(value = "/deleteArticle")
    public String deleteArticle(HttpServletResponse response, @RequestParam String articleId, HttpServletRequest request, ModelMap modelMap) {
        ArticleService articleService = new ArticleService();
        GArticleEntity articleEntity=new GArticleEntity();
        articleEntity.setArticleId(articleId);
        boolean result = articleService.delete(articleEntity);
        if (result)
            return "/jsp/success.jsp";
        else
            return "/jsp/error.jsp";
    }

    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public String updateArticle(HttpServletResponse response, @RequestBody GArticleEntity gArticleEntity, HttpServletRequest request, ModelMap modelMap) {
        ArticleService articleService = new ArticleService();
        boolean result = articleService.update(gArticleEntity);
        if (result)
            return "/jsp/success.jsp";
        else
            return "/jsp/error.jsp";
    }

    @RequestMapping(value = "/showArticle")

    public String showArticle(@RequestParam(value = "num", required = false) String num, HttpServletResponse response, HttpServletRequest request, ModelMap modelMap) {

        if (num == null) {
            ArticleService articleService = new ArticleService();
            List list = articleService.cursor();
            String jsonArray = JSON.toJSONString(list);
            PrintWriter printWriter = null;
            try {
                response.setCharacterEncoding("utf-8");
                printWriter = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printWriter.write(jsonArray);
            return null;
        } else {
            response.setCharacterEncoding("utf-8");
            return "/html/news/" + num + ".html";
        }
    }
    @RequestMapping(value = "/showArticleAll")
    public String showAllArticle(HttpServletResponse response,HttpServletRequest request){
        response.setCharacterEncoding("utf-8");

        return "/html/detail/detail.html";
    }
    @RequestMapping(value = "/showThinkingActivity", method = RequestMethod.GET)
    public String showThinkingHtml(String num, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        return "/html/thinking/" + num + ".html";
    }

    @RequestMapping(value = "/addFeedBack", method = RequestMethod.POST)
    public void addFeedBack(@RequestBody GFeedbackEntity gFeedbackEntity, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        FeedBackService feedBackService = new FeedBackService();
        boolean result = feedBackService.add(gFeedbackEntity);


        if (result) {
            PrintWriter printWriter = null;
            try {
                printWriter = response.getWriter();
                printWriter.write("true");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                PrintWriter printWriter = response.getWriter();
                printWriter.write("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping("/showAllFeedBack")
    public void showFeedBack(HttpServletRequest request,HttpServletResponse response){
        FeedBackService feedBackService=new FeedBackService();
        List list=feedBackService.cursor("select * from g_feedback",GFeedbackEntity.class);
        String jsonArray=JSON.toJSONString(list);
        TeacherController.printMessage(response,jsonArray);
    }

}
