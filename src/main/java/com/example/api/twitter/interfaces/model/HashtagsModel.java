package com.example.api.twitter.interfaces.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonDeserialize
public class HashtagsModel implements Serializable {

    private static final long serialVersionUID = -1361321468974920167L;

    @JsonProperty(value = "#_hashtags")
    String hashtags;

    @JsonProperty(value = "amount_tweets")
    long count;
}
