package com.batchkatas.batchkatas.model;

import java.math.BigDecimal;

public record ProductDto(Integer productId, String productName, String productDesc, BigDecimal price,Integer unit) {

	@Override
	public String toString() {
		return "ProductDto{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", productDesc='" + productDesc + '\'' +
				", price=" + price +
				", unit=" + unit +
				'}';
	}
}
