package com.goeuro.service;

import com.goeuro.Utils;
import com.goeuro.http.HttpClient;
import com.goeuro.http.Request;
import com.goeuro.http.Response;
import com.goeuro.model.Location;

import java.util.List;

/**
 * Created by sujay on 23/01/16.
 */
public class LocationServiceClientImpl implements LocationServiceClient {
    private static final String LOCATION_API_END_POINT =
            "http://api.goeuro.com/api/v2/position/suggest/en/";

    private static final int OK = 200;

    private HttpClient httpClient;

    public LocationServiceClientImpl(){
        httpClient = new HttpClient();
    }

    public List<Location> getLocations(final String cityName) {
        final String json = getResponse(cityName);
        return Utils.convertJsonToLocation(json);
    }

    private String getResponse(final String cityName){
        final String url = LOCATION_API_END_POINT + cityName;
        Request request = new Request(url, Request.HttpMethod.GET);

        Response response = httpClient.sendRequest(request);
        int statusCode = response.getStatusCode();
        String body = null;
        if(statusCode == OK){
            body = response.getBody();
        }else {
            throw new ServiceException(statusCode);
        }
        return body;
    }
}
