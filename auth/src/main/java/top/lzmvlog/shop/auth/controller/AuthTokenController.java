package top.lzmvlog.shop.auth.controller;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.common.key.RedisKey;
import top.lzmvlog.common.result.R;
import top.lzmvlog.shop.auth.vo.TokenVo;
import top.lzmvlog.shop.customer.model.Customer;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年03月10日
 * @Description: 资源权限 管理
 */
@RestController
@RequestMapping("auth")
public class AuthTokenController {

    /**
     * redis 的 token 过期时间
     */
    @Value("${spring.redis.token-timeout}")
    private String tokenTimeOut;

    /**
     * redisTemplate
     */
    @Autowired
    public StringRedisTemplate redisTemplate;

    /**
     * 签发 token
     *
     * @param customer 用户信息
     * @return
     */
    @PostMapping("/token")
    public R getToken(Customer customer) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = MessageFormat.format(RedisKey.ACCESSTOKEN, customer.getAccount());
        String accessToken = valueOperations.get(key);
        if (Objects.nonNull(accessToken)) {
            return new R(HttpStatus.HTTP_OK, JSON.parseObject(accessToken, TokenVo.class));
        }
        // 读取用户信息
//        TokenVo tokenVo = userService.selectUser(use);

        TokenVo tokenVo = new TokenVo();
        // 将获取的 token 存放在 redis 中
        valueOperations.set(key, tokenVo.toString(), Long.valueOf(tokenTimeOut), TimeUnit.SECONDS);
        return new R(HttpStatus.HTTP_OK, tokenVo);
    }

}