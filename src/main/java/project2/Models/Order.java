package project2.Models;

public class Order
{
    private int id;
    private int idClient;

    public Order(int client)
    {
        idClient = client;
    }

    public int getId ()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getIdClient ()
    {
        return idClient;
    }

    public void setIdClient (int idClient)
    {
        this.idClient = idClient;
    }
}
