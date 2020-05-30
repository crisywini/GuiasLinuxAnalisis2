package co.edu.uniquindio.project.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * The class CreditCalculatorBean
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Named("creditCalculatorBean")
@ViewScoped
public class CreditCalculatorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The values. */
	private List<Value> values;

	/** The n. */
	private String n;

	/** The value credit. */
	private String valueCredit;

	/** The tasa interes. */
	private String tasaInteres;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		values = new ArrayList<Value>();
	}

	/**
	 * Fill values.
	 */
	public void fillValues() {
		values.clear();
		int n2 = Integer.parseInt(n);
		double valueCredit2 = Double.parseDouble(valueCredit);
		double tasaInteres2 = Double.parseDouble(tasaInteres);
		tasaInteres2 = tasaInteres2 / 100;
		double cuota = calcularCuota();
		double interes = calcularInteres();
		double amortizacion = calcularAmortizacion(cuota, interes);
		values.add(new Value(String.format("%.2f", valueCredit2), 0.0 + "", 0.0 + "", 0.0 + ""));
		for (int i = 0; i < n2; i++) {
			valueCredit2 = valueCredit2 - amortizacion;
			interes = valueCredit2 * tasaInteres2;
			amortizacion = cuota - interes;
			values.add(new Value(String.format("%.2f", valueCredit2), String.format("%.2f", amortizacion),
					String.format("%.2f", interes), String.format("%.2f", cuota)));
		}
	}

	/**
	 * Calcular amortizacion.
	 *
	 * @param cuota   the cuota
	 * @param interes the interes
	 * @return the double
	 */
	public double calcularAmortizacion(double cuota, double interes) {
		double amortizacion = cuota - interes;
		return amortizacion;
	}

	/**
	 * Calcular interes.
	 *
	 * @return the double
	 */
	public double calcularInteres() {
		double valueCredit2 = Double.parseDouble(valueCredit);
		double tasaInteres2 = Double.parseDouble(tasaInteres) / 100;
		double interes = valueCredit2 * tasaInteres2;
		return interes;
	}

	/**
	 * Calcular cuota.
	 *
	 * @return the double
	 */
	public double calcularCuota() {
		int n2 = Integer.parseInt(n);
		double valueCredit2 = Double.parseDouble(valueCredit);
		double tasaInteres2 = Double.parseDouble(tasaInteres) / 100;
		double cuota = (valueCredit2 * tasaInteres2) / (1 - Math.pow((1 + tasaInteres2), -n2));
		return cuota;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public List<Value> getValues() {
		return values;
	}

	/**
	 * Sets the values.
	 *
	 * @param values the new values
	 */
	public void setValues(List<Value> values) {
		this.values = values;
	}

	/**
	 * Gets the n.
	 *
	 * @return the n
	 */
	public String getN() {
		return n;
	}

	/**
	 * Sets the n.
	 *
	 * @param n the new n
	 */
	public void setN(String n) {
		this.n = n;
	}

	/**
	 * Gets the value credit.
	 *
	 * @return the value credit
	 */
	public String getValueCredit() {
		return valueCredit;
	}

	/**
	 * Sets the value credit.
	 *
	 * @param valueCredit the new value credit
	 */
	public void setValueCredit(String valueCredit) {
		this.valueCredit = valueCredit;
	}

	/**
	 * Gets the tasa interes.
	 *
	 * @return the tasa interes
	 */
	public String getTasaInteres() {
		return tasaInteres;
	}

	/**
	 * Sets the tasa interes.
	 *
	 * @param tasaInteres the new tasa interes
	 */
	public void setTasaInteres(String tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
}
