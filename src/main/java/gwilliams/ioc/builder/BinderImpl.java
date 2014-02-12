package gwilliams.ioc.builder;

import gwilliams.ioc.IOC;

/**
 * The binding implementation
 * 
 * @author Gareth Williams
 */
class BinderImpl<T> implements IOC.Binder<T> {
	
	private final Class<T> interfaceClazz;
	
	private Class<? extends T> implementationClazz;

	/**
	 * Default constructor
	 * 
	 * @param interfaceClazz The interface clazz
	 */
	BinderImpl(Class<T> interfaceClazz) {
		this.interfaceClazz = interfaceClazz;
	}
	
	/**
	 * Binds an implementation to the interface
	 * 
	 */
	public void to(Class<? extends T> implementationClazz) {
		this.implementationClazz = implementationClazz;
	}
	
	/**
	 * Gets the interface class
	 * 
	 * @return The interface class
	 */
	Class<T> getInterfaceClazz() {
		return interfaceClazz;
	}
	
	/**
	 * Gets the implementation class
	 * 
	 * @return The implementation class
	 */
	Class<? extends T> getImplementationClazz() {
		return implementationClazz;
	}
}