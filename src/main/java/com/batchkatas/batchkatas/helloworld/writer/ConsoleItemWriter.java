package com.batchkatas.batchkatas.helloworld.writer;

import java.util.List;

import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

public class ConsoleItemWriter extends AbstractItemStreamItemWriter<Integer> {

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		items.stream().forEach( System.out::println );
		System.out.println("******* Writing each chunk ********");
	}
}
