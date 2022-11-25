package org.test.dynamic.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DynamicMapper {

    void insertListMapMysql(@Param("tableName") String tableName,@Param("list") List<Map<String,Object>> list);

    void insertListMap(@Param("tableName") String tableName,@Param("list") List<Map<String,Object>> list);

    void updateListMap(@Param("tableName") String tableName,@Param("list") List<Map<String,Object>> list,@Param("idKey") String idKey);

    void deleteByIds(@Param("tableName") String tableName,@Param("list") List<Integer> ids,@Param("idKey") String idKey,@Param("setMap") Map<String,Object> setMap);
}
