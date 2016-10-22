package tuwien.ac.at.service.api;

import tuwien.ac.at.db.relational.model.entity.Following;
import tuwien.ac.at.db.relational.model.entity.TwitterUser;

public interface IFollowingService extends IGenericService<Following, Long>{
	public void createFollowing(TwitterUser follower, TwitterUser followee);

}
