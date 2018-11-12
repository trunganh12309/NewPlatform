package com.example.service;

import com.example.dto.NewDTO;
import com.example.entity.NewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import com.example.repository.paging.Pageable;
import java.util.List;

public interface INewService {
    void findAll(NewDTO model, Pageable pageable);
    NewDTO save(NewDTO newDTO);
    NewDTO update(NewDTO updateNew);
    NewDTO findById(long id);
}
