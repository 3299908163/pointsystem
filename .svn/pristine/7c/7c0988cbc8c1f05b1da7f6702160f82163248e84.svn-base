package cn.bdqn.pointsystem.dao;

import cn.bdqn.pointsystem.emtitys.PrizeExchangeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yajun
 * @create 2019/12/31
 */
@Mapper
public interface PrizeExchangeRecordMapper extends BaseMapper<PrizeExchangeRecord> {


    /**
     * 分页查询信息
     * @param page
     * @return
     */
    public IPage<PrizeExchangeRecord> selectPrizeExchangeRecordPage(Page<PrizeExchangeRecord> page,@Param("name") String name);


    /**
     * 返回分页总记录数
     * @param name
     * @return
     */
    public int getPrizeCount(@Param("name") String name);

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
    public int updatePrizeExchangeRecordReceive(@Param("id") Integer id,@Param("isReceive") String isReceive);
}
