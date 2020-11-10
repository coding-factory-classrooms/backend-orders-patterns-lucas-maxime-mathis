package org.example.middlewares;

import spark.Request;
import spark.Response;

/**
 * A middleware intercepts a Request or a Response and provides a behavior (logging, auth, etc).
 */
public interface Middleware {

    /**
     * The logic of the middleware should take place in this function.
     * @param request: the original request object
     * @param response: the original response object
     */
    void process(Request request, Response response);
}
