package tuwien.ac.at.service.api;

import tuwien.ac.at.db.relational.model.entity.TwitterUser;

public interface ITwitterUserService extends IGenericService<TwitterUser, Long>{
	public void fillDatabase(TwitterUser user);
}
