package com.zjl.pgsql.mapper;

import com.zjl.pgsql.dto.UsersReqDTO;
import com.zjl.pgsql.dto.UsersResDTO;
import com.zjl.pgsql.entity.Users;

import java.util.List;

/***
 * --按年分组查看
 *
 *    select  to_char(time_field, 'YYYY') as d ,  count(id)  as  total_count,sum (count_field)  as  total_amount from  table_name
 *   where  time_field  between  start_time  and  end_time group by d
 *
 * --按月分组查看
 *    select  to_char(time_field, 'YYYY-MM') as d ,  count(id)  as  total_count,sum (count_field)   as  total_amount from  table_name
 *   where time_field between  start_time  and  end_time  group by d
 *
 * --按天分组查看
 *    select  to_char(time_field, 'YYYY-MM-DD') as d ,  count(id)  as  total_count,sum (count_field)   as  total_amount from  table_name
 *   where time_field between  start_time  and  end_time  group by d
 *
 *
 * --按小时分组查看
 *      select  to_char(time_field, 'YYYY-MM-DD  HH24 ' ) as d ,  count(id)  as  total_count,sum (count_field)   as  total_amount from  table_name
 *   where time_field  between start_time  and  end_time  group by d  order  by  d
 *
 * --按秒分组查看
 *      select  to_char(time_field, 'YYYY-MM-DD  HH24:MI:SS ' ) as d ,  count(id)  as  total_countl,sum (count_field)   as  total_amount from  table_name
 *   where  time_field  between start_time  and  end_time  group by d
 */
public interface UsersMapper {
    List<UsersResDTO> groupByTime(UsersReqDTO usersReqDTO);
    List<Users> findAll();
}
