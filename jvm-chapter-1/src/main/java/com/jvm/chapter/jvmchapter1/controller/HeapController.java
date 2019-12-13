package com.jvm.chapter.jvmchapter1.controller;

import com.jvm.chapter.jvmchapter1.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deke
 * @description 堆溢出
 * @date 2019/12/13
 */
@RestController
public class HeapController {

    List<Person> list = new ArrayList<>(10);

    @GetMapping("/heap")
    public String heap() throws Exception{
        while (true){
            list.add(new Person());
            Thread.sleep(1);
        }
    }

}
