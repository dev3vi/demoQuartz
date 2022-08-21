package com.example.demoquartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class TestJob implements Job {

    @Autowired
    private Service service;
    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
        service.inject();
        System.out.println("Task is running!");
    }

}
