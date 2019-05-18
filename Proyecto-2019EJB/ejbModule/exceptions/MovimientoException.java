package exceptions;

import obj.dto.DtoAlquiler;

public class MovimientoException extends Exception {

	DtoAlquiler alquiler;
	
	public MovimientoException(String msg) {
		super(msg);
	}
	

	public DtoAlquiler getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(DtoAlquiler alquiler) {
		this.alquiler = alquiler;
	}
	
}
