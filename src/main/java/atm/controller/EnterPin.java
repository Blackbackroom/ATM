package atm.controller;

import atm.service.AtmOperation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/enterPin")
public class EnterPin extends HttpServlet {
    final static Logger logger = Logger.getLogger(EnterPin.class);

    AtmOperation atmOperation = AtmOperation.getAtmOperation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if(AtmOperation.checkCard==true){
                String pin = req.getParameter("pin");
                AtmOperation.checkPin=atmOperation.checkCardPin(pin);
                if(AtmOperation.countFalsePin<2){
                 if(AtmOperation.checkPin==true){
                     resp.getWriter().println("ATM MENU:" +
                             "\nshowBalance" +
                             "\ntransfer?toAccount=*&amount=*" +
                             "\nwithdraw?amount=*" +
                             "\nreturnCard");
                 }else{
                  resp.getWriter().println("Wrong pin");
                 }
                }else{
                    resp.getWriter().println("Card blocked");
                }
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
