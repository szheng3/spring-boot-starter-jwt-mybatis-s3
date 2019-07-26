package com.digitalsoftware.accounting.mapper;

import reactor.core.publisher.Mono;

public interface Webflux<T, I> {
    Mono<T> findMono(I Id);
}
