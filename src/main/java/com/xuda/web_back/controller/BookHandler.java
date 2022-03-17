package com.xuda.web_back.controller;

import com.xuda.web_back.entity.BookList;
import com.xuda.web_back.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookHandler
{
    Sort sort = Sort.by(Sort.Direction.DESC,"id");

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/findall")
    public List<BookList> findAll()
    {
        return bookRepository.findAll(sort);
    }

    @PostMapping("/find")
    public List<BookList> find(@RequestBody LinkedHashMap<String, String> findMethod)
    {
        return bookRepository.findByContenttype(findMethod.get("contenttype"), sort);
    }

    @PostMapping("/rate")
    public void rate(@RequestBody LinkedHashMap<String, String> rateMethod)
    {
        BookList book = bookRepository.findFirstById(Integer.valueOf(rateMethod.get("id")));
        Float newScore = (book.getScore() * book.getRates() + Integer.parseInt(rateMethod.get("score"))) / (book.getRates() + 1);
        book.setRates(book.getRates() + 1);
        book.setScore(newScore);
        bookRepository.save(book);
    }
}
