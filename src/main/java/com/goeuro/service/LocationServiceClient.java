package com.goeuro.service;

import com.goeuro.model.Location;

import java.util.List;

/**
 * Created by sujay on 23/01/16.
 */
public interface LocationServiceClient {
    List<Location> getLocations(final String cityName);

    class Factory {
        public static LocationServiceClient newInstance(){
            return new LocationServiceClientImpl();
        }
    }
}
