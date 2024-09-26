package com.ql.demo.executor.dto;/**
 *
 */

/**
 * xxl-job任务对象
 *
 * @author Autumn Li
 * @date 2024/09/26
 */
public class XxlJobTaskDto {

    // 任务描述
    private String desc;
    // cron表达式
    private String cron;
    // 超时时间 单位秒
    private int executorTimeout;
    // 重试次数
    private int executorFailRetryCount;
    // 创建人
    private String author;
    // 执行参数
    private String executorParam;
    // 执行处理方法
    private String executorHandler;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public int getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(int executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public int getExecutorFailRetryCount() {
        return executorFailRetryCount;
    }

    public void setExecutorFailRetryCount(int executorFailRetryCount) {
        this.executorFailRetryCount = executorFailRetryCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    public String getExecutorParam() {
        return executorParam;
    }

    public void setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
    }
}
