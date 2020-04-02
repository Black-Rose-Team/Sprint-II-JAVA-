/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author NahlaJ
 */
public class Event {
     private int idevent;
    private Date dateevent;
    private String lieuevent;
    private int nbrepersonnes;
    private int capevent;
    private String nomevent;
    private String description;
    private float ticketprice;
    private String eventImg;

    public Event() {
    }

    public Event(int idevent, Date dateevent, String lieuevent, int nbrepersonnes, int capevent, String nomevent, String description, float ticketprice, String eventImg) {
        this.idevent = idevent;
        this.dateevent = dateevent;
        this.lieuevent = lieuevent;
        this.nbrepersonnes = nbrepersonnes;
        this.capevent = capevent;
        this.nomevent = nomevent;
        this.description = description;
        this.ticketprice = ticketprice;
        this.eventImg = eventImg;
    }

    public Event(Date dateevent, String lieuevent, int capevent, String nomevent, String description, float ticketprice, String eventImg) {
        this.dateevent = dateevent;
        this.lieuevent = lieuevent;
        //this.nbrepersonnes = nbrepersonnes;
        this.capevent = capevent;
        this.nomevent = nomevent;
        this.description = description;
        this.ticketprice = ticketprice;
        this.eventImg = eventImg;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public Date getDateevent() {
        return dateevent;
    }

    public void setDateevent(Date dateevent) {
        this.dateevent = dateevent;
    }

    public String getLieuevent() {
        return lieuevent;
    }

    public void setLieuevent(String lieuevent) {
        this.lieuevent = lieuevent;
    }

    public int getNbrepersonnes() {
        return nbrepersonnes;
    }

    public void setNbrepersonnes(int nbrepersonnes) {
        this.nbrepersonnes = nbrepersonnes;
    }

    public int getCapevent() {
        return capevent;
    }

    public void setCapevent(int capevent) {
        this.capevent = capevent;
    }

    public String getNomevent() {
        return nomevent;
    }

    public void setNomevent(String nomevent) {
        this.nomevent = nomevent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(float ticketprice) {
        this.ticketprice = ticketprice;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }

    @Override
    public String toString() {
        return "Event{" + "idevent=" + idevent + ", dateevent=" + dateevent + ", lieuevent=" + lieuevent + ", nbrepersonnes=" + nbrepersonnes + ", capevent=" + capevent + ", nomevent=" + nomevent + ", description=" + description + ", ticketprice=" + ticketprice + ", eventImg=" + eventImg + '}';
    }
    
    
    
}
