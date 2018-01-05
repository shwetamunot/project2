package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDao {
List<User> suggestedUserList(String username);
void addFriendRequest(Friend friend);
List<Friend> pendingRequests(String username);
void updatePendingRequests(Friend friend);
List<User> listOfFriends(String username);
}
