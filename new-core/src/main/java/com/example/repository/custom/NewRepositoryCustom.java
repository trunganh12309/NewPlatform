package com.example.repository.custom;

import com.example.entity.NewEntity;
import com.example.repository.paging.Pageable;

import java.util.List;
import java.util.Map;

public interface NewRepositoryCustom {
//    List<NewEntity> findAll(Map<String, Object> properties, Pageable pageable);
    List<NewEntity> findAll(Pageable pageable);
    Long getTotalItems();
}
