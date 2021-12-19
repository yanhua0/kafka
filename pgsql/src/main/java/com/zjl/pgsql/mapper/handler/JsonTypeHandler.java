package com.zjl.pgsql.mapper.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.postgresql.util.PGobject;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis json序列化
 */
@Slf4j
@MappedTypes(JsonNode.class)
@MappedJdbcTypes(JdbcType.OTHER)
public class JsonTypeHandler implements TypeHandler<JsonNode> {
    /**
     * 引入PGSQL提供的工具类PGobject
     */
  //  private static final PGobject JSON_OBJECT = new PGobject();

    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, JsonNode o, JdbcType jdbcType)
            throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        PGobject JSON_OBJECT = new PGobject();
        JSON_OBJECT.setType("json");
        JSON_OBJECT.setValue(objectMapper.writeValueAsString(o));
        preparedStatement.setObject(i, JSON_OBJECT);
    }

    @Override
    public JsonNode getResult(ResultSet resultSet, String s) throws SQLException {
        if (StringUtils.isEmpty(resultSet.getString(s))) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.readTree(resultSet.getString(s));
        } catch (IOException e) {
            log.warn("Parsing Json string failed, message:{} .", e.getMessage());
        }
        return null;
    }

    @Override
    public JsonNode getResult(ResultSet resultSet, int i) throws SQLException {
        if (StringUtils.isEmpty(resultSet.getString(i))) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.readTree(resultSet.getString(i));
        } catch (IOException e) {
            log.warn("Parsing Json string failed, message:{} ..", e.getMessage());
        }
        return null;
    }

    @Override
    public JsonNode getResult(CallableStatement callableStatement, int i) throws SQLException {
        if (StringUtils.isEmpty(callableStatement.getString(i))) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.readTree(callableStatement.getString(i));
        } catch (IOException e) {
            log.warn("Parsing Json string failed, message:{} ...", e.getMessage());
        }
        return null;
    }
}