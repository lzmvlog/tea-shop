package top.lzmvlog.shop.auth.vo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年03月10日
 * @Description: 返回的 token 信息
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class TokenVo {

    /**
     * 生成的 token
     */
    private String token;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiration;

    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime currentTime;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
