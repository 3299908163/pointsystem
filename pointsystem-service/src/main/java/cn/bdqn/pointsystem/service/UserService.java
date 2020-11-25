package cn.bdqn.pointsystem.service;

import cn.bdqn.pointsystem.emtitys.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author yajun
 * @create 2019/12/27
 */
public interface UserService {

    String batchImport(String fileName, MultipartFile file) throws Exception;

    /**
     * 根据用户编号查询sessionId
     * @param userId  用户编号
     * @return
     */
    String getSessionIdById(int userId);

    /**
     * 登陆方法
     * @param identity
     * @param username
     * @return
     */
    User login(String identity,String username);
    /**
     * 查询所有学生
     * @return
     */
    List<User> userList();

    /**
     * 根据身份证查询用户
     * @param identity 身份证号
     * @return
     */
    User findUserByidentity(String identity);

    /**
     * 查询用户总记录数
     * @return
     */
    int count( Integer gradeId,String name);

    /**
     * 用户分页
     * @param page
     * 用户表的班级编号 @param  gradeId
     * 用户表的是否为管理员 @param isAdmin
     * 用户表的姓名 @param name
     * @return
     */
    IPage<User> getPageUser(Page page, @Param("gradeId") Integer gradeId,
                            @Param("name")  String name);

    /**
     * 修改sessionid
     * @return
     */
    Integer updateSession(User user);

    /**
     * 添加用户（含学生和教师）
     * @param user
     * @return
     */
    int addtUser(User user);

    /**
     * 根据班级id查询学生
     * @param gradeId
     * @return
     */
    List<User> selectUsersByGradeId(int gradeId);

    /**
     * 根据id查找用户信息
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 修改用户积分
     * @param point
     * @param id
     * @return
     */
    int changeUserPoint(int point,int id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int changeUser(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int removeUser(int id);
}
