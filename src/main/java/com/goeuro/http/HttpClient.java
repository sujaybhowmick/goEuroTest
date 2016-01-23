package com.goeuro.http;

import java.util.concurrent.*;

/**
 * Created by sujay on 23/01/16.
 */
public class HttpClient {
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private final long SERVICE_TIMEOUT = 5000; // 5 secs
    private final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    public Response sendRequest(Request request){
        Future<Response> responseFuture = executorService.submit(request);
        try {
            return responseFuture.get(SERVICE_TIMEOUT, TIME_UNIT);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted Exception");
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Service Execution Error");
        }catch (TimeoutException te){
            throw new RuntimeException("Request timed out");
        }
        finally {
            executorService.shutdown();
        }
    }
}
