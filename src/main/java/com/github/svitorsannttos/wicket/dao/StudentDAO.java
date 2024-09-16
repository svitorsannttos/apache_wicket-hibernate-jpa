package com.github.svitorsannttos.wicket.dao;

import com.github.svitorsannttos.wicket.model.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class StudentDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public StudentDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("postgres");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findAll(){
        String query = "SELECT s FROM Student s ORDER BY id";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        return typedQuery.getResultList();
    }

    public void save(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        closedConnection();
    }

    public void update(Student student){
        entityManager.getTransaction().begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
        closedConnection();
    }

    public void delete(Student student){
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        closedConnection();
    }

    private void closedConnection(){
        entityManager.close();
        entityManagerFactory.close();
    }

}
