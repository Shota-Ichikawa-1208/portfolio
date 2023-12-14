package beans;

import java.io.Serializable;

import DB_Connection.Get_UnitpriceDAO;

public class Order_ProductBeans implements Serializable{
	private String product_code;
	private String product_color;
	private String size ;
	private int quantity; 
	
	//商品単価格
	//private int T_shirt_unitprice = 1890;
	//private int Jeans_unitprice = 3980;
	
	
	public Order_ProductBeans() {}
	public Order_ProductBeans(String size,String qusntity) {
		
		this.size = size;
		
		//文字列を値に変換
		Integer Intqusntity = Integer.parseInt(qusntity);
		this.quantity = Intqusntity;
	}
	
	//setterの定義
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public void setProduct_color(String product_color) {
		this.product_color = product_color;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setQuantity(String quantity) {
		//文字列を値に変換
		Integer Intqusntity = Integer.parseInt(quantity);
		this.quantity = Intqusntity;
	}
	
	//getterの定義
	public String getProduct_color() {
		return this.product_color;
	}
	public String getSize() {
		return this.size;
	}
	public int getQuantity() {
		return this.quantity;
	}
	
	
	/*商品別の金額算出 
	 プログラム上で定価を決めるのは良くないデータベースから商品の定価を持って来てそれを掛ければいい*/
//	public int getProduct_price() {
//		if(this.product_code.equals("t")) {
//			return  T_shirt_unitprice * this.quantity;
//		}else if(this.product_code.equals("j")) {
//			return Jeans_unitprice * this.quantity;
//		}
//		return 0;
//	}
	
	//DB上で商品単価を変更できるようにする
	Get_UnitpriceDAO getPriceDAO = new Get_UnitpriceDAO();
	private int Product_Price = 0;
	public void setProduct_price(String product_color) {
		Product_Price = getPriceDAO.getUnitPrice(product_color);
	}
	public int getProduct_price() {
		return Product_Price * this.quantity;
	}
}