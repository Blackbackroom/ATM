package atm.service;

import atm.model.Card;
import atm.persisting.impl.CardDataBaseImpl;

import atm.persisting.impl.utils.MySQLConnector;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CardOperationTest {
private CardOperation cardOperation;
private CardDataBaseImpl cardDataBase;


@Before
public void setUp() throws SQLException {
    ResultSet resultSet=Mockito.mock(ResultSet.class);
    Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
    Mockito.when(resultSet.getInt("id")).thenReturn(3);
    Mockito.when(resultSet.getInt("accountId")).thenReturn(3);
    Mockito.when(resultSet.getString("pin")).thenReturn("4321");
    Mockito.when(resultSet.getString("type")).thenReturn("MASTERCARD");
    Mockito.when(resultSet.getString("date")).thenReturn("1019");
    Mockito.when(resultSet.getBoolean("chip")).thenReturn(false);


    PreparedStatement statement=Mockito.mock(PreparedStatement.class);
    Mockito.when(statement.executeQuery()).thenReturn(resultSet);

    Connection connection=Mockito.mock(Connection.class);
    Mockito.when(connection.prepareStatement("SELECT * FROM `card` WHERE id=?")).thenReturn(statement);

    MySQLConnector mySQLConnector=Mockito.mock(MySQLConnector.class);
    Mockito.when(mySQLConnector.getConnection()).thenReturn(connection);

    cardOperation=new CardOperation();
    cardDataBase=new CardDataBaseImpl();
}

@Test
    public void testGetCard(){

    Card card=cardOperation.getCard(3);
assertEquals(3,cardDataBase.getCard(card.getId()).getId());

}



}
