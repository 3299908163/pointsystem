package cn.bdqn.controller;


import cn.bdqn.pointsystem.dao.UserMapper;
import cn.bdqn.pointsystem.emtitys.Cause;
import cn.bdqn.pointsystem.emtitys.Grade;
import cn.bdqn.pointsystem.emtitys.IntegralRecord;
import cn.bdqn.pointsystem.emtitys.User;
import cn.bdqn.pointsystem.service.CauseService;
import cn.bdqn.pointsystem.service.GradeService;
import cn.bdqn.pointsystem.service.IntegralRecordService;
import cn.bdqn.pointsystem.service.UserService;
import cn.bdqn.pointsystem.utlis.Constant;
import cn.bdqn.pointsystem.utlis.ReturnResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yajun,wxz
 * @create 2019/12/27
 */
@Controller
@RequestMapping("/student/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆方法
     * @param identity 身份证号
     * @param name 用户名
     * @param session
     * @return
     */
    @ResponseBody
    @GetMapping("/login")
    public Map<String,Object> login(@RequestParam String identity, @RequestParam String name,
                                    HttpSession session, HttpServletRequest request){
        User user = userService.login(identity, name);
        Map<String, Object> map=null;
        if (null==user){
            //判断身份证是否存在
            if (null==userService.findUserByidentity(identity)){
                //为空返回5002，身份证不存在
                 map = ReturnResult.returnFail(null, null, 5002);
            }else {
                //返回5003，身份存在，则用户名不匹配
                map = ReturnResult.returnFail(null,null,5003);
            }
        }else {
            //保存用户到session对象中
            session.setAttribute(Constant.CURRENT_USER, user);
            String id = request.getSession(true).getId();
            user.setSessionId(id);
            userService.updateSession(user);
            //登陆成功
            map = ReturnResult.returnSuccess(null,user.getIsAdmin());
        }
        return map;
    }

    /**
     * 注销方法
     * @param session
     * @return
     */
    @GetMapping("/sessionCancellation")
    public @ResponseBody
    boolean sessionCancellation(HttpSession session){
        session.removeAttribute(Constant.CURRENT_USER);
        return true;
    }

    /**
     * 重复登陆 跳转页面
     * @return
     */
    @GetMapping("/toErrorLogin")
    public String toErrorLogin(){
        return "errorLogin";
    }
    @RequestMapping("/index")
    public String intoIndex(){
        return "/student/index";
    }

}
