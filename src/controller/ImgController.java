package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;
@Controller
public class ImgController  extends BaseController  {
    @RequestMapping("/uploadImg")
    public void editormdPic (@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String trueFileName = file.getOriginalFilename();
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
        String fileName = System.currentTimeMillis()+"_"+ UUID.randomUUID() +suffix;
        String path = request.getSession().getServletContext().getRealPath("/static/upload_img/");
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject res = new JSONObject();
        res.put("url", "static/upload_img/"+fileName);
        res.put("success", 1);
        res.put("message", "upload success!");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(res.toJSONString());
    }
}
