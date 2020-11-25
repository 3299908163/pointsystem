package cn.bdqn.pointsystem.service;

import cn.bdqn.pointsystem.emtitys.Prize;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @Classname PrizeService
 * @Description 奖品
 * @Date 2019/12/30 10:49
 * @Created by lenovo
 */

public interface PrizeService{

    /**
     * 奖品查询
     * @return
     */
    List<Prize> selectPrize();

    /**
     * 添加奖品
     * @param prize
     * @return
     */
    boolean insertPrize(Prize prize);

    /**
     * 修改商品
     * @param prize
     * @return
     */
    boolean updatePrize(Prize prize);
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
    int prizeCount(String name);

    /**
     * 商品数量-1
     * @param id
     * @return
     */
    int updatePrizeNumber(int id);

    /**
     * 商品分页
     * @param page  分页实体类
     * @param name   商品名
     * @return
     */
    IPage<Prize> selectPagePrize(Page page,@Param("name") String name);

    /**
     * 查询数据库查询此商品是否唯一
     * @param name
     * @return
     */
    int selectPrizeOneName(String name);

}
