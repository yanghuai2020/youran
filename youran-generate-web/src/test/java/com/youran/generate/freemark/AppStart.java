package com.youran.generate.freemark;

import com.youran.common.optimistic.EnableOptimisticLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 项目启动入口
 *
 * @author: cbb
 * @date: 2017/9/20
 */
@SpringBootApplication
@EnableOptimisticLock
public class AppStart extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppStart.class);
        app.run(args);
    }

}
