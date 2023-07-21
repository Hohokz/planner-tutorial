package com.company.planner.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Table(name = "PLANNER_SESSION")
@Entity(name = "planner_Session")
@NamePattern("%s|topic")
public class Session extends StandardEntity {
    private static final long serialVersionUID = 3271255497931152196L;

    @NotNull
    @Column(name="TOPIC", nullable = false)
    protected String topic;

    @NotNull
    @Column(name="START_DATE", nullable = false)
    protected LocalDateTime startDate;

    @NotNull
    @Column(name="DURATION", nullable = false)
    @Positive
    protected Integer duration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="SPEAKER_ID")
    protected Speaker speaker;

    @Lob
    @Column(name="DESCRIPTION", length = 1000)
    protected String description;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    @MetaProperty(related = {"startDate", "duration"})
    public LocalDateTime getEndDate(){
        if(startDate != null && duration != null){
            return startDate.plusHours(duration);
        }else{
            return null;
        }
    }
}