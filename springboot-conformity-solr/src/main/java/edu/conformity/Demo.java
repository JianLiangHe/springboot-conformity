package edu.conformity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Demo {

	public static void main(String[] args) {
		int[] arr = {12, 89, 2, 3, 12, 51, 13, 12, 12, 6, 1};
		Set<Integer> set = new HashSet<Integer>();
		List list = Arrays.asList(arr);
		set.addAll(list);
		Iterator it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		//List<Object> list = Arrays.asList(arr);
		
		/*Iterator it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}*/
		//test1();

		
	}
	
	public static void test1() {
		List list = new ArrayList();
		list.add(2);
		list.add(23);
		list.add(2);
		list.add(3);
		list.add(2);
		list.add(43);
		list.add(2);
		list.add(11);
		
		Iterator it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
}
