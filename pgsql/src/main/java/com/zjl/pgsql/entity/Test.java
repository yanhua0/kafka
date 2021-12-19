package com.zjl.pgsql.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.zjl.pgsql.mapper.handler.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Table(name = "test")
@Entity
@TypeDef(name = "JsonType", typeClass = JsonType.class)
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "obj")
    @Type(type = "JsonType")
    private JsonNode obj;
}
