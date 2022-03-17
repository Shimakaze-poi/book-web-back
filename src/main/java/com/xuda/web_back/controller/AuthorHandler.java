package com.xuda.web_back.controller;

import com.xuda.web_back.entity.AuthorList;
import com.xuda.web_back.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorHandler
{
    Sort sort = Sort.by(Sort.Direction.DESC,"id");

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/findall")
    public List<AuthorList> findAll()
    {
        return authorRepository.findAll(sort);
    }

    @PostMapping("/find")
    public List<AuthorList> find(@RequestBody LinkedHashMap<String, String> findMethod)
    {
        return authorRepository.findByAuthortype(findMethod.get("authortype"), sort);
    }
}
