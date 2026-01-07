package com.work.tools;

import com.work.domain.pojo.Appointment;
import com.work.domain.pojo.Lesson;
import com.work.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Dosphy
 * @Date 2026/1/7 11:34
 */
@Component
public class AppointmentTools {

    @Autowired
    AppointmentService appointmentService;

    //TODO可行性待研究
    @Tool(name="制定学习计划", value = "根据参数，先执行工具方法quertBySubjectAndTeacher查询是否存在相关课程，并直接给用户回答是否可制定计划，并让用户确认所有学习计划信息，用户确认后再进行制定。")
    public String bookAppointment(Appointment appointment) {
        //查找数据库中是否包含对应的预约记录
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
    public boolean quertBySubjectAndTeacher(Lesson lesson) {
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
}
