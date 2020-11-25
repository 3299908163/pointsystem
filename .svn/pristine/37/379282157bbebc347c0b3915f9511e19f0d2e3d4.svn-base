package cn.bdqn.controller;

import cn.bdqn.pointsystem.emtitys.IntegralRecord;
import cn.bdqn.pointsystem.emtitys.User;
import cn.bdqn.pointsystem.service.IntegralRecordService;
import cn.bdqn.pointsystem.service.UserService;
import cn.bdqn.pointsystem.utlis.Constant;
import cn.bdqn.pointsystem.utlis.ReturnResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yajun
 * @create 2019/12/30
 */
@RestController
@RequestMapping("/student/point")
public class IntegralRecordController {
    @Autowired
    private IntegralRecordService recordService;
    /**
     * 查询学生增减积分的所有数据
     * @param UserId
     * @return
     */
    @GetMapping("/getIntegralRecordUserList")
    public Map<String,Object> getIntegralRecordUserList(@RequestParam Integer UserId){
        Map<String,Object> map = null;
        List<IntegralRecord> list = recordService.findIntegralRecordById(UserId,null);
        if (list.size()>0){
            map = ReturnResult.returnSuccess(null,list);
        }else {
            map = ReturnResult.returnFail("没有数据",null,500);
        }
        return map;
    }

    /**
     * 增减积分的所有数据
     * @param session
     * @return
     */
    @GetMapping("/getIntegralRecordListByCurrentUser")
    public Map<String,Object> getIntegralRecordList(HttpSession session){
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        Map<String,Object> map = null;
        try{
            List<IntegralRecord> list = recordService.findIntegralRecordById((int)user.getId(),null);

            map = ReturnResult.returnSuccess(list.size()>0?list:"暂无数据",list);

            return map;
        }catch (Exception e){
            e.printStackTrace();
            map = ReturnResult.returnFail("服务器忙，请稍后重试",null,500);
            return map;
        }
    }

}
