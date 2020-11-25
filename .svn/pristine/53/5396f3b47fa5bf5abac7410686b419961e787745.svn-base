package cn.bdqn.controller;

import cn.bdqn.pointsystem.emtitys.Prize;
import cn.bdqn.pointsystem.service.PrizeService;
import cn.bdqn.pointsystem.utlis.FileUpload;
import cn.bdqn.pointsystem.utlis.FileUtlis;
import cn.bdqn.pointsystem.utlis.ReturnResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @Classname PrizeController
 * @Description 奖品
 * @Date 2019/12/30 10:36
 * @Created by zhangshilong
 */
@Controller
@RequestMapping("/student/prize")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    /**
     * 查询全部奖品
     * @return
     * @作者 yajun
     */
    @ResponseBody
    @GetMapping("/getPrizeList")
    public Map<String,Object> getPrizeList(){
        Map<String,Object> map = null;
        try{
            List<Prize> prizes = prizeService.selectPrize();
            map = ReturnResult.returnSuccess("",prizes);
        }catch(Exception e){
            map = ReturnResult.returnFail("","",500);
        }
        return map;
    }

    /**
     * 根据奖品id获取奖品对象
     * ReturnResult.returnFail("","",500)查询异常
     * ReturnResult.returnFail("","",505);奖品数量为空
     * ReturnResult.returnSuccess("",prize);查询成功返回
     * @param prizeId 奖品id
     * @return
     */
    @ResponseBody
    @GetMapping("/getPrizeById")
    public Map<String,Object> getPrizeById(@RequestParam Integer prizeId){
        Map<String,Object> map = null;
        Prize prize = prizeService.selectPrizeOne(prizeId);
        if (null==prize){
            map = ReturnResult.returnFail("","",500);
        }else {
            if (prize.getNumber()==0){
                map = ReturnResult.returnFail("","",505);
            }else {
                map = ReturnResult.returnSuccess("",prize);
            }
        }
        return map;
    }



}
