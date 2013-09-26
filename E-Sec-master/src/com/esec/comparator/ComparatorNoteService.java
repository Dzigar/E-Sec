package com.esec.comparator;

import java.util.Comparator;

import com.esec.model.Note;

public class ComparatorNoteService implements Comparator<Note>{

	@Override
	public int compare(Note first, Note second) {		
		return (first.getDate().compareTo(second.getDate()));
	}

}
