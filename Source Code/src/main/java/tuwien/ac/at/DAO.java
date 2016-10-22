package tuwien.ac.at;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tuwien.ac.at.db.relational.model.entity.Following;
import tuwien.ac.at.db.relational.model.entity.TwitterUser;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DAO {

	
//	Connection c=null;
	
//	public void openConnection(){
//		try {
//			c=(Connection) DAL.connect();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public void closeConnection(){
//		try {
//			DAL.disconnect();
//			c=null;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public List<TwitterUser> getAll() throws Exception{
        try{
        Connection c= (Connection) DAL.connect();
        Statement ps = (Statement)c.createStatement();
        ResultSet rs = ps.executeQuery("SELECT * FROM twitteruser");
        List<TwitterUser> users = new ArrayList<TwitterUser>();
        while (rs.next()){
            users.add(loadUser(rs));
        }
        return users;
        } catch (Exception e) {
            DAL.disconnect();
            throw new Exception(e.getMessage());
        }  finally {
        	DAL.disconnect();
		} 
         
    }
	
	public TwitterUser getByScreenname(String screenname) throws Exception
    {
        Connection c = (Connection) DAL.connect();
        try
        {
            c= (Connection) DAL.connect();
            PreparedStatement getByName = c.prepareStatement("SELECT * FROM twitteruser where screenname=?");
            getByName.setString(1, screenname);
            ResultSet rs = getByName.executeQuery();
            while (rs.next())
            {
                return loadUser(rs);
            }
        } 
        catch (Exception e) {
            DAL.disconnect();
            throw new Exception(e.getMessage());
        }  finally {
        	DAL.disconnect();
		} 
         
        return null;
         
    }
	
	public TwitterUser getByID(Long id) throws Exception
    {
        Connection c = (Connection) DAL.connect();
        try
        {
            //c= (Connection) DAL.connect();
            PreparedStatement getByID = c.prepareStatement("SELECT * FROM twitteruser where id=?");
            getByID.setLong(1, id);
            ResultSet rs = getByID.executeQuery();
            while (rs.next())
            {
                return loadUser(rs);
            }
        } 
        catch (Exception e) {
            DAL.disconnect();
            throw new Exception(e.getMessage());
        }  finally {
        	DAL.disconnect();
		} 
         
        return null;
         
    }
	
	public List<Following> getFollowingByFollowee(Long id) throws Exception
    {   Connection c = (Connection) DAL.connect();
        try
        {
          //  c= (Connection) DAL.connect();
            PreparedStatement getFollowing = c.prepareStatement("SELECT * FROM following where followee=?");
            getFollowing.setLong(1, id);
            ResultSet rs = getFollowing.executeQuery();
            List<Following> following = new ArrayList<Following>();
            while (rs.next())
            {
            	following.add(loadFollowing(rs));
            }
            return following;
        } 
        catch (Exception e) {
            DAL.disconnect();
            throw new Exception(e.getMessage());
        } finally {
        	DAL.disconnect();
		}         
    }
	
	 public void addFollowing (TwitterUser follower, TwitterUser followee) throws Exception
	    {
	        try
	        {
	        	Connection c = (Connection)DAL.connect();
	          
	        	Statement ps = (Statement)c.createStatement();
	            ps.executeUpdate("INSERT INTO `following` (`follower`, `followee`) VALUES ('"+follower.getId()+ "', '"+ followee.getId() + "')");
	        } 
	        catch (Exception e) 
	        {
	            DAL.disconnect();
	            throw new Exception(e.getMessage());
	        }finally {
	        	DAL.disconnect();
			}   
	    }
	
	public String contains(long twitterId) {
		String result = null;
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("twitterId: " + twitterId);
		try {

			c = (Connection) DAL.connect();
			c.setAutoCommit(false);
			pstmt = c
					.prepareStatement("SELECT screenname FROM twitteruser WHERE twitterID = ?");
			pstmt.setLong(1, twitterId);
			rs = pstmt.executeQuery();
			c.commit();

			if (rs.isBeforeFirst()) {
				rs.next();
				result = rs.getString("screenname");
			}
		} catch (Exception e) {
			try {
				DAL.disconnect();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}  finally {
        	try {
				DAL.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}   
		return result;
	}
	
	private TwitterUser loadUser(ResultSet rs) throws Exception{
		TwitterUser f=new TwitterUser();
        f.setId(rs.getInt(1));
        f.setScreenname(rs.getString(2));
        f.setFollowersNumber(rs.getInt(3));
        f.setFolloweesNumber(rs.getInt(4));
        f.setTweets(rs.getInt(5));
        f.setLocation(rs.getString(6));
        f.setDescription(rs.getString(7));
        f.setTwitterID(rs.getLong(8));
        
        return f;
    }
	
	private Following loadFollowing(ResultSet rs) throws Exception{
		Following f=new Following();
        f.setId(rs.getLong(1));
        TwitterUser follower = getByID(rs.getLong(2));
        TwitterUser followee = getByID(rs.getLong(3));
        f.setFollower(follower);
        f.setFollowee(followee);
        return f;
    }
}
