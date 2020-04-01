/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author NahlaJ
 */
public class ReservationEvent {
    private int id;
    private int event;
    private int user;

    public ReservationEvent() {
    }

    public ReservationEvent(int id, int event, int user) {
        this.id = id;
        this.event = event;
        this.user = user;
    }

    public ReservationEvent(int event, int user) {
        this.event = event;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReservationEvent{" + "id=" + id + ", event=" + event + ", user=" + user + '}';
    }
    
    
    
}
