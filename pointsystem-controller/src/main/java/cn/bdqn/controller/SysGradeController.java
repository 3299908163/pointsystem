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

@Controller
@RequestMapping("/sys/grade")
public class SysGradeController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private CauseService causeService;
    @Autowired
    private IntegralRecordService integralRecordService;




    /**
     * 根据班级名称查询是否重复
     * @param gradeName
     * @return
     */
    @ResponseBody
    @RequestMapping("/isHaveGradeName")
    public String isHaveGradeName(String gradeName){
        System.out.println("班级名："+gradeName);
        List<Grade> grads= gradeService.acquireGradesByGradeName(gradeName);
//        System.out.println("班级信息："+grad);
        if(grads.size()!=0){  //班级名存在
//            int id=grads.get(0).getId();
            return "true";
        }else{
            return "false";
        }
    }


    /**
     * 添加班级
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/addGrade")
    public String addGrade(String name){
        if(gradeService.insertGrade(name)>0){
            return "true";
        }else{
            return "false";
        }
    }

    /**
     * 删除班级
     * @param list 班级id集合
     * @return
     */
    @GetMapping("/del")
    @ResponseBody
    public String delUser(int[] list){
//        Cause cause1=new Cause();
        for (int i = 0; i < list.length ; i++) {
            int a = gradeService.removeGrade(list[i]);
            System.out.println("删除成功！" + i);
            if (a == 0) {
                return "false";
            }

        }
        return "true";
    }

    /**
     * 跳转班级奖惩页面
     * @return
     */
    @RequestMapping("/gradeReward")
    public String gradeReward(Model model){
        List<Cause> causeList=causeService.getAllCause();
        List<Grade> gradeList=gradeService.getGradeName();
        model.addAttribute("causeList",causeList);
        model.addAttribute("gradeName",gradeList);
        return "admin/gradeReward";
    }

    /**
     * 加载班级
     * @return
     */
    @RequestMapping("/gradePoint")
    public String gradePoint(){
        return "admin/gradePoint";
    }

    /**
     * 执行班级奖罚
     * @param gradeid
     * @param causeid
     * @param point
     * @param isadd
     * @param date
     * @param request
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("/gradePointChange")
    public String gradePointChange(String gradeid, String causeid, String point, String isadd,String date, HttpServletRequest request) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date time = simpleDateFormat.parse(date);
        int gradeId=Integer.valueOf(gradeid);
        int causeId=Integer.valueOf(causeid);                  //将String类型转为integer类型
        int pointNum=Integer.valueOf(point);
//        Integer isAdd=Integer.valueOf(isadd);
        Grade grade= gradeService.getGradeById(gradeId);        //根据id获取班级信息
        int num=0;
        System.out.println("是增加还是减少"+isadd);
        if(isadd=="1"){                //根据isadd判断是增加还是减少
            int p=grade.getPoint();
            num=p+pointNum;       //num为增加后的积分
            System.out.println("增加后的积分是"+num);
        }else{
            int p=grade.getPoint();
            num=p-pointNum;
            System.out.println("减少后的积分是"+num);
        }
        //将班级积分变化加入记录表
        IntegralRecord integralRecord=new IntegralRecord();
        integralRecord.setGradeId(gradeId);
        integralRecord.setPoint(pointNum);
        integralRecord.setIsAdd(isadd);
        integralRecord.setCauseId(causeId);
        integralRecord.setCreationTime(time);
        User user=(User)request.getSession().getAttribute(Constant.CURRENT_USER); //获取当前用户
        int recorderId=(int)user.getId();
        integralRecord.setRecorderId(recorderId);
        if(integralRecordService.insertGradeIntegralRecord(integralRecord)>0 && gradeService.changeGradePoint(gradeId,num)>0){ //添加记录成功
            return "true";
        }else{
            return "false";
        }
    }


    /**
     * 根据班级名称查询是否重复
     * @param gradeName
     * @return
     */
    @ResponseBody
    @RequestMapping("/isHaveGradeName2")
    public String isHaveGradeName2(String gradeName){
        System.out.println("班级名："+gradeName);
        List<Grade> grads= gradeService.acquireGradesByGradeName(gradeName);
//        System.out.println("班级信息："+grad);
        if(grads.size()!=0){  //班级名存在
            int id=grads.get(0).getId();
            String Id=String.valueOf(id);
            return Id;
        }else{
            return "false";
        }
    }

    /**
     * 跳转到后台主页
     * @return
     */
        @RequestMapping("/adminIndex")
    public String homePage(Model model){
        List<Grade> gradeName = gradeService.getGradeName();
        model.addAttribute("gradeNames",gradeName);
        return "admin/homePage";
    }

    /**
     * 添加学生页面所需要的班级集合
     * @return
     */
    @ResponseBody
    @GetMapping("/getGradeListByCreate")
    public Map<String, Object> getGradeListByCreate(){
        try{
            List<Grade> gradeList = gradeService.getGradeList(null);//获取班级集合
            return ReturnResult.returnSuccess(null, gradeList);
        }catch (Exception e){
            return ReturnResult.returnFail(null,null,500);
        }
    }
}
