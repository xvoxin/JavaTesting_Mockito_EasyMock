package project2.Interfaces;

import project2.Models.Client;
import project2.Models.Order;
import project2.Models.Product;

import java.util.List;

public interface IDbContext
{
    void AddClient(Client client);
    Client GetClientByName(String name);
    List<Client> GetAllClients();
    void DeleteClient(Client client);
    void EditClient(Client client);

    void AddProduct(Product product);
    Product GetProductByName(String name);
    List<Product> GetAllProducts();
    void DeleteProduct(Product product);
    void EditProduct(Product product);

    List<Order> GetClientOrders(Client client);
    List<Product> GetProductsByOrder(Order order);
    void CreateOrder(Product product, Order order);

    void AddOrder(Order order);
    Order GetOrderById(int id);
    void DeleteOrder(Order order);
}
