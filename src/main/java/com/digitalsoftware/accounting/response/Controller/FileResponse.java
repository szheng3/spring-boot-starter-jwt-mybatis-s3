package com.digitalsoftware.accounting.response.Controller;

import com.digitalsoftware.accounting.response.Response;
import lombok.Data;
import reactor.core.publisher.Mono;


@Data
public class FileResponse extends Response {


    private String path;

    public FileResponse(String path) {
        this.path = path;
    }

    public static FileResponseBuilder Builder() {
        return new FileResponseBuilder();
    }

    public static final class FileResponseBuilder {

        private String path;

        private FileResponseBuilder() {
        }


        public FileResponseBuilder withPath(String path) {
            this.path = path;
            return this;
        }

        public Mono<FileResponse> build() {
            return Mono.just(new FileResponse(path));
        }
    }
}
