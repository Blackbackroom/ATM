package atm.persisting.impl;

import atm.model.History;
import atm.persisting.HistoryDataBase;
import atm.persisting.impl.utils.MySQLConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryDataBaseImpl implements HistoryDataBase {
    final static Logger logger=Logger.getLogger(HistoryDataBaseImpl.class);
    MySQLConnector mySQLConnector=new MySQLConnector();

    @Override
    public void addHistory(String transaction) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `history` (dateTime, transaction) VALUES(?,?)")){
                preparedStatement.setString(1, String.valueOf(System.currentTimeMillis()));
                preparedStatement.setString(2, transaction);
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Add history throws exception: "+e);
        }
    }

    @Override
    public History getLastHistory() {
        History history=null;
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `history` WHERE id=?")){
                preparedStatement.setInt(1, getMaxId());
                try(ResultSet resultSet=preparedStatement.executeQuery()){
                    while (resultSet.next()){
                        history=new History(resultSet.getString("dateTime"),resultSet.getString("transaction"));
                    }
                }
        }catch (SQLException e){
            logger.error("Get last history throws exception: "+e);
        }
        return history;
    }

    private int getMaxId(){
        int maxId=-1;
        try(Connection connection=mySQLConnector.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT MAX(id) FROM `history`")){
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while (resultSet.next()){
                    maxId=resultSet.getInt("MAX(ID)");
                }
            }
        }catch (SQLException e){
            logger.error("Get max id throws exception: "+e);
        }
        return maxId;
    }

    public void setMySQLConnector(MySQLConnector mySQLConnector) {
        this.mySQLConnector = mySQLConnector;
    }
}
