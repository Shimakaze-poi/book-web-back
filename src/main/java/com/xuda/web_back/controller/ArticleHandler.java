package com.xuda.web_back.controller;

import com.xuda.web_back.entity.ArticleList;
import com.xuda.web_back.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleHandler
{
    Sort sort = Sort.by(Sort.Direction.DESC, "id");

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/findall")
    public List<ArticleList> findAll()
    {
        return articleRepository.findAll(sort);
    }

    @PostMapping("/add")
    public void add(@RequestBody ArticleList articleList)
    {
        articleRepository.save(articleList);
    }

    @PostMapping("/changeapproval")
    public void changeApproval(@RequestBody LinkedHashMap<String, String> changeApprovalInformation)
    {
        ArticleList articleList = articleRepository.getById(Integer.valueOf(changeApprovalInformation.get("id")));
        articleList.setApproval(articleList.getApproval() + Integer.parseInt(changeApprovalInformation.get("num")));
        articleRepository.save(articleList);
    }

    @PostMapping("/search")
    public List<ArticleList> search(@RequestBody LinkedHashMap<String, String> searchInformation)
    {
        return articleRepository.findByTitleLike("%" + searchInformation.get("title") + "%", sort);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody LinkedHashMap<String, String> deleteMethod)
    {
        articleRepository.deleteById(Integer.valueOf(deleteMethod.get("id")));
    }
}
