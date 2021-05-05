package com.example.api.twitter.interfaces.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonDeserialize
public class TweetModel extends RepresentationModel<TweetModel> {

    private String id;

    private UserModel user;

    private String text;

    private LocationModel location;

    private boolean validation;
}
