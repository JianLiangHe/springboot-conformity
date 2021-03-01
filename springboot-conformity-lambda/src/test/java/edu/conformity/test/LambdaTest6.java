package edu.conformity.test;

import java.util.ArrayList;
import java.util.Collections;

import edu.conformity.pojo.Item;

/**
 * lambda遍历集合
 * @author hejianliang
 *
 */
public class LambdaTest6 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7);
		
		// lambda表达式方法引用
		list.forEach(System.out::println);
		
		System.out.println("===========================");
		
		list.forEach(element -> {
			if (element % 2 == 0) {
				System.out.println(element);
			}
		});
		
		System.out.println("===========================");
		
		// 删除集合元素，带条件
		list.removeIf(element -> (element % 2 == 0));
		list.forEach(System.out::println);
		
		System.out.println("=========================== sortList");
		
		sortList();
	}
	
	/**
	 * list排序
	 */
	public static void sortList() {
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(new Item(5, "背心", 11.2));
		list.add(new Item(2, "袜子", 9.8));
		list.add(new Item(1, "秋裤", 12.5));
		list.add(new Item(3, "衬衫", 10.6));
		list.add(new Item(4, "羽绒", 110));
		
		list.sort((p1, p2) -> p1.getId() - p2.getId());
		System.out.println(list);
		list.forEach(obj -> System.out.println(obj.getId()));
	}
	
}
