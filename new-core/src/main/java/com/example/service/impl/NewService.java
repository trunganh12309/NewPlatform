package com.example.service.impl;

import com.example.converter.NewConverter;
import com.example.dto.NewDTO;
import com.example.entity.CategoryEntity;
import com.example.entity.NewEntity;
import com.example.repository.CategoryRepository;
import com.example.repository.NewRepository;
import com.example.service.INewService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import com.example.repository.paging.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewService implements INewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private NewConverter newConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    //Custom JPA
//    @Override
//    public void findAll(NewDTO model, Pageable pageable) {
//        java 7
//        List<NewEntity> newEntities = newRepository.findAll(pageable);
//        List<NewDTO> newDTOS = new ArrayList<>();
//        for(NewEntity item: newEntities){
//            NewDTO newDTO = newConverter.convertToDto(item);
//            newDTOS.add(newDTO);
//        }
        //java 8
//        Map<String, Object> properties = buildNewProperty(model);
//        List<NewEntity> news = newRepository.findAll(properties, pageable);
//        model.setListResult(news.stream().map(item -> newConverter.convertToDto(item)).collect(Collectors.toList()));
//        model.setTotalItems(newRepository.getTotalItems().intValue());
//    }
    //Spring data JPA
    public void findAll(NewDTO model, Pageable pageable) {
        List<NewEntity> news = newRepository.findAll(pageable).getContent();
        model.setListResult(news.stream().map(item -> newConverter.convertToDto(item)).collect(Collectors.toList()));
        model.setTotalItems(newRepository.getTotalItems().intValue());
    }

    private Map<String, Object> buildNewProperty(NewDTO model) {
        Map<String, Object> results = new HashMap<>();
        if(StringUtils.isNotBlank(model.getTitle())){
            results.put("title",model.getTitle());
        }
        if(StringUtils.isNotBlank(model.getCategoryName())){
            results.put("categoryName",model.getCategoryName());
        }
        if(StringUtils.isNotBlank(model.getContent())){
            results.put("content",model.getContent());
        }
        return results;
    }

    @Override
    @Transactional
    public NewDTO save(NewDTO newDTO) {
        CategoryEntity category = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        NewEntity newEntity = newConverter.convertToEntity(newDTO);
        newEntity.setCategory(category);
        newRepository.save(newEntity);
        return newConverter.convertToDto(newEntity);
    }

    @Override
    @Transactional
    public NewDTO update(NewDTO updateNew) {
        NewEntity existNew = newRepository.findOne(updateNew.getId());
//        existNew.setTitle(updateNew.getTitle());
//        existNew.setContent(updateNew.getContent());
        NewEntity updateNewEntity = newConverter.convertToEntity(updateNew);
        updateNewEntity.setCategory(categoryRepository.findOneByCode(updateNew.getCategoryCode()));
        updateNewEntity.setCreatedDate(existNew.getCreatedDate());
        updateNewEntity.setCreatedBy(existNew.getCreatedBy());
        existNew = newRepository.save(updateNewEntity);
        return newConverter.convertToDto(existNew);
    }

    @Override
    public NewDTO findById(long id) {
        NewEntity newEntity = newRepository.findOne(id);
        return newConverter.convertToDto(newEntity);
    }
}
