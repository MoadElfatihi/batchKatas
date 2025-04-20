package com.batchkatas.batchkatas.helloworld;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldExecutionListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("launching job="+jobExecution.getJobInstance().getJobName()+
							" with parameters="+jobExecution.getJobParameters())
						;
		jobExecution.getExecutionContext().put( "name", " My name" );
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("After  job execute for greeting ="+jobExecution.getExecutionContext().get( "name" ));

	}
}
