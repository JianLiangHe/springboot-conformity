package edu.conformity.test;

import edu.conformity.pojo.Item;

/**
 * lambda构造方法的引用
 * @author hejianliang
 *
 */
public class LambdaTest4 {

	public static void main(String[] args) {
		ItemCreatorBlankConstruct creator = () -> new Item();
		Item item = creator.getItem();
		
		ItemCreatorBlankConstruct creator2 = Item::new;
		Item item2 = creator2.getItem();
		
		ItemCreatorParamContruct creator3 = Item::new;
		creator3.getItem(1, "pen", 2.00);
	}
	
}

interface ItemCreatorBlankConstruct {
	Item getItem();
}

interface ItemCreatorParamContruct {
	Item getItem(int id, String name, double price);
}
