package com.test.review.rest.elements.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.test.review.model.Category;

@XmlRootElement
public class CategoryResponse extends BaseResponse{
	private List<Category> items;
	private int totalPages;
	public List<Category> getItems() {
		return items;
	}
	public void setItems(List<Category> items) {
		this.items = items;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
