package cn.bdqn.controller;

import cn.bdqn.pointsystem.emtitys.Cause;
import cn.bdqn.pointsystem.service.CauseService;
import cn.bdqn.pointsystem.service.IntegralRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname sysCauseController
 * @Description 注释
 * @Date 2020/1/6 12:04
 * @Created by zhaomengliang
 */
@Controller
@RequestMapping("/sys/cause")
public class SysCauseController {
    @Autowired
    private CauseService causeService;

    @Autowired
    private IntegralRecordService integralRecordService;

    @GetMapping("/add")
    @ResponseBody
    public String addCause(String cause){
        Cause cause1=new Cause();
        cause1.setCause(cause);
        int a =causeService.AddCause(cause1);
        if(a==0){
            return "false";
        }
        return "true";
    }

    @GetMapping("/del")
    @ResponseBody
    public String delCause(int[] list){
//        Cause cause1=new Cause();
        for (int i = 0; i < list.length ; i++) {
            if(integralRecordService.findIntegralRecordCause(list[i])==null) {  //判断是否有记录使用该数据
                int a = causeService.delCause(list[i]);
                System.out.println("删除成功！" + i);
                if (a == 0) {
                    return "false";
                }
            }
        }
        return "true";
    }


    @GetMapping("/selecCausePage")
    @ResponseBody
    public Map<String,Object> selecCausePage(int page, int limit){
        Map<String,Object> map = new HashMap<>();
        Page<Cause> page1 = new Page<>(page,limit);  // 查询第1页，每页返回5条
        IPage<Cause> iPage=causeService.selecCausePage(page1);
        int count =causeService.getCauses().size();
        List<Cause> list=iPage.getRecords();
        for (int i=0;i<list.size();i++){
            if(list.get(i).getId()==43 && list.get(i).getCause().equals("兑换奖品")){
                list.remove(i);
            }
        }
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",list);
        return map;
    }
    @GetMapping("/enen")
    public String Enen(){
        return "/grade/adminIndex";
    }


}
