package org.pcbe.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.pcbe.dto.Order;

// TODO: Remove this
@ApplicationScoped
@Transactional
public class JPATestBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void createOrder(Order order) {
        entityManager.persist(order);
    }

}
