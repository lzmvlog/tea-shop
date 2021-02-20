package top.lzmvlog.shop.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年02月20日 18:01
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

}
