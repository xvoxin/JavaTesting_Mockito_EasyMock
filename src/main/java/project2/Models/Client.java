package project2.Models;

public class Client
{
    private int id;
    private String name;
    private String surname;
    private String email;

    public Client (String name, String surname, String email)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public int getId()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getSurname ()
    {
        return surname;
    }

    public void setSurname (String surname)
    {
        this.surname = surname;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

}
