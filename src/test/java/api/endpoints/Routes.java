package api.endpoints;

//https://petstore.swagger.io/v2/user

//https://petstore.swagger.io/v2/user/{username}

public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";

	// User module routes
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String update_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";

	// Store module routes
    public static String placeOrder_url = base_url + "/store/order";
    public static String getOrder_url = base_url + "/store/order/{orderId}";
    public static String deleteOrder_url = base_url + "/store/order/{orderId}";
    public static String inventory_url = base_url + "/store/inventory";
    
    //Pet module routes
    public static String post_pet_url = base_url + "/pet";                     
    public static String get_pet_url = base_url + "/pet/{petId}";   
    public static String update_pet_url = base_url + "/pet";                   
    public static String delete_pet_url = base_url + "/pet/{petId}";           
    public static String find_by_status_url = base_url + "/pet/findByStatus";


}
