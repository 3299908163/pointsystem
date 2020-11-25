package cn.bdqn.pointsystem.service.impl;

import cn.bdqn.pointsystem.dao.*;
import cn.bdqn.pointsystem.emtitys.Cause;
import cn.bdqn.pointsystem.emtitys.IntegralRecord;
import cn.bdqn.pointsystem.emtitys.PrizeExchangeRecord;
import cn.bdqn.pointsystem.service.PrizeExchangeRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author yajun
 * @create 2019/12/31
 */
@Service
public class PrizeExchangeRecordServiceImpl implements PrizeExchangeRecordService {
    //奖品兑换记录数据层
    @Autowired
    private PrizeExchangeRecordMapper exchangeRecordMapper;
    //奖品数据层
    @Autowired
    private PrizeMapper prizeMapper;
    //用户数据层
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IntegralRecordMapper integralRecordMapper;
    @Autowired
    private CauseMapper causeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized boolean exchangePrizes(Integer userId, Integer prizeId, Integer point) throws Exception {
        Integer result = 0;
        //1.添加一条兑换记录
        PrizeExchangeRecord prizeExchangeRecord = new PrizeExchangeRecord();
        prizeExchangeRecord.setUserId(userId);
        prizeExchangeRecord.setPrizeId(prizeId);
        prizeExchangeRecord.setQrCode(UUID.randomUUID().toString().replace("-",""));
        result = exchangeRecordMapper.insert(prizeExchangeRecord);
        if (result == 0) throw new Exception("添加兑换奖品记录异常");
        //2.减去兑换的奖品数量 -1
        result = prizeMapper.updatePrizeNumber(prizeId);
        if (result == 0) throw new Exception("修改商品数量异常");
        //3.修改学生积分
        result = userMapper.updateUserPoint(point, userId);
        if (result == 0) throw new Exception("修改学生积分异常");
        //4.添加一条积分加减记录
        IntegralRecord record = new IntegralRecord();
        record.setUserId(userId);
        record.setIsAdd("0");
        record.setPoint(point);
        record.setRecorderId(userId);
        QueryWrapper<Cause> wrapperCause = new QueryWrapper<>();
        // 先查询出"兑换奖品"的编号
        wrapperCause.eq("cause","兑换奖品");
        Cause cause = causeMapper.selectOne(wrapperCause);
        record.setCauseId((int)cause.getId());
        record.setCreationTime(new Date());
        result = integralRecordMapper.insert(record);
        if (result == 0) throw new Exception("添加积分加减记录异常");
        return true;
    }

    public List<PrizeExchangeRecord> getPrizeExchangeRecordByUserId(Integer userId){
        QueryWrapper<PrizeExchangeRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).orderByDesc("creation_time");
        return  exchangeRecordMapper.selectList(wrapper);
    }

    /**
     * 根据奖品兑换记录编号查询信息
     *
     * @param id
     * @return
     */
    @Override
    public PrizeExchangeRecord getPrizeExchangeRecordById(int id) {
        return exchangeRecordMapper.getPrizeExchangeRecordById(id);
    }

    /**
     * 根据奖品兑换记录编号修改领取
     *
     * @param id
     * @param isReceive
     * @return
     */
    @Override
    @Transactional
    public boolean updatePrizeExchangeRecordReceive(Integer id, String isReceive) {
        return exchangeRecordMapper.updatePrizeExchangeRecordReceive(id,isReceive)>0;
    }

    /**
     * 分页查询信息
     *
     * @param page
     * @param name
     * @return
     */
    @Override
    public IPage<PrizeExchangeRecord> selectPrizeExchangeRecordPage(Page<PrizeExchangeRecord> page, String name) {
        IPage<PrizeExchangeRecord> iPage = exchangeRecordMapper.selectPrizeExchangeRecordPage(page
                , name);
        for(PrizeExchangeRecord prizeExchangeRecord :iPage.getRecords()){//遍历商品兑换记录
            if("0".equals(prizeExchangeRecord.getIsReceive())){//如果是否领取等0就是未领取
                prizeExchangeRecord.setIsReceive("未领取");
            }else{//否则已领取
                prizeExchangeRecord.setIsReceive("已领取");
            }
        }
        return iPage;
    }

    /**
     * 返回分页总记录数
     *
     * @param name
     * @return
     */
    @Override
    public int getPrizeCount(String name) {
        return exchangeRecordMapper.getPrizeCount(name);
    }

}
