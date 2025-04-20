package com.batchkatas.batchkatas.helloworld.reader;

import java.util.Arrays;
import java.util.List;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;

public class HelloWorldItemReader extends AbstractItemStreamItemReader<Integer> {

	private final List<Integer> myList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	int index = 0;

	@Override
	public Integer read() {
		Integer nextItem = null;


		if(index < myList.size()){
			nextItem = myList.get(index);
			index++;
		}
		else {
			index = 0;
		}
		return nextItem;
	}
}
