package TestServlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/atm")
public class AtmServlet extends HttpServlet {
Gson gson = new Gson();
AtmService atmService=new AtmService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Atm atm=new Atm(0,0);
        atm=gson.fromJson(req.getReader(), Atm.class);
    atmService.add(atm);
    }

}
