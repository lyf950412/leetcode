package github.lyf.middle;

import java.util.*;

/**
 * @author lyf
 */
public class Twitter {
    /**
     *设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，
     * 能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
     *
     * postTweet(userId, tweetId): 创建一条新的推文
     * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。
     * 推文必须按照时间顺序由最近的开始排序。
     * follow(followerId, followeeId): 关注一个用户
     * unfollow(followerId, followeeId): 取消关注一个用户
     * 示例:
     *
     * Twitter twitter = new Twitter();
     *
     * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
     * twitter.postTweet(1, 5);
     *
     * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
     * twitter.getNewsFeed(1);
     *
     * // 用户1关注了用户2.
     * twitter.follow(1, 2);
     *
     * // 用户2发送了一个新推文 (推文id = 6).
     * twitter.postTweet(2, 6);
     *
     * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
     * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
     * twitter.getNewsFeed(1);
     *
     * // 用户1取消关注了用户2.
     * twitter.unfollow(1, 2);
     *
     * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
     * // 因为用户1已经不再关注用户2.
     * twitter.getNewsFeed(1);
     */
    class User{
        Integer id;
        List<User> follows;

        public User(Integer id) {
            this.id      = id;
            this.follows = new ArrayList<>();
        }
    }
    class Tweet{
        int userId;
        int tweetId;

        public Tweet(int userId, int tweetId) {
            this.userId  = userId;
            this.tweetId = tweetId;
        }
    }

    List<Tweet> tweets;
    HashMap<Integer,User> users;

    public Twitter(){
        users  = new HashMap<>();
        tweets = new ArrayList<>();
    }

    public void postTweet(int userId, int tweetId) {
        User user = users.get(userId);
        if (user == null)
            user = new User(userId);
        Tweet tweet = new Tweet(userId, tweetId);
        tweets.add(tweet);
        users.put(userId, user);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> list = new ArrayList<>();
        User user = users.get(userId);
        if (user == null) return list;
        List<User> follows = user.follows;
        for (int i = tweets.size() - 1; i >= 0; i--) {
            Tweet tweet = tweets.get(i);
            if (user.id == tweet.userId) list.add(tweet.tweetId);
            if (list.size() == 10) return list;
            for (User u: follows) {
                if (user.id.equals(u.id)) continue;
                if (u.id == tweet.userId) list.add(tweet.tweetId);
                if (list.size() == 10) return list;
            }
        }
        return list;
    }

    public void follow(int followerId, int followeeId) {
        User user = users.get(followerId);
        User f = users.get(followeeId);
        if (f == null)
            f = new User(followeeId);
        if (user == null) {
            user = new User(followerId);
            users.put(followerId, user);
            user.follows.add(f);
            return;
        }
        for (User u: user.follows) {
            // 已经关注过了
            if (u.id == followeeId){
                return;
            }
        }
        users.put(followeeId, f);
        user.follows.add(f);
    }

    public void unfollow(int followerId, int followeeId) {
        User user = users.get(followerId);
        if (user == null) return;
        for (int i = 0; i < user.follows.size(); i++) {
            if (user.follows.get(i).id == followeeId){
                user.follows.remove(i);
                break;
            }
        }
    }
}
