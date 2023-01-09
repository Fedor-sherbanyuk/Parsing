package com.example.demo.service;

import com.example.demo.model.SiteDto;
import com.example.demo.model.StackOverflowWebsite;
import com.example.demo.persitence.StackOverflowWebsiteRepository;
import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class StackOverflowService {
    @Autowired
    private StackExchangeClient stackExchangeClient;
    @Autowired
    private StackOverflowWebsiteRepository repository;

    public List<StackOverflowWebsite> findAll() {
        return stackExchangeClient.getSites().stream().map(this::tostackOverflowWebsite)
                .filter(this::ignoreMeta).collect(collectingAndThen(toList(),
                        ImmutableList::copyOf));
    }

    private StackOverflowWebsite tostackOverflowWebsite(@NonNull SiteDto input) {
        return new StackOverflowWebsite(input.getSite_url().substring("http://".length(), input.getSite_url().length() - ".com".length()),
                input.getFavicon_url(),
                input.getAudience(),
                input.getSite_url(),
                input.getName());
    }

    private boolean ignoreMeta(@NonNull StackOverflowWebsite stackOverflowWebsite) {
        return !stackOverflowWebsite.getId().startsWith("meta.");
    }

}
