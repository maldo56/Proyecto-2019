package exceptions;

public class ImageException extends Exception {

	private boolean success;
	
	public ImageException(String message) {
		super(message);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
