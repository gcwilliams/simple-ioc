package gwilliams.ioc.builder;

/**
 * The binding exception
 * 
 * @author Gareth Williams
 */
@SuppressWarnings("serial")
public class BindingException extends RuntimeException {
	
	/**
	 * Default constructor
	 * 
	 * @param message The message
	 */
	public BindingException(String message) {
		super(message);
	}
	
	/**
	 * Constructor with nested exception
	 * 
	 * @param message The message
	 * @param throwable The throwable
	 */
	public BindingException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
