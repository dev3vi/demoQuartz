//package com.example.demoquartz;
//
//import org.quartz.SchedulerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//import java.io.IOException;
//
//public class ScheduleConfig {
//    @Autowired
//    private ApplicationContext applicationContext;
//    @Bean
//    public SchedulerFactoryBean quartzScheduler() throws IOException, SchedulerException
//    {
//        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
//
//        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
//        jobFactory.setApplicationContext(applicationContext);
////        quartzScheduler.setJobFactory(jobFactory);
////        scheduler.setTriggers(jobOneTrigger(), jobTwoTrigger());
////        scheduler.setQuartzProperties(quartzProperties());
////        scheduler.setJobDetails(jobOneDetail(), jobTwoDetail());
//        scheduler.setApplicationContextSchedulerContextKey("applicationContext");
//        return scheduler;
//    }
//}
