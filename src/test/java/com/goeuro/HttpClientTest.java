package com.goeuro;

import com.goeuro.http.HttpClient;
import com.goeuro.http.Request;
import com.goeuro.http.Response;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;

/**
 * Created by sujay on 23/01/16.
 */
public class HttpClientTest {

    ScriptEngine engine;

    @org.junit.Before
    public void setUp() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("javascript");
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testSendRequest() throws Exception {

        Request request = new Request("http://api.goeuro.com/api/v2/position/suggest/en/Berlin", Request.HttpMethod.GET);
        HttpClient httpClient = new HttpClient();
        Response response = httpClient.sendRequest(request);
        int responseCode = response.getStatusCode();
        StringBuilder sb = new StringBuilder();
        if(responseCode == 200) {
            String body = response.getBody();
            //System.out.println(sb.toString());
            ScriptObjectMirror jsonObj =
                    (ScriptObjectMirror)this.engine.eval(body);
            for(Map.Entry<String, Object> entry: jsonObj.entrySet()){
                ScriptObjectMirror object = (ScriptObjectMirror)entry.getValue();

            }


        }
    }
}