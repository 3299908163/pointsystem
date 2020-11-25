package cn.bdqn.pointsystem.service;

import cn.bdqn.pointsystem.emtitys.IntegralRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author yajun
 * @create 2019/12/30
 */
public interface IntegralRecordService {

    /**
     * @Description 使用原因id查询是否有记录是用当前原因
     * @Param causeid 原因表id
     * @return 返回数据
     * @Date 2020/1/6 8:44
     * @auther zhaomengliang
     */
    IntegralRecord findIntegralRecordCause(Integer CauseId);


    /**
     * 根据用户或班级的id查询增加减去的积分详情
     * @param UserId 用户id
     * @param GradeId 班级id
     * @return
     */
    List<IntegralRecord> findIntegralRecordById(Integer UserId, Integer GradeId);

    /**
     * 根据用户id查询所有原因为兑换奖品的记录
     * 1111为奖品编号
     * @param userId
     * @return
     */
    List<IntegralRecord> findIntegralRecordCauseByUserId(Integer userId);


    /**
     * 积分加减学生记录分页
     * @param page
     * @return
     */
    IPage<IntegralRecord> selectIntegralRecordStudentPage(
            Page<IntegralRecord> page,
            @Param("integralRecord") IntegralRecord integralRecord,
            @Param("userName") String userName);

    /**
     * 积分加减班级记录分页
     * @param page
     * @return
     */
    IPage<IntegralRecord> selectIntegralRecordGradePage(
            Page<IntegralRecord> page,
            @Param("integralRecord") IntegralRecord integralRecord);
    /**
     * 积分加减班级记录分页总数量
     * @return
     */
    int selectIntegralRecordGradePageCount(
            @Param("integralRecord") IntegralRecord integralRecord);
    /**
     * 积分加减学生记录分页总数量
     * @return
     */
    int selectIntegralRecordStudentPageCount(
            @Param("integralRecord") IntegralRecord integralRecord,
            @Param("userName") String userName);


    /**
     * 添加班级扣分记录
     * @param integralRecord
     * @return
     */
    int insertGradeIntegralRecord(IntegralRecord integralRecord);

    /**
     *添加学生扣分记录
     * @param integralRecord
     * @return
     */
    int insertUserIntegralRecord(IntegralRecord integralRecord);


}
