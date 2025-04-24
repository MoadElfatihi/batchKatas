package com.batchkatas.batchkatas.xml;

import com.batchkatas.batchkatas.ItemConsoleItemWriter;
import com.batchkatas.batchkatas.model.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class XmlBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job xmlCustomerJob(){
		return jobBuilderFactory.get( "xmlCustomerJob" )
				.start( xmlStepJob() )
				.build();
	}

	@Bean
	public Step xmlStepJob(){
		return stepBuilderFactory.get( "xmlStepCustomerJob" )
				.chunk( 3 )
				.reader( xmlCustomerReader() )
				.writer( new ItemConsoleItemWriter() )
				.build();

	}

	@Bean
	public StaxEventItemReader xmlCustomerReader(){
		StaxEventItemReader reader = new StaxEventItemReader();
		reader.setResource( new FileSystemResource( "files/customer.xml" ) );

		reader.setFragmentRootElementName( "customer" );
		reader.setUnmarshaller( new Jaxb2Marshaller(){
			{
				setClassesToBeBound( Customer.class );
			}
		} );

		return reader;
	}
}
