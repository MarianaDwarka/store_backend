package com.marianadwarka.service.impl;

import com.marianadwarka.model.Product;
import com.marianadwarka.repo.IGenericRepo;
import com.marianadwarka.repo.IProductRepo;
import com.marianadwarka.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

    private final IProductRepo repo;


    @Override
    protected IGenericRepo<Product, Integer> getRepo() {
        return repo;
    }
}
