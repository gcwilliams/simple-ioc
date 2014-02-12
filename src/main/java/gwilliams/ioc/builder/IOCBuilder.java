package gwilliams.ioc.builder;

import gwilliams.ioc.IOC;

import java.util.List;

import com.google.common.collect.Lists;


/**
 * The IOC builder
 * 
 * @author Gareth Williams
 *
 */
public final class IOCBuilder {
	
	private final List<BinderImpl<?>> binders = Lists.newArrayList();

	private IOCBuilder() {}
	
	/**
	 * Creates a new instance of the IOC builder
	 * 
	 * @return A new instance of {@link IOCBuilder}
	 */
	public static IOCBuilder newInstance() {
		return new IOCBuilder();
	}
	
	/**
	 * Creates a binding
	 * 
	 * @param clazz The class to bind
	 * @return The binder
	 */
	public <T> IOC.Binder<T> bind(Class<T> interfaceClazz) {
		BinderImpl<T> binder = new BinderImpl<T>(interfaceClazz);
		binders.add(binder);
		return binder;
	}
	
	/**
	 * Builds the IOC container
	 * 
	 * @return The {@link IOC} container
	 */
	public IOC build() {
		return new IOCImpl(binders);
	}
}
