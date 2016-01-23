package com.goeuro.command;

import com.goeuro.Utils;
import com.goeuro.model.Location;
import com.goeuro.service.LocationServiceClient;

import java.util.List;

/**
 * Created by sujay on 23/01/16.
 */
public class HttpCommand implements Command {

    private final LocationServiceClient locationServiceClient;

    public HttpCommand(){
        locationServiceClient = LocationServiceClient.Factory.newInstance();
    }

    public void execute(final String cityName) {
        List<Location> locations = locationServiceClient.getLocations(cityName);
        Utils.writeCSV(cityName, locations);
    }
}
