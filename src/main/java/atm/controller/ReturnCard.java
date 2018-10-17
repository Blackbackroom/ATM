package atm.controller;

import atm.service.AtmOperation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/returnCard")
public class ReturnCard extends HttpServlet {
    final static Logger logger = Logger.getLogger(ReturnCard.class);
    AtmOperation atmOperation = AtmOperation.getAtmOperation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if(AtmOperation.checkCard==true||AtmOperation.checkPin==true) {
                atmOperation.returnCard();
                resp.getWriter().println("Card returned \n\n" +
                        "putCard?id=*");
            }
            else{
                atmOperation.returnCard();
                resp.getWriter().println("Cardreader empty \n\n" +
                        "putCard?id=*");
            }
        }catch (Exception e){
            logger.error(e);
            resp.getWriter().println("Server internal error");
            resp.setStatus(503);
        }
    }
}
