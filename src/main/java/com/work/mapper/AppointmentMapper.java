package com.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.domain.pojo.Appointment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Dosphy
 * @Date 2026/1/7 11:35
 */
@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
}
