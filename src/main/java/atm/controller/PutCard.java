package atm.controller;

import atm.service.AtmOperation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/putCard")
public class PutCard extends HttpServlet {
    final static Logger logger=Logger.getLogger(PutCard.class);

    AtmOperation atmOperation=new AtmOperation();
    private boolean checkCard=false;

    public AtmOperation getAtmOperation(){
        return atmOperation;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String id = req.getParameter("id");
            checkCard=atmOperation.checkCard(Integer.valueOf(id));
            if(checkCard==true){
                resp.getWriter().println("enterPin?pin=****");
            }else{
                resp.getWriter().println("ERROR");
            }
        }catch (Exception e){
            logger.error(e);
            resp.getWriter().println("Server internal error");
            resp.setStatus(503);
        }
    }
}
