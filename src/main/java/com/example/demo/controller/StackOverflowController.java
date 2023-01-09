package com.example.demo.controller;

import com.example.demo.model.StackOverflowWebsite;
import com.example.demo.persitence.StackOverflowWebsiteRepository;
import com.example.demo.service.StackOverflowService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stackoverflow")
public class StackOverflowController {
    @Autowired
    private StackOverflowService stackOverflowService;
    @Autowired
    private StackOverflowWebsiteRepository website;
    @GetMapping({"","/"})
    public List<StackOverflowWebsite> getListOfProviders(){
        return stackOverflowService.findAll();
    }
    @PostConstruct
    public  void  init(){
       List<StackOverflowWebsite> newWeb =getListOfProviders();
       website.saveAll(newWeb);
    }
    @PostMapping("web")
    public  void  save(@RequestBody StackOverflowWebsite overflowWebsite){
        website.save(overflowWebsite);
    }
}
