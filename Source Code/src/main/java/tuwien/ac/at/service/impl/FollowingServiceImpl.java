package tuwien.ac.at.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuwien.ac.at.db.relational.model.entity.Following;
import tuwien.ac.at.db.relational.model.entity.TwitterUser;
import tuwien.ac.at.db.relational.repo.api.IFollowingRepository;
import tuwien.ac.at.service.api.IFollowingService;

@Service
public class FollowingServiceImpl extends GenericServiceImpl<Following, Long>
		implements IFollowingService {

	private static final long serialVersionUID = 8030738803199015520L;

	private IFollowingRepository followingRepository;

	public FollowingServiceImpl() {
	}

	@Autowired
	public FollowingServiceImpl(IFollowingRepository followingRepository) {
		super(followingRepository);
		this.followingRepository = followingRepository;
	}

	@Override
	public void createFollowing(TwitterUser follower,
			TwitterUser followee) {
		Following following = new Following(follower, followee);
		followingRepository.save(following);
	}

}
