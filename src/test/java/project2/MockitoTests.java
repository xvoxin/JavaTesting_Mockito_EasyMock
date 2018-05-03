package project2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import project2.Controllers.WebShopController;
import project2.Interfaces.IDbContext;
import project2.Models.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MockitoTests
{
    private final String CLIENT_NAME = "Adrian";
    private final String CLIENT_NAME2 = "Krzysztof";
    private final String CLIENT_NAME3 = "Agata";
    private final String CLIENT_NAME4 = "Stefan";
    private final String NEW_EMAIL = "testowanie@java.pl";

    private Client client;

    private final String CLIENT_SURNAME = "Smith";
    private final String CLIENT_EMAIL = "some@email.com";

    @Mock
    private IDbContext dbMock;

    //@InjectMocks
    private WebShopController webShopController;

    @BeforeEach void SetUp ()
    {
        dbMock = mock(IDbContext.class);
        webShopController = new WebShopController(dbMock);
        client = new Client(CLIENT_NAME, CLIENT_SURNAME, CLIENT_EMAIL);
    }

    @Test void CheckAddClient_ShouldAddNewClient ()
    {
        Client client = new Client(CLIENT_NAME, CLIENT_SURNAME, CLIENT_EMAIL);
        when(dbMock.AddClient(any(Client.class))).thenReturn(true);

        assertTrue(webShopController.AddClient(client));
        verify(dbMock, times(1)).AddClient(any(Client.class));
    }

    @Test void CheckAddClient_ShouldNotAddClient ()
    {
        when(dbMock.AddClient(any(Client.class))).thenReturn(false);

        assertFalse(webShopController.AddClient(client));
        verify(dbMock, times(1)).AddClient(any(Client.class));
    }

    @Test void CheckAddClient_ShouldThrowException ()
    {
        Client client = new Client("very", "bad", "exception");
        when(dbMock.AddClient(any(Client.class))).thenThrow(new IllegalArgumentException("Get out!"));

        assertThrows(IllegalArgumentException.class, () ->
        {
            webShopController.AddClient(client);
        });
    }

    @Test void CheckAddMultipleClients_ShouldReturnProperValue ()
    {
        Client client2 = new Client(CLIENT_NAME2, CLIENT_SURNAME, CLIENT_EMAIL);
        Client client3 = new Client(CLIENT_NAME3, CLIENT_SURNAME, CLIENT_EMAIL);
        Client client4 = new Client(CLIENT_NAME4, CLIENT_SURNAME, CLIENT_EMAIL);

        List<Client> clients = new ArrayList<>(
                Arrays.asList(new Client[]{client, client2, client3, client4}));

        when(dbMock.AddClient(any(Client.class)))
                .thenReturn(true, true, true, true);

        assertEquals(webShopController.AddClients(clients), 4);
        verify(dbMock, times(4)).AddClient(any(Client.class));
    }

    @Test void CheckAddMultipleClients_ShouldNotAddEveryClient ()
    {
        Client client2 = new Client(CLIENT_NAME2, CLIENT_SURNAME, CLIENT_EMAIL);
        Client client3 = new Client(CLIENT_NAME3, CLIENT_SURNAME, CLIENT_EMAIL);
        Client client4 = new Client(CLIENT_NAME4, CLIENT_SURNAME, CLIENT_EMAIL);

        List<Client> clients = new ArrayList<>(
                Arrays.asList(new Client[]{client, client2, client3, client4}));

        when(dbMock.AddClient(any(Client.class)))
                .thenReturn(true, false, true, false);

        assertEquals(2, webShopController.AddClients(clients));
        verify(dbMock, times(4)).AddClient(any(Client.class));
    }

    @Test void CheckGetClientByName_ShouldReturnProperClient ()
    {
        when(dbMock.GetClientByName(CLIENT_NAME)).thenReturn(client);

        assertEquals(webShopController.GetClientByName(CLIENT_NAME), client);
        verify(dbMock, times(1)).GetClientByName(CLIENT_NAME);
    }

    @Test void CheckGetAllClients_EmptyDB_ShouldReturnZeroClients ()
    {
        when(dbMock.GetAllClients()).thenReturn(new ArrayList<>());

        assertThat(webShopController.GetAllClients()).hasSize(0);
        verify(dbMock, times(1)).GetAllClients();
    }

    @Test void CheckGetAllClients_ShouldReturnProperValue ()
    {
        Client client2 = new Client(CLIENT_NAME2, CLIENT_SURNAME, CLIENT_EMAIL);
        Client client3 = new Client(CLIENT_NAME3, CLIENT_SURNAME, CLIENT_EMAIL);
        Client client4 = new Client(CLIENT_NAME4, CLIENT_SURNAME, CLIENT_EMAIL);

        List<Client> clients = new ArrayList<>(Arrays.asList(new Client[]{client, client2, client3, client4}));
        when(dbMock.GetAllClients()).thenReturn(clients);

        assertThat(webShopController.GetAllClients()).
                hasSize(4).containsOnly(client, client2, client3, client4);

        verify(dbMock, times(1)).GetAllClients();
    }

    @Test void CheckDeleteClient_ShouldDeleteClient ()
    {
        when(dbMock.DeleteClient(client)).thenReturn(true);
        assertThat(webShopController.DeleteClient(client)).isTrue();
        verify(dbMock, times(1)).DeleteClient(client);
    }

    @Test void CheckDeleteClient_ClientDontExists_ShouldReturnFalse ()
    {
        when(dbMock.DeleteClient(client)).thenReturn(false);
        assertThat(webShopController.DeleteClient(client)).isFalse();
        verify(dbMock, times(1)).DeleteClient(client);
    }

    @Test void CheckEditClient_ShouldEdit ()
    {
        doAnswer(invocationOnMock ->
        {
            Object[] args = invocationOnMock.getArguments();
            if(args.length == 2)
            {
                String email = (String)args[0];
                Client cl = (Client)args[1];
                cl.setEmail(email);
            }
            return null;
        }).when(dbMock).EditClientEmail(any(String .class), any(Client.class));

        webShopController.EditClientEmail(NEW_EMAIL, client);
        assertThat(client.getEmail()).isEqualTo(NEW_EMAIL);
    }
}
