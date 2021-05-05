package com.example.api.twitter.infrastructure.redis;

import com.example.api.twitter.domain.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Gloria R. Leyva Jerez
 */
@Repository
public interface TweetJPARepository extends JpaRepository<TweetEntity, Long>, JpaSpecificationExecutor<TweetEntity> {

    List<TweetEntity> findAllByUserIdAndValidation(long userId, boolean validate);
}
