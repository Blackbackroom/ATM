package atm.persisting.impl;

import atm.model.Card;
import atm.model.TypeCard;
import atm.persisting.CardDataBase;
import atm.persisting.impl.utils.MySQLConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardDataBaseImpl implements CardDataBase {
    final static Logger logger=Logger.getLogger(CardDataBaseImpl.class);
    MySQLConnector mySQLConnector=new MySQLConnector();

    @Override
    public void addCard(long id, long accountId, String pin, TypeCard typeCard, String dateMMyy, boolean chip) {
     try(Connection connection=mySQLConnector.getConnection();
     PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `card` (id, accountId, pin, type, date, chip, block, reason) VALUES(?,?,?,?,?,?,?,?)")){
         preparedStatement.setLong(1, id);
         preparedStatement.setLong(2, accountId);
         preparedStatement.setString(3, pin);
         preparedStatement.setString(4, typeCard.toString());
         preparedStatement.setString(5, dateMMyy.toString());
         preparedStatement.setBoolean(6, chip);
         preparedStatement.setBoolean(7, false);
         preparedStatement.setString(8, null);
         preparedStatement.executeUpdate();
     }catch (SQLException e) {
         logger.error("Add card throws exception: "+e);
     }
     }

    @Override
    public Card getCard(long id) {
        Card card=null;
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `card` WHERE id=?")){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                card=new Card(
                        resultSet.getLong("id"),
                        resultSet.getLong("accountId"),
                        resultSet.getString("pin"),
                        getType(resultSet.getString("type")),
                        resultSet.getString("date"),
                        resultSet.getBoolean("chip")
                        );
            }
        }catch (SQLException e) {
            logger.error("Get card throws exception: "+e);
        }
        return card;
    }

    @Override
    public void update(Card card) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE `card` SET (pin, block, reason) VALUES(?,?,?) WHERE id=?")){
                preparedStatement.setString(1, card.getPin());
                preparedStatement.setBoolean(2, card.isBlock());
                preparedStatement.setString(3, card.getReason());
                preparedStatement.setLong(4, card.getId());
                preparedStatement.executeUpdate();
        }catch (SQLException e) {
            logger.error("Update card throws exception: "+e);
        }
    }

    @Override
    public void delete(long id) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `card` WHERE id=?")){
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
        }catch (SQLException e) {
            logger.error("Delete card throws exception: "+e);
        }
    }

    private TypeCard getType(String type){
        if(type.equalsIgnoreCase("visa")){
            return TypeCard.VISA;
        }else if(type.equalsIgnoreCase("mastercard")){
            return TypeCard.MASTERCARD;
        }else {
            return TypeCard.VISA;
        }
    }

    private Date getDate(String dateMMyy){
        Date date=null;
        try {
            date = new SimpleDateFormat("MMdd").parse(dateMMyy);
        } catch (ParseException e) {
            logger.error("Error in date");
        }
        return date;
    }
}
