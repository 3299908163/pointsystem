package cn.bdqn.pointsystem.service.impl;

import cn.bdqn.pointsystem.dao.CauseMapper;
import cn.bdqn.pointsystem.dao.IntegralRecordMapper;
import cn.bdqn.pointsystem.dao.UserMapper;
import cn.bdqn.pointsystem.emtitys.Cause;
import cn.bdqn.pointsystem.emtitys.IntegralRecord;
import cn.bdqn.pointsystem.emtitys.User;
import cn.bdqn.pointsystem.service.IntegralRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yajun
 * @create 2019/12/30
 */
@Service
public class IntegralRecordServiceImpl implements IntegralRecordService {
    @Autowired
    private IntegralRecordMapper integralRecordMapper;
    @Autowired
    private CauseMapper causeMapper;

    @Override
    public List<IntegralRecord> findIntegralRecordCauseByUserId(Integer userId) {
        QueryWrapper<IntegralRecord> wrapper = new QueryWrapper<>();
        QueryWrapper<Cause> wrapperCause = new QueryWrapper<>();
        // 先查询出"兑换奖品"的编号
        wrapperCause.eq("cause","兑换奖品");
        Cause cause = causeMapper.selectOne(wrapperCause);
        wrapper.eq("user_id",userId).eq("cause_id", cause.getId());
        return  integralRecordMapper.selectList(wrapper);
    }

    /**
     * 积分加减学生记录分页
     *
     * @param page
     * @param integralRecord
     * @param userName
     * @return
     */
    @Override
    public IPage<IntegralRecord> selectIntegralRecordStudentPage(Page<IntegralRecord> page,
                                                                 IntegralRecord integralRecord, String userName) {
        return integralRecordMapper.selectIntegralRecordStudentPage(page,integralRecord,userName);
    }

    /**
     * 积分加减班级记录分页
     *
     * @param page
     * @param integralRecord
     * @return
     */
    @Override
    public IPage<IntegralRecord> selectIntegralRecordGradePage(Page<IntegralRecord> page,
                                                               IntegralRecord integralRecord) {
        return integralRecordMapper.selectIntegralRecordGradePage(page,integralRecord);
    }

    /**
     * 积分加减班级记录分页总数量
     * @param integralRecord
     * @return
     */
    @Override
    public int selectIntegralRecordGradePageCount(  IntegralRecord integralRecord) {
        return integralRecordMapper.selectIntegralRecordGradePageCount(integralRecord);
    }
    public int insertGradeIntegralRecord(IntegralRecord integralRecord) {
        return integralRecordMapper.addGradeIntegralRecord(integralRecord);
    }

    @Override
    public int insertUserIntegralRecord(IntegralRecord integralRecord) {
        return integralRecordMapper.addUserIntegralRecord(integralRecord);
    }

//    @Override
//    public int insertIntegralRecord(IntegralRecord integralRecord) {
//        return integralRecordMapper.addIntegralRecord(integralRecord);
//    }

    /**
     * 积分加减学生记录分页总数量
     *
     * @param integralRecord
     * @param userName
     * @return
     */
    @Override
    public int selectIntegralRecordStudentPageCount(IntegralRecord integralRecord, String userName) {
        return integralRecordMapper.selectIntegralRecordStudentPageCount(integralRecord,userName);
    }


//    @Override
//    public int insertIntegralRecord(IntegralRecord integralRecord) {
//        return integralRecordMapper.addIntegralRecord(integralRecord);
//    }


    @Override
    public IntegralRecord findIntegralRecordCause(Integer CauseId) {
        QueryWrapper<IntegralRecord> wrapper=new QueryWrapper<>();
        wrapper.eq("cause_id",CauseId);
        return integralRecordMapper.selectOne(wrapper);
    }

    @Override
    public List<IntegralRecord> findIntegralRecordById(Integer UserId,Integer GradeId) {
        QueryWrapper<IntegralRecord> wrapper = new QueryWrapper<>();
        if (UserId>0 || UserId!=null){
            wrapper.eq("user_id",UserId);
        }else {
            wrapper.eq("grade_id",GradeId);
        }
        return integralRecordMapper.selectList(wrapper);
    }

}
