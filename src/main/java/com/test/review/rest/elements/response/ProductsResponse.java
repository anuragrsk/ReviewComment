package com.test.review.rest.elements.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.test.review.model.Product;
@XmlRootElement
public class ProductsResponse extends BaseResponse{
	private int totalPages;
	private List<Product> items;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

}
