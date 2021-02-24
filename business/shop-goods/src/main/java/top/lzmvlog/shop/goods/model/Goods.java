package top.lzmvlog.shop.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * goods
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24
 */
@Data
@TableName("goods")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String goodsImg;

    /**
     * 商品价格
     */
    private BigDecimal goodsMoney;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
