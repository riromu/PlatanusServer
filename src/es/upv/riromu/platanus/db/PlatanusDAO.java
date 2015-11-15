package es.upv.riromu.platanus.db;	


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
 
 
public class PlatanusDAO {
         
    Connection con;
     
    public void save(Platanus declira) throws SQLException {
        Connection con = DBUtil.getConnection();
        String insert = "INSERT INTO platanus (" +
        		"indice, " +//null
        		"tipo," +//1
        		" fecha," +//now
        		"ip," +//2 
        		"iddispositivo," +//3
        		"modelodispositivo," +//4
        		"coordenadas," +//5
        		"proveedor," +//6
        		"email," +//7
        		"captura," +//8
        		"numcaptura" +//9
        		",R," +//10
        		"G," +//11
        		"B," +//12
        		"fichero," +//13
        		"ficherohistogramatxt," +//14
        		"ficherocrop," +//15
        		"ficheromascara," +//16
        		"ficherohistograma," +//17
        		"version," +//18
        		"observaciones," +//19
        		"publicado)" +//20
        		" VALUES (null,?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
        
       // ps.setInt(1, Integer.valueOf(declira.getIndice()));
        ps.setInt(1, Integer.valueOf(declira.getTipo()));
   //     ps.setString(2, declira.getFecha());
        ps.setString(2, declira.getIp());
        ps.setInt(3, declira.getIddispositivo());
        ps.setString(4, declira.getModelodispositivo());
        if(declira.getCoordenadas()==null){
        	 ps.setString(5,"");	
        }
        else
        {
        ps.setString(5, declira.getCoordenadas());
        }
        if(declira.getProveedor()==null)
        {
        	ps.setString(6, "");
        }
        else
        {
        	ps.setString(6, declira.getProveedor());
        }
        	
        ps.setString(7, declira.getEmail());
        ps.setInt(8, Integer.valueOf(declira.getCaptura()));
        ps.setInt(9, Integer.valueOf(declira.getNumcaptura()));
        ps.setInt(10, Integer.valueOf(declira.getR()));
        ps.setInt(11, Integer.valueOf(declira.getG()));
        ps.setInt(12, Integer.valueOf(declira.getB()));
        ps.setString(13, declira.getFichero());
        ps.setString(14, declira.getFicherohistogramtxt());
        ps.setString(15, declira.getFicherocrop());
        ps.setString(16, declira.getFicheromascara());
        ps.setString(17, declira.getFicherohistograma());

        ps.setInt(18, Integer.valueOf(declira.getVersion()));
        ps.setString(19, declira.getObservaciones());
        ps.setInt(20, 0);
        String query=ps.toString();
        int result=ps.executeUpdate();
        // Se obtiene la clave generada
        ResultSet rs = ps.getGeneratedKeys();
        while (rs.next()) {
           int claveGenerada = rs.getInt(1);
           System.out.println("Clave generada = " + claveGenerada);
        }
        ps.close();
        con.commit();
        System.out.println("Storing Capture Object is Done!");
    }
     
     
    public void update(Platanus declira)throws SQLException {
    	 Connection con = DBUtil.getConnection();

    	String update = "UPDATE platanus " +
    	"SET indice=?," +//1
		"tipo=?," +//2
		"fecha=?," +//3
		"ip=?," +//4
		"iddispositivo=?," +//5
		"modelodispositivo=?," +//6
		"coordenadas=?," +//7
		"proveedor=?," +//8
		"email=?," +//9
		"captura=?," +//10
		"numcaptura=?" +//11
		",R=?," +//12
		"G=?," +//13
		"B=?," +//14
		"fichero=?," +//15
		"ficherohistogramatxt=?," +//16
		"ficherocrop=?," +//17
		"ficheromascara=?," +//18
		"ficherohistograma=?," +//19
		"version=?," +//20
		"observaciones=?," +//21
		"publicado=? " +//22
    	"WHERE captura=?;";
    
    	PreparedStatement ps = con.prepareStatement(update,Statement.RETURN_GENERATED_KEYS);
        
         ps.setInt(1, Integer.valueOf(declira.getIndice()));
         ps.setInt(2, Integer.valueOf(declira.getTipo()));
         ps.setString(3, declira.getFecha());
         ps.setString(4, declira.getIp());
         ps.setInt(5, declira.getIddispositivo());
         ps.setString(6, declira.getModelodispositivo());
         ps.setString(7, declira.getCoordenadas());
         ps.setString(8,declira.getProveedor());
         ps.setString(9, declira.getEmail());
         ps.setInt(10, Integer.valueOf(declira.getCaptura()));
         ps.setInt(11, Integer.valueOf(declira.getNumcaptura()));
         ps.setInt(12, Integer.valueOf(declira.getR()));
         ps.setInt(13, Integer.valueOf(declira.getG()));
         ps.setInt(14, Integer.valueOf(declira.getB()));
         ps.setString(15, declira.getFichero());
         ps.setString(16, declira.getFicherohistogramtxt());
         ps.setString(17, declira.getFicherocrop());
         ps.setString(18, declira.getFicheromascara());
         ps.setString(19, declira.getFicherohistograma());
         ps.setInt(20, Integer.valueOf(declira.getVersion()));
         ps.setString(21, declira.getObservaciones());
         ps.setInt(22, Integer.valueOf(declira.getPublicado()));
         ps.setInt(23, Integer.valueOf(declira.getCaptura()));
         String query=ps.toString();
         int result=ps.executeUpdate();
    }
     
     
    public void remove(Platanus std){
        System.out.println("Storing Capture Object is Done!");
    }
     
public Platanus find(int captura){
    	
    	Connection con = DBUtil.getConnection();
     	Platanus platanus=new Platanus();
    	 try { 
    	String select="SELECT * FROM platanus WHERE captura=?;";
    	
    	PreparedStatement ps = con.prepareStatement(select,Statement.RETURN_GENERATED_KEYS);
    	ps.setInt(1,captura);
    	ps.executeQuery();
		ResultSet rs=null;
		rs= ps.getResultSet();
       while(rs.next()){
   
       	platanus.setIndice(rs.getInt("indice"));
       	platanus.setTipo(rs.getInt("tipo"));
       	platanus.setFecha(rs.getString("fecha"));
    	platanus.setIp(rs.getString("ip"));
       	platanus.setIddispositivo(rs.getInt("iddispositivo"));
       	platanus.setModelodispositivo(rs.getString("modelodispositivo"));
       	platanus.setCoordenadas(rs.getString("coordenadas"));
       	platanus.setProveedor(rs.getString("proveedor"));
       	platanus.setEmail(rs.getString("email"));
       	platanus.setCaptura(rs.getInt("captura"));
       	platanus.setNumcaptura(rs.getInt("numcaptura"));
       	platanus.setR(rs.getString("R"));
       	platanus.setG(rs.getString("G"));
       	platanus.setB(rs.getString("B"));
       	platanus.setFichero(rs.getString("fichero"));
    	platanus.setFicherohistogramtxt((rs.getString("ficherohistogramatxt")));
       	platanus.setFicherocrop(rs.getString("ficherocrop"));
       	platanus.setFicheromascara(rs.getString("ficheromascara"));
       	platanus.setFicherohistograma(rs.getString("ficherohistograma"));
       	platanus.setVersion(rs.getString("version"));
       	platanus.setObservaciones(rs.getString("observaciones"));
       	platanus.setPublicado(rs.getInt("publicado"));
        
       }
    	 } catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			System.out.println("message"+e.getMessage());
 		}
    	 return platanus;    	
    	  
    }
       
    public List<Platanus> findAll(){
    	
    	Connection con = DBUtil.getConnection();
    	 List<Platanus> list=new ArrayList<Platanus>();
    	 try { 
    	String select="SELECT * FROM `platanus`;";
    	PreparedStatement ps = con.prepareStatement(select,Statement.RETURN_GENERATED_KEYS);
        ps.execute(select);
		ResultSet rs=null;
		rs= ps.getResultSet();
       while(rs.next()){
    	Platanus platanus=new Platanus();
       	platanus.setIndice(rs.getInt("indice"));
       	platanus.setTipo(rs.getInt("tipo"));
       	platanus.setFecha(rs.getString("fecha"));
    	platanus.setIp(rs.getString("ip"));
       	platanus.setIddispositivo(rs.getInt("iddispositivo"));
       	platanus.setModelodispositivo(rs.getString("modelodispositivo"));
       	platanus.setCoordenadas(rs.getString("coordenadas"));
       	platanus.setProveedor(rs.getString("proveedor"));
       	platanus.setEmail(rs.getString("email"));
       	platanus.setCaptura(rs.getInt("captura"));
       	platanus.setNumcaptura(rs.getInt("numcaptura"));
       	platanus.setR(rs.getString("R"));
       	platanus.setG(rs.getString("G"));
       	platanus.setB(rs.getString("B"));
       	platanus.setFichero(rs.getString("fichero"));
       	platanus.setFicherocrop(rs.getString("ficherocrop"));
       	platanus.setFicherohistograma(rs.getString("ficherohistograma"));
       	platanus.setFicherohistogramtxt(rs.getString("ficherohistogramatxt"));
       	platanus.setVersion(rs.getString("version"));
       	platanus.setObservaciones(rs.getString("observaciones"));
       	platanus.setPublicado(rs.getInt("publicado"));
        list.add(platanus);
       }
    	 } catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			System.out.println("message"+e.getMessage());
 		}
    	 return list;    	
    	  
    }
    
