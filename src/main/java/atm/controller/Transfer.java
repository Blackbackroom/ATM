package atm.controller;

import atm.service.AtmOperation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/transfer")
public class Transfer extends HttpServlet {
    final static Logger logger = Logger.getLogger(Transfer.class);
    AtmOperation atmOperation = AtmOperation.getAtmOperation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try{
                if(AtmOperation.checkCard==true&&AtmOperation.checkPin==true){
                    String toAccount = req.getParameter("toAccount");
                    String amount = req.getParameter("amount");
                    atmOperation.transfer(Integer.valueOf(toAccount), Integer.valueOf(amount));
                    resp.getWriter().println("Transfer to account "+toAccount+" "+amount+" uah success" +
                            "\n\n" +
                            "ATM MENU:" +
                            "\nshowBalance" +
                            "\ntransfer?toAccount=*&amount=*" +
                            "\nwithdraw?amount=*" +
                            "\nreturnCard");
                }else{
                    resp.getWriter().println("Access denied");
                }
        }catch (Exception e){
            logger.error(e);
            resp.getWriter().println("Server internal error");
            resp.setStatus(503);
        }
    }
}
