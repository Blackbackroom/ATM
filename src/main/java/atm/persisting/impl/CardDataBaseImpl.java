package atm.persisting.impl;

import atm.model.Card;
import atm.persisting.CardDataBase;
import atm.persisting.impl.utils.MySQLConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDataBaseImpl implements CardDataBase {
    final static Logger logger=Logger.getLogger(CardDataBaseImpl.class);
    MySQLConnector mySQLConnector=new MySQLConnector();

    @Override
    public void addCard(Card card) {


        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `card` (id, accountId, pin, type, date, chip, block, reason) VALUES (?,?,?,?,?,?,?,?)")){
                preparedStatement.setInt(1, card.getId());
                preparedStatement.setInt(2, card.getAccountId());
                preparedStatement.setString(3, card.getPin());
                preparedStatement.setString(4, card.getTypeCard());
                preparedStatement.setString(5, card.getEndCard().toString());
                preparedStatement.setBoolean(6,card.isChip());
                preparedStatement.setBoolean(7, false);
                preparedStatement.setString(8, null);
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Add card throws exception: "+e);
        }
    }

    @Override
    public Card getCard(int id) {
        Card card=null;
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `card` WHERE id=?")){
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet=preparedStatement.executeQuery()){
                    while (resultSet.next()){
                        card=new Card(
                                resultSet.getInt("id"),
                                resultSet.getInt("accountId"),
                                resultSet.getString("pin"),
                                resultSet.getString("type"),
                                resultSet.getString("date"),
                                resultSet.getBoolean("chip")
                        );
                        card.setBlock(resultSet.getBoolean("block"));
                        card.setReason(resultSet.getString("reason"));
                    }
                }
        }catch (SQLException e){
            logger.error("Get card throws exception: "+e);
        }
        return card;
    }

    @Override
    public void update(Card card) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE `card` SET block=?, reason=? WHERE id=?")){
                preparedStatement.setBoolean(1,card.isBlock());
                preparedStatement.setString(2, card.getReason());
                preparedStatement.setInt(3, card.getId());
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Update card throws exception: "+e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `card` WHERE id=?")){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Delete card throws exception: "+e);
        }
    }

    public void setMySQLConnector(MySQLConnector mySQLConnector) {
        this.mySQLConnector = mySQLConnector;
    }
}
