package es.upv.riromu.platanus.db;


public class HistogramRelation {
private Platanus captura_1;
private Platanus captura_2;
private double relation;


public HistogramRelation(Platanus captura_1, Platanus captura_2, double relation) {
	super();
	this.captura_1 = captura_1;
	this.captura_2 = captura_2;
	this.relation = relation;
}
public Platanus getCaptura_1() {
	return captura_1;
}
public void setCaptura_1(Platanus captura_1) {
	this.captura_1 = captura_1;
}
public Platanus getCaptura_2() {
	return captura_2;
}
public void setCaptura_2(Platanus captura_2) {
	this.captura_2 = captura_2;
}
public double getRelation() {
	return relation;
}
public void setRelation(double relation) {
	this.relation = relation;
}


}
