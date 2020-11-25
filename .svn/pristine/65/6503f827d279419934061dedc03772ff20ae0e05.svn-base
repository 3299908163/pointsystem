package cn.bdqn.controller;

import cn.bdqn.pointsystem.emtitys.Prize;
import cn.bdqn.pointsystem.emtitys.PrizeExchangeRecord;
import cn.bdqn.pointsystem.emtitys.User;
import cn.bdqn.pointsystem.service.PrizeExchangeRecordService;
import cn.bdqn.pointsystem.service.PrizeService;
import cn.bdqn.pointsystem.service.UserService;
import cn.bdqn.pointsystem.utlis.Constant;
import cn.bdqn.pointsystem.utlis.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author wxz
 * @create 2019/12/31
 */
@Controller
@RequestMapping("/sys/prizeExchangeRecord")
public class SysPrizeExchangeRecordController {

    @Autowired
    private PrizeExchangeRecordService prizeExchangeRecordService;



    @ResponseBody
    @GetMapping("/getPrizeExchangeRecordById")
    public Object  getPrizeExchangeRecordById(@RequestParam Integer id){
        PrizeExchangeRecord prizeExchangeRecord = prizeExchangeRecordService.getPrizeExchangeRecordById(id);
        if(prizeExchangeRecord!=null){
            return ReturnResult.returnSuccess(null,prizeExchangeRecord);
        }else {
           return ReturnResult.returnFail(null,null,"500");
        }
    }

    /**
     * 修改商品兑换记录领取
     * @param id   商品编号
     * @param isReceive  是否领取  0:未领取  1:领取
     * @return
     */
    @ResponseBody
    @GetMapping("/updPrizeExchangeRecordReceive")
    public Object  updPrizeExchangeRecordReceive(@RequestParam Integer id,@RequestParam String isReceive){
        //修改商品兑换记录领取
        boolean flag = prizeExchangeRecordService.updatePrizeExchangeRecordReceive(id, isReceive);
        if(flag){//true
            return ReturnResult.returnSuccess(null,flag);
        }else {//false
           return ReturnResult.returnFail(null,flag,"500");
        }
    }

}
