package com.marianadwarka.service.impl;

import com.marianadwarka.model.Role;
import com.marianadwarka.repo.IGenericRepo;
import com.marianadwarka.repo.IRoleRepo;
import com.marianadwarka.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

    private final IRoleRepo repo;


    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }
}
