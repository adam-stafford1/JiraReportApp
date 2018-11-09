package com.adam.stafford.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResourceModel {

    @XmlElement(name = "timeSpent")
    private Long timeSpent;

    @XmlElement(name = "timeRemaining")
    private Long timeRemaining;

    public RestResourceModel() {
    }

    public RestResourceModel(Long timeSpent, Long timeRemaining) {
        this.timeSpent = timeSpent;
        this.timeRemaining = timeRemaining;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpen(Long timeSpent) {
        this.timeSpent = timeSpent;
    }
}