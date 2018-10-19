package atm.service;

import atm.persisting.impl.AccountDataBaseImpl;
import atm.persisting.impl.utils.MySQLConnector;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class AccountOperationTest {

    private AccountOperation accountOperation;
    private AccountDataBaseImpl accountDataBase;

    @Before
    public void setUp() throws SQLException {
        ResultSet resultSet=Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("id")).thenReturn(3);
        Mockito.when(resultSet.getInt("inn")).thenReturn(3);
        Mockito.when(resultSet.getInt("uah")).thenReturn(100);

        PreparedStatement statement=Mockito.mock(PreparedStatement.class);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);

        Connection connection=Mockito.mock(Connection.class);
        Mockito.when(connection.prepareStatement("SELECT * FROM `account` WHERE id=?")).thenReturn(statement);

        MySQLConnector mySQLConnector=Mockito.mock(MySQLConnector.class);
        Mockito.when(mySQLConnector.getConnection()).thenReturn(connection);

        accountDataBase=new AccountDataBaseImpl();
        accountOperation=new AccountOperation();

        accountDataBase.setMySQLConnector(mySQLConnector);
        accountOperation.setAccountDataBase(accountDataBase);
    }

    @Test
    public void testGetAccount(){
        assertNotNull(accountOperation.getAccount(3));
    }

    @Test
    public void testGetBalance(){
        assertEquals(100, accountOperation.getBalance(3));
    }


}
