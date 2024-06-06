package com.marianadwarka.service.impl;

import com.marianadwarka.model.Category;
import com.marianadwarka.repo.ICategoryRepo;
import com.marianadwarka.repo.IGenericRepo;
import com.marianadwarka.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    //@Autowired
    private final ICategoryRepo repo;// = new CategoryRepo();

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Category> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Category> findByNameLike(String name) {
        return repo.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Category> findByNameAndEnabled(String name, boolean enabled) {
        //return repo.findByNameAndEnabled(name, enabled);
        return repo.findByNameOrEnabled(name, enabled);
    }

    @Override
    public List<Category> getNameAndDescription1(String name, String description) {
        return repo.getNameAndDescription1(name, description);
    }

    @Override
    public List<Category> getNameAndDescription2(String name, String description) {
        return repo.getNameAndDescription2(name, description);
    }

    @Override
    public List<Category> getNameSQL(String name) {
        return repo.getNameSQL(name);
    }

    @Override
    public Page<Category> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Category> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction, "name"));
    }

    /*@Override
    public Category save(Category category) throws Exception {
        return repo.save(category);
    }

    @Override
    public Category update(Category category, Integer id) throws Exception {
        category.setIdCategory(id);
        return repo.save(category);
    }

    @Override
    public List<Category> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Category readById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Category());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }*/



    /*public CategoryServiceImpl(ICategoryRepo repo) {
        this.repo = repo;
    }*/

    /*@Override
    public Category saveAndValid(Category category){
        if(category.getIdCategory() == 0){

            return repo.save(category);
        }else{
            return new Category();
        }
    }*/
}