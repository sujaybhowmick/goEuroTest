package com.goeuro.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sujay on 23/01/16.
 */
public class Response {
    private int statusCode;
    private InputStream body;

    public Response(int statusCode, InputStream body){
        this.statusCode = statusCode;
        this.body = body;
    }

    public String getBody() {
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(body));
            String read;

            while ((read = bufferedReader.readLine()) != null) {
                sb.append(read);
            }
        }catch(IOException ioe){
            throw new RuntimeException(ioe);
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return sb.toString();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
