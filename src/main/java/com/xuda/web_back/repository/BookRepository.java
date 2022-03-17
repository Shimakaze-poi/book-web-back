package com.xuda.web_back.repository;

import com.xuda.web_back.entity.BookList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookList, Integer>
{
    List<BookList> findByContenttype(String contenttype, Sort sort);
    BookList findFirstById(Integer id);
}
