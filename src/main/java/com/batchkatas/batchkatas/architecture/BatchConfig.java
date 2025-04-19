package com.batchkatas.batchkatas.architecture;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilder;

	@Autowired
	private StepBuilderFactory stepBuilder;

	@Autowired
	private HelloWorldExecutionListener helloWorldExecutionListener;

	@Autowired
	private HelloWorldStepListener helloWorldStepListener;

	@Bean
	public Job testJob(){
		return jobBuilder.get( "testJob" )
				.listener( helloWorldExecutionListener )
				.start( testStep() )
				.build();
	}

	@Bean
	public Step testStep(){
		return stepBuilder.get( "testStep" )
				.listener( helloWorldStepListener )
				.tasklet( new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
							throws Exception {
						System.out.println("doing tasklet...");
						return RepeatStatus.FINISHED;
					}
				} )
				.build();
	}
}
