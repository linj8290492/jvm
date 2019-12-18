package com.jvm.chapter.jvmchapter1.controller;

import com.jvm.chapter.jvmchapter1.entity.MyMetaspace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deke
 * @description 方法区溢出
 * @date 2019/12/18
 */
@RestController
public class NonHeapController {
    List<Class<?>> list = new ArrayList<Class<?>>();

    @GetMapping("/nonheap")
    public String nonheap() throws Exception {
        while (true) {
            list.addAll(MyMetaspace.createClasses());
            Thread.sleep(5);
        }
    }
}