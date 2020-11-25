package cn.bdqn.pointsystem.dao;

import cn.bdqn.pointsystem.emtitys.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author yajun
 * @create 2019/12/27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> Listuser();

    /**
     * 查询用户总记录数
     * @return
     */
    int getCount(@Param("gradeId") Integer gradeId,@Param("name")  String name);


    /**
     * 用户分页
     * @param page
     * 用户表的班级编号 @param  gradeId
     * 用户表的是否为管理员 @param isAdmin
     * 用户表的姓名 @param name
     * @return
     */
    IPage<User>  selectPageUser(Page page, @Param("gradeId") Integer gradeId,@Param("name")  String name);
    /**
     * 修改学生积分
     * @param id
     * @param point
     * @return
     */
    Integer updateUserPoint(@Param("point") Integer point,@Param("id")Integer id);

    /**
     * 添加用户（含学生和教师）
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据班级编号查询学生
     * @param gradeId
     * @return
     */
    List<User> findUsersByGradeId(int gradeId);

    /**
     * 根据id查找用户信息
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 根据用户编号查询sessionId
     * @param id  用户编号
     * @return
     */
    String getSessionIdById(int id);

    /**
     * 修改学生信息
     * @param user
     * @return
     */
    int updateUser(User user);

    @Update("update user set name= #{name},identity=#{identity},address=#{address}, grade_id=#{gradeId},phone=#{phone},parent_phone=#{parentPhone},sex=#{sex} where id =#{id} ")
    void updateUserByName(User user1);

    @Select("select * from user where 1=1 and identity=#{identity}")
    User selectUserByIdentity(String identity);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int delUser(int id);
}
