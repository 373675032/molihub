package controller;

import domain.User;
import domain.other.Sex;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.ActiveCodeUtils;
import utils.MailUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户的表现层实现类
 * @User MOTI
 * @Time 2019/8/4 7:44
 */
@Controller
public class UserController extends BaseController {

    /**
     * 根据用户名和密码获得对象,如果能获得结果,那么登录成功,反之登录失败
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String login(User user){
        User user1 = us.getUserByUserNameAndPassword(user.getUser_name(),user.getPassword());
        User user2 = us.getUserByUserName(user.getUser_name());
        if(user1 != null){
            System.out.println(user1);
            session.setAttribute("tempUser",user1);
            if(user1.getStatus() == 1){
                session.setAttribute("onlineUser",user1);
                System.out.println("当前在线用户:"+user1);
                return "my_home";
            }else{
                logger.info("登录验证开始发送邮件");
                sentMail(user1);
                return "wait";
            }
        }
        if(user2 != null){
            request.setAttribute("error","密码错误!");
            return "forward:login.jsp";
        }
        request.setAttribute("error","该账号尚未注册,快去注册吧~");
        return "forward:login.jsp";
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user){
        List<String> resultList = new ArrayList<>();
        User existName = us.getUserByUserName(user.getUser_name());
        User existEmail = us.getUserByEmail(user.getEmail());
        User existPhone = us.getUserByPhone(user.getPhone());
        if(existName != null){
            resultList.add("已经有小伙伴使用过这个用户名啦~");
        }
        if(existEmail != null){
            resultList.add("咦,这个邮箱已经被人注册过啦~");
        }
        if(existPhone != null){
            resultList.add("咦,这个手机号已经被人注册过啦~");
        }
        if(resultList.size() == 0){
            user.setActivecode(ActiveCodeUtils.createActiveCode());
            logger.info("注册之后发送邮件");
            sentMail(user);
            user.setSex(Sex.保密);
            user.setAge(0);
            us.addUser(user);
            User user1 = us.getUserByUserName(user.getUser_name());
            session.setAttribute("onlineUser",user1);
            return "redirect:login.jsp";
        }else{
            request.setAttribute("error", resultList);
            return "ERROR";
        }
    }

    /**
     * 发送邮件
     * @param user
     */
    public void sentMail(User user){
        try {
            MailUtils.sendMail(user.getEmail(),"<h2>Molihub-IT学习社区</h2>" +
                    "<h3>用户注册-邮箱验证<h3/>" +
                    "您刚刚注册了Molihub账号<br>" +
                    "用户名 :"+user.getUser_name()+
                    "<br>官方地址为 : <a href = 'http://moti.work'>Molihub</a><br>" +
                    "现在你需要激活你的账号才可以使用," +
                    "请<a href='http://xuewei.world:8081/Molihub/activeUser.do?code=" +
                    user.getActivecode()
                    +"&username="+user.getUser_name() +
                    "'>" +
                    "点击此处激活</a>" +
                    "<h3 style='color : red'>如果并非本人操作,请忽略本邮件</h3>");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("发送邮件失败! 原因:"+e.getMessage());
        }
    }


    /**
     * 邮箱验证
     * @return
     */
    @RequestMapping("/activeUser")
    public String activeUser(String code,String username){
        System.out.println(code+"===="+username);
        us.updateUserStatus(code,username);
        User user = us.getUserByUserName(username);
        session.setAttribute("onlineUser",user);
        return "my_home";
    }

    /**
     * 修改邮箱
     * @return
     */
    @RequestMapping("/updateEmail")
    public void updateEmail(String email) throws IOException {
        System.out.println(email+"=====");
        User user = (User) session.getAttribute("tempUser");

        if(us.getUserByEmail(email) != null){
            response.getWriter().write("no");
        }else{
            user.setEmail(email);
            us.updateUser(user);
            response.getWriter().write("yes");
        }
    }

    /**
     * 判断当前用户名是否已经存在
     * @param userName
     * @throws Exception
     */
    @RequestMapping("/isExistUserName")
    public void isExistUserName(String userName) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        if (us.getUserByUserName(userName) != null) {
            response.getWriter().write("yes");
        } else {
            response.getWriter().write("no");
        }
    }

    /**
     * 判断当前邮箱是否已经存在
     * @param email
     * @throws Exception
     */
    @RequestMapping("/isExistEmail")
    public void isExistEmail(String email) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        if (us.getUserByEmail(email) != null) {
            response.getWriter().write("yes");
        } else {
            response.getWriter().write("no");
        }
    }

    /**
     * 判断当前手机号是否已经存在
     * @param phone
     * @throws Exception
     */
    @RequestMapping("/isExistPhone")
    public void isExistPhone(String phone) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        if (us.getUserByPhone(phone) != null) {
            response.getWriter().write("yes");
        } else {
            response.getWriter().write("no");
        }
    }

    /**
     * 修改用户资料
     * @param user_name
     * @param sex
     * @param phone
     * @param email
     * @param age
     * @throws IOException
     */
    @RequestMapping("/updateInfo")
    public void updateInfo(String user_name,String sex,String phone,String email,int age) throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        response.setContentType("text/html;charset=UTF-8");
        Sex sex1;
        if(sex.equals("男")){
            sex1 = Sex.男;
        }else if(sex.equals("女")){
            sex1 = Sex.女;
        }else {
            sex1 = Sex.保密;
        }
        //正确:如果查到的结果为空或者没有发生改变
        //错误:信息发生改变但是查到的结果不为空
        if(us.getUserByUserName(user_name) != null && !user.getUser_name().equals(user_name)){
            response.getWriter().write("fail:user_name");
        }else if(us.getUserByPhone(phone) != null && !user.getPhone().equals(phone)){
            response.getWriter().write("fail:phone");
        }else if(us.getUserByEmail(email) != null && !user.getEmail().equals(email)){
            response.getWriter().write("fail:email");
        }else{
            int ststus = 0;
            if(user.getEmail().equals(email)){
                ststus = 1;
            }
            as.updateComment(user_name,user.getUser_name());
            user.setUser_name(user_name);
            user.setAge(age);
            user.setSex(sex1);
            user.setPhone(phone);
            user.setEmail(email);
            us.updateUser(user);
            session.setAttribute("onlineUser",user);
            if(ststus == 0){
                us.unValidateEmail(user.getUser_name());
            }
            response.getWriter().write("success");
        }
    }

    /**
     * 修改用户密码
     * @param password
     */
    @RequestMapping("/updatePassword")
    public void updatePassword(String password){
        User user = (User) session.getAttribute("onlineUser");
        user.setPassword(password);
        us.updateUser(user);
        session.setAttribute("onlineUser",user);
    }

}
