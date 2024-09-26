package com.ql.demo.executor.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.ql.demo.executor.config.XxlJobParamConfig;
import com.ql.demo.executor.dto.XxlJobGroupDataDto;
import com.ql.demo.executor.dto.XxlJobGroupDto;
import com.ql.demo.executor.dto.XxlJobTaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.net.HttpCookie;
import java.util.*;

/**
 * xxl-job http工具
 *
 * @author Autumn Li
 * @date 2024/09/25
 */
@Component
public class XxlHttpUtil {

    private final Logger logger = LoggerFactory.getLogger(XxlHttpUtil.class);

    @Resource
    private XxlJobParamConfig xxlLoginParam;

    // 登陆地址
    private final static String XXL_ADMIN_LOGIN_ADDRESS = "/login";
    // 添加执行任务地址
    private final static String XXL_ADD_TASK_ADDRESS = "/jobinfo/add";
    // 搜索执行端信息地址
    private final static String XXL_ADD_EXEC_ADDRESS = "/jobgroup/pageList";


    /**
     * 添加定时任务
     *
     * @param xxlJobTaskDto xxl-job任务传输对象
     */
    public void addXxlJobTask(XxlJobTaskDto xxlJobTaskDto) {

        Map<String, Object> paramMap = new HashMap<>(16);
        /*
         * jobGroup                 执行端id
         * jobDesc                  任务描述
         * schedule_conf_CRON       cron表达式
         * glueType                 任务模式，可选值参考 com.xxl.job.core.glue.GlueTypeEnum
         * executorHandler          执行端handler名字
         * executorBlockStrategy    任务阻塞策略，可选值参考 com.xxl.job.core.enums.ExecutorBlockStrategyEnum
         * executorTimeout          任务超时时间，单位秒，大于零时生效
         * executorFailRetryCount   失败重试次数
         * author                   负责人
         * triggerStatus            调度状态：0-停止，1-运行
         */
        paramMap.put("jobDesc", xxlJobTaskDto.getDesc());
        paramMap.put("author", xxlJobTaskDto.getAuthor());
        paramMap.put("scheduleType", "CRON");
        paramMap.put("scheduleConf", xxlJobTaskDto.getCron());
        paramMap.put("cronGen_display", xxlJobTaskDto.getCron());
        paramMap.put("schedule_conf_CRON", xxlJobTaskDto.getCron());
        paramMap.put("glueType", "BEAN");
        paramMap.put("executorHandler", xxlJobTaskDto.getExecutorHandler());
        paramMap.put("executorParam", xxlJobTaskDto.getExecutorParam());
        paramMap.put("executorRouteStrategy", "FIRST");
        paramMap.put("misfireStrategy", "DO_NOTHING");
        paramMap.put("executorBlockStrategy", "SERIAL_EXECUTION");
        paramMap.put("executorTimeout", xxlJobTaskDto.getExecutorTimeout());
        paramMap.put("executorFailRetryCount", xxlJobTaskDto.getExecutorFailRetryCount());
        paramMap.put("glueRemark", xxlJobTaskDto.getDesc());
        paramMap.put("triggerStatus", 1);

        try {
            // 校验参数
            checkTaskParam(xxlJobTaskDto);
            // 获取执行端id
            paramMap.put("jobGroup", getJobGroupId());
            HttpResponse response = HttpRequest.post(xxlLoginParam.getAdminAddresses() + XXL_ADD_TASK_ADDRESS)
                    .form(paramMap)
                    .cookie(getCookie())
                    .execute();
            if (response.isOk()) {
                logger.info("xxl-job添加定时任务成功: {}", response.body());
                return;
            }
            logger.info("xxl-job添加定时任务失败: {}", response.body());
        } catch (Exception e) {
            logger.info("xxl-job添加定时任务异常: {}", e.getMessage());
        }
    }

    /**
     * 返回执行端id
     *
     * @return 执行端id
     */
    private int getJobGroupId() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appname", xxlLoginParam.getAppName());
        paramMap.put("start", "0");
        paramMap.put("length", "100");

        try (HttpResponse response = HttpRequest.post(xxlLoginParam.getAdminAddresses() + XXL_ADD_EXEC_ADDRESS)
                .form(paramMap)
                .cookie(getCookie())
                .execute()) {
            if (response.isOk()) {
                XxlJobGroupDto xxlJobGroupDto = JSON.parseObject(response.body(), XxlJobGroupDto.class);
                if (Objects.isNull(xxlJobGroupDto)) {
                    throw new Exception("执行端id结果异常");
                }
                List<XxlJobGroupDataDto> dataEntity = xxlJobGroupDto.getData();
                if (CollectionUtils.isEmpty(dataEntity)) {
                    throw new Exception("执行端id结果空");
                }
                return dataEntity.get(0).getId();
            }
        } catch (Exception e) {
            logger.error("xxl-job查询异常: {}", e.getMessage());
        }
        throw new Exception("xxl获取执行端id失败");
    }

    /**
     * 获取xxl-job管理端cookie
     *
     * @return cookie值
     */
    private String getCookie() throws Exception {
        logger.info("xxlLoginParam: {}", xxlLoginParam.toString());

        String url = xxlLoginParam.getAdminAddresses() + XXL_ADMIN_LOGIN_ADDRESS;
        Map<String, Object> loginMap = new HashMap<>();
        loginMap.put("userName", xxlLoginParam.getXxlAdminAccount());
        loginMap.put("password", xxlLoginParam.getXxlAdminPassword());


        try (HttpResponse response = HttpRequest.post(url).form(loginMap).execute()) {
            boolean ok = response.isOk();
            if (ok) {
                List<HttpCookie> cookies = response.getCookies();
                logger.info("获取xxl cookie成功,返回信息:{}", cookies);
                StringBuilder sb = new StringBuilder();
                for (HttpCookie cookie : cookies) {
                    sb.append(cookie.toString());
                }
                return sb.toString();
            }
            logger.error("xxl获取cookie失败:{}", response.body());
        } catch (Exception e) {
            logger.error("xxl获取cookie异常: {}", e.getMessage());
        }
        throw new Exception("xxl获取cookie失败");
    }

    /**
     * 校验添加定时任务传输对象参数
     *
     * @param xxlJobTaskDto 新增定时任务传输对象
     */
    protected void checkTaskParam(XxlJobTaskDto xxlJobTaskDto) throws Exception {
        if (xxlJobTaskDto == null) {
            throw new Exception("传输对象空");
        }

        checkAndThrow(xxlJobTaskDto.getDesc(),  "xxl-job任务描述空");
        checkAndThrow(xxlJobTaskDto.getAuthor(),  "xxl-job负责人空");
        checkAndThrow(xxlJobTaskDto.getCron(),  "cron表达式空");
        checkAndThrow(xxlJobTaskDto.getExecutorHandler(),  "xxl-job job-handler空");
    }

    /**
     * 检验字段并抛出异常
     *
     * @param checkInfo 检查字符串信息
     * @param msg       异常信息
     * @throws Exception 字段异常
     */
    protected void checkAndThrow(String checkInfo, String msg) throws Exception {
        if (checkInfo == null || checkInfo.isEmpty()) {
            throw new Exception(msg);
        }
    }
}
