package cn.bdqn.pointsystem.dao;

import cn.bdqn.pointsystem.emtitys.Grade;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname GradeMapper
 * @Description TODO
 * @Date 2019/12/27 18:23
 * @Created by wxz
 */
@Component
@Mapper
public interface GradeMapper {
    public List<Grade> getGradeList(Grade grade);

    IPage<Grade> selectPageGrade(Page page, @Param("name") String  gradeName);
    
    /**
     * @Description 使用班级名称查询
     * @Param      班级名称
     * @return 
     * @Date 2020/1/3 10:48
     * @auther zhaomengliang
     */
    @Select("select * from grade where 1=1 and name=#{gradeName}")
    Grade getGradeId(String gradeName);

    /**
     * 根据主键进行查询
     */
    Grade getGradeById(int id);

    /**
     * 根据积分由大到小进行查询
     */
    List<Grade> getGradesOrderByPoint();

    /**
     * 查询班级个数
     * @return
     */
    int selectGradeCount();

    /**
     * 获得班级名称
     * @return
     */
    List<Grade> getGradeName();

    /**
     * 添加班级
     * @param name
     * @return
     */
    int addGrade(String name);

    /**
     * 根据班级编号，积分，改动班级积分
     * @param gradeId
     * @param point
     * @return
     */
    int updateGradePoint(@Param("gradeId") int gradeId,@Param("point") int point);

    /**
     * 根据班级名称查询班级信息
     * @param gradeName
     * @return
     */
    List<Grade> getGradesByGradeName(String gradeName);

    /**
     * 根据id删除班级
     * @param id
     * @return
     */
    int delGrade(int id);
}
