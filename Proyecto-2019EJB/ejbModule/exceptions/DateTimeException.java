package exceptions;

import obj.dto.DtoAlquiler;

public class DateTimeException extends Exception {

	DtoAlquiler alquiler;
	
	public DateTimeException(String msg) {
		super(msg);
	}
	

	public DtoAlquiler getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(DtoAlquiler alquiler) {
		this.alquiler = alquiler;
	}

}
