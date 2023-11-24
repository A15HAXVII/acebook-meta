package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username_requester")
    private String usernameRequester;
    @Column(name = "username_accepter")
    private String usernameAccepter;

    private String status;

    public Friendship() {}

    public Friendship(String usernameRequester, String usernameAccepter, String status) {
        this.usernameRequester = usernameRequester;
        this.usernameAccepter = usernameAccepter;
        this.status = status;
    }

    public String getUsernameRequester() {
        return usernameRequester;
    }

    public void setUsernameRequester(String usernameRequester) {
        this.usernameRequester = usernameRequester;
    }

    public String getUsernameAccepter() {
        return usernameAccepter;
    }

    public void setUsernameAccepter(String usernameAccepter) {
        this.usernameAccepter = usernameAccepter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
