package org.event.manager.repository.guice;

import static com.google.inject.matcher.Matchers.*;

import javax.persistence.EntityManager;

import org.event.annotations.annotations.PerformanceLog;
import org.event.manager.repository.Repository;
import org.event.manager.repository.annotations.Transactional;
import org.event.manager.repository.interceptors.PerformanceInterceptor;
import org.event.manager.repository.interceptors.TransactionInterceptor;

import com.google.inject.AbstractModule;

/**
 * 
 * @author fabricio
 * 
 */
public class InterceptorModule extends AbstractModule {

	/**
	 * Configures the JPA configuration for this project
	 */
	@Override
	protected void configure() {

		bindInterceptor(subclassesOf(Repository.class), annotatedWith(Transactional.class),
				new TransactionInterceptor());

		bindInterceptor(any(), annotatedWith(PerformanceLog.class),
				new PerformanceInterceptor());
		
		bind(EntityManager.class).toProvider(EntityManagerProvider.class);
	}

}
