package top.lzmvlog.shop.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import top.lzmvlog.shop.auth.util.JwtUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年03月10日
 * @Description: 构建认证对象
 * <p>
 * 添加用户的权限信息
 */
@Slf4j
@Component
public class TokenProvider {

    /**
     * 权限密钥
     */
    private static final String AUTHORITIES_KEY = "auth";

    /**
     * 用户信息
     */
    private static final String ID = "id";

    /**
     * 签名密钥
     */
    @Value("${auth.signingKey}")
    private String signingKey;

    @Autowired
    JwtUtil jwtUtil;

    /**
     * 获取 Spring Context 的 SecurityContext 对象
     * 用于获取用户的身份验证
     *
     * @param token jwt 生成的 token 信息
     * @return authentication 认证对象
     */
    public Authentication getAuthentication(String token) {

        // parser() 解析token
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
        Object claim = claims.get(AUTHORITIES_KEY);

        // 权限
        String auth = "";
        if (Objects.nonNull(claim)) {
            auth = claim.toString();
        }

        // 权限集合
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(auth.split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // 创建 Spring Security 的 user 对象
        User principal = new User((String) claims.get(ID), "", authorities);

        // 创建返回 Authentication 对象
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

}
