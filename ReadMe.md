# 数据库表设计

-  g_teacher 存放 老师信息
- 根据g_teacher 中的teacher_id 到g_time 中去查询相关的预约时间，每个时间对应一个预约状态，-1 对应不可预约，0 显示预约失败，1 显示预约成功