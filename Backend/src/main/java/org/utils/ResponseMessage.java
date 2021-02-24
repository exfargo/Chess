package org.utils;

import javax.ws.rs.core.Response;

public class ResponseMessage {

    private final String message;

    public ResponseMessage(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
