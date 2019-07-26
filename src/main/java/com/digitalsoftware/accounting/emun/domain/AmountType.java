
package com.digitalsoftware.accounting.emun.domain;


import com.digitalsoftware.accounting.emun.CodeTypeByteEnum;

public enum AmountType implements CodeTypeByteEnum<AmountType> {
        MINOR((byte)0),
        PLUS((byte)1);

        private final Byte code;

        AmountType(Byte code) {
                this.code = code;
        }

        @Override
        public Byte getCode(){
                return this.code;
        }
}
