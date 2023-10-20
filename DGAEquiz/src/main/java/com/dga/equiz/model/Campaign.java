package com.dga.equiz.model;

public class Campaign {
    private final long campaignNumber;
    private final String title;
    private final String description;
    private Lesson lesson;
    private Revision revision;

    public Campaign(long campaignNumber, String title, String description) {
        this.campaignNumber = campaignNumber;
        this.title = title;
        this.description = description;
    }

    public long getCampaignNumber() {
        return campaignNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
