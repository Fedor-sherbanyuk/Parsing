package com.example.demo.persitence;

import com.example.demo.model.StackOverflowWebsite;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StackOverflowWebsiteRepository extends MongoRepository<StackOverflowWebsite, String> {

//    List<StackOverflowWebsite> findByWebsite(String website);
//
//    public List<StackOverflowWebsite> findAll(){
//        return mongoTemplate.findAll(StackOverflowWebsite.class);
//    }
}
