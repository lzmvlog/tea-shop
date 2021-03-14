package top.lzmvlog.shop.auth.util;

import cn.hutool.core.date.DateTime;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年03月10日
 * @Description: 生成解析 token 的工具类
 */
@Component
@Slf4j
public class JwtUtil {

    /**
     * 签名密钥
     */
    @Value("${auth.signingKey}")
    private String signingKey;

    /**
     * 创建生成 token
     * <p>
     * setClaims() 与 setSubject() 冲突所以不设置主体信息
     *
     * @param account 用户id
     * @return String 生成的 token
     */
    public String createToken(String account) {
        return Jwts.builder()
                // 设置唯一的 id
                .setId(account)
                // 设置 token 签发的时间
                .setIssuedAt(new DateTime())
                // 设置签名 使用HS256算法，并设置SecretKey(字符串)  签名算法和秘钥
                .signWith(SignatureAlgorithm.HS256, signingKey)
                // 以下内容构建JWT并将其序列化为紧凑的，URL安全的字符串
                .compact();
    }

}
