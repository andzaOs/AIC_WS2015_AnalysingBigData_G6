package tuwien.ac.at.db.relational.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

;

@Entity
@Table(name = "following")
public class Following extends AbstractEntity{

	private static final long serialVersionUID = 7948504870595968829L;

	@ManyToOne
	@JoinColumn(name = "follower", referencedColumnName = "id")
	private TwitterUser follower;

	@ManyToOne
	@JoinColumn(name = "followee", referencedColumnName = "id")
	private TwitterUser followee;

	public Following() {
	}

	public Following(final TwitterUser follower, final TwitterUser followee) {
		this.follower = follower;
		this.followee = followee;
	}

	public TwitterUser getFollower() {
		return follower;
	}

	public void setFollower(final TwitterUser follower) {
		this.follower = follower;
	}

	public TwitterUser getFollowee() {
		return followee;
	}

	public void setFollowee(final TwitterUser followee) {
		this.followee = followee;
	}

}
