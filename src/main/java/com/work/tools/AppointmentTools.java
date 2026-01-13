package com.work.tools;

import com.work.domain.pojo.Appointment;
import com.work.domain.pojo.Lesson;
import com.work.service.AppointmentService;
import com.work.utils.UserContextHolder;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Dosphy
 * @Date 2026/1/7 11:34
 */
@Component
public class AppointmentTools {

    @Autowired
    AppointmentService appointmentService;

    //TODO可行性待研究
    @Tool(name="制定学习计划", value = "根据参数，先执行工具方法queryBySubjectAndTeacher查询是否存在相关课程，存在则随机设置学习时长（单位周）后执行bookAppointment方法，不存在则返回不存在相关课程或老师，不要过多回答。")
    public String bookAppointment(Appointment appointment) {
        // 查找数据库中是否包含对应的预约记录
        appointment.setUsername(UserContextHolder.getUser().getUsername());
        Appointment one = appointmentService.getOne(appointment);

        if (one == null) {
            appointment.setId(null);//防止大模型幻觉设置了id
            if(appointmentService.save(appointment)) {
                return "制定学习计划成功，返回学习计划详情...";
            } else
                return "制定学习计划失败...";
        }
        return "您已有相同学习计划";
    }

    @Tool(name = "取消学习计划",value = "根据参数，查询学习计划是否存在，如果存在则删除学习计划并返回取消学习计划成功，否则返回取消学习计划失败。")
    public String cancelAppointment(Appointment appointment) {
        //查找数据库中是否包含对应的学习计划
        Appointment one = appointmentService.getOne(appointment);


        if (one != null) {
            if(appointmentService.removeById(one.getId())){
                return "取消学习计划成功...";
            } else
                return "取消学习计划失败...";
        }

        return "您没有预约记录，请核对预约科室和时间";
    }

    @Tool(name = "查询是否有课程",value = "根据课程名称，课程对应老师查询是否有课程，并返回给用户")
    public boolean queryBySubjectAndTeacher(Lesson lesson) {
        System.out.println("查询是否有课程");
        System.out.println("课程名称：" + lesson.getSubject());
        System.out.println("课程对应老师：" + lesson.getTeacher());

        //查找数据库是否存在相关课程
        Lesson one = appointmentService.getOne(lesson);

        if(one != null) {
            return true;
        } else
            return false;
    }

    @Tool(name = "获取课程和老师",value = "查询所有课程和老师,并返回给用户")
    public String getSubjectAndTeacher() {

        //获取所有课程数据
        List<Lesson> lessons = appointmentService.getAllLessons();

        //处理空数据场景
        if (lessons == null || lessons.isEmpty()) {
            return "当前数据库中暂无课程和老师相关信息";
        }

        List<Lesson> lessonsInfoList = lessons.stream()
                //过滤掉空数据或无效课程
                .filter(l -> l != null && l.getSubject() != null && l.getTeacher() != null)
                .collect(Collectors.toList());

        //转换为JSON格式
        String resultJson = JSON.toJSONString(lessonsInfoList);

        return String.format("数据库中查询到的所有课程和老师信息如下：%s", resultJson);
    }
}
