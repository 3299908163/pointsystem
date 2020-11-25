package cn.bdqn.controller;

import cn.bdqn.pointsystem.emtitys.Prize;
import cn.bdqn.pointsystem.emtitys.PrizeExchangeRecord;
import cn.bdqn.pointsystem.emtitys.User;
import cn.bdqn.pointsystem.service.PrizeExchangeRecordService;
import cn.bdqn.pointsystem.service.PrizeService;
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
 * @create 2019/12/31
 */
@RestController
@RequestMapping("/student/prizeExchangeRecord")
public class PrizeExchangeRecordController {

    @Autowired
    private PrizeExchangeRecordService prizeExchangeRecordService;
    @Autowired
    private UserService userService;
    @Autowired
    private PrizeService prizeService;


    /**
     * 兑换奖品0.
     * @param prizeId
     * @param session
     * @return
     * @throws Exception
     */
    @PostMapping("/addPrizeExchangeRecord")
    public synchronized Map<String, Object> add(@RequestParam Integer prizeId, HttpSession session) throws Exception {
        User user = ((User) session.getAttribute((Constant.CURRENT_USER)));
        // 查询商品数量 --> 防止多人同时操作造成商品负数
        Prize prize = prizeService.selectPrizeOne(prizeId);
        if (prize.getNumber() <= 0){
            return ReturnResult.returnFail(null, null, 505);
        }
        // 执行添加交易信息的各类数据
        boolean flag = prizeExchangeRecordService.exchangePrizes((int)user.getId(), prizeId, prize.getPoint());
        if(flag){
            // 执行成功，重新查询user并存入session
            User userTemp = userService.findUserByidentity(user.getIdentity());
            session.setAttribute(Constant.CURRENT_USER, userTemp);
            return ReturnResult.returnSuccess(null, null);
        }
        return ReturnResult.returnFail(null, null, 500);
    }

    /**
     * 根据当前用户获取兑奖信息
     * @param session
     * @return
     */
    @GetMapping("/getPrizeExchangeRecordByCurrentUser")
    public Map<String, Object>  getPrizeExchangeRecordByCurrentUser(HttpSession session){
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        List<PrizeExchangeRecord> records = prizeExchangeRecordService.getPrizeExchangeRecordByUserId((int)user.getId());
        Map<String,Object> map = null;
        if (null==records){
            map = ReturnResult.returnFail("","",500);
        }else {
            map = ReturnResult.returnSuccess("",records);
        }
        return map;
    }
}
