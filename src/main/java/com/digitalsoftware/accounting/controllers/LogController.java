package com.digitalsoftware.accounting.controllers;

import com.digitalsoftware.accounting.domain.models.LogDTO;
import com.digitalsoftware.accounting.mapper.repositories.UserLogRepository;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogController {

    private final UserLogRepository userLogRepository;

    @Autowired
    public LogController(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    @GetMapping("/admin/log")
    public PageInfo<LogDTO> log(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "limit", required = false, defaultValue = "10") int limit, @RequestParam(value = "orderBy", required = false, defaultValue = "id desc") String orderBy, @RequestParam(value = "method", required = false) List<String> methods) {
        return userLogRepository.findByPage(pageNum, limit, orderBy, methods);
    }


}
