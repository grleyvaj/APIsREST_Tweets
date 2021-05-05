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
public class LocationModel extends RepresentationModel<LocationModel>  implements Serializable {

    private static final long serialVersionUID = 5414490016514627670L;

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "latitude")
    private double latitude;

    @JsonProperty(value = "longitude")
    private double longitude;
}
