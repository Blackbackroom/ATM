package atm.persisting.impl;

import atm.model.History;
import atm.persisting.HistoryDataBase;
import atm.persisting.impl.utils.MySQLConnector;
import org.apache.log4j.Logger;
import java.util.Date;
import java.sql.*;

public class HistoryDataBaseImpl implements HistoryDataBase {
    MySQLConnector mySQLConnector=new MySQLConnector();
    final static Logger logger=Logger.getLogger(HistoryDataBaseImpl.class);
    History history;
    Date date;

    @Override
    public void addHistory(String transaction) {
        date=new Date();
        history=new History(date,getMaxNumber()+1, transaction);
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `history` (date, number, transaction) VALUES (?,?,?)")){
            preparedStatement.setDate(1, (java.sql.Date) history.getDate());
            preparedStatement.setInt(2, history.getNumber());
            preparedStatement.setString(3, history.getOperaton());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            logger.error("Add history throws exception: "+e);
        }
    }

    @Override
    public History getHistory(int number) {
        History history=null;
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT FROM `history` WHERE number=?")){
            preparedStatement.setInt(1, number);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while (resultSet.next()){
                   history=new History((java.util.Date) resultSet.getDate("date"), resultSet.getInt("number"), resultSet.getString("transaction"));
                }
            }
        }catch(SQLException e){
            logger.error("Get history throws exception: "+e);
        }
        return history;
    }

    @Override
    public int getMaxNumber() {
        //TODO METHOD
        return 0;
    }
}
