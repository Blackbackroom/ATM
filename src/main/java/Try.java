import atm.service.AtmOperations;
import atm.service.AtmService;
import atm.service.CardService;

public class Try {


    public static void main(String[] args) {

        CardService cardService=new CardService();
        AtmService atmService=new AtmService();
        atmService.putCard(1);
        atmService.enterPin("1234");
        System.out.println(atmService.isCheckPin());



    }
}
