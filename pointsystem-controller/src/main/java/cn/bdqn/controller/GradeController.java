package cn.bdqn.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname GradeController
 * @Description TODO
 * @Date 2019/12/27 18:30
 * @Created by Administrator
 */
@Controller
@RequestMapping("/student/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 查询班级的积分
     * @param session
     * @return
     */
    @GetMapping("/getPointByGradeId")
    @ResponseBody
    public  Map<String,Object> getPointByGradeId(HttpSession session){
        Map<String,Object>map =null;
        User user = (User)session.getAttribute(Constant.CURRENT_USER);
        Grade grade = gradeService.getGradeById((int) user.getGradeId());
        if (null == grade){
            map = ReturnResult.returnFail(null,null,500);
        }else {
            map = ReturnResult.returnSuccess(null,grade.getPoint());
        }
        return map;
    }

    @GetMapping("/getGradeListOrderByPoint")
    @ResponseBody
    public Map<String,Object> getGradeListOrderByPoint(){
        Map<String,Object>map = null;
        List<Grade> point = gradeService.getGradesOrderByPoint();
        map = ReturnResult.returnSuccess(null,point);
        return map;
    }
}
