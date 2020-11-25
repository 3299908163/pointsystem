package cn.bdqn.pointsystem.service.impl;

import cn.bdqn.pointsystem.dao.GradeMapper;
import cn.bdqn.pointsystem.emtitys.Grade;
import cn.bdqn.pointsystem.service.GradeService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname GradeServiceImpl
 * @Description TODO
 * @Date 2019/12/27 18:27
 * @Created by Administrator
 */

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    public GradeMapper gradeMapper;

    @Override
    public List<Grade> getGradeList(Grade grade) {
        return gradeMapper.getGradeList(grade);
    }

    @Override
    public Grade getGradeId(String gradeName) {
        return gradeMapper.getGradeId(gradeName);
    }

    /**
     * 分页
     *
     * @param page
     * @param gradeName
     * @return
     */
    @Override
    public IPage<Grade> selectPageGrade(Page page, String gradeName) {
        return gradeMapper.selectPageGrade(page,gradeName);
    }

    /**
     * 根据主键进行查询
     *
     * @param id
     */
    @Override
    public Grade getGradeById(int id) {
        return gradeMapper.getGradeById(id);
    }

    /**
     * 根据积分由大到小进行查询
     */
    @Override
    public List<Grade> getGradesOrderByPoint() {
        return gradeMapper.getGradesOrderByPoint();
    }

    @Override
    public int selectGradeCount() {
        return gradeMapper.selectGradeCount();
    }

    @Override
    public List<Grade> getGradeName() {
        return gradeMapper.getGradeName();
    }

    @Override
    public int insertGrade(String name) {
        return gradeMapper.addGrade(name);
    }

    @Override
    public int changeGradePoint(int gradeId, int point) {
        return gradeMapper.updateGradePoint(gradeId,point);
    }

    @Override
    public List<Grade> acquireGradesByGradeName(String gradeName) {
        return gradeMapper.getGradesByGradeName(gradeName);
    }

    @Override
    public int removeGrade(int id) {
        return gradeMapper.delGrade(id);
    }



   /* @Override
    public PageInfo<Grade> getGradeList(Integer pageStart, Integer pageSize, Grade grade) {
        PageHelper.startPage(pageStart,pageSize);
        return new PageInfo<Grade>(gradeMapper.getGradeList(grade));
    }*/
}
