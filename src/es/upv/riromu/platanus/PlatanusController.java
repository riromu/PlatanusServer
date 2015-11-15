package es.upv.riromu.platanus;




import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upv.riromu.platanus.db.HistogramRelationDAO;
import es.upv.riromu.platanus.db.PlatanusDAO;

@WebServlet("/PlatanusController")
public class PlatanusController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String LIST = "/listPlatanus.jsp";
    private static String COMPARELIST = "/listHistogramPlatanus.jsp";

    private static String GENERATE = "/Generate";
    private static String ORDER = "/Order";
   

    public PlatanusController() {
        super();
        platanusDao = new PlatanusDAO();
        histogramRelationDao = new HistogramRelationDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
      
     if (action.equalsIgnoreCase("list")){
            forward = LIST;
            request.setAttribute("platanus", platanusDao.findAllMapCapturedImages());
        };
        if (action.equalsIgnoreCase("compare")){
            forward = COMPARELIST;
            request.setAttribute("histogramrelation", histogramRelationDao.findAll());
        };
        if (action.equalsIgnoreCase("generate")){
            forward = GENERATE;
        }
        if (action.equalsIgnoreCase("order")){
            forward = ORDER;
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     /*xception e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        String userid = request.getParameter("userid");
        if(userid == null || userid.isEmpty())
        {
            dao.addUser(user);
        }
        else
        {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.get*/}
}