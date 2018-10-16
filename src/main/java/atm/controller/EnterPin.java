package atm.controller;

import atm.service.AtmService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("enterPin")
public class EnterPin extends HttpServlet {
    final static Logger logger=Logger.getLogger(EnterPin.class);
    AtmService atmService=new AtmService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String pin=req.getParameter("pin");
            atmService.enterPin(pin);
            if(atmService.isCheckPin()==true){
                resp.getWriter().println("Pin checked!");
            }

        }catch (Exception e){
            logger.error(e);
            resp.getWriter().println("Server internal error");
            resp.setStatus(503);
        }
    }
}
