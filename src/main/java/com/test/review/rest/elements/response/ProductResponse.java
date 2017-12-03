package com.test.review.rest.elements.response;


import javax.xml.bind.annotation.XmlRootElement;

import com.test.review.model.Product;

@XmlRootElement
public class ProductResponse extends BaseResponse{
	private Product  item;

	public Product getItem() {
		return item;
	}

	public void setItem(Product item) {
		this.item = item;
	}

}
