package org.utils;

import javax.ws.rs.core.Response;

public class ErrorResponse {

    private final Response.Status status;
    private final String message;

    public ErrorResponse(Response.Status status, String message) {
        this.status = status;
        this.message = message;
    }
}
