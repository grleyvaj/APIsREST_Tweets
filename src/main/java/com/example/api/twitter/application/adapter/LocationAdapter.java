package com.example.api.twitter.application.adapter;

import com.example.api.twitter.domain.entity.LocationEntity;
import com.example.api.twitter.interfaces.model.LocationModel;
import org.springframework.stereotype.Component;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class LocationAdapter {

    public LocationModel mapTo(LocationEntity geoLocation) {
        return LocationModel.builder()
                .latitude(geoLocation.getLatitude())
                .longitude(geoLocation.getLongitude())
                .build();
    }
}
