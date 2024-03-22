package com.ohgiraffers.common;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	/* 설명. 쇼핑카트에 담긴 상품들 */
	private final List<Product> items;
	
	public ShoppingCart() {
		items = new ArrayList<>();
	}

	/* 설명. 카트에 물품을 담는 기능 */
	public void addItem(Product item) {
		items.add(item);
	}

	/* 설명. 카트에 담긴 물품을 출력하는 기능 */
	public List<Product> getItem() { return items; }
}
