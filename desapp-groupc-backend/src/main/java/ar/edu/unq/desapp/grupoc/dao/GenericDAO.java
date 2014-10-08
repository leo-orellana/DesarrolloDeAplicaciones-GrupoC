package ar.edu.unq.desapp.grupoc.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {

	void save(T entity);

	void delete(T entity);

	void update(T entity);

	T findById(Serializable id);

	List<T> findAll();

	void deleteById(Serializable id);

	int count();

	List<T> findByExample(T exampleObject);
	
	List<T> filterByName(String name);
}
