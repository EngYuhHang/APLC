package rss.solution;

import java.util.List;
import java.util.function.Function;

public class Admin extends User{
	UserDao ud = new UserDao();
	ProductDao pd = new ProductDao();

	public Admin(String username, String password) {
	super(username, password, "Admin");
	this.setAccountType("Admin");
	}
	
	// add new admin account
        public Function<String, 
                Function<String, 
                Function<String, 
                Function<Integer, Boolean>>>>
                addAdmin = username -> password -> email -> phone ->
                        ud.addUser(username, password, "Admin", email, phone);
	
	// update Customer account
        public Function<String, 
                Function<String, 
                Function<String, 
                Function<Integer, Boolean>>>> 
                updateCustomer = username -> password -> email -> phoneNumber ->
                        ud.updateUser(username, password, "Customer", email, phoneNumber);
	
	// delete customer account
        public Function<String, 
                Function<String, Boolean>> 
                deleteUser = username -> type -> ud.deleteUser(username, type);

	
	// add new product
        public Function<String, 
                Function<String,
                Function<Boolean, 
                Function<Integer,
                Function<Double, Boolean>>>>>
                addProduct = name -> description -> fragile -> quantity -> price -> 
                        pd.addProduct(name, description, fragile, quantity, price);
	
	// update product details    
        public Function<String, 
                Function<String,
                Function<String,
                Function<Boolean, 
                Function<Integer,
                Function<Double, Boolean>>>>>>
                updateProduct = id -> name -> description -> fragile -> quantity -> price -> 
                        pd.updateProduct(id, name, description, fragile, quantity, price);
	
	// delete product
        public Function<String, Boolean>
                deleteProduct = id -> pd.deleteProduct(id);
	
	@Override
	public List<OrderItem> viewOrderItem(String orderId){
	OrderDao od = new OrderDao();
	od.getOrder(orderId);
	return od.o.getOrderItems();
	}
	
	// cancel order
	public Boolean editOrder(String orderId, String status){
		OrderDao od = new OrderDao();
		// instantiate the customer object in the OrderDao
		od.setCustomer(this.getUsername(), this.getPassword());
		od.getOrder(orderId);
		return od.updateOrder(orderId, this.getUsername(), od.o.getOrderTime(),
			od.o.getPayment(), od.o.getAddress(), od.o.getPrice(), status);
	}
}


