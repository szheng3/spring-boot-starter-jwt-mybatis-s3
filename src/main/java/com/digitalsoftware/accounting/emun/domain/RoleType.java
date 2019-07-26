package com.digitalsoftware.accounting.emun.domain;

import com.digitalsoftware.accounting.emun.CodeTypeByteEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public enum RoleType implements CodeTypeByteEnum<RoleType> {

        ROLE_USER((byte) 0),
        ROLE_ADMIN((byte) 1),
        ROLE_TRUSTED_CLIENT((byte) 2),
        ROLE_REGISTER((byte) 3);


        private final Byte code;

        RoleType(Byte code) {
                this.code = code;
        }

        @Override
        public Byte getCode() {
                return this.code;
        }
}
