package tuwien.ac.at.db.relational.model.dto;

public class FollowingDTO {
	
	TwitterUserDTO follower;
	TwitterUserDTO followee;
		
	public FollowingDTO(){}
	
	public FollowingDTO(TwitterUserDTO follower, TwitterUserDTO followee){
		this.follower = follower;
		this.followee = followee;
	}
	
	public TwitterUserDTO getFollower() {
		return follower;
	}
	public void setFollower(TwitterUserDTO follower) {
		this.follower = follower;
	}
	public TwitterUserDTO getFollowee() {
		return followee;
	}
	public void setFollowee(TwitterUserDTO followee) {
		this.followee = followee;
	}

}
