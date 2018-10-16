package atm.persisting.impl;

import atm.model.Atm;
import atm.persisting.AtmDataBase;
import atm.persisting.impl.utils.MySQLConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtmDataBaseImpl implements AtmDataBase {
    final static Logger logger=Logger.getLogger(AtmDataBaseImpl.class);
    MySQLConnector mySQLConnector=new MySQLConnector();

    @Override
    public void addAtm(Atm atm) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `atm` (id, uah) VALUES (?,?)")){
                preparedStatement.setInt(1, atm.getId());
                preparedStatement.setInt(2, atm.getUah());
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Add atm throws exception: "+e);
        }
    }

    @Override
    public Atm getAtm(int id) {
        Atm atm=null;
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `atm` WHERE id=?")){
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet=preparedStatement.executeQuery()){
                    while (resultSet.next()){
                        atm=new Atm(resultSet.getInt("id"), resultSet.getInt("uah"));
                    }
                }
        }catch (SQLException e){
            logger.error("Get atm throws exception: "+e);
        }
        return atm;
    }

    @Override
    public void updateAtm(Atm atm) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE `atm` SET uah=? WHERE id=?")){
                preparedStatement.setInt(1, atm.getUah());
                preparedStatement.setInt(2, atm.getId());
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Update atm throws exception: "+e);
        }
    }

    @Override
    public void deleteAtm(int id) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `atm` WHERE id=?")){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Delete atm throws exception: "+e);
        }
    }
}
