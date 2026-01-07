package com.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.domain.pojo.Appointment;

/**
 * @Author Dosphy
 * @Date 2026/1/7 11:36
 */
public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);

}
