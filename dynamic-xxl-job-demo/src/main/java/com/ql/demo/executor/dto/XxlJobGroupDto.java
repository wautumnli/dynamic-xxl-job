package com.ql.demo.executor.dto;/**
 *
 */

import java.util.List;

/**
 * 执行端查询结果传输对象
 *
 * @author Autumn Li
 * @date 2024/09/26
 */
public class XxlJobGroupDto {

    // 执行端结果集合
    private List<XxlJobGroupDataDto> data;


    public List<XxlJobGroupDataDto> getData() {
        return data;
    }

    public void setData(List<XxlJobGroupDataDto> data) {
        this.data = data;
    }
}
