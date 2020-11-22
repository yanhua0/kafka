# sql按天统计记录数，查询记录为空返回0
SELECT
	left_table.join_time AS TIME,
	COALESCE ( real_table.COUNT, 0 ) AS COUNT 
FROM
	(
	SELECT
		to_char( to_date( '2020-11-22 22:12:48', 'YYYY-MM' ) :: TIMESTAMP + ( full_time_table.add_num || 'month' ) :: INTERVAL, 'YYYY-MM' ) AS join_time 
	FROM
		generate_series (
			0,
			(
				EXTRACT (
				YEAR 
				FROM
					age( '2020-11-22 22:12:48', '2020-11-22 22:12:48' )) * 12 + EXTRACT (
				MONTH 
				FROM
				age( '2020-11-22 22:12:48', '2020-11-22 22:12:48' ))) :: INT 
		) AS full_time_table ( add_num ) 
	) AS left_table
	LEFT JOIN ( SELECT COUNT ( * ), to_char( report_time, 'YYYY-MM' ) AS report_time FROM users GROUP BY report_time ) real_table ON left_table.join_time = real_table.report_time
# pgsql日期加减法
SELECT now()::timestamp + '1 year';  --当前时间加1年
SELECT now()::timestamp + '1 month';  --当前时间加一个月
SELECT now()::timestamp + '1 day';  --当前时间加一天
SELECT now()::timestamp + '1 hour';  --当前时间加一个小时
SELECT now()::timestamp + '1 min';  --当前时间加一分钟
SELECT now()::timestamp + '1 sec';  --加一秒钟
select now()::timestamp + '1 year 1 month 1 day 1 hour 1 min 1 sec';  --加1年1月1天1时1分1秒
SELECT now()::timestamp + (col || ' day')::interval FROM table --把col字段转换成天 然后相加
# pgsql 日期差计算方法大全
- 日期之差
  --获取秒差
SELECT round(date_part('epoch', TIMESTAMP '2019-05-05 12:11:20' - TIMESTAMP '2019-05-05 10:10:10'));
  --获取分钟差
SELECT round(date_part('epoch', TIMESTAMP '2019-05-05 12:11:20' - TIMESTAMP '2019-05-05 10:10:10')/60);
  --获取小时差
SELECT round(date_part('epoch', TIMESTAMP '2019-05-05 12:11:20' - TIMESTAMP '2019-05-05 10:10:10')/60/60);
  --获取天数差
SELECT Date('2019-06-05') - Date('2019-05-03');
--获取月份差
select extract(year from age( '2019-04-05', '2017-02-04')) * 12  + extract(MONTH from age( '2019-04-05', '2017-02-04'));
--获取年份差
SELECT extract(year from age( '2018-04-05', '2017-02-04'));