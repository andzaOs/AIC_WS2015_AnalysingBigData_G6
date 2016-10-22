package tuwien.ac.at.commons.util;

import java.util.ArrayList;
import java.util.List;

public class ListConverter {

	public static <E> List<E> convertToList(Iterable<E> iter) {
		List<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        list.add(item);
	    }
	    return list;
	}
	
}
