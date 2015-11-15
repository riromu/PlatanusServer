package es.upv.riromu.platanus.db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
 
 
public class HistogramRelationDAO {
         
    Connection con;
    PlatanusDAO platanusDao;
     
    public void save(HistogramRelation declira) throws SQLException {
        Connection con = getConnection();
        String insert = "INSERT INTO histogram_relation (" +
        		"captura_1, " +//null
        		"captura_2," +//1
        		" relation" +//now
        	")" +//16
        		" VALUES (?,?,?);";
        PreparedStatement ps = con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, Integer.valueOf(declira.getCaptura_1().getCaptura()));
        ps.setInt(2, Integer.valueOf(declira.getCaptura_2().getCaptura()));
        ps.setDouble(3,declira.getRelation());
        ps.executeUpdate();
        // Se obtiene la clave generada
        ResultSet rs = ps.getGeneratedKeys();
        while (rs.next()) {
           int claveGenerada = rs.getInt(1);
           System.out.println("Clave generada = " + claveGenerada);
        }
        ps.close();
        con.commit();
        con.close();
        System.out.println("Storing Capture Object is Done!");
    }
     
     
    public void update(Platanus std){
        System.out.println("Storing Capture Object is Done!");
    }
     
     
    public void remove(Platanus std){
        System.out.println("Storing Capture Object is Done!");
    }
     
    public HistogramRelation find(int id1,int id2) throws SQLException {
    	
    	   platanusDao=new PlatanusDAO();
    	   Connection con = getConnection();
           String insert = "SELECT * FROM  histogram_relation WHERE ((captura_1="+id1+"and captura_2="+id2+
        		   ")or(captura_1="+id2+",captura2="+id1+");";
           PreparedStatement ps = con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            ps.executeQuery();
    
           HistogramRelation histogramRelation=null;
           // Se obtiene la clave generada
           ResultSet rs = ps.getResultSet();
           while(rs.next()) {
        	   Platanus p1=  platanusDao.find(rs.getInt("captura_1"));
        	   Platanus p2=  platanusDao.find(rs.getInt("captura_2"));
        	   
        	histogramRelation =new HistogramRelation(p1,p2,
        			   rs.getDouble("relation")
        			   );
        	
           }  	   
           ps.close();
           con.commit();
           con.close();
           System.out.println("Storing Capture Object is Done!");
           return histogramRelation;
    }
     
       
    public List<HistogramRelation> findAll(){
    	 platanusDao=new PlatanusDAO();
    	List<HistogramRelation> list=new ArrayList<HistogramRelation>();
    	try{
    	Connection con = getConnection();
           String insert = "SELECT * FROM  histogram_relation;";
           PreparedStatement ps = con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
           ps.execute(insert);
           Platanus p1,p2;
           int captura_1,captura_2;
    
           // Se obtiene la clave generada
           ResultSet rs = ps.getResultSet();
           while (rs.next()) {
        	   captura_1=rs.getInt("captura_1");
        	   captura_2=rs.getInt("captura_2");
        	   
        	   p1=platanusDao.find(captura_1);
        	   p2=platanusDao.find(captura_2);
        	   HistogramRelation histogramRelation=new HistogramRelation(p1,p2,rs.getDouble("relation"));
        	   list.add(histogramRelation);
           }  	   
           ps.close();
           con.commit();
           con.close();
           System.out.println("Storing Capture Object is Done!");
    	}catch(SQLException e){
    		System.out.println("error"+e.getMessage());
    	}catch(Exception e){
    		System.out.println("error"+e.getMessage());
    	}
           return list;
    	}
     
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cropprotbdarbol?relaxAutoCommit=true", "cropprotbdarbol", "UnTupido2015");
           // con.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace(); 
            System.out.println("errror"+e.getMessage());
        }
        return con;
  }
 } 