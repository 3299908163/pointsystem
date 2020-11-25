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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname sysUserController
 * @Description 注释
 * @Date 2020/1/6 11:34
 * @Created by zhaomengliang
 */
@Controller
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CauseService causeService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private IntegralRecordService recordService;

    @PostMapping("/import")
    @ResponseBody
    public Map<String,Object> setUsers(MultipartFile file){
        if(file.isEmpty()){
            return null;
        }
        String result=null;
        String fileName=file.getOriginalFilename();
        try {
            result= userService.batchImport(fileName,file);
//            System.out.println(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code",result);

        map.put("data",null);
        map.put("msg","ok");
        return map;
    }

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
     * 添加用户
     * @param user  用户实体类
     * @param session
     * @return
     */
    @RequestMapping("/registerUser")
    @ResponseBody
    public Object registerUser(User user, HttpSession session){
        User user1 = userService.findUserByidentity(user.getIdentity());
        if(user1!=null){
            return ReturnResult.returnFail("此用户已存在",null,"600");
        }

        int i = userService.addtUser(user);//添加用户

        if(i>0){//注册成功

            //保存用户到session对象中
            if(user.getIsAdmin()==1){
                session.setAttribute(Constant.CURRENT_USER, user);
            }
            return ReturnResult.returnSuccess("成功",user.getIsAdmin());
        }else{
            return ReturnResult.returnFail("失败",null,"500");
        }
    }

    /**
     * @Description 修改用户信息
     * @Param
     * @return
     * @Date 2020/1/6 11:38
     * @auther zhaomengliang
     */
    @ResponseBody
    @RequestMapping("/updateStudent")
    public String updateStudent(String name,String identity,String address,String gradeId,String id,String sex,String phone,String parentPhone){
        Integer gradeid=Integer.valueOf(gradeId);
        Integer Id=Integer.valueOf(id);
        User user=new User();
        user.setId(Id);
        user.setName(name);
        user.setIdentity(identity);
        user.setAddress(address);
        user.setGradeId(gradeid);
        user.setSex(sex);
        user.setPhone(phone);
        user.setParentPhone(parentPhone);
        if(userService.changeUser(user)>0){
            return "true";
        }else{
            return "false";
        }
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


    //学生奖罚信息源
    @RequestMapping("/studentReward")
    public String toStudentReward(Model model){
        List<Cause> causeList=causeService.getAllCause();
        List<Grade> gradeList=gradeService.getGradeName();
        model.addAttribute("causeList",causeList);
        model.addAttribute("gradeName",gradeList);
        return "admin/studentReward";
    }


    /**
     *根据班级编号查找学生
     * @param gradeId 班级编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectUsersByGradeId")
    public List<User> selectUsersByGradeId(String gradeId){
        Integer gradeid=Integer.valueOf(gradeId);
        List<User> users= userService.selectUsersByGradeId(gradeid);
        return users;
    }




    /**
     * 学生积分修改   添加积分记录
     * @param studentId 学生编号
     * @param causeid 奖罚原因编号
     * @param point 学生分数
     * @param isadd  是否是奖罚  0:减 1:加
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUserIngegral")
    public String addUserIngegral(String studentId,String causeid,String point,String isadd,String date,HttpServletRequest request) throws ParseException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date time = simpleDateFormat.parse(date);
        int studentid=Integer.valueOf(studentId);
        System.out.println("被修改的人是："+studentid);
        int causeId=Integer.valueOf(causeid);                  //将String类型转为integer类型
        int pointNum=Integer.valueOf(point);
//        Integer isAdd=Integer.valueOf(isadd);
        User user=userService.selectUserById(studentid);        //根据id获取学生信息
        int num=0;                                 //计算积分
        if(isadd=="1"){    //增加
            int p=(int)user.getPoint();
            num=p+pointNum;
        }else{
            int p=(int)user.getPoint();
            num=p-pointNum;
        }
        //将班级积分变化加入记录表
        IntegralRecord integralRecord=new IntegralRecord();
        integralRecord.setUserId(studentid);
        integralRecord.setPoint(pointNum);
        integralRecord.setIsAdd(isadd);
        integralRecord.setCauseId(causeId);
        integralRecord.setCreationTime(time);
        User user2=(User)request.getSession().getAttribute(Constant.CURRENT_USER); //获取当前用户
        int recorderId=(int)user2.getId();
        integralRecord.setRecorderId(recorderId);
        if(userService.changeUserPoint(num,studentid)>0 && recordService.insertUserIntegralRecord(integralRecord)>0){
            //学生积分修改成功和积分修改记录成功后执行
            return "true";              //修改成功
        }else{
            return "false";
        }
    }



    @GetMapping("/toErrorLogin")
    public String toTeacherErrorLogin(){
        return "admin/errorLogin";
    }

    @GetMapping("/del")
    @ResponseBody
    public String delUser(int id){
//        Cause cause1=new Cause();
        int a = userService.removeUser(id);
        System.out.println("删除成功！" + a);
        if (a>0) {
            return "true";
        }else{
            return "false";
        }
    }
}
