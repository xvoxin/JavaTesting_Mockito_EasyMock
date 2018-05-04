package project2.Models;

public class Order
{
    public int Id;
    public int IdClient;

    public Order(int client)
    {
        IdClient = client;
    }
}
