package com.example.api.twitter.infrastructure.redis;

import com.example.api.twitter.domain.entity.TweetEntity;
import com.example.api.twitter.domain.repository.TweetRepository;
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
@CacheConfig(cacheNames = "tweetRepository")
public class TweetRedisRepositoryImpl implements TweetRepository {

    private final TweetJPARepository tweetJPARepository;

    @Autowired
    public TweetRedisRepositoryImpl(TweetJPARepository tweetJPARepository) {
        this.tweetJPARepository = tweetJPARepository;
    }

    @Override
    @Cacheable(value = "tweetData")
    public Page<TweetEntity> findAll(Pageable pageable) {
        return tweetJPARepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "tweetData")
    public List<TweetEntity> findAll() {
        return tweetJPARepository.findAll();
    }

    @Override
    public void save(TweetEntity tweetEntity) {
        tweetJPARepository.save(tweetEntity);
    }

    @Override
    @Cacheable(value = "tweetData", key = "#id")
    public Optional<TweetEntity> findOne(long tweetId) {
        return tweetJPARepository.findById(tweetId);
    }

    @Override
    public List<TweetEntity> findAllByUserIdAndValidation(long userId) {
        return tweetJPARepository.findAllByUserIdAndValidation(userId, true);
    }
}
