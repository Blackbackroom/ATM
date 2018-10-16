package atm.controller;

import atm.model.Card;
import atm.persisting.impl.CardDataBaseImpl;
import atm.service.AtmOperations;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/putCard")
public class PutCard extends HttpServlet {
    CardDataBaseImpl cardDataBase=new CardDataBaseImpl();
    AtmOperations atmOperations = new AtmOperations();
    final static Logger logger=Logger.getLogger(PutCard.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String id=req.getParameter("id");
            Card card=cardDataBase.getCard(Integer.valueOf(id));
            atmOperations.putCard(card.getId());
            if(atmOperations.isCheckCard()==true){
                resp.getWriter().println("Card Ok! Enter pin");

            }
        }catch (Exception e){
            logger.error(e);
            resp.getWriter().println("Server internal error");
            resp.setStatus(503);
        }
    }
}
