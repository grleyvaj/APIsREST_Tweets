package com.example.api.twitter.infrastructure.redis;

import com.example.api.twitter.domain.entity.HashtagPtnEntity;
import com.example.api.twitter.domain.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
@CacheConfig(cacheNames = "hashtagRepository")
public class HashtagRedisRepositoryImpl implements HashtagRepository {

    private final HashtagJPARepository hashtagJPARepository;

    @Autowired
    public HashtagRedisRepositoryImpl(HashtagJPARepository hashtagJPARepository) {
        this.hashtagJPARepository = hashtagJPARepository;
    }

    @Override
    @Cacheable(value = "hashtagData")
    public Page<HashtagPtnEntity> findAll(Pageable pageable) {
        return hashtagJPARepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "hashtagData")
    public List<HashtagPtnEntity> findAll() {
        return hashtagJPARepository.findAll();
    }

    @Override
    public void save(HashtagPtnEntity hashtagPtnEntity) {
        hashtagJPARepository.save(hashtagPtnEntity);
    }

    @Override
    @Cacheable(value = "hashtagData", key = "#id")
    public Optional<HashtagPtnEntity> findOne(String hashtagId) {
        return hashtagJPARepository.findById(hashtagId);
    }
}
