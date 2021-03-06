package top.lzmvlog.shop.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年03月06日 17:32
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesApplication.class, args);
    }

}
