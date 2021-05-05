package com.example.api.twitter.domain.repository;

import com.example.api.twitter.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public interface UserRepository {

    Page<UserEntity> findAll(Pageable pageable);

    List<UserEntity> findAll();

    void save(UserEntity userEntity);

    Optional<UserEntity> findOne(long userId);
}
