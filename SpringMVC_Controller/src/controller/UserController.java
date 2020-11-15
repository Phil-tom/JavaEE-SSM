package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.UserForm;
import service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    //得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(UserController.class);
    //将服务依赖注入到属性userService
    @Autowired
    public UserService userService;

    /**
     * 处理登录
     */
    @RequestMapping("/login")
    public String login(UserForm user, HttpSession session, Model model) {
        if (userService.login(user)) {
            session.setAttribute("u", user);
            logger.info("成功");
            return "main";//登录成功，跳转到main.jsp
        } else {
            logger.info("失败");
            model.addAttribute("messageError", "用户名或密码错误");
            return "login";
        }
    }
//    /**
//     * 处理注册
//     * 使用UserForm对象（实体Bean）user接收注册页面接受的请求参数
//     */
//    @RequestMapping("/register")
//    public String register(UserForm user, Model model) {
//        if ("zhangsan".equals(user.getUname())
//                && "123456".equals(user.getUpass())) {
//        	logger.info("成功");
//        	return "login";
//        }else{
//        	logger.info("失败");
//        	//在register.jsp页面上可以使用EL表达式取出model的uname
//        	model.addAttribute("uname",user.getUname());
//        	return "register";
//		}
//    }

//    @RequestMapping("/register")
//    /**
//     * 通过形参接受请求参数，形参名称与请求参数名称完全相同！
//     */
//    public String register(String uname,String upass, Model model) {
//        if ("zhangsan".equals(uname)
//                && "123456".equals(upass)) {
//            logger.info("成功");
//            return "login"; //注册成功，跳转到login.jsp
//        } else {
//            logger.info("失败");
//            //在register.jsp页面上可以使用EL表达式取出model的uname
//            model.addAttribute("uname", uname);
//            return "register";//返回register.jsp
//        }
//    }

//    @RequestMapping("/register")
//    /**
//     * 通过HttpServletRequest接受请求参数
//     */
//    public String register(HttpServletRequest request, Model model) {
//        String uname = request.getParameter("uname");
//        String upass = request.getParameter("upass");
//        if ("zhangsan".equals(uname)
//                && "123456".equals(upass)) {
//            logger.info("成功");
//            return "login"; //注册成功，跳转到login.jsp
//        } else {
//            logger.info("失败");
//            //在register.jsp页面上可以使用EL表达式取出model的uname
//            model.addAttribute("uname", uname);
//            return "register";//返回register.jsp
//        }
//    }

    @RequestMapping("/register")
    /**
     * 通过@RequestParam接收请求参数
     */
    public String register(@RequestParam String uname, @RequestParam String upass, Model model) {
        if ("zhangsan".equals(uname)
                && "123456".equals(upass)) {
            logger.info("成功");
            return "login"; //注册成功，跳转到login.jsp
        } else {
            logger.info("失败");
            //在register.jsp页面上可以使用EL表达式取出model的uname
            model.addAttribute("uname", uname);
            return "register";//返回register.jsp
        }
    }

}
