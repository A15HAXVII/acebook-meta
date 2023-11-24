package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friendship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
    @Query("SELECT f FROM Friendship f WHERE f.status='pending' AND f.usernameAccepter=?1")
    List<Friendship> findRequestsForUser(String usernameAccepter);

    @Query("SELECT f FROM Friendship f WHERE (f.status='pending' AND f.usernameRequester=?1) OR (f.status='pending' AND f.usernameAccepter=?1)")
    List<Friendship> findAcceptedFriendsForUser(String username);
}