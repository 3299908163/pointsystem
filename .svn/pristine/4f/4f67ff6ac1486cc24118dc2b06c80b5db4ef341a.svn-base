package cn.bdqn.pointsystem.service.impl;

import cn.bdqn.pointsystem.dao.CauseMapper;
import cn.bdqn.pointsystem.emtitys.Cause;
import cn.bdqn.pointsystem.service.CauseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname CauseService
 * @Description 奖罚表
 * @Date 2019/12/27 17:25
 * @Created by zhaomengliang
 */
@Service
public class CauseServiceImpl implements CauseService {

    @Autowired
    private CauseMapper causeMapper;
    @Override
    public int AddCause(Cause cause) {
        return causeMapper.insert(cause);
    }

    @Override
    public List<Cause> getAllCause() {
        return causeMapper.selectAllCause();
    }

    @Override
    public List<Cause> getCauses() {
        return causeMapper.selectList(null);
    }

    @Override
    public int delCause(int id) {
        QueryWrapper<Cause> wrapper= new QueryWrapper<>();
        wrapper.eq("id",id);
        return causeMapper.delete(wrapper);
    }

    @Override
    public IPage<Cause> selecCausePage(Page<Cause> page) {
        QueryWrapper<Cause> queryWrapper =  new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");

        IPage<Cause> iPage = causeMapper.selectPage(page,queryWrapper);
        return iPage;
    }

}
