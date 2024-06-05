package com.marianadwarka.service.impl;

import com.marianadwarka.model.Provider;
import com.marianadwarka.repo.IGenericRepo;
import com.marianadwarka.repo.IProviderRepo;
import com.marianadwarka.service.IProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {

    private final IProviderRepo repo;


    @Override
    protected IGenericRepo<Provider, Integer> getRepo() {
        return repo;
    }
}
