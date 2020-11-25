package cn.bdqn.pointsystem.dao;

import cn.bdqn.pointsystem.emtitys.IntegralRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yajun
 * @create 2019/12/30
 */
@Mapper
public interface IntegralRecordMapper extends BaseMapper<IntegralRecord> {

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
    int addGradeIntegralRecord(IntegralRecord integralRecord);

    /**
     *添加学生扣分记录
     * @param integralRecord
     * @return
     */
    int addUserIntegralRecord(IntegralRecord integralRecord);

    /**
     * 积分加减记录分页
     * @param page
     * @return
     */
    IPage<IntegralRecord> selectIntegralRecordPageTwo( Page<IntegralRecord> page,@Param("integralRecord") IntegralRecord integralRecord);
}
