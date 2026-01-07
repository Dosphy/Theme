package com.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.domain.pojo.Appointment;
import com.work.mapper.AppointmentMapper;
import com.work.service.AppointmentService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Dosphy
 * @Date 2026/1/7 11:36
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public Appointment getOne(Appointment appointment) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getUsername, appointment.getUsername());
        queryWrapper.eq(Appointment::getTeacher, appointment.getTeacher());
        queryWrapper.eq(Appointment::getSubject, appointment.getSubject());
        queryWrapper.eq(Appointment::getTime, appointment.getTime());
        queryWrapper.eq(Appointment::getAddTime, appointment.getAddTime());

        Appointment appointmentDB = appointmentMapper.selectOne(queryWrapper);
        return appointmentDB;
    }
}
