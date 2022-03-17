package com.xuda.web_back.repository;

import com.xuda.web_back.entity.RecommendList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository<RecommendList, Integer>
{
}
