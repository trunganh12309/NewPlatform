package com.example.repository.custom.impl;

import com.example.entity.NewEntity;
import com.example.repository.custom.NewRepositoryCustom;
import com.example.repository.paging.Pageable;

//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public class NewRepositoryImpl implements NewRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
//    public List<NewEntity> findAll(Map<String, Object> properties, Pageable pageable) {
    public List<NewEntity> findAll(Pageable pageable) {
        //SQL native
//        StringBuilder sql = new StringBuilder("SELECT * FROM new AS n");
//        if (properties.containsKey("categoryName")){
//            sql.append(" INNER JOIN category AS c ON c.id = n.category_id ");
//        }
//        sql.append(" WHERE 1=1 ");
//        if (properties.containsKey("categoryName")){
//            sql.append(" AND LOWER(name) LIKE '%' || :categoryName || '%' ");
//        }
//        if (properties.containsKey("title")){
//            sql.append(" AND LOWER(title) LIKE '%' || :title || '%' ");
//        }
//        if (properties.containsKey("content")){
//            sql.append(" AND LOWER(content) LIKE '%' || :content || '%' ");
//        }
//        Query query = entityManager.createNativeQuery(sql.toString(), NewEntity.class);
//        if (properties.containsKey("categoryName")){
//            query.setParameter("categoryName",properties.get("categoryName").toString().toLowerCase());
//        }
//        if (properties.containsKey("title")){
//            query.setParameter("title",properties.get("title").toString().toLowerCase());
//        }
//        if (properties.containsKey("content")){
//            query.setParameter("content",properties.get("content").toString().toLowerCase());
//        }
//        if (pageable.getOffset() != null) {
//            query.setFirstResult(pageable.getOffset());
//        }
//        if (pageable.getPageSize() != null) {
//            query.setMaxResults(pageable.getPageSize());
//        }
        //HQL
        StringBuilder sql = new StringBuilder("FROM NewEntity");
        Query query = entityManager.createQuery(sql.toString());
        if (pageable.getOffset() != null) {
            query.setFirstResult(pageable.getOffset());
        }
        if (pageable.getPageSize() != null) {
            query.setMaxResults(pageable.getPageSize());
        }
        return query.getResultList();
    }

    @Override
    public Long getTotalItems() {
        //SQL native
//        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM newplatform.new AS n");
//        Query query = entityManager.createNativeQuery(sql.toString());
//        List<BigInteger> bigIntegers = query.getResultList();
//        return Long.parseLong(bigIntegers.get(0).toString(), 10);
        //HQL
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM NewEntity");
        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getResultList().get(0);
    }
}
