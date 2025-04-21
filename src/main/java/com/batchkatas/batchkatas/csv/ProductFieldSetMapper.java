package com.batchkatas.batchkatas.csv;

import java.math.BigDecimal;

import com.batchkatas.batchkatas.model.ProductDto;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ProductFieldSetMapper implements FieldSetMapper<ProductDto> {

	@Override
	public ProductDto mapFieldSet(FieldSet fieldSet) throws BindException {
		Integer productId = fieldSet.readInt(0);
		String productName = fieldSet.readString(1);
		String productDesc = fieldSet.readString(2);
		BigDecimal price = fieldSet.readBigDecimal( 3);
		Integer unit = fieldSet.readInt(4);

		return new ProductDto( productId,productName,productDesc,price,unit );
	}
}
