package intercepter;

import domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 实现登录拦截器
 */
public class LoginIntercepter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		//获得url地址
		StringBuffer url = req.getRequestURL();
		if(url.toString().contains("readArticleById.do")){
			return true;
		}
		if(url.indexOf("login.do")>= 0||url.indexOf("register.do")>= 0||url.indexOf("isExistUserName.do")>= 0||url.indexOf("isExistEmail.do")>= 0||url.indexOf("isExistPhone.do")>= 0||url.indexOf("activeUser.do")>= 0||url.indexOf("updateEmail.do")>= 0){
			return true;
		}
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("onlineUser");
		if(user != null){
			return true;
		}
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		return false;
	}
}
