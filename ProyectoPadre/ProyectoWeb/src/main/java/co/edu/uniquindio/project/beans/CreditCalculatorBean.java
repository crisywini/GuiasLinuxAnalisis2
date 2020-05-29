package co.edu.uniquindio.project.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named("creditCalculatorBean")
@ViewScoped
public class CreditCalculatorBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Value> values;
	private String n;
	private String valueCredit;
	private String tasaInteres;
	@PostConstruct
	public void init() {
		values = new  ArrayList<Value>();
	}
	
	public void fillValues() {
		values.clear();
		int n2 = Integer.parseInt(n);
		double valueCredit2 = Double.parseDouble(valueCredit);
		double tasaInteres2 = Double.parseDouble(tasaInteres); 
		double cuota = calcularCuota();
		double interes = calcularInteres();
		double amortizacion = calcularAmortizacion(cuota, interes);
		values.add(new Value(String.format("%.2f", valueCredit2), String.format("%.2f", amortizacion), String.format("%.2f", interes), String.format("%.2f", cuota)));
		for (int i = 0; i < n2; i++) {
			valueCredit2 = valueCredit2-amortizacion;
			interes = valueCredit2*tasaInteres2;
			amortizacion = cuota-interes;
			values.add(new Value(String.format("%.2f", valueCredit2), String.format("%.2f", amortizacion), String.format("%.2f", interes), String.format("%.2f", cuota)));
		}
	}
	public double calcularAmortizacion(double cuota, double interes) {
		double amortizacion = cuota-interes;
		return amortizacion;
	}
	
	public double calcularInteres() {
		double valueCredit2 = Double.parseDouble(valueCredit);
		double tasaInteres2 = Double.parseDouble(tasaInteres); 
		double interes = valueCredit2*tasaInteres2;
		return interes;
	}
	
	public double calcularCuota() {
		int n2 = Integer.parseInt(n);
		double valueCredit2 = Double.parseDouble(valueCredit);
		double tasaInteres2 = Double.parseDouble(tasaInteres); 
		double cuota = (valueCredit2*tasaInteres2)/(1-Math.pow((1+tasaInteres2), -n2));
		return cuota;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getValueCredit() {
		return valueCredit;
	}

	public void setValueCredit(String valueCredit) {
		this.valueCredit = valueCredit;
	}

	public String getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(String tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	
}
