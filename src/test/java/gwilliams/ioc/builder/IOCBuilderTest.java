package gwilliams.ioc.builder;

import gwilliams.ioc.IOC;

import org.junit.Test;


/**
 * The IOC builder tests
 * 
 * @author Gareth Williams
 */
public class IOCBuilderTest {

	@Test
	public void no_dependencies() {
		
		// arrange
		IOCBuilder builder = IOCBuilder.newInstance();
		builder.bind(TestDao.class).to(TestDaoImpl.class);
		
		IOC ioc = builder.build();
		
		// act
		ioc.resolve(TestDao.class);
	}
	
	@Test
	public void one_dependencies() {
		
		// arrange
		IOCBuilder builder = IOCBuilder.newInstance();
		builder.bind(TestDao.class).to(TestDaoImpl.class);
		builder.bind(TestValidator.class).to(TestValidatorImpl.class);
		
		IOC ioc = builder.build();
		
		// act
		ioc.resolve(TestValidator.class);
	}
	
	@Test
	public void two_dependencies() {
		
		// arrange
		IOCBuilder builder = IOCBuilder.newInstance();
		builder.bind(TestDao.class).to(TestDaoImpl.class);
		builder.bind(TestValidator.class).to(TestValidatorImpl.class);
		builder.bind(TestService.class).to(TestServiceImpl.class);
		
		IOC ioc = builder.build();
		
		// act
		ioc.resolve(TestService.class);
	}
	
	@Test(expected = BindingException.class)
	public void missing_dependency() {
		
		// arrange
		IOCBuilder builder = IOCBuilder.newInstance();
		builder.bind(TestDao.class).to(TestDaoImpl.class);
		builder.bind(TestService.class).to(TestServiceImpl.class);
		
		IOC ioc = builder.build();
		
		// act
		ioc.resolve(TestService.class);
	}
}
