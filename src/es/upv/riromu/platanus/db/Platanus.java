package es.upv.riromu.platanus.db;


public class Platanus {
   private int indice;
   private int  tipo;
   private String fecha;
   private String ip;
   private int iddispositivo;
   private String modelodispositivo;
   private String coordenadas;
   private String proveedor;
   private String email;
   private int captura;
   private int numcaptura;
   private String R;
   private String G;
   private String B;
   private String fichero;
   private String ficherocrop;
   private String ficherohistograma;
   private String ficherohistogramtxt;
   public String getFicherohistogramtxt() {
	return ficherohistogramtxt;
}

public void setFicherohistogramtxt(String ficherohistogramtxt) {
	this.ficherohistogramtxt = ficherohistogramtxt;
}

public String getFicheromascara() {
	return ficheromascara;
}

public void setFicheromascara(String ficheromascara) {
	this.ficheromascara = ficheromascara;
}

private String ficheromascara;
   private String version;
   private String observaciones;
   private int publicado;
   
   public int getIndice() {
	return indice;
}

public String getFicherocrop() {
	return ficherocrop;
}

public void setFicherocrop(String ficherocrop) {
	this.ficherocrop = ficherocrop;
}

public String getFicherohistograma() {
	return ficherohistograma;
}

public void setFicherohistograma(String ficherohistograma) {
	this.ficherohistograma = ficherohistograma;
}

public void setIndice(int indice) {
	this.indice = indice;
}

public int getTipo() {
	return tipo;
}

public void setTipo(int tipo) {
	this.tipo = tipo;
}

public String getFecha() {
	return fecha;
}

public void setFecha(String fecha) {
	this.fecha = fecha;
}

public String getIp() {
	return ip;
}

public void setIp(String ip) {
	this.ip = ip;
}

public int getIddispositivo() {
	return iddispositivo;
}

public void setIddispositivo(int iddispositivo) {
	this.iddispositivo = iddispositivo;
}

public String getModelodispositivo() {
	return modelodispositivo;
}

public void setModelodispositivo(String modelodispositivo) {
	this.modelodispositivo = modelodispositivo;
}

public String getCoordenadas() {
	return coordenadas;
}

public void setCoordenadas(String coordenadas) {
	this.coordenadas = coordenadas;
}

public String getProveedor() {
	return proveedor;
}

public void setProveedor(String proveedor) {
	this.proveedor = proveedor;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public int getCaptura() {
	return captura;
}

public void setCaptura(int captura) {
	this.captura = captura;
}

public int getNumcaptura() {
	return numcaptura;
}

public void setNumcaptura(int numcaptura) {
	this.numcaptura = numcaptura;
}

public String getR() {
	return R;
}

public void setR(String r) {
	R = r;
}

public String getG() {
	return G;
}

public void setG(String g) {
	G = g;
}

public String getB() {
	return B;
}

public void setB(String b) {
	B = b;
}

public String getFichero() {
	return fichero;
}

public void setFichero(String fichero) {
	this.fichero = fichero;
}

public String getVersion() {
	return version;
}

public void setVersion(String version) {
	this.version = version;
}

public String getObservaciones() {
	return observaciones;
}

public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
}

public int getPublicado() {
	return publicado;
}

public void setPublicado(int publicado) {
	this.publicado = publicado;
}

public Platanus(){
       System.out.println("Object Created");
   }

public Platanus( int tipo, String fecha, String ip,
		int iddispositivo, String modelodispositivo, String coordenadas,
		String proveedor, String email, int captura, int numcaptura,
		String r, String g, String b, String fichero, String version,
		String observaciones) {
	super();
	
	this.tipo = tipo;
	this.fecha = fecha;
	this.ip = ip;
	this.iddispositivo = iddispositivo;
	this.modelodispositivo = modelodispositivo;
	this.coordenadas = coordenadas;
	this.proveedor = proveedor;
	this.email = email;
	this.captura = captura;
	this.numcaptura = numcaptura;
	R = r;
	G = g;
	B = b;
	this.fichero = fichero;
	this.version = "3";
	this.observaciones = "";
	this.publicado = publicado;
}


    
}