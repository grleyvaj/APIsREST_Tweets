package com.example.api.twitter.domain.repository;

import com.example.api.twitter.domain.entity.HashtagPtnEntity;
import com.example.api.twitter.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public interface HashtagRepository {

    Page<HashtagPtnEntity> findAll(Pageable pageable);

    List<HashtagPtnEntity> findAll();

    void save(HashtagPtnEntity hashtagPtnEntity);

    Optional<HashtagPtnEntity> findOne(String hashtagId);
}
