package com.itheima.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.itheima.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {

}
