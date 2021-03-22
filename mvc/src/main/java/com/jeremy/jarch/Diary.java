package com.jeremy.jarch;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Diary {
    private String id;
    private String title;
    private String description;

    private List<Observer<Diary>> mObservers;

    public Diary(String title, String description) {
        this(UUID.randomUUID().toString(), title, description);
    }

    public Diary(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer<Diary> observer : getObservers()) {
            observer.update(this);
        }
    }

    private List<Observer<Diary>> getObservers() {
        if (mObservers == null) {
            mObservers = new ArrayList<>();
        }
        return mObservers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    public void registerObserver(Observer<Diary> observer) {
        getObservers().add(observer);
    }
}
