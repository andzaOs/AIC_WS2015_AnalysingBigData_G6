package tuwien.ac.at.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tuwien.ac.at.commons.util.ListConverter;
import tuwien.ac.at.db.relational.model.entity.AbstractEntity;
import tuwien.ac.at.service.api.IGenericService;

/**
 * @author Vanja Bisanovic
 *
 */
public abstract class GenericServiceImpl<E extends AbstractEntity, K extends Serializable> implements IGenericService<E, K> {

	private static final long serialVersionUID = 8478645675122668528L;

	private CrudRepository<E, K> repository;

	public GenericServiceImpl(final CrudRepository<E, K> repository) {
		this.repository = repository;
	}

	public GenericServiceImpl() {
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public E create(final E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public E update(final E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(final E entity) {
		repository.delete(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public E find(final K key) {
		return repository.findOne(key);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<E> getAll() {
		List<E> entities = ListConverter.convertToList(repository.findAll());
		return entities;
	}

}