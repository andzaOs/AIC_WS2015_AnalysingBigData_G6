package tuwien.ac.at.db.graph.repo.api;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import tuwien.ac.at.db.graph.model.entity.User;

/**
 * 
 * @author Vanja Bisanovic
 *
 */
public interface UserRepository extends GraphRepository<User> {

	List<User> findByScreenname(String name);
}
