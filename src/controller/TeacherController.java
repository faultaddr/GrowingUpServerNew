package controller;

import com.alibaba.fastjson.JSON;
import entity.GTeacherEntity;
import entity.GTimeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.TeacherService;
import service.TimeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by panyunyi on 2017/8/1.
 * CUFE cs14
 */
@Controller
public class TeacherController {

    @RequestMapping(value = "/showTeacher")
    public void getTeacherInfo(HttpServletResponse response){
        String sql ="select * from g_teacher";
        TeacherService teacherService=new TeacherService();
        List list=teacherService.cursor(sql, GTeacherEntity.class);
        String jsonArray= JSON.toJSONString(list);


        printMessage(response,jsonArray);

    }
    @RequestMapping(value = "/addOrder",method = RequestMethod.GET)
    public void addOrder(HttpServletResponse response, String id){
        TimeService timeService=new TimeService();
        List <GTimeEntity>list=timeService.cursor("select * from g_time as g where g.id="+id+"",GTimeEntity.class);
        GTimeEntity gTimeEntity=list.get(0);
        System.out.print(gTimeEntity.getTeacherId());
        gTimeEntity.setTimeStatus("0");
        boolean result=timeService.update(gTimeEntity);


        if(result){
            PrintWriter printWriter= null;
            try {
                printWriter = response.getWriter();
                printWriter.write("true");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            try {
                PrintWriter printWriter=response.getWriter();
                printWriter.write("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @RequestMapping(value = "/showTeacherTime",method = RequestMethod.GET)
    public void getTime(HttpServletResponse response, @RequestParam(value = "teacherId",required =true)String teacherId){
        TimeService timeService=new TimeService();
        List <GTimeEntity>timeList=timeService.cursor("select * from g_time as g where g.teacher_id = '"+teacherId+"'", GTimeEntity.class);
        String jsonArray= JSON.toJSONString(timeList);
        printMessage(response,jsonArray);


    }

    public void printMessage(HttpServletResponse response,String jsonArray){
        try {
            response.setCharacterEncoding("utf-8");
            PrintWriter printWriter=response.getWriter();
            printWriter.write(jsonArray);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
