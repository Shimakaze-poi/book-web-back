package com.xuda.web_back.repository;

import com.xuda.web_back.entity.ArticleList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleList, Integer>
{
    List<ArticleList> findByTitleLike(String title, Sort sort);
}
