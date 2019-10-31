package cn.dpy.dual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/9/17 10:50
 * @Version: 1.0
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = "cn.dpy.dual.*")
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
