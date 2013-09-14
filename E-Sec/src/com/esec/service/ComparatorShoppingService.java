package com.esec.service;

import java.util.Comparator;

import com.esec.model.Shopping;


public class ComparatorShoppingService implements Comparator<Shopping> {

	@Override
	public int compare(Shopping 
			first, Shopping second) {		
		return (first.getTitle().compareTo(second.getTitle()));
	}

}
