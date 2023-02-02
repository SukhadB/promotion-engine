package scenarios;

public class Product {
	
	private String id;
	private int price;
	
	public Product(String id, int price) {
		super();
		this.id = id;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	

}
