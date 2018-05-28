package com.daojpa.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerDao implements Dao<Customer, Integer> {

    private EntityManagerFactory currentSession;
    private static EntityManager em;


    private EntityManager openCurrentSessionWithTransaction(){
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("Customer");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }
    private EntityManager openCurrentSession(){
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("Customer");
        em = emf.createEntityManager();

        return em;
    }


    private void closeCurrentSession() {


        em.close();

    }

    private void closeCurrentSessionwithTransaction() {

        em.getTransaction().commit();
        em.close();

    }

    public void persist(Customer entity) {
        em = openCurrentSessionWithTransaction();
        em.persist(entity);
        closeCurrentSessionwithTransaction();
    }

    public void update(Customer entity) {
        em = openCurrentSessionWithTransaction();
        int id = entity.getId();
        Customer customer = em.find(Customer.class, id);
        if (entity.getFirstName()!=null){
            customer.setFirstName(entity.getFirstName());
        }
        if(entity.getSecondName()!=null) {
            customer.setSecondName(entity.getSecondName());
        }
        if(entity.getEmail()!=null) {
            customer.setEmail(entity.getEmail());
        }
        if(entity.getPhone()!=null){
            customer.setPhone(entity.getPhone());
        }
        closeCurrentSessionwithTransaction();

    }

    public void remove(Integer id) {
        em = openCurrentSessionWithTransaction();
       Customer customer =  em.find(Customer.class,id);
        em.remove(customer);
       closeCurrentSessionwithTransaction();
    }

    public Customer findById(Integer id) {
        em = openCurrentSession();
        Customer cust = em.find(Customer.class, id);
        closeCurrentSession();
        return cust;
    }
}
