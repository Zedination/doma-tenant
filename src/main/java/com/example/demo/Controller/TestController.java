package com.example.demo.Controller;

import com.example.demo.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestDao testDao;


    @GetMapping("/product")
    public List<String> getProductNames(){
        return testDao.getListProduct();
    }
}
