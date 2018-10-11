package atm.model;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Card {
final static Logger logger=Logger.getLogger(Card.class);

    private long id;
    private long accountId;
    private String pin;
    private String typeCard;
    private Date endCard;
    private boolean chip;
    private boolean block;
    private String reason;

    public Card(long id, long accountId, String pin, String typeCard, String dateMMyy, boolean chip){
        this.id=id;
        this.accountId=accountId;
        this.pin=pin;
        this.typeCard=typeCard;
        try {
            endCard = new SimpleDateFormat("MMyy").parse(dateMMyy);
        }catch (Exception e){
            logger.error("Error in card date");
        }
        this.chip=chip;
        block=false;
        reason=null;
    }

    public long getId() {
        return id;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getPin() {
        return pin;
    }

    public String getTypeCard() {
        return typeCard;
    }

    public Date getEndCard() {
        return endCard;
    }

    public boolean isChip() {
        return chip;
    }

    public boolean isBlock() {
        return block;
    }

    public String getReason() {
        return reason;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
