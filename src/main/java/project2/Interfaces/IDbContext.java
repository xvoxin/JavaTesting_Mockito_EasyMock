package project2.Interfaces;

import project2.Models.Client;
import project2.Models.Order;
import project2.Models.Product;

import java.util.List;

public interface IDbContext
{
    boolean AddClient(Client client);
    Client GetClientByName(String name);
    List<Client> GetAllClients();
    boolean DeleteClient(Client client);
    void EditClientEmail(String email, Client client);

    boolean AddProduct(Product product);
    Product GetProductByName(String name);
    List<Product> GetAllProducts();
    boolean DeleteProduct(Product product);
    boolean EditProduct(Product product);

    List<Order> GetClientOrders(Client client);
    List<Product> GetProductsByOrder(Order order);
    boolean CreateOrder(Product product, Order order);

    boolean AddOrder(Order order);
    Order GetOrderById(int id);
    boolean DeleteOrder(Order order);
}
