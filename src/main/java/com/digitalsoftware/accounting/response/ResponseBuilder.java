package com.digitalsoftware.accounting.response;

import reactor.core.publisher.Mono;

public class ResponseBuilder {
    protected int status = 1;

    private ResponseBuilder() {
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    public ResponseBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public Mono<Response> build() {
        Response response = new Response();
        response.status = this.status;
        return Mono.just(response);
    }
}
