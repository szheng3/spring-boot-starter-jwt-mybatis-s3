package com.digitalsoftware.accounting.emun.domain;


import com.digitalsoftware.accounting.emun.CodeTypeByteEnum;

public enum StatusState implements CodeTypeByteEnum<StatusState> {
        INACTIVE((byte)0),
        ACTIVE((byte)1);

        private final Byte code;

        StatusState(Byte code) {
                this.code = code;
        }

        @Override
        public Byte getCode(){
                return this.code;
        }
}