package com.xuda.web_back.repository;

import com.xuda.web_back.entity.AccountList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountList, Integer>
{
    AccountList findFirstByAccountname(String accountname);
}
