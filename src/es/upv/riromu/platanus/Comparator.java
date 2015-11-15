package es.upv.riromu.platanus;

import java.io.File;
import java.util.List;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import es.upv.riromu.platanus.db.HistogramRelation;
import es.upv.riromu.platanus.db.HistogramRelationDAO;
import es.upv.riromu.platanus.db.Platanus;
import es.upv.riromu.platanus.db.PlatanusDAO;
import es.upv.riromu.platanus.image.ImageUtil;

public class Comparator{
	public static void compare(int platanusid)
	{
	 try {
			NativeLibraries nl=new NativeLibraries();
		if(!(nl.getLoadedLibraries().contains("/usr/lib/libopencv_java300.so")))
		{
		  Opencvlibrary.loadlibrary();
		}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	
		Mat m=new Mat();	 
	 String applicationPath = "/usr/share/tomcat7-data";
       // constructs path of the directory to save uploaded file
      String uploadFilePath = applicationPath + File.separator;
   
	 try{
  	PlatanusDAO insertDao=new PlatanusDAO();
    List <Platanus> list=insertDao.findAll();
    
    Platanus p1,p2;
    p1=(Platanus)insertDao.find(platanusid);
for(int i=0;i<list.size();i++)
{
	if(i!=platanusid)
	{
		p2=(Platanus)list.get(i);
		String filename1=p1.getFicherohistogramtxt();
		String filename2=p2.getFicherohistogramtxt();
		if(filename1.endsWith(".txt")&& filename2.endsWith(".txt"))
		{	
		String JSON1=ImageUtil.readFile(uploadFilePath+File.separator+filename1);
		Mat histogram1=ImageUtil.matFromJson(JSON1);
		Mat histogram_temp1=new Mat();
		Mat histogram_temp2=new Mat();
		histogram1.convertTo(histogram_temp1, CvType.CV_32F);
		String JSON2=ImageUtil.readFile(uploadFilePath+File.separator+filename2);
		Mat histogram2=ImageUtil.matFromJson(JSON2);
		histogram2.convertTo(histogram_temp2, CvType.CV_32F);
		double comparison=Imgproc.compareHist(histogram_temp1, histogram_temp2, Imgproc.CV_COMP_CORREL);
	    HistogramRelation histogramRelation=new HistogramRelation(p1,p2,comparison);
	    HistogramRelationDAO histogramDAO=new HistogramRelationDAO();
	    histogramDAO.save(histogramRelation);
		}
	}
}
	 }catch(Exception e){}
	}
}
	 
	

       

  
      