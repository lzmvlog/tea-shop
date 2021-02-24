package top.lzmvlog.shop.customer.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * customer
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24
 */
@Data
@TableName("customer")
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 微信的openid
     */
    private String wxOpenid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
