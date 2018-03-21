package com.itliusir.ms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 基础Dao层(放一些通用方法)
 *
 * @author liugang
 * @since 2018-03-05
 */

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> ,JpaSpecificationExecutor{

}
