package zhihu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhihu.dao.UpvoteDao;
import zhihu.model.Upvote;

/**
 * Created by ZJ on 2016/5/10.
 */

@Service
public class UpvoteService {

	@Autowired
	UpvoteDao upvoteDao;

	public Upvote save(Upvote upvote){
		return upvoteDao.save(upvote);
	}

	public Upvote findByAnsIDAndUserID(long ansID,long userID){
		Upvote  upvote= upvoteDao.findByAnsIDAndUserID(ansID,userID);

		if (upvote==null)
			upvote = save(new Upvote(userID,ansID));

		return upvote;
	}

	public void updateUpvote(Upvote upvote){
		Upvote queryUpvote = findByAnsIDAndUserID(upvote.getAnsID(),upvote.getUserID());
		queryUpvote.setUp(upvote.isUp());
		queryUpvote.setDown(upvote.isDown());
		save(queryUpvote);
	}

}
