package com.digitalsoftware.accounting.emun;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CodeTypeByteHandler<E extends CodeTypeByteEnum> extends BaseTypeHandler<E> {

        private Map<Byte, E> enumMap = new HashMap<Byte, E>();

        public CodeTypeByteHandler(Class<E> type) {

                E[] enums = type.getEnumConstants();
                if (enums == null) {
                        throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
                }

                for(E e : enums){
                        enumMap.put(e.getCode(), e);
                }

        }

        @Override
        public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
                ps.setByte(i, parameter.getCode());
        }

        @Override
        public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
                return enumMap.get(rs.getByte(columnName));
        }

        @Override
        public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
                return enumMap.get(rs.getByte(columnIndex));
        }

        @Override
        public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
                return enumMap.get(cs.getByte(columnIndex));
        }
}