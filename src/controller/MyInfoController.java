package controller;

import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyInfoController {

   @Autowired
   ServletContext context; 

   @RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
   public ModelAndView fileUploadPage() {
      FileModel file = new FileModel();
      ModelAndView modelAndView = new ModelAndView("fileUpload", "command", file);
      return modelAndView;
   }

   @RequestMapping(value="/fileUploadPage", method = RequestMethod.POST)
   public String fileUpload(FileModel file, BindingResult result, ModelMap model) throws IOException {
        System.out.println("fileUploadPage");
      if (result.hasErrors()) {
         System.out.println("validation errors");
         return "fileUpload";
      } else {            
         System.out.println("Fetching file");
         MultipartFile multipartFile = file.getFile();
         String uploadPath = context.getRealPath("")  + File.separator;
         System.out.print("上传"+uploadPath);
         //Now do something with file...
         FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath+file.getFile().getOriginalFilename()));
         String fileName = multipartFile.getOriginalFilename();
         model.addAttribute("fileName", fileName);
         return "success";
      }
   }
    @RequestMapping("/download")
    public String download(String fileName, HttpServletRequest request,
                           HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName);
        try {
            String path = request.getSession().getServletContext().getRealPath
                    ("image")+File.separator;
            InputStream inputStream = new FileInputStream(new File(path
                    + fileName));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
        return null;
    }
}