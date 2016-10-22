package tuwien.ac.at.db.relational.model.dto;

import java.io.Serializable;

/**
 * @author Vanja Bisanovic
 *
 */
public class AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	// public AbstractDTO() {
	// }

	public AbstractDTO(final long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

}
