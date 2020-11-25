package cn.bdqn.pointsystem.emtitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Prize
 * @Description 奖品
 * @Date 2019/12/30 10:40
 * @Created by zhangshilong
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Prize {
    private Integer id;//商品编号
    private String name;//商品名
    private int point;//商品积分
    private String imgUrl;//商品图片路径
    private int number;//商品数量
}
