package com.batchkatas.batchkatas.csv;


import com.batchkatas.batchkatas.ItemConsoleItemWriter;
import com.batchkatas.batchkatas.model.ProductDto;
import com.batchkatas.batchkatas.model.SupplierDto;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.Job;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
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
	public Job csvProductJob(){
		return jobBuilderFactory.get( "csvProductJob" )
				.incrementer( new RunIdIncrementer() )
				.start( csvStep() )
				.next( csvProductSupplierStep() )
				.build();
	}

	@Bean
	public Step csvProductSupplierStep(){
		return stepBuilderFactory.get( "csvProductSupplier" )
				.chunk( 3 )
				.reader( productSupplierReader() )
				.writer( new ItemConsoleItemWriter() )
				.build();
	}

	@Bean
	public ItemReader<SupplierDto> productSupplierReader(){
		return new FlatFileItemReaderBuilder<SupplierDto>()
				.name( "supplierReader" )
				.resource( new FileSystemResource( "files/products_supplier.csv" ) )
				.delimited()
				.names( new String[]{"registrationNumber","lastName","firstName","position","birthYear","debutYear"} )
				.linesToSkip( 1 )
				.fieldSetMapper(
						new BeanWrapperFieldSetMapper<SupplierDto>(){
							{
								setTargetType( SupplierDto.class );
							}
						}
				)
				.build();
	}
	@Bean
	public Step csvStep(){
		return stepBuilderFactory.get( "csvProductStep" )
				.chunk( 3 )
				.reader(  productCsvReader())
				.writer(  new ItemConsoleItemWriter())
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
