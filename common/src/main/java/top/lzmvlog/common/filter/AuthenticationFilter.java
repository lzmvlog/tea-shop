package top.lzmvlog.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.lzmvlog.common.key.RedisKey;
import top.lzmvlog.common.utils.StringUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年03月10日
 * @Description: jwt 权限拦截器
 */
@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    /**
     * redisTemplate
     */
    @Autowired
    public StringRedisTemplate redisTemplate;

    /**
     * 与{@code doFilter}的合同相同，但保证在单个请求线程中每个请求仅被调用一次。
     * 有关详细信息，请参见{@link #shouldNotFilterAsyncDispatch（）}。
     * <p>提供HttpServletRequest和HttpServletResponse参数，而不是默认的ServletRequest和ServletResponse参数。
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!"/auth/token".equals(httpServletRequest.getRequestURI())) {
            String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (token == null) {
                throw new RuntimeException("请先进行认证后访问");
            }
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String key = MessageFormat.format(RedisKey.ACCESSTOKEN, token);
            String accessToken = valueOperations.get(key);
            if (StringUtil.isEmpty(accessToken)) {
                throw new RuntimeException("accessToken -- 不存在");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
