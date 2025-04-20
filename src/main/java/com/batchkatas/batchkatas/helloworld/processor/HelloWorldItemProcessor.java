package com.batchkatas.batchkatas.helloworld.processor;

import org.springframework.batch.item.ItemProcessor;

public class HelloWorldItemProcessor implements ItemProcessor<Integer,Integer> {

	@Override
	public Integer process(Integer item) throws Exception {
		return Integer.sum(item,10);
	}
}
