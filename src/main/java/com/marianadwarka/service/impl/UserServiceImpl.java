package com.marianadwarka.service.impl;

import com.marianadwarka.model.User;
import com.marianadwarka.repo.IGenericRepo;
import com.marianadwarka.repo.IUserRepo;
import com.marianadwarka.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    private final IUserRepo repo;


    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }
}
