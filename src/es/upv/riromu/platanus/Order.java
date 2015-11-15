package es.upv.riromu.platanus;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upv.riromu.platanus.db.HistogramRelationDAO;
import es.upv.riromu.platanus.db.Platanus;
import es.upv.riromu.platanus.db.PlatanusDAO;
import es.upv.riromu.platanus.generate.GA;
import es.upv.riromu.platanus.generate.Population;
import es.upv.riromu.platanus.generate.TourManager;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PlatanusDAO platanusDao=new PlatanusDAO();
//		 HistogramRelationDAO histogramDao=new HistogramRelationDAO();

	     List <Platanus> list=platanusDao.findAll();
	   /*
	     TourManager tmanager=new TourManager();
	     for(int i=0;i<list.size();i++)
	 	{
          tmanager.addCapture(list.get(i));
	 	}
	 	*/
	  // Initialize population
	        Population pop = new Population(50, true);
	        System.out.println("Initial distance: " + pop.getFittest().getDistance());

	        // Evolve population for 100 generations
	        pop = GA.evolvePopulation(pop);
	        for (int i = 0; i < 100; i++) {
	            pop = GA.evolvePopulation(pop);
	        }
	        System.out.println("Final distance: " + pop.getFittest().getDistance());
	        System.out.println("Solution:");
	        System.out.println(pop.getFittest());
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
