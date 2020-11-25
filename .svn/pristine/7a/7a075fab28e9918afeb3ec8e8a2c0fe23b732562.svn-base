package cn.bdqn.pointsystem.service.impl;

import cn.bdqn.pointsystem.dao.PrizeMapper;
import cn.bdqn.pointsystem.emtitys.Prize;
import cn.bdqn.pointsystem.service.PrizeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname PirzeServiceImpl
 * @Description 奖品
 * @Date 2019/12/30 10:51
 * @Created by lenovo
 */
@Service
public class PirzeServiceImpl implements PrizeService {

    @Autowired
    PrizeMapper prizemapper;

    /**
     * 添加奖品
     * @param prize
     * @return
     */
    @Override
    public boolean insertPrize(Prize prize) {
        return prizemapper.insert(prize)==1;
    }

    /**
     * 更新商品
     * @param prize
     * @return
     */
    @Override
    public boolean updatePrize(Prize prize) {
        return prizemapper.updatePrize(prize)>0;
    }

    /**
     * 查询单个商品
     * @param id
     * @return
     */
    @Override
    public Prize selectPrizeOne(int id) {
        QueryWrapper<Prize> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        return prizemapper.selectOne(wrapper);
    }

    /**
     * 查询商品总数量
     *
     * @param name
     * @return
     */
    @Override
    public int prizeCount(String name) {
        return prizemapper.prizeCount(name);
    }

    /**
     * 商品数量-1
     *
     * @param id
     * @return
     */
    @Override
    public int updatePrizeNumber(int id) {
        return prizemapper.updatePrizeNumber(id);
    }


    /**
     * 奖品查询
     *
     * @return
     */
    @Override
    public List<Prize> selectPrize() {
        return prizemapper.selectPrize();
    }

    /**
     * 商品分页
     *
     * @param page 分页实体类
     * @param name 商品名
     * @return
     */
    @Override
    public IPage<Prize> selectPagePrize(Page page, String name) {
        return prizemapper.selectPagePrize(page,name);

    }

    @Override
    public int selectPrizeOneName(String name) {
        return prizemapper.selectPrizeOneName(name);
    }
}
