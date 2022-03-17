package com.xuda.web_back.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class AuthorList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name, nationality, lifetime, masterwork, honor;
    private String shortintro, introduction, authortype, imageurl;
}
