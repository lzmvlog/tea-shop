package top.lzmvlog.shop.customer.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年03月14日 19:49
 * @Description: 登录
 */
@Data
public class Login {

    /**
     * 账号
     */
    @NotNull(message = "账号不能为空，使用手机或邮箱")
    private String account;

    /**
     * 用户密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

}
