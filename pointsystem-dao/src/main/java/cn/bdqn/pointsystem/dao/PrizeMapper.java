package cn.bdqn.pointsystem.dao;

import cn.bdqn.pointsystem.emtitys.Prize;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname PrizeMapper
 * @Description  奖品
 * @Date 2019/12/30 10:39
 * @Created by zhangshilong
 */
@Mapper
public interface PrizeMapper extends BaseMapper<Prize> {

    /**
     * 商品信息
     * @return
     */
    List<Prize> selectPrize();


    /**
     * 商品分页
     * @param page  分页实体类
     * @param name   商品名
     * @return
     */
    IPage<Prize> selectPagePrize(Page page,@Param("name") String name);


    /**
     * 添加商品信息
     * @param prize 商品实体类
     * @return
     */
    int insertPrize(Prize prize);
    /**
     * 修改商品信息
     * @param prize 商品实体类
     * @return
     */
    int updatePrize(Prize prize);

    /**
     * 修改商品数量
     * @param id 商品编号
     * @return
     */
    int updatePrizeCount(Integer id);

    /**
     * 查询商品详细信息
     * @param id  商品编号
     * @return
     */
    Prize selectPrizeOne(int id);

    /**
     * 查询商品总数量
     * @param name
     * @return
     */
    int prizeCount(@Param("name") String name);

    /**
     * 商品数量-1
     * @param id
     * @return
     */
    int updatePrizeNumber(int id);

    /**
     * 查询数据库查询此商品是否唯一
     * @param name
     * @return
     */
    int selectPrizeOneName(String name);

}
