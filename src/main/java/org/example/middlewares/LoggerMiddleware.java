package org.example.middlewares;

import spark.Request;
import spark.Response;

/**
 * Logs the HTTP request in the form of "GET /login user=bob".
 */
public class LoggerMiddleware implements Middleware {


    @Override
    public void process(Request request, Response response) {
        String log = request.requestMethod() +
                " " + request.url() +
                " " + request.body();
        System.out.println(log);
    }
}
