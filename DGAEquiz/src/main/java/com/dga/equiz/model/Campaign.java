package com.dga.equiz.model;

public class Campaign {
    private final long id;
    private final String title;
    private final String description;
    private final Lesson lesson;
    private final String campaignAvatar;

    public Campaign(long id, String title, String description, Lesson lesson, String campaignAvatar) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lesson = lesson;
        this.campaignAvatar = campaignAvatar;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public String getCampaignAvatar() {
        return campaignAvatar;
    }
}
