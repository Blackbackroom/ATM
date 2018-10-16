package atm.controller;


import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inputCard")
public class inputCard extends HttpServlet {
    final static Logger logger=Logger.getLogger(inputCard.class);
    atm.service.AtmService atmService=new atm.service.AtmService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String id=req.getParameter("id");
            atmService.putCard(Integer.valueOf(id));
            if(atmService.isCheckCard()==true){
                resp.getWriter().println("Card Checked! enterPin?pin=****");
            }
        }catch (Exception e){
            logger.error(e);
            resp.getWriter().println("Server internal error");
            resp.setStatus(503);
        }
    }
}
