package com.marianadwarka.repo;

import com.marianadwarka.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer> {

    //FROM User u WHERE u.username = :username
    User findOneByUsername(String username);

}
