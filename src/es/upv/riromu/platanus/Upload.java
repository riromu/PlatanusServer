package es.upv.riromu.platanus;

import java.io.File;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import es.upv.riromu.platanus.db.Platanus;
import es.upv.riromu.platanus.db.PlatanusDAO;



/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
maxFileSize=1024*1024*50,      	// 50 MB
maxRequestSize=1024*1024*100)   		// 100 MB
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	 private static final String UPLOAD_DIR = "uploads";
	private ServletFileUpload uploader;

    /**
     * @see HttpServlet#HttpServlet()
     */
   

        public Upload() {
            super();
            // TODO Auto-generated constructor stub
        }
    	    @Override
      public void init() throws ServletException{
    	    	DiskFileItemFactory fileFactory = new DiskFileItemFactory();
    	   	ServletContext servletContext = this.getServletConfig().getServletContext();
    	  	File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
    	 fileFactory.setRepository(repository);
    	  	this.uploader = new ServletFileUpload(fileFactory);
    	    //	 filePath = getServletContext().getInitParameter("file_upload"); 
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}
	public String getCurrentDateTime (){
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
				   //get current date time with Date()
				//   Date date = new Date();
				//   String result=dateFormat.format(date);
			   //get current date time with Calendar()
				   Calendar cal = Calendar.getInstance();
				 String date= dateFormat.format(cal.getTime());
				 return date;
			  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		 PrintWriter out = response.getWriter();
		 if(!ServletFileUpload.isMultipartContent(request)){
			             throw new ServletException("Content type is not multipart/form-data");
		    }
		 // String applicationPath = request.getServletContext().getRealPath("");
		// String applicationPath = request.getServletContext().getInitParameter("upload_file");
		 String applicationPath = "/usr/share/tomcat7-data";
		 
	        // constructs path of the directory to save uploaded file
	        String uploadFilePath = applicationPath + File.separator;
	          
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(uploadFilePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdirs();
	        }
	        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
	         
	        String fileName = null;
	        //Get all the parts from request and write it to the file on server
	        for (Part part : request.getParts()) {
	            fileName = getFileName(part);
	            part.write(uploadFilePath + File.separator + fileName);
	   try{
	        
		  String p = request.getParameter("p");
	      //string indice = request.getParameter("indice");
	      // String tipo = request.getParameter("tipo");
	       String fecha = request.getParameter("fecha");
	       if(fecha==null)
	       {
	       	Calendar calendar = Calendar.getInstance();
	       	Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
	         fecha=getCurrentDateTime();
	       }
	       String ip = request.getRemoteAddr();
	       int tipo = 1;
	       int iddispositivo = 1;
	       String modelodispositivo =  request.getHeader("User-Agent");
	       String coordenadas = request.getParameter("l");
	       String proveedor = request.getParameter("pro");
	       String email= request.getParameter("e");
	       int captura = Integer.valueOf(request.getParameter("captura"));
	       int numcaptura = Integer.valueOf(request.getParameter("numcaptura"));
	       String R = request.getParameter("R");
	       String G = request.getParameter("G");
	       String B = request.getParameter("B");
	       String filename = request.getParameter("filename");
	       String version = request.getParameter("v");
	       String observaciones = request.getParameter("o");
	       Platanus declira;
	       
	      PlatanusDAO platanusDao=new PlatanusDAO();
	      if(request.getParameter("numcaptura").equals("1")) {
	       declira = new Platanus(tipo,fecha, ip, iddispositivo,modelodispositivo, coordenadas, proveedor, email, captura, numcaptura,R,G,B,filename, version, observaciones);
	      platanusDao.save(declira);
	      }
	      else{
	    	  declira=platanusDao.find(captura);
	    	  switch(Integer.valueOf(request.getParameter("numcaptura")))
	    	  {
	    	  case 2:declira.setFicherohistogramtxt(filename);
	    	  		 platanusDao.update(declira);
	    	  		break;
	    	  case 3:declira.setFicherocrop(filename);
	    	  		platanusDao.update(declira);
	    	  		break;
	    	  case 4:declira.setFicheromascara(filename);
	    	  		platanusDao.update(declira);	
	    	  		break;
	    	  case 5:declira.setFicherohistograma(filename);
	    	  		 platanusDao.update(declira);
	    	  		 Comparator.compare(captura);
	    	  		break;
	    	  }
	    	  platanusDao.update(declira);
	      }
	      } catch (SQLException e) {
			   
			   e.printStackTrace();
				   System.out.println("error"+e.getMessage());               
		}catch (Exception e) {
			 		out.write("failure");
	    }
      }
   //RequestDispatcher dispatcher = request.getRequestDispatcher("/success_upload.jsp");
	//	    dispatcher.forward(request, response);
	//
out.write("success");
	out.close();

	    // dispatcher = request.getRequestDispatcher("/success_upload.jsp");
	    // dispatcher.forward(request, response);
	 //    out.close();
	    //  out.write("</body></html>");
			
				 	        
	       }

  	   
   
	
private String getFileName(final Part part) {
final String partHeader = part.getHeader("content-disposition");
//    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
for (String content : part.getHeader("content-disposition").split(";")) {
   if (content.trim().startsWith("filename")) {
       return content.substring(
               content.indexOf('=') + 1).trim().replace("\"", "");
   }
}
return null;
}

}
