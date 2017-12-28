package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePicDao {
	void saveOrUpdateProfilePicture(ProfilePicture profilePicture);
	ProfilePicture getProfilePicture(String username);
}
