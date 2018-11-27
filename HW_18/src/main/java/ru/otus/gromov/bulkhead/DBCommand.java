package ru.otus.gromov.bulkhead;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.netflix.hystrix.HystrixCommand;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.gromov.dao.Repository;

import java.util.function.Function;

@Component
@Scope("prototype")
@Primary
public class DBCommand extends HystrixCommand {

	private Repository dao;
	private HystrixCommand.Setter config;
	private Function repositoryMethod;
	private Object inputParameterToFunction;

	@Autowired
	protected DBCommand(@Qualifier("hystrixConfig") HystrixCommand.Setter config) {
		super(config);
	}

	@Override
	@SneakyThrows
	protected Object run() {
		return repositoryMethod.apply(inputParameterToFunction);
	}


	public DBCommand setRepositoryMethod(Function function, Object object, Repository dao) {
		check();
		this.dao = dao;
		this.repositoryMethod = function;
		this.inputParameterToFunction = object;
		return this;
	}

	public DBCommand setRepositoryMethodWithoutParametrs(Function function, Repository dao) {
		check();
		this.dao = dao;
		this.repositoryMethod = function;
		this.inputParameterToFunction = null;
		return this;
	}

	private void check() {
		if (this.repositoryMethod != null) throw new RuntimeException("Command isn't empty!");
	}
}
