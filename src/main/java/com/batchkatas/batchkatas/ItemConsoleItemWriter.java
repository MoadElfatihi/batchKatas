package com.batchkatas.batchkatas;

import java.util.List;

import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

public class ItemConsoleItemWriter extends AbstractItemStreamItemWriter<Object> {

	@Override
	public void write(List<?> list) throws Exception {
		list.stream().forEach( System.out::println );
		System.out.println("******* Writing each chunk ********");
	}
}
