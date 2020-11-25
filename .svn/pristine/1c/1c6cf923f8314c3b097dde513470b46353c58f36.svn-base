package cn.bdqn.pointsystem.emtitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
  @TableId(type = IdType.AUTO)
  private Integer id;
  private String name;
  //用户身份证号
  private String identity;
  //用户贯籍
  private String address;
  //剩余积分
  private long point;
  //是否是管理员(0是学生，1是管理员)
  private long isAdmin;
  //所属班级
  private long gradeId;
  //默认为空 验证是否多个地方登录
  private String sessionId;
  @TableField(exist = false)
  private Grade grade;//班级
  private String parentPhone;//父母手机号
  private String phone;//手机号
  private String sex;//性别0女1男
}
