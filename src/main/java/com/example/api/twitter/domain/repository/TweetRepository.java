package com.example.api.twitter.domain.repository;

import com.example.api.twitter.domain.entity.TweetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public interface TweetRepository {

    Page<TweetEntity> findAll(Pageable pageable);

    List<TweetEntity> findAll();

    void save(TweetEntity tweetEntity);

    Optional<TweetEntity> findOne(long tweetId);

    List<TweetEntity> findAllByUserIdAndValidation(long userId);
}