public List<Platanus> findAllMapCapturedImages(){
    	
    	Connection con = DBUtil.getConnection();
    	 List<Platanus> list=new ArrayList<Platanus>();
    	 try { 
    	
    	String select="select * from platanus where coordenadas>='';";
    	PreparedStatement ps = con.prepareStatement(select,Statement.RETURN_GENERATED_KEYS);
        ps.execute(select);
		ResultSet rs=null;
		rs= ps.getResultSet();
       while(rs.next()){
    	Platanus platanus=new Platanus();
       	platanus.setIndice(rs.getInt("indice"));
       	platanus.setTipo(rs.getInt("tipo"));
       	platanus.setFecha(rs.getString("fecha"));
    	platanus.setIp(rs.getString("ip"));
       	platanus.setIddispositivo(rs.getInt("iddispositivo"));
       	platanus.setModelodispositivo(rs.getString("modelodispositivo"));
       	platanus.setCoordenadas(rs.getString("coordenadas"));
       	platanus.setProveedor(rs.getString("proveedor"));
       	platanus.setEmail(rs.getString("email"));
       	platanus.setCaptura(rs.getInt("captura"));
       	platanus.setNumcaptura(rs.getInt("numcaptura"));
       	platanus.setR(rs.getString("R"));
       	platanus.setG(rs.getString("G"));
       	platanus.setB(rs.getString("B"));
       	platanus.setFichero(rs.getString("fichero"));
    	platanus.setFicherocrop(rs.getString("ficherocrop"));
    	platanus.setFicherohistograma(rs.getString("ficherohistograma"));
       	platanus.setVersion(rs.getString("version"));
       	platanus.setObservaciones(rs.getString("observaciones"));
       	platanus.setPublicado(rs.getInt("publicado"));
        list.add(platanus);
       }
    	 } catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			System.out.println("message"+e.getMessage());
 		}
    	 return list;    	
    	  
    }
    
 /*    
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
  }*/
 } 