package top.lzmvlog.shop.auth.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.common.key.RedisKey;
import top.lzmvlog.common.model.customer.Customer;
import top.lzmvlog.common.model.customer.vo.Login;
import top.lzmvlog.common.model.customer.vo.TokenVo;
import top.lzmvlog.common.result.R;
import top.lzmvlog.shop.auth.feign.CustomerFeignService;
import top.lzmvlog.shop.auth.util.JwtUtil;

import java.text.MessageFormat;
import java.time.LocalDateTime;
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
    @Value("${auth.timeout}")
    private Long tokenTimeOut;

    /**
     * redisTemplate
     */
    @Autowired
    public StringRedisTemplate redisTemplate;

    @Autowired
    public CustomerFeignService customerFeignService;

    @Autowired
    public JwtUtil jwtUtil;

    /**
     * 签发 token
     *
     * @param login 用户信息
     * @return
     */
    @PostMapping("/token")
    public R getToken(@RequestBody Login login) {
        // 读取用户信息
        Customer customer = customerFeignService.selectCustomer(login);
        TokenVo tokenVo = new TokenVo();
        tokenVo.setAccount(customer.getAccount());
        String token = jwtUtil.createToken(customer.getAccount());
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = MessageFormat.format(RedisKey.ACCESSTOKEN, token);
        String accessToken = valueOperations.get(key);
        if (Objects.nonNull(accessToken)) {
            return R.ok(JSON.parseObject(accessToken, TokenVo.class));
        }
        tokenVo.setToken(token);
        tokenVo.setCurrentTime(LocalDateTime.now());
        tokenVo.setExpiration(LocalDateTime.now().minusSeconds(tokenTimeOut));

        // 将获取的 token 存放在 redis 中
        valueOperations.set(key, tokenVo.toString(), tokenTimeOut, TimeUnit.SECONDS);
        return R.ok(tokenVo);
    }

}