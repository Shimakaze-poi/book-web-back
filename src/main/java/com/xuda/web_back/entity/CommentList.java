package com.xuda.web_back.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CommentList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author, contenttype;
    private Integer contentid;
    private String comment, publishdate;
    private Integer authorid;
    private String contentname;
}
