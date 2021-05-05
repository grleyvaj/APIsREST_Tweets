package com.example.api.twitter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tweets")
@RedisHash("tweetsRedis")
public class TweetEntity implements Serializable {

    @Id
    private long id;

    @Column(name = "user_id")
    private long userId;

    private String text;

    @Column(name = "location_id")
    private Long locationId;

    private boolean validation;
}
