package com.zdravko.models;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by C5204250 on 4/26/2016.
 */
@Transactional
public class CustomerRepositoryImpl implements CustomerAdditionalRepository {

    // An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;

    public int updateGmailCustomers() {
        String update = "Update Customer c SET c.email ='unknown' WHERE c.email like '%gmail.com'";

        return entityManager.createQuery(update).executeUpdate();
    }
}
