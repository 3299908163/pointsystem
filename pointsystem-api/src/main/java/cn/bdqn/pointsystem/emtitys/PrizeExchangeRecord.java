package cn.bdqn.pointsystem.emtitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 奖品兑换记录表
 */

@AllArgsConstructor
@NoArgsConstructor
public class PrizeExchangeRecord {

  @TableId(type = IdType.AUTO)
  private Integer id;//积分编号
  private Integer userId;//用户id
  private Integer prizeId;//积分id
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date creationTime;//兑换时间
  private String isReceive;//0未领取1已领取
  private String qrCode;//奖品二维码
  @TableField(exist = false)
  private User user;//用户表
  @TableField(exist = false)
  private Prize prize;


  public String getIsReceive() {
    return isReceive;
  }

  public void setIsReceive(String isReceive) {
    this.isReceive = isReceive;
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

  public Integer getPrizeId() {
    return prizeId;
  }

  public void setPrizeId(Integer prizeId) {
    this.prizeId = prizeId;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }
//
//  public String getIsReceive() {
//    return Integer.toString(isReceive)=="0"?"未领取":"已领取";
//  }
//
//  public void setIsReceive(Integer isReceive) {
//    this.isReceive = isReceive;
//  }

  public String getQrCode() {
    return qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Prize getPrize() {
    return prize;
  }

  public void setPrize(Prize prize) {
    this.prize = prize;
  }
}
