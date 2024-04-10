package com.marianadwarka.service.impl;

import com.marianadwarka.model.Client;
import com.marianadwarka.repo.IClientRepo;
import com.marianadwarka.repo.IGenericRepo;
import com.marianadwarka.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    private final IClientRepo repo;


    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }
}
