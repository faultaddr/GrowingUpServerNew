package controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by panyu on 2017/5/26.
 */
@Controller
public class ContentController {


    @RequestMapping("/person")
    public JSONArray showAllPerson(){
        return null;

    }
    @RequestMapping("/Article")
    public JSONArray showArticle(){
        return null;

    }

}
