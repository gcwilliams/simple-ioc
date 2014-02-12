package gwilliams.ioc.builder;

import gwilliams.ioc.IOC;

import java.lang.reflect.Constructor;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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
		
		Optional<BinderImpl<?>> maybeBinding = find(clazz);
		
		if (maybeBinding.isPresent() == false) {
			throw new BindingException(String.format("Binding not found for %s", clazz.getName()));
		}
		
		Class<?> implementationClazz = maybeBinding.get().getImplementationClazz();

		try {
			Constructor<?> constructor = implementationClazz.getConstructors()[0];
			return (T) constructor.newInstance(resolveDependencies(constructor));
			
		} catch (Exception exception) {
			throw new BindingException(String.format("Error creating class %s", clazz.getName()), exception);
		}
	}
	
	/**
	 * Resolves the dependencies of the {@link Constructor}
	 * 
	 * @param constructor The {@link Constructor}
	 * @return The dependencies
	 */
	private Object[] resolveDependencies(Constructor<?> constructor) {
		List<Object> dependencies = Lists.newArrayList();
		for (Class<?> dependency : constructor.getParameterTypes()) {
			dependencies.add(resolve(dependency));
		}
		return dependencies.toArray();
	}
	
	/**
	 * Finds the binding for the specified class
	 * 
	 * @param clazz The {@link Class} to find the binding for
	 * @return The {@link Optional} of {@link BinderImpl}
	 */
	private Optional<BinderImpl<?>> find(final Class<?> clazz) {
		return Iterables.tryFind(binders, new Predicate<BinderImpl<?>>() {
			public boolean apply(BinderImpl<?> input) {
				return input.getInterfaceClazz().equals(clazz);
			}
		});
	}
}
