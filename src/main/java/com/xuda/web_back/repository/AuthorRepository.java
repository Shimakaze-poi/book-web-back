package com.xuda.web_back.repository;

import com.xuda.web_back.entity.AuthorList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorList, Integer>
{
    List<AuthorList> findByAuthortype(String authortype, Sort sort);
}
