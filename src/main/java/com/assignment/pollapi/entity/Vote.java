package com.assignment.pollapi.entity;

import jakarta.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @ManyToOne
    private Poll poll;

    @ManyToOne
    private PollOption option;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public PollOption getOption() {
        return option;
    }

    public void setOption(PollOption option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", poll=" + poll +
                ", option=" + option +
                '}';
    }
}
