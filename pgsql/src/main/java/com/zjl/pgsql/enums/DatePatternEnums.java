package com.zjl.pgsql.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  DatePatternEnums{
    YYYY(0,"YYYY","year"),
    YYYY_MM(1,"YYYY-MM","month"),
    YYYY_MM_DD(2,"YYYY-MM-DD","day");
    private Integer code;
    private String datePattern;
    private String addUnit;
}
