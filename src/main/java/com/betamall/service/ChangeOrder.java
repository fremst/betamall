package com.betamall.service;

import com.betamall.dao.OrderDao;
import com.betamall.dao.StockDao;
import com.betamall.dto.OrderDto;

public class ChangeOrder {
	
	public static int changeOrdSta(int ordNo, String ordSta) {
		OrderDao orderDao = OrderDao.getInstance();

		if(ordSta.equals("주문취소")) {
			int n = StockDao.getInstance().changeStock(ordNo, +1);
			if(n>0) {
				n = orderDao.delete(ordNo);
			}
			return n;
		}
		
		OrderDto orderDto = orderDao.select(ordNo);
		orderDto.setOrdSta(ordSta);
		int n = orderDao.update(orderDto);
		
		if(ordSta.equals("결제취소")) {
			if (n > 0) {
				n = StockDao.getInstance().changeStock(ordNo, +1);
			}
		}
		return n;
	}
}
