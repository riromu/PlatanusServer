package es.upv.riromu.platanus.image;
/**********************************************************************   

MatToBufImg is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

MattoBufImg is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with MatToBufImg.  If not, see <http://www.gnu.org/licenses/>
****************************************************************/


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.apache.tomcat.util.codec.binary.Base64;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//A Note on HIghGUI image read

//http://docs.opencv.org/java/2.4.2/org/opencv/highgui/Highgui.html#imread(java.lang.String, int)
//Currently, the following file formats are supported:
//
//Windows bitmaps - *.bmp, *.dib (always supported)
//JPEG files - *.jpeg, *.jpg, *.jpe (see the *Notes* section)
//JPEG 2000 files - *.jp2 (see the *Notes* section)
//Portable Network Graphics - *.png (see the *Notes* section)
//Portable image format - *.pbm, *.pgm, *.ppm (always supported)
//Sun rasters - *.sr, *.ras (always supported)
//TIFF files - *.tiff, *.tif (see the *Notes* section)

public class ImageUtil {
 Mat matrix;
 MatOfByte mob;
 String fileExten;

 // The file extension string should be ".jpg", ".png", etc
public ImageUtil(Mat amatrix, String fileExtension){
	matrix = amatrix;
	fileExten = fileExtension;
	mob = new MatOfByte();
}

public static Mat readInputStreamIntoMat(InputStream inputStream) throws IOException {
    // Read into byte-array
    byte[] temporaryImageInMemory = readStream(inputStream);

    // Decode into mat. Use any IMREAD_ option that describes your image appropriately
    Mat outputImage = Imgcodecs.imdecode(new MatOfByte(temporaryImageInMemory), Imgcodecs.IMREAD_GRAYSCALE);

    return outputImage;
}

public static Mat matFromJson(String json){
    JsonParser parser = new JsonParser();
    JsonObject JsonObject = parser.parse(json).getAsJsonObject();

    int rows = JsonObject.get("rows").getAsInt();
    int cols = JsonObject.get("cols").getAsInt();
    int type = JsonObject.get("type").getAsInt();

    String dataString = JsonObject.get("data").getAsString();       
    byte[] data = Base64.decodeBase64(dataString.getBytes()); 

    Mat mat = new Mat(rows, cols, type);
    mat.put(0, 0, data);

    return mat;
}



public static byte[] readStream(InputStream stream) throws IOException {
    // Copy content of the image to byte-array
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    int nRead;
    byte[] data = new byte[16384];

    while ((nRead = stream.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, nRead);
    }

    buffer.flush();
    byte[] temporaryImageInMemory = buffer.toByteArray();
    buffer.close();
    stream.close();
    return temporaryImageInMemory;
}

public BufferedImage getImage(){
	//convert the matrix into a matrix of bytes appropriate for
	//this file extension
	Imgcodecs.imencode(fileExten, matrix ,mob); 
	//convert the "matrix of bytes" into a byte array
	 byte[] byteArray = mob.toArray();
	 BufferedImage bufImage = null;
	 try {
	        InputStream in = new ByteArrayInputStream(byteArray);
	        bufImage = ImageIO.read(in);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	 return bufImage;
}
public static String readFile( String file ) throws IOException {
    BufferedReader reader = new BufferedReader( new FileReader (file));
    String         line = null; 
    StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    while( ( line = reader.readLine() ) != null ) {
        stringBuilder.append( line );
        stringBuilder.append( ls );
    }

    return stringBuilder.toString();
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
