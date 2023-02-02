package scenarios;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<Product> productList;
	
	public int calculateTotalCartValue() {
		int totalCost = 0;
		for (Product product : productList) {
			totalCost += product.getPrice();
		}
		return totalCost;
	}

	public void addProduct(Product product) {
		if(this.productList == null) {
			this.productList = new ArrayList<Product>();
		}
		this.productList.add(product);		
	}

}
