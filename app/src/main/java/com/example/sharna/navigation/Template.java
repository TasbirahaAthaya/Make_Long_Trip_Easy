package com.example.sharna.navigation;

/**
 * Created by SHARNA on 3/30/2016.
 */
public class Template {

     int Column_CityId;
     String Column_source;
     String Column_destination;
     String Column_busname;
     int Column_busid;
     String Column_schedule;
     String Column_journey_duration;
     String Column_counter_name;
     String Column_counter_adress;
     int Column_counter_id;
     String Column_counter_contact;
     String Column_ticketcost;


    public Template(){}

    public Template( int cityid,
            String source,
            String destination,
            String busname,
            int busid,
            String schedule,
            String duration,
            String countername,
            String adress,
            int counter_id,
            String counter_contact,
            String ticketcost){


         this.Column_CityId = cityid;
        this.Column_source = source;
        this.Column_destination = destination;
        this.Column_busname = busname;
        this.Column_busid = busid;
        this.Column_schedule = schedule;
        this.Column_journey_duration = duration;
        this.Column_counter_name = countername;
        this.Column_counter_adress =adress;
        this.Column_counter_id = counter_id;
        this.Column_counter_contact = counter_contact;
        this.Column_ticketcost = ticketcost;



    }
    public Template(
                     String source,
                     String destination,
                     String busname,
                     int busid,
                     String schedule,
                     String duration,
                     String countername,
                     String adress,
                     int counter_id,
                     String counter_contact,
                     String ticketcost){



        this.Column_source = source;
        this.Column_destination = destination;
        this.Column_busname = busname;
        this.Column_busid = busid;
        this.Column_schedule = schedule;
        this.Column_journey_duration = duration;
        this.Column_counter_name = countername;
        this.Column_counter_adress =adress;
        this.Column_counter_id = counter_id;
        this.Column_counter_contact = counter_contact;
        this.Column_ticketcost = ticketcost;



    }

    public void setColumn_busid(int column_busid) {
        Column_busid = column_busid;
    }

    public void setColumn_busname(String column_busname) {
        Column_busname = column_busname;
    }

    public void setColumn_CityId(int column_CityId) {
        Column_CityId = column_CityId;
    }

    public void setColumn_counter_adress(String column_counter_adress) {
        Column_counter_adress = column_counter_adress;
    }

    public void setColumn_counter_contact(String column_counter_contact) {
        Column_counter_contact = column_counter_contact;
    }

    public void setColumn_counter_id(int column_counter_id) {
        Column_counter_id = column_counter_id;
    }

    public void setColumn_counter_name(String column_counter_name) {
        Column_counter_name = column_counter_name;
    }

    public void setColumn_destination(String column_destination) {
        Column_destination = column_destination;
    }

    public void setColumn_journey_duration(String column_journey_duration) {
        Column_journey_duration = column_journey_duration;
    }

    public void setColumn_schedule(String column_schedule) {
        Column_schedule = column_schedule;
    }

    public void setColumn_source(String column_source) {
        Column_source = column_source;
    }

    public void setColumn_ticketcost(String column_ticketcost) {
        Column_ticketcost = column_ticketcost;
    }

    public int getColumn_busid() {
        return Column_busid;
    }

    public int getColumn_CityId() {
        return Column_CityId;
    }

    public int getColumn_counter_id() {
        return Column_counter_id;
    }

    public String getColumn_busname() {
        return Column_busname;
    }

    public String getColumn_counter_adress() {
        return Column_counter_adress;
    }

    public String getColumn_counter_contact() {
        return Column_counter_contact;
    }

    public String getColumn_counter_name() {
        return Column_counter_name;
    }

    public String getColumn_destination() {
        return Column_destination;
    }

    public String getColumn_journey_duration() {
        return Column_journey_duration;
    }

    public String getColumn_schedule() {
        return Column_schedule;
    }

    public String getColumn_source() {
        return Column_source;
    }

    public String getColumn_ticketcost() {
        return Column_ticketcost;
    }

}
