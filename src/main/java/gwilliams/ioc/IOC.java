package gwilliams.ioc;

/**
 * The Simple IOC
 * 
 * @author Gareth Williams
 *
 */
public interface IOC {
	
	/**
	 * Resolves and class injecting its dependencies
	 * 
	 * @param clazz The class to resolve
	 * @return The class
	 */
	<T> T resolve(Class<T> clazz);
	
	/**
	 * The binder interface
	 * 
	 * @author Gareth Williams
	 */
	interface Binder<T> {
		
		/**
		 * Binds the implementation
		 * 
		 * @param implementationClazz
		 */
		void to(Class<? extends T> implementationClazz);
	}
}
