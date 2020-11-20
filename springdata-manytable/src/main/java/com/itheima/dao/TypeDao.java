package com.itheima.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.itheima.domain.Type;

public interface TypeDao extends JpaRepository<Type, Integer>, JpaSpecificationExecutor<Type> {

}
