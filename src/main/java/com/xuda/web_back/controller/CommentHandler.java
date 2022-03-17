package com.xuda.web_back.controller;

import com.xuda.web_back.entity.CommentList;
import com.xuda.web_back.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentHandler
{
    Sort sort = Sort.by(Sort.Direction.DESC,"id");

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/findall")
    public List<CommentList> findAll() { return commentRepository.findAll(sort); }

    @PostMapping("/findspecial")
    public List<CommentList> findSpecial(@RequestBody LinkedHashMap<String, String> findMethod)
    {
        return commentRepository.findByContenttypeAndContentid(findMethod.get("contenttype"), Integer.parseInt(findMethod.get("contentid")), sort);
    }

    @PostMapping("/add")
    public void add(@RequestBody CommentList commentList)
    {
        CommentList result = commentRepository.save(commentList);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody LinkedHashMap<String, String> deleteMethod)
    {
        commentRepository.deleteById(Integer.valueOf(deleteMethod.get("id")));
    }
}
