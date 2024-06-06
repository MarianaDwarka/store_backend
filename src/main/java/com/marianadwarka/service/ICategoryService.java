package com.marianadwarka.service;

import com.marianadwarka.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService extends ICRUD<Category, Integer> {

    /*Category save(Category category) throws Exception;
    Category update(Category category, Integer id) throws Exception;
    List<Category> readAll() throws Exception;
    Category readById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;*/

    //public Category saveAndValid(Category category);

    List<Category> findByName(String name);
    List<Category> findByNameLike(String name);
    List<Category> findByNameAndEnabled(String name, boolean enabled);
    List<Category> getNameAndDescription1(String name, String description);
    List<Category> getNameAndDescription2(String name, String description);
    List<Category> getNameSQL(String name);

    Page<Category> findPage(Pageable pageable);
    List<Category> findAllOrder(String param);
}
