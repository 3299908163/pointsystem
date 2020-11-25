package cn.bdqn.pointsystem.service;

import cn.bdqn.pointsystem.emtitys.Grade;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname GradeService
 * @Description TODO
 * @Date 2019/12/27 18:26
 * @Created by wxz
 */
@Service
public interface GradeService {
public List<Grade> getGradeList(Grade grade);

    /**
     * @Description 使用班级名称查询班级id
     * @Param 班级名称
     * @return 返回班级对象
     * @Date 2020/1/3 10:55
     * @auther zhaomengliang
     */
    Grade getGradeId(String gradeName);
    /**
     * 分页
     * @param page
     * @param gradeName
     * @return
     */
    IPage<Grade> selectPageGrade(Page page, String gradeName);
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
    int insertGrade(String name);

    /**
     *
     * @param gradeId
     * @param point
     * @return
     */
    int changeGradePoint(int gradeId,int point);

    /**
     * 根据班级名获取班级信息
     * @param gradeName 班级名称
     * @return
     */
    List<Grade> acquireGradesByGradeName(String gradeName);

    /**
     * 根据id删除班级
     * @param id
     * @return
     */
    int removeGrade(int id);
}
