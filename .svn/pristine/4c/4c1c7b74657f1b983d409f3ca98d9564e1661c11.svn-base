package cn.bdqn.pointsystem.emtitys;
/**
 * 积分增减详情表
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegralRecord {

  @TableId(type = IdType.AUTO)
  private Integer id;
  private Integer userId;//用户id
  private Integer gradeId;//班级id
  private Integer point;//加减的分数
  private String  isAdd;//1加，0减
  private Integer causeId;//详情id
  private Integer recorderId;//记录者id
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date creationTime;//记录时间
  @TableField(exist = false)
  private Grade grade;//班级
  @TableField(exist = false)
  private User user;//用户
  @TableField(exist = false)
  private Cause cause;//奖罚原因
  @TableField(exist = false)
  private String recorderName;//操作者姓名
  @TableField(exist = false)
  private Integer integralRecordGradeId;
  @TableField(exist = false)
  private String integralRecordGradeName;

  public String getIntegralRecordGradeName() {
    return integralRecordGradeName;
  }

  public void setIntegralRecordGradeName(String integralRecordGradeName) {
    this.integralRecordGradeName = integralRecordGradeName;
  }

  public Integer getIntegralRecordGradeId() {
    return integralRecordGradeId;
  }

  public void setIntegralRecordGradeId(Integer integralRecordGradeId) {
    this.integralRecordGradeId = integralRecordGradeId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }

  public Integer getPoint() {
    return point;
  }

  public void setPoint(Integer point) {
    this.point = point;
  }

  public String getIsAdd() {
    return isAdd;
  }

  public void setIsAdd(String isAdd) {
    this.isAdd = isAdd;
  }

  public Integer getCauseId() {
    return causeId;
  }

  public void setCauseId(Integer causeId) {
    this.causeId = causeId;
  }

  public Integer getRecorderId() {
    return recorderId;
  }

  public void setRecorderId(Integer recorderId) {
    this.recorderId = recorderId;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }

  public Grade getGrade() {
    return grade;
  }

  public void setGrade(Grade grade) {
    this.grade = grade;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Cause getCause() {
    return cause;
  }

  public void setCause(Cause cause) {
    this.cause = cause;
  }

  public String getRecorderName() {
    return recorderName;
  }

  public void setRecorderName(String recorderName) {
    this.recorderName = recorderName;
  }
}
