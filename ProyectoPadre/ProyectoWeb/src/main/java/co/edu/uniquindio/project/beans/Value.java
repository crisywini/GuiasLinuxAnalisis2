package co.edu.uniquindio.project.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * The class Value
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Named
@ApplicationScoped
public class Value {

	/** The valor credito. */
	private String valorCredito;

	/** The amortizacion. */
	private String amortizacion;

	/** The interes. */
	private String interes;

	/** The cuota. */
	private String cuota;

	/**
	 * Instantiates a new value.
	 *
	 * @param valorCredito the valor credito
	 * @param amortizacion the amortizacion
	 * @param interes      the interes
	 * @param cuota        the cuota
	 */
	public Value(String valorCredito, String amortizacion, String interes, String cuota) {
		this.valorCredito = valorCredito;
		this.amortizacion = amortizacion;
		this.interes = interes;
		this.cuota = cuota;
	}

	/**
	 * Gets the valor credito.
	 *
	 * @return the valor credito
	 */
	public String getValorCredito() {
		return valorCredito;
	}

	/**
	 * Sets the valor credito.
	 *
	 * @param valorCredito the new valor credito
	 */
	public void setValorCredito(String valorCredito) {
		this.valorCredito = valorCredito;
	}

	/**
	 * Gets the amortizacion.
	 *
	 * @return the amortizacion
	 */
	public String getAmortizacion() {
		return amortizacion;
	}

	/**
	 * Sets the amortizacion.
	 *
	 * @param amortizacion the new amortizacion
	 */
	public void setAmortizacion(String amortizacion) {
		this.amortizacion = amortizacion;
	}

	/**
	 * Gets the interes.
	 *
	 * @return the interes
	 */
	public String getInteres() {
		return interes;
	}

	/**
	 * Sets the interes.
	 *
	 * @param interes the new interes
	 */
	public void setInteres(String interes) {
		this.interes = interes;
	}

	/**
	 * Gets the cuota.
	 *
	 * @return the cuota
	 */
	public String getCuota() {
		return cuota;
	}

	/**
	 * Sets the cuota.
	 *
	 * @param cuota the new cuota
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
}
