package com.batchkatas.batchkatas.helloworld;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println(">>>>>launching step="+stepExecution.getStepName()+"\n"
							+ ">>>>>>> with executionContext="+stepExecution.getJobExecution().getExecutionContext().get( "name" ));
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println(">>>>>Ending step="+stepExecution.getStepName());
		return null;
	}
}
