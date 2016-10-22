package tuwien.ac.at.db.relational.repo.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import tuwien.ac.at.db.relational.model.entity.Following;

@Component
public interface IFollowingRepository extends CrudRepository<Following, Long> {
}
