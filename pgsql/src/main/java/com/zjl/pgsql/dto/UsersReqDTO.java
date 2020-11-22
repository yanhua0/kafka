package com.zjl.pgsql.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zjl.pgsql.enums.DatePatternEnums;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *  select left_table.report_time,coalesce(b.count,0) from
 *       (select to_char(to_date('2020-09-01 09:00:00', 'YYYY-MM')::timestamp+(full_time_table.add_num||'month')::interval,'YYYY-MM')
 *       as join_time from generate_series(0,
 * --       根据年、月、日统计修改做差方法
 *       date('2019-06-05')-date('2019-06-01'))
 *       as full_time_table(add_num)
 *       )
 *       as left_table
 *       left join (
 *       select count(*),to_char(report_time, 'yyyy-MM') as report_time from users group by report_time) real_table
 *       on left_table.join_time = real_table.report_time
 */
@Getter
@Setter
public class UsersReqDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    //年、月、日统计的枚举值
    private DatePatternEnums datePatternEnums=DatePatternEnums.YYYY_MM_DD;
}
