package com.batchkatas.batchkatas.csv;


import com.batchkatas.batchkatas.model.ProductDto;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.Job;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class CsvBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job csvJob(){
		return jobBuilderFactory.get( "csvJob" )
				.incrementer( new RunIdIncrementer() )
				.start( csvStep() )
				.build();
	}

	@Bean
	public Step csvStep(){
		return stepBuilderFactory.get( "csvStep" )
				.chunk( 3 )
				.reader(  productCsvReader())
				.writer(  new ProductConsoleItemWriter())
				.build();
	}

	@Bean
	public ItemReader<ProductDto> productCsvReader(){
		var reader = new FlatFileItemReader<ProductDto>();
		reader.setResource( new FileSystemResource( "files/products.csv" ) );
		reader.setLinesToSkip( 1 );
		reader.setLineMapper( buildProductCsvLineMapper() );
		return reader;
	}

	private LineMapper<ProductDto> buildProductCsvLineMapper(){
		var lineMapper = new DefaultLineMapper<ProductDto>();
		lineMapper.setFieldSetMapper( new ProductFieldSetMapper() );
		lineMapper.setLineTokenizer( new DelimitedLineTokenizer() );
		return lineMapper;
	}
}
