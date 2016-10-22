package tuwien.ac.at.service.api;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vanja Bisanovic
 *
 */
public interface IGenericService<E, K> extends Serializable {

	public E create(E entity);

	public E update(E entity);

	public void remove(E entity);

	public E find(K key);

	public List<E> getAll();
}
