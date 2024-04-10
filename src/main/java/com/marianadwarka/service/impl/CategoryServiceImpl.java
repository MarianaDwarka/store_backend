package com.marianadwarka.service.impl;

import com.marianadwarka.model.Category;
import com.marianadwarka.repo.ICategoryRepo;
import com.marianadwarka.repo.IGenericRepo;
import com.marianadwarka.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    //@Autowired
    private final ICategoryRepo repo;// = new CategoryRepo();

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
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
