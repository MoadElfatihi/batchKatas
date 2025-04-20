package com.batchkatas.batchkatas.helloworld;


import com.batchkatas.batchkatas.helloworld.processor.HelloWorldItemProcessor;
import com.batchkatas.batchkatas.helloworld.reader.HelloWorldItemReader;
import com.batchkatas.batchkatas.helloworld.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class HelloWorldBatchConfig {

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
				.incrementer( new RunIdIncrementer() )
				.listener( helloWorldExecutionListener )
				.start( testStep() )
				.next( step1() )
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

	@Bean
	public Step step1(){
		return stepBuilder.get("step1")
				.<Integer,Integer>chunk( 3 )
				.reader( helloWorldReader() )
				.processor( helloWorldProcessor() )
				.writer( helloWorldItemWriter() )
				.build();
	}

	@Bean
	public ItemReader<Integer> helloWorldReader() {
		return new HelloWorldItemReader();
	}

	@Bean
	public ItemProcessor<Integer,Integer> helloWorldProcessor(){
		return new HelloWorldItemProcessor();
	}

	@Bean
	public ItemWriter<Integer> helloWorldItemWriter(){
		return new ConsoleItemWriter();
	}

}
