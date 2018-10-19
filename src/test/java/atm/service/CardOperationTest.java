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
    Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
    Mockito.when(resultSet.getInt("id")).thenReturn(3);
    Mockito.when(resultSet.getInt("accountId")).thenReturn(3);
    Mockito.when(resultSet.getString("pin")).thenReturn("4321");
    Mockito.when(resultSet.getString("type")).thenReturn("MASTERCARD");
    Mockito.when(resultSet.getString("date")).thenReturn("1019");
    Mockito.when(resultSet.getBoolean("chip")).thenReturn(false);
    Mockito.when(resultSet.getBoolean("block")).thenReturn(false);
    Mockito.when(resultSet.getString("reason")).thenReturn("");

    PreparedStatement statement=Mockito.mock(PreparedStatement.class);
    Mockito.when(statement.executeQuery()).thenReturn(resultSet);

    Connection connection=Mockito.mock(Connection.class);
    Mockito.when(connection.prepareStatement("SELECT * FROM `card` WHERE id=?")).thenReturn(statement);

    MySQLConnector mySQLConnector=Mockito.mock(MySQLConnector.class);
    Mockito.when(mySQLConnector.getConnection()).thenReturn(connection);

    cardDataBase=new CardDataBaseImpl();
    cardOperation=new CardOperation();

    cardDataBase.setMySQLConnector(mySQLConnector);
    cardOperation.setCardDataBase(cardDataBase);


}

@Test
    public void testGetCard(){
assertNotNull(cardOperation.getCard(3));
}

@Test
    public void testCheckCardDate(){
assertTrue(cardOperation.checkCardDate(3));
}

@Test
    public void testCheckCardBlock(){
    assertTrue(cardOperation.checkCardBlock(3));
}

@Test
    public void testCheckCardPin() {
    assertTrue(cardOperation.checkCardPin(3, "4321"));
}


}
