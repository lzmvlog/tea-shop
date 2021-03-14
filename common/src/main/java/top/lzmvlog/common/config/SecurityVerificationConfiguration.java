//package top.lzmvlog.common.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
///**
// * @author ShaoJie zhang1591313226@163.com
// * @Date 2021年03月14日
// * @Description: 安全验证配置
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityVerificationConfiguration extends WebSecurityConfigurerAdapter {
//
//    /**
//     * 授权 、 验证
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                // 授权地址不需要验证
//                .antMatchers("/auth/auth/token").permitAll();
//    }
//
//}
