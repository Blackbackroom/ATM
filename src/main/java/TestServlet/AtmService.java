package TestServlet;

import atm.persisting.impl.utils.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class AtmService {
    MySQLConnector mySQLConnector = new MySQLConnector();

    HashMap<Integer,Integer> atm1=new HashMap<>();

    private void addToDB(Atm atm){
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO atm (id, uah) VALUES (?,?)")){
            preparedStatement.setInt(1, atm.getId());
            preparedStatement.setInt(2, atm.getUah());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void add(Atm atm){
        atm1.put(atm.getId(), atm.getUah());
    }
}
