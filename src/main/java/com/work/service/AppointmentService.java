package com.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.domain.pojo.Appointment;
import com.work.domain.pojo.Lesson;

import java.util.List;


/**
 * @Author Dosphy
 * @Date 2026/1/7 11:36
 */
public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);

    Lesson getOne(Lesson lesson);

    List<Lesson> getAllLessons();
}
