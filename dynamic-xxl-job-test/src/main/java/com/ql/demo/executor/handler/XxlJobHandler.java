package com.ql.demo.executor.handler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Autumn Li
 * @date 2024/09/26
 */
@Component
public class XxlJobHandler {

    private final Logger logger = LoggerFactory.getLogger(XxlJobHandler.class);
    @XxlJob("firstDemoHandle")
    public void firstDemoHandle() {
        String jobParam = XxlJobHelper.getJobParam();
        logger.info("======执行xxl-job任务: {}", jobParam);
    }
}
