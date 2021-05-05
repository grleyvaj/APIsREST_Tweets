package com.example.api.twitter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hashtags")
@RedisHash("hashtagRedis")
public class HashtagPtnEntity {

    @Id
    private String id;

    @OrderBy("typeName DESC")
    private long count;
}
