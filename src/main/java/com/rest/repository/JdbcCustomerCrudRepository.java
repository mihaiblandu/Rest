package com.rest.repository;

import com.rest.entity.JdbcCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JdbcCustomerCrudRepository extends CrudRepository<JdbcCustomer, Integer> {

    List<JdbcCustomer> findByEmail(String email);

    List<JdbcCustomer> findByGender(String gender);

}