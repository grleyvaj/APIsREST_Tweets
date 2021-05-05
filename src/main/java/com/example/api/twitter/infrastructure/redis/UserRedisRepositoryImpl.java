package com.example.api.twitter.infrastructure.redis;

import com.example.api.twitter.domain.entity.UserEntity;
import com.example.api.twitter.domain.repository.UserRepository;
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
@CacheConfig(cacheNames = "userRepository")
public class UserRedisRepositoryImpl implements UserRepository {

    private final UserJPARepository userJPARepository;

    @Autowired
    public UserRedisRepositoryImpl(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    @Cacheable(value = "userData")
    public Page<UserEntity> findAll(Pageable pageable) {
        return userJPARepository.findAll(pageable);
    }

    @Override
    public List<UserEntity> findAll() {
        return userJPARepository.findAll();
    }

    @Override
    public void save(UserEntity userEntity) {
        userJPARepository.save(userEntity);
    }

    @Override
    @Cacheable(value = "userData", key = "#id")
    public Optional<UserEntity> findOne(long tweetId) {
        return userJPARepository.findById(tweetId);
    }
}
