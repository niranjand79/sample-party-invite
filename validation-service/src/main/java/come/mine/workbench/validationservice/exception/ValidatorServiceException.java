package come.mine.workbench.validationservice.exception;

/**
 * Exception class for database service
 * @author niranjandeshpande
 *
 */
public class ValidatorServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Parameterized constructor with error
	 * 
	 * @param throwable
	 *            error object
	 */
	public ValidatorServiceException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * Parameterized constructor with custom message and error
	 * 
	 * @param message
	 *            custom message
	 * @param throwable
	 *            error object
	 */
	public ValidatorServiceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * Parameterized constructor with custom message
	 * 
	 * @param message
	 *            error message
	 */
	public ValidatorServiceException(final String message) {
		super(message);
	}

}
