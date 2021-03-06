/**
 * Sort list event by date
 */
package com.esec.comparator;

import java.util.Comparator;

import com.esec.model.Todo;

public class ComparatorListEvents implements Comparator<Todo> {

	@Override
	public int compare(Todo lhs, Todo rhs) {
		return (lhs.getDate() < rhs.getDate() ? -1 : (lhs.getDate() == rhs
				.getDate() ? 0 : 1));
	}
}
