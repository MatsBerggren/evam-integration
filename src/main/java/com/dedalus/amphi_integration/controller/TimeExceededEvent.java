package com.dedalus.amphi_integration.controller;

import org.springframework.context.ApplicationEvent;

public class TimeExceededEvent extends ApplicationEvent {
    private final Boolean timeExceeded;
    
    public TimeExceededEvent(Object source, Boolean timeExceeded) {
        super(source);
        this.timeExceeded = timeExceeded;
    }

    public Boolean getTimeExceeded() {
        return timeExceeded;
    }
}