package ar.edu.unq.desapp.grupoc.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoc.dao.GenericDAO;

public class GenericService<T> implements Serializable {

	private static final long serialVersionUID = -6540963495078524186L;

	public GenericDAO<T> dao;

	public GenericDAO<T> getDao() {
		return this.dao;
	}

	public void setDao(final GenericDAO<T> dao) {
		this.dao = dao;
	}

	@Transactional
	public void delete(final T object) {
		this.getDao().delete(object);
	}

	@Transactional
	public List<T> retriveAll() {
		return this.getDao().findAll();
	}

	@Transactional
	public void save(final T object) {
		this.getDao().save(object);
	}

	@Transactional
	public void update(final T object) {
		this.getDao().update(object);
	}

	@Transactional
	public List<T> filterByName(String name) {
		List<T> list = this.dao.filterByName(name);
		return list;
	}
	
	public T getById(int id){
		T object = this.dao.findById(id);
		return object;
	}
}
