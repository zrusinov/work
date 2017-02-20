package com.zdravko.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends CustomerAdditionalRepository, JpaRepository<Customer, Long> {

    public List<Customer> findByLastName(String lastName);

    @Query("select c from Customer c where c.email like '%sap.com'")
    List<Customer> findAllSapCustomers();
}
