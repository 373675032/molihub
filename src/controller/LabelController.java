package controller;

import com.alibaba.fastjson.JSON;
import domain.Label;
import domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章标签的控制器
 * @User MOTI
 * @Time 2019/8/6 20:35
 */
@Controller
public class LabelController extends BaseController {

    /**
     * 获得我的所有标签
     * @throws IOException
     */
    @RequestMapping("/getMyAllLabels")
    public void getMyAllLabels()  throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        List<String> list = ls.getAllLabelsByUserId(user.getUser_id());
        // 解决response返回的数据中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        // 使用工具类JSON将集合转为json字符串,方便前端调取
        String result = JSON.toJSONString(list);
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(result);
    }

    /**
     * 获得我的所有标签和标签对应的文章数
     * @throws IOException
     */
    @RequestMapping("/getMyAllLabelsAndArticleCount")
    public void getMyAllLabelsAndArticleCount() throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        List<String> list = ls.getAllLabelsByUserId(user.getUser_id());
        Map<String,Integer> map = new LinkedHashMap<>();
        for (String tag:list
             ) {
            int num = ls.getTagArticleCount(tag,user.getUser_id());
            map.put(tag,num);
        }
        // 解决response返回的数据中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        // 使用工具类JSON将集合转为json字符串,方便前端调取
        String string = JSON.toJSONString(map);
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(string);
    }

    /**
     * 添加标签
     * @param name
     * @throws IOException
     */
    @RequestMapping("/addLabel")
    public void addLabel(String name) throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        response.setContentType("text/html;charset=UTF-8");
        if(!isExist(name,user.getUser_id())){
            ls.addLabel(name,user.getUser_id());
            response.getWriter().write("yes");
        }else{
            response.getWriter().write("no");
        }
    }

    /**
     * 删除标签
     * @param name
     */
    @RequestMapping("/deleteLabel")
    public void deleteLabel(String name){
        User user = (User) session.getAttribute("onlineUser");
        ls.deleteLabel(name,user.getUser_id());
    }

    /**
     * 重命名标签
     * @param oldLabel
     * @param newLabel
     */
    @RequestMapping("/updateLabel")
    public void updateLabel(String oldLabel,String newLabel){
        User user = (User) session.getAttribute("onlineUser");
        ls.updateLabel(oldLabel,newLabel,user.getUser_id());
    }

    /**
     * 判断标签是否已经存在
     * @param name
     * @param user_id
     * @return
     */
    public boolean isExist(String name,int user_id){
        Label label = ls.getLabel(name,user_id);
        if(label == null){
            return false;
        }
        return true;
    }

}
