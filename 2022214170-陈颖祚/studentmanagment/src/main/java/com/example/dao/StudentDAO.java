package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.SearchData;
import com.example.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import com.example.util.HibernateUtil;

@Repository()
public class StudentDAO {

    public void addStudent(Student student) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(student);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStudent(Student student) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(student);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Student getStudentById(int id) {
        Transaction tx = null;
        Student student = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            student = (Student) session.get(Student.class, id);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }

    private boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Student> searchStudent(SearchData searchData) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> query = builder.createQuery(Student.class);
            Root<Student> root = query.from(Student.class);
            List<Predicate> predicates = new ArrayList<>();
            if (!isEmpty(searchData.name)) {
                predicates.add(builder.like(root.get("name"), "%" + searchData.name + "%"));
            }
            if (searchData.major != null) {
                predicates.add(builder.equal(root.get("major"), searchData.major));
            }
            if (searchData.school != null) {
                predicates.add(builder.equal(root.get("school"), searchData.school));
            }
            if (!isEmpty(searchData.stuNumber)) {
                predicates.add(builder.equal(root.get("stuNumber"), searchData.stuNumber));
            }
            if (!isEmpty(searchData.stuType)) {
                predicates.add(builder.equal(root.get("stuType"), searchData.stuType));
            }
            query.select(root).where(predicates.toArray(new Predicate[0]));
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> getAllStudents() {
        Transaction tx = null;
        List<Student> students = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            students = session.createQuery("FROM Student").list();
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return students;
    }

}