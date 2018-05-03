package project2.Controllers;

import project2.Interfaces.*;
import project2.Models.*;

import java.util.List;

public class WebShopController implements IWebShopController
{
    IDbContext dbcontext;

    public WebShopController(IDbContext dbcontext)
    {
        this.dbcontext = dbcontext;
    }

    public boolean AddClient (Client client)
    {
        return dbcontext.AddClient(client);
    }

    public int AddClients (List<Client> clients)
    {
        int res = 0;
        for (Client client : clients)
        {
            if(dbcontext.AddClient(client))
                res++;
        }
        return res;
    }

    public Client GetClientByName (String name)
    {
        return dbcontext.GetClientByName(name);
    }

    public List<Client> GetAllClients ()
    {
        return dbcontext.GetAllClients();
    }

    public boolean DeleteClient (Client client)
    {
        return dbcontext.DeleteClient(client);
    }

    public void EditClientEmail (String email, Client client)
    {
        dbcontext.EditClientEmail(email, client);
    }

    public boolean AddProduct (Product product)
    {
        return dbcontext.AddProduct(product);
    }

    public int AddProducts (List<Product> products)
    {
        int res = 0;
        for (Product product : products)
        {
            if(dbcontext.AddProduct(product))
                res++;
        }
        return res;
    }

    public Product GetProductByName (String name)
    {
        return dbcontext.GetProductByName(name);
    }

    public List<Product> GetAllProducts ()
    {
        return dbcontext.GetAllProducts();
    }

    public boolean DeleteProduct (Product product)
    {
        return dbcontext.DeleteProduct(product);
    }

    public boolean EditProduct (Product product)
    {
        return dbcontext.EditProduct(product);
    }

    public List<Order> GetClientOrders (Client client)
    {
        return dbcontext.GetClientOrders(client);
    }

    public List<Product> GetProductsByOrder (Order order)
    {
        return dbcontext.GetProductsByOrder(order);
    }

    public boolean CreateOrder(Product product, Order order)
    {
        return dbcontext.CreateOrder(product, order);
    }

    public boolean AddOrder (Order order)
    {
        return dbcontext.AddOrder(order);
    }

    public Order GetOrderById (int id)
    {
        return dbcontext.GetOrderById(id);
    }

    public boolean DeleteOrder (Order order)
    {
        return dbcontext.DeleteOrder(order);
    }
}
