package atm.persisting.impl;

import atm.model.Account;
import atm.persisting.AccountDataBase;
import atm.persisting.impl.utils.MySQLConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDataBaseImpl implements AccountDataBase {
    final static Logger logger=Logger.getLogger(AccountDataBaseImpl.class);
    MySQLConnector mySQLConnector=new MySQLConnector();

    @Override
    public void addAccount(long id, long inn, int uah) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `account` (id, uah, inn) VALUES (?,?,?)")){
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, uah);
            preparedStatement.setLong(3, inn);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Add account throws exception: "+e);
        }
    }

    @Override
    public Account getAccount(long id) {
        Account account=null;
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `account` WHERE id=?")){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                    account=new Account(resultSet.getLong("id"), resultSet.getLong("inn"), resultSet.getInt("uah"));
                }
            }
        }catch (SQLException e){
            logger.error("Get account throws exception: "+e);
        }
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE `account` SET uah=? WHERE id=?")){
            preparedStatement.setInt(1, account.getBalance().getUah());
            preparedStatement.setLong(2, account.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Update account throws exception: "+e);
        }
    }

    @Override
    public void deleteAccount(long id) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `account` WHERE id=?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Delete account throws exception: "+e);
        }
    }
}
