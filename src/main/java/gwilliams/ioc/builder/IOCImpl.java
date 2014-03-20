package gwilliams.ioc.builder;

import gwilliams.ioc.IOC;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The IOC implementation
 * 
 * @author Gareth Williams
 */
class IOCImpl implements IOC {
	
	private final List<BinderImpl<?>> binders;

	/**
	 * Default constructor
	 * 
	 * @param bindings The bindings
	 */
	IOCImpl(List<BinderImpl<?>> bindings) {
		this.binders = bindings;
	}
	
	/*
	 * (non-Javadoc)
	 * @see gwilliams.ioc.IOC#resolve(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> T resolve(Class<T> clazz) {
		
		Optional<BinderImpl<?>> maybeBinding = binders
			.stream()
			.filter(b -> b.getInterfaceClazz().equals(clazz))
			.findFirst();
		
		if (maybeBinding.isPresent() == false) {
			throw new BindingException(String.format("Binding not found for %s", clazz.getName()));
		}
		
		Class<?> implementationClazz = maybeBinding.get().getImplementationClazz();

		try {
			Constructor<?> constructor = implementationClazz.getConstructors()[0];

			Object[] dependencies = Arrays
				.asList(constructor.getParameterTypes())
				.parallelStream()
				.<Object>map(c -> resolve(c))
				.toArray();

			return (T) constructor.newInstance(dependencies);
			
		} catch (Exception exception) {
			throw new BindingException(String.format("Error creating class %s", clazz.getName()), exception);
		}
	}
}
