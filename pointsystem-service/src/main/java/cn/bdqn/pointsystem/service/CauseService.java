package cn.bdqn.pointsystem.service;

import cn.bdqn.pointsystem.emtitys.Cause;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @Classname CauseService
 * @Description 奖罚表
 * @Date 2019/12/27 17:24
 * @Created by zhaomengliang
 */
public interface CauseService {
    /**
     * @Description 添加奖罚表信息
     * @Param
     * @return
     * @Date 2019/12/27 17:30
     * @auther zhaomengliang
     */
    public int AddCause(Cause cause);

    /**
     * @Description 查询所有信息
     * @Param
     * @return 
     * @Date 2019/12/27 17:43
     * @auther zhaomengliang
     */
    public List<Cause> getCauses();

    /**
     * @Description 删除方法
     * @Param cause 要删除的信息
     * @return  返回int类型进行判断
     * @Date 2019/12/30 8:34
     * @auther zhaomengliang
     */
    public int delCause(int id);

    public IPage<Cause> selecCausePage(Page<Cause> page);

    /**
     * 查找所有原因
     * @return
     */
    List<Cause> getAllCause();
}
