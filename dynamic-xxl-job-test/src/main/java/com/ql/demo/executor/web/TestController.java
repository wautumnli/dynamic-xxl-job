package com.ql.demo.executor.web;

import com.ql.demo.executor.dto.XxlJobTaskDto;
import com.ql.demo.executor.util.XxlHttpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Autumn Li
 * @date 2024/09/26
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private XxlHttpUtil xxlHttpUtil;

    @PostMapping("/add")
    public String add(@RequestBody XxlJobTaskDto xxlJobTaskDto) {
        xxlHttpUtil.addXxlJobTask(xxlJobTaskDto);
        return "success";
    }
}
