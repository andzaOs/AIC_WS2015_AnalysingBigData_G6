package tuwien.ac.at.db.relational.repo.api;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import tuwien.ac.at.db.relational.model.entity.TwitterUser;

@Component
public interface ITwitterUserRepository  extends CrudRepository<TwitterUser, Long>{
	
	// spublic void fillDatabase(TwitterUserEntity user);
	public TwitterUser getById(long id);
	public TwitterUser getByScreenname(String screenname);
	
}
