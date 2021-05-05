package com.example.api.twitter.domain.factory;

import com.example.api.twitter.domain.entity.LocationEntity;
import org.springframework.stereotype.Component;
import twitter4j.GeoLocation;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class LocationFactory {

    public static LocationEntity mapTo(GeoLocation geoLocation, long locationId) {
        return LocationEntity.builder()
                .id(locationId)
                .latitude(geoLocation.getLatitude())
                .longitude(geoLocation.getLongitude())
                .build();
    }
}
