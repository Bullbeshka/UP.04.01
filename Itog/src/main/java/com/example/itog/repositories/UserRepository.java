package com.example.itog.repositories;

import com.example.itog.model.ModelUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ModelUser,Long> {
    ModelUser findByUsername(String username);
}