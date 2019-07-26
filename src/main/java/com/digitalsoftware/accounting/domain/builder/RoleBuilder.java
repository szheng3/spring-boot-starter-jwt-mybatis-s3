package com.digitalsoftware.accounting.domain.builder;

import com.digitalsoftware.accounting.domain.generated.Role;
import com.digitalsoftware.accounting.emun.domain.RoleType;
import reactor.core.publisher.Mono;

public class RoleBuilder implements Builder<Mono<Role>> {

    private Integer idRole;

    private RoleType role;

    public RoleBuilder withIdRole(Integer idRole) {
        this.idRole = idRole;
        return this;
    }

    public RoleBuilder withRole(RoleType role) {
        this.role = role;
        return this;
    }

    @Override
    public Mono<Role> build() {
        return Mono.just(new Role(idRole, role));
    }

    public static RoleBuilder getBuilder() {
        return new RoleBuilder();
    }
}
