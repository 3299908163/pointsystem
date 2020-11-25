package cn.bdqn.controller;

import cn.bdqn.pointsystem.emtitys.Cause;
import cn.bdqn.pointsystem.service.CauseService;
import cn.bdqn.pointsystem.service.IntegralRecordService;
import cn.bdqn.pointsystem.utlis.ReturnResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname CauseController
 * @Description 奖罚表
 * @Date 2019/12/27 17:46
 * @Created by zhaomengliang
 */
@Controller
@RequestMapping("/student/cause")
public class CauseController {
    @Autowired
    private CauseService causeService;

    @GetMapping("/getCauses")
    @ResponseBody
    public Map<String, Object> getCauseList(){
        List<Cause> list=causeService.getCauses();
        if(list == null){
            return ReturnResult.returnFail(null,null,500);
        }
        return ReturnResult.returnSuccess(null, list);
    }

}
