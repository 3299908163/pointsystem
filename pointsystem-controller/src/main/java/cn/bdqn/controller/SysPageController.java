package cn.bdqn.controller;

import cn.bdqn.pointsystem.emtitys.*;
import cn.bdqn.pointsystem.service.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yajun
 * @create 2020/1/6
 */
@Controller
@RequestMapping("/sys/page")
public class SysPageController {

    @Autowired
    private PrizeExchangeRecordService prizeExchangeRecordService;

    @Autowired
    private IntegralRecordService recordService;
    @Autowired
    private PrizeService prizeService;

    @Autowired
    private UserService userService;

    @Autowired
    private GradeService gradeService;
    /**
     * 奖品兑换记录分页
     * @param page 页数
     * @param limit 每行显示的数据
     * @param name 用户名
     * @return
     */
    @GetMapping("/pageList")
    @ResponseBody
    public Map<String,Object> selectPrizeExchangeRecordPage(int page, int limit, @RequestParam(required = false) String name){
        Page<PrizeExchangeRecord> page1 = new Page<>(page,limit);   //查询第page页，每页返回limit条
        IPage<PrizeExchangeRecord> iPage = prizeExchangeRecordService.selectPrizeExchangeRecordPage(page1,name);//奖品兑换记录分页
        int count=prizeExchangeRecordService.getPrizeCount(name);//查询奖品兑换记录总记录数
        Map<String, Object> map = getStringObjectMap(count);
        map.put("data",iPage.getRecords());
        return map;
    }

    /**
     * 学生积分记录分页
     * @param gradeId  班级编号
     * @param userId   学生编号
     * @param userName  学生名
     * @param page   页数
     * @param limit 每行显示的数据
     * @return
     */
    @RequestMapping("/getIntegralRecordPage")
    @ResponseBody
    public Object getIntegralRecordPage(@RequestParam(required = false)Integer gradeId,
                                        @RequestParam(required = false) Integer userId,
                                        @RequestParam(required = false) String userName,
                                        @RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer limit){
        IntegralRecord integralRecord=new IntegralRecord();
        integralRecord.setIntegralRecordGradeId(gradeId);//班级积分记录编号
        integralRecord.setUserId(userId);//记录者姓名
        Page<IntegralRecord> integralRecordPage=new Page<>(page,limit);//学生积分记录分页实体类
        //学生积分记录分页
        IPage<IntegralRecord> recordIPage =recordService.selectIntegralRecordStudentPage(integralRecordPage,integralRecord,userName);
        //获取学生积分记录集合
        int count = recordService.selectIntegralRecordStudentPageCount(integralRecord, userName);
        return getIntegralRecord(recordIPage, count);
    }

    /**
     * 学生分页
     * @param page 当前页数
     * @param limit 显示几条数据
     * @param name 用户名
     * @param gradeId 班级编号
     * @return
     */
    @ResponseBody
    @GetMapping("/userList")
    public Object listUser(@RequestParam(required = false) Integer page,
                           @RequestParam(required = false) Integer limit,
                           @RequestParam(required = false)  String name,
                           @RequestParam(required = false)  Integer gradeId){
        Page<User> ipage=new Page<>(page,limit);//分页实体类 当前页,每页显示的数据
        IPage<User> pageUser = userService.getPageUser(ipage, gradeId, name);//分页
        int count=userService.count(gradeId,name);//用户集合count
        Map<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "");
        map.put("count", count);
        map.put("data",pageUser.getRecords());
        return map;
    }

    /**
     * 班级积分记录
     * @param gradeName 班级名
     * @param page  当前页数
     * @param limit 显示几条数据
     * @return
     */
    @RequestMapping("/getIntegralRecordGradePage")
    @ResponseBody
    public Object getIntegralRecordGradePage(@RequestParam(required = false)String gradeName,
                                             @RequestParam(required = false) Integer page,
                                             @RequestParam(required = false) Integer limit){
        IntegralRecord integralRecord=new IntegralRecord();
        integralRecord.setIntegralRecordGradeName(gradeName);//班级名
        Page<IntegralRecord> integralRecordPage=new Page<>(page,limit);//班级分页实体类
        //班级积分记录分页
        IPage<IntegralRecord> recordIPage =recordService.selectIntegralRecordGradePage(integralRecordPage,integralRecord);
        //获取班级集合
        int count = recordService.selectIntegralRecordGradePageCount(integralRecord);
        return getIntegralRecord(recordIPage, count);
    }

    /**
     * 分页查询班级信息
     * @param page 第几页
     * @param limit 每页几条数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/grades")
    public Map<String, Object> grades(int page,int limit){
        Page<Grade> gradePage=new Page<>(page,limit);//班级信息分页实体类
        //班级信息分页
        IPage<Grade> gradeIPage =gradeService.selectPageGrade(gradePage,"");
        int count=gradeService.selectGradeCount();
        Map<String, Object> map = getStringObjectMap(count);
        map.put("data",gradeIPage.getRecords());
        return map;
    }



    @ResponseBody
    @GetMapping("/getPrizePage")
    public  Object getPrizePage(@RequestParam(required = false) Integer page,
                                @RequestParam(required = false) Integer limit,
                                @RequestParam(required = false) String name){
        Page<Prize> ipage=new Page<>(page,limit);//分页实体类  (当前页,每页显示几条信息)
        IPage<Prize> prizeIPage = prizeService.selectPagePrize(ipage, name);//分页

        int count=prizeService.prizeCount(name);//查询商品总记录数
        Map<Object,Object> map=new HashMap<>();
        map.put("data",prizeIPage.getRecords());//返回的集合数据
        map.put("code","0");
        map.put("count",count);
        map.put("msg","");
        return map;
    }



    private Object getIntegralRecord(IPage<IntegralRecord> recordIPage, int count) {
        for (IntegralRecord record : recordIPage.getRecords()) {
            if ("0".equals(record.getIsAdd().trim())) {
                record.setIsAdd("减");
            }
            if (record.getIsAdd().equals("1")) {
                record.setIsAdd("加");
            }
        }
        Map<String, Object> map = getStringObjectMap(count);
        map.put("data", recordIPage.getRecords());
        return map;
    }

    private Map<String, Object> getStringObjectMap(int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "");
        map.put("count", count);
        return map;
    }
}
