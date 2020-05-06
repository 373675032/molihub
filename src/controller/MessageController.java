package controller;

import com.alibaba.fastjson.JSON;
import domain.MessageToShow;
import domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * 消息控制器
 * @User MOTI
 * @Time 2019/9/8 13:30
 */
@Controller
public class MessageController extends BaseController {

    /**
     * 获得我的所有消息
     * @throws IOException
     */
    @RequestMapping("/getAllMessages")
    public void  getAllMessages() throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        List<MessageToShow> list = ms.getAllMessages(user.getUser_id());
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getComment_content()!=null){
                String content = dealStr(list.get(i).getComment_content());
                list.get(i).setComment_content(content);
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        // 使用工具类JSON将集合转为json字符串,方便前端调取
        String result = JSON.toJSONString(list);
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(result);
    }

    /**
     * 将我的消息全部设置为已读
     * @throws IOException
     */
    @RequestMapping("/readAllMessages")
    public void  readAllMessages() throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        ms.setLooked(user.getUser_id());
        response.getWriter().write("ok");
    }

    /**
     * 清空我的消息
     * @throws IOException
     */
    @RequestMapping("/deleteReadMessages")
    public void  deleteReadMessages() throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        ms.deleteRead(user.getUser_id());
        response.getWriter().write("ok");
    }

    /**
     * 是不是存在新的消息
     * @throws IOException
     */
    @RequestMapping("/haveNewMessage")
    public void  haveNewMessage() throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        List<MessageToShow> list = ms.getAllMessages(user.getUser_id());
        String result = "no";
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getIs_looked().equals("no")){
                result = "yes";
                break;
            }
        }
        response.getWriter().write(result);
    }

    /**
     * 处理HTML实体字符<>
     * @param content
     * @return
     */
    private String dealStr(String content){
        String temp1 = content.replaceAll("<","&lt;");
        String temp2 = temp1.replaceAll(">","&gt;");
        return temp2;
    }

}
