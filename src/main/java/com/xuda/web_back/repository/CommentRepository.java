package com.xuda.web_back.repository;

import com.xuda.web_back.entity.CommentList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentList, Integer>
{
    List<CommentList> findByContenttypeAndContentid(String contenttype, Integer contentid, Sort sort);
}
