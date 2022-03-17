package com.xuda.web_back.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class BookList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name, author, authornationality;
    private String shortintro, introduction, extract, contenttype, genre;
    private Integer wordnumber;
    private String publishdate, honor;
    private Float score;
    private Integer rates;
    private String imageurl;
}
