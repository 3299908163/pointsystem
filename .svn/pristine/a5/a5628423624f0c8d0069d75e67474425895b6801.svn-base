package cn.bdqn.controller;

import cn.bdqn.pointsystem.emtitys.Grade;
import cn.bdqn.pointsystem.emtitys.IntegralRecord;
import cn.bdqn.pointsystem.emtitys.User;
import cn.bdqn.pointsystem.service.GradeService;
import cn.bdqn.pointsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 跳转controller
 */
@RequestMapping("/jump")
@Controller
public class JumpController {
    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;

    /**
     * 跳转学生积分记录页面
     * @param model
     * @return
     */
    @RequestMapping("/integralRecord")
    public String integralRecord(Model model) {
        List<User> userList=userService.userList();//获取用户集合
        List<Grade> gradeList = gradeService.getGradeName();//获取班级集合
        model.addAttribute("gradeList",gradeList);
        model.addAttribute("userList",userList);
        return "admin/integralRecord";
    }

    /**
     * 跳转商品信息页面
     * @return
     */
    @RequestMapping("/prizeInfo")
    public String prizeInfo(){
        return "admin/prize";
    }

    /**
     * 跳转商品兑换记录页面
     * @return
     */
    @RequestMapping("/prizeExchangRecord")
    public String prizeEx(){
        return "admin/prizeExchangRecord";
    }

    /**
     * 跳转用户页面
     * @param model
     * @return
     */
    @RequestMapping("/user")
    public String userInfo(Model model){
        List<Grade> gradeList = gradeService.getGradesOrderByPoint();//查询班级表
        model.addAttribute("gradeList",gradeList);
        return "admin/user";
    }

    /**
     * 跳转学生奖罚页面
     * @return
     */
    @RequestMapping("/addCause")
    public String addCause(){
        return "admin/enNe";
    }

    /**
     * 跳转注册管理员页面
     * @return
     */
    @RequestMapping("/registerUser")
    public String registerUser(){
        return "/registerUser";
    }

    /**
     * 跳转添加学生页面
     * @return
     */
    @RequestMapping("/addStudent")
    public String addStudent(){
        return "/addStudent";
    }

    /**
     * 跳转老师登录页面
     * @return
     */
    @RequestMapping("/loginTeacher")
    public String loginTeacher(){
        return "/loginTeacher";
    }


    /**
     * 跳转学生积分记录页面
     * @param model
     * @return
     */
    @RequestMapping("/integralGradeRecord")
    public String integralGradeRecord(Model model){
        List<Grade> gradeList = gradeService.getGradeName();//获取班级集合
        model.addAttribute("gradeList",gradeList);
        return "admin/integralGradeRecord";
    }
}
