package com.batchkatas.batchkatas.csv;

import java.util.List;

import com.batchkatas.batchkatas.model.ProductDto;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

public class ProductConsoleItemWriter extends AbstractItemStreamItemWriter<Object> {

	@Override
	public void write(List<?> list) throws Exception {
		list.stream().forEach( System.out::println );
		System.out.println("******* Writing each chunk ********");
	}
}
