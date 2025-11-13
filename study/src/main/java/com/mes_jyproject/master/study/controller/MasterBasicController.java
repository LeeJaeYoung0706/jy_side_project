package com.mes_jyproject.master.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterBasicController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
