package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Friendship;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendshipRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class FriendshipController {

    @Autowired
    FriendshipRepository friendshipRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile/{username}")
    public String profile(Model model, @PathVariable("username") String username) {
        List<User> users = userRepository.findByUsername(username);
        if(users.isEmpty()) {
            User user = new User();
            model.addAttribute(user);
            return "users/new";
        } else {
            User user = users.get(0);
            model.addAttribute("user", user);
            int numOfFriends = friendshipRepository.findAcceptedFriendsForUser(user.getUsername()).size();
            model.addAttribute("numOfFriends", numOfFriends);
            return "profile/userprofile";
        }
    }

    @GetMapping("/friends/{username}")
    public String friendRequest(Model model, @PathVariable("username") String username) {
        List<User> users = userRepository.findByUsername(username);
        User user = users.get(0);
        model.addAttribute("user", user);

        model.addAttribute("friendship", new Friendship());

        List<Friendship> friends = friendshipRepository.findAcceptedFriendsForUser(username);
        model.addAttribute("friends", friends);

        List<Friendship> friendRequests = friendshipRepository.findRequestsForUser(username);
        model.addAttribute("friendRequests", friendRequests);

        return "/profile/friends";
    }

    @PostMapping("/friends/{username}")
    public RedirectView friendRequest(@ModelAttribute Friendship friendship, @PathVariable("username") String username){
        friendshipRepository.save(friendship);
        String url = "/profile/" + username;
        return new RedirectView(url);
    }

    @PostMapping("friends/{usernameAccepter}/{usernameRequester/{action}")
    public void acceptOrRejectRequest(@PathVariable("action") String action, @PathVariable("username") Long userid, @PathVariable("requestid") Long requestid ){
        Optional<Friendship> friendship = friendshipRepository.findById(requestid);
        if(Objects.equals(action, "accept") || Objects.equals(action, "reject")) {
            friendshipRepository.deleteById(requestid);
        }
        if(action.equals("accept")) {
            Friendship friendship1 = new Friendship(friendship.get().getUsernameRequester(), friendship.get().getUsernameAccepter(), "accepted");
            friendshipRepository.save(friendship1);
        }
    }
}
