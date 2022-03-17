package com.xuda.web_back.controller;

import com.xuda.web_back.entity.RecommendList;
import com.xuda.web_back.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendHandler
{
    Sort sort = Sort.by(Sort.Direction.DESC, "id");

    @Autowired
    private RecommendRepository recommendRepository;

    @GetMapping("/find")
    public RecommendList find()
    {
        List<RecommendList> recommendListList = recommendRepository.findAll(sort);
        return recommendListList.get(0);
    }
}
