package cn.bdqn.pointsystem.service;

import cn.bdqn.pointsystem.emtitys.PrizeExchangeRecord;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yajun
 * @create 2019/12/31
 */
public interface PrizeExchangeRecordService {

    /**
     * 兑换奖品
     * @param
     * @return
     */
    boolean exchangePrizes(Integer userId,Integer prizeId,Integer point)throws Exception;

    /**
     * 根据userid获取兑奖记录
     * @param userId
     * @return
     */
    List<PrizeExchangeRecord> getPrizeExchangeRecordByUserId(Integer userId);

    /**
     * 根据奖品兑换记录编号查询信息
     * @param id
     * @return
     */
    public PrizeExchangeRecord getPrizeExchangeRecordById(int id);

    /**
     * 根据奖品兑换记录编号修改领取
     * @param id
     * @param isReceive
     * @return
     */
    public boolean updatePrizeExchangeRecordReceive(@Param("id") Integer id, @Param("isReceive") String isReceive);

    /**
     * 分页查询信息
     * @param page
     * @return
     */
    public IPage<PrizeExchangeRecord> selectPrizeExchangeRecordPage(Page<PrizeExchangeRecord> page,String name);

    /**
     * 返回分页总记录数
     * @param name
     * @return
     */
    public int getPrizeCount(String name);
}
