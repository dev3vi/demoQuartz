package com.example.demoquartz;



import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

//import com.jelies.spring3tomcat7.config.quartz.AutowiringSpringBeanJobFactory;

@Configuration
public class QuartzConfig {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        log.debug("QuartzConfig initialized.");
    }

    @Bean
    public SchedulerFactoryBean quartzScheduler() {
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

//        quartzScheduler.setDataSource(dataSource);
//        quartzScheduler.setTransactionManager(transactionManager);
        quartzScheduler.setOverwriteExistingJobs(true);
        quartzScheduler.setSchedulerName("jelies-quartz-scheduler");

        // custom job factory of spring with DI support for @Autowired!
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        quartzScheduler.setJobFactory(jobFactory);

        quartzScheduler.setQuartzProperties(quartzProperties());

//        Trigger[] triggers = { procesoMQTrigger().getObject() };
//        quartzScheduler.setTriggers(triggers);

        return quartzScheduler;
    }

//    @Bean
//    public JobDetailFactoryBean procesoMQJob() {
//        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
//        jobDetailFactory.setJobClass(ExampleService.class);
//        jobDetailFactory.setGroup("spring3-quartz");
//        return jobDetailFactory;
//    }

//    @Bean
//    public CronTriggerFactoryBean procesoMQTrigger() {
//        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
//        cronTriggerFactoryBean.setJobDetail(procesoMQJob().getObject());
//        cronTriggerFactoryBean.setCronExpression(0 * * * * ?);
//        cronTriggerFactoryBean.setGroup("spring3-quartz");
//        return cronTriggerFactoryBean;
//    }

    @Bean
    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        Properties properties = null;
        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();

        } catch (IOException e) {
            log.warn("Cannot load quartz.properties.");
        }

        return properties;
    }
}
