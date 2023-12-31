package rss.solution;

public class Customer extends User {
        
	public Customer(String username, String password) {
		super(username, password, "Customer");
		this.setAccountType("Customer");
	}
        
	public Boolean updateAccount(String password, String email, int phone){
		UserDao ud = new UserDao();
		return ud.updateUser(this.getUsername(), password, this.getaccountType(), email, phone);
	}
		
	public String placeOrder(String payment, String address, double price){
		OrderDao od = new OrderDao();
                //instantiate the customer object in the OrderDao
		od.setCustomer(this.getUsername(), this.getPassword());
		return od.addOrder(payment, address, price);
	}
       
	
	// update order
	public Boolean updateOrder(String orderId, String address){
		// instantiate the customer object in the OrderDao
                OrderDao od = new OrderDao();
		od.setCustomer(this.getUsername(), this.getPassword());
		od.getOrder(orderId);
		return od.updateOrder(orderId, this.getUsername(), od.o.getOrderTime(),
			od.o.getPayment(), address, od.o.getPrice(), od.o.getStatus());
	}
	
	// cancel order
	public Boolean cancelOrder(String orderId){
		// instantiate the customer object in the OrderDao
                OrderDao od = new OrderDao();
		od.setCustomer(this.getUsername(), this.getPassword());
		od.getOrder(orderId);
		return od.updateOrder(orderId, this.getUsername(), od.o.getOrderTime(),
			od.o.getPayment(), od.o.getAddress(), od.o.getPrice(), "Cancel");
	}
		
}
