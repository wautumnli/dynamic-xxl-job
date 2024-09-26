package com.ql.demo.executor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * xxl-job执行端配置类参数
 *
 * @author Autumn Li
 * @date 2024/9/25
 */
@Component
public class XxlJobParamConfig {

    // 调度中心部署根地址 [选填]
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    // 执行器通讯TOKEN [选填]
    @Value("${xxl.job.accessToken}")
    private String accessToken;
    // 执行器AppName [选填]
    @Value("${xxl.job.executor.appName}")
    private String appName;
    // 执行器注册 [选填]
    @Value("${xxl.job.executor.address}")
    private String address;
    // 执行器IP [选填]
    @Value("${xxl.job.executor.ip}")
    private String ip;
    // 执行器端口号 [选填]
    @Value("${xxl.job.executor.port}")
    private int port;
    // 执行器运行日志文件存储磁盘路径 [选填]
    @Value("${xxl.job.executor.logpath}")
    private String logPath;
    // 执行器日志文件保存天数 [选填]
    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;
    // 管理端账号
    @Value("${xxl.login.username}")
    private String xxlAdminAccount;
    // 管理端密码
    @Value("${xxl.login.password}")
    private String xxlAdminPassword;


    public String getAdminAddresses() {
        return adminAddresses;
    }

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddresses = adminAddresses;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public int getLogRetentionDays() {
        return logRetentionDays;
    }

    public void setLogRetentionDays(int logRetentionDays) {
        this.logRetentionDays = logRetentionDays;
    }

    public String getXxlAdminAccount() {
        return xxlAdminAccount;
    }

    public void setXxlAdminAccount(String xxlAdminAccount) {
        this.xxlAdminAccount = xxlAdminAccount;
    }

    public String getXxlAdminPassword() {
        return xxlAdminPassword;
    }

    public void setXxlAdminPassword(String xxlAdminPassword) {
        this.xxlAdminPassword = xxlAdminPassword;
    }

    @Override
    public String toString() {
        return "XxlJobParamConfig{" +
                "adminAddresses='" + adminAddresses + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", appName='" + appName + '\'' +
                ", address='" + address + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", logPath='" + logPath + '\'' +
                ", logRetentionDays=" + logRetentionDays +
                ", xxlAdminAccount='" + xxlAdminAccount + '\'' +
                ", xxlAdminPassword='" + xxlAdminPassword + '\'' +
                '}';
    }
}
