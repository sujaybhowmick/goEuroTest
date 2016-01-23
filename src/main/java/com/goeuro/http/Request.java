package com.goeuro.http;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by sujay on 23/01/16.
 */
public class Request implements Callable<Response> {

    private final URL url;
    private final HttpMethod method;

    public Request(final String endPoint, final HttpMethod method){
        try {
            this.url = new URL(endPoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.method = method;
    }

    public Response call() throws Exception {
        int responseCode = 0;
        try {
            HttpURLConnection urlConnection =
                    (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method.getMethod());
            responseCode = urlConnection.getResponseCode();

            return new Response(responseCode, urlConnection.getInputStream());
        }catch (Exception e){
            return new Response(responseCode, null);
        }
    }

    public enum HttpMethod {
        GET("GET"), POST("POST");

        private String method;

        HttpMethod(final String method){
            this.method = method;
        }

        public String getMethod() {
            return method;
        }
    }
}
