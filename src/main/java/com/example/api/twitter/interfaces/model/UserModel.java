package com.example.api.twitter.interfaces.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonDeserialize
public class UserModel extends RepresentationModel<UserModel> implements Serializable {

    private static final long serialVersionUID = -5466320560155993797L;

    @JsonProperty(value = "id")
    long id;

    @JsonProperty(value = "name")
    String name;

    @JsonProperty(value = "email")
    String email;
}
