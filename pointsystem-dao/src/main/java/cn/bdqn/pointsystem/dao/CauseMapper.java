package cn.bdqn.pointsystem.dao;

import cn.bdqn.pointsystem.emtitys.Cause;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname CauseMapper
 * @Description 奖罚表
 * @Date 2019/12/27 17:23
 * @Created by zhaomengliang
 */
@Mapper
public interface CauseMapper extends BaseMapper<Cause> {

    /**
     * 查找所有原因
     * @return
     */
    List<Cause> selectAllCause();
}
