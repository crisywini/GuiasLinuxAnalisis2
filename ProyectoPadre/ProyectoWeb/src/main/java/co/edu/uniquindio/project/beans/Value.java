package co.edu.uniquindio.project.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Value {
	private String valorCredito;
	private String amortizacion;
	private String interes;
	private String cuota;
	
	public Value(String valorCredito, String amortizacion, String interes, String cuota) {
		this.valorCredito = valorCredito;
		this.amortizacion = amortizacion;
		this.interes = interes;
		this.cuota = cuota;
	}
	public String getValorCredito() {
		return valorCredito;
	}
	public void setValorCredito(String valorCredito) {
		this.valorCredito = valorCredito;
	}
	public String getAmortizacion() {
		return amortizacion;
	}
	public void setAmortizacion(String amortizacion) {
		this.amortizacion = amortizacion;
	}
	public String getInteres() {
		return interes;
	}
	public void setInteres(String interes) {
		this.interes = interes;
	}
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
}
