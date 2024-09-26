package com.ql.demo.executor.config;


import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * xxl-job执行端配置类
 *
 * @author Autumn Li
 * @date 2024/9/25
 */
@Configuration
public class XxlJobConfig {
    private final Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Resource
    private XxlJobParamConfig xxlJobParamConfig;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobParamConfig.getAdminAddresses());
        xxlJobSpringExecutor.setAppname(xxlJobParamConfig.getAppName());
        xxlJobSpringExecutor.setAddress(xxlJobParamConfig.getAddress());
        xxlJobSpringExecutor.setIp(xxlJobParamConfig.getIp());
        xxlJobSpringExecutor.setPort(xxlJobParamConfig.getPort());
        xxlJobSpringExecutor.setAccessToken(xxlJobParamConfig.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlJobParamConfig.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobParamConfig.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
