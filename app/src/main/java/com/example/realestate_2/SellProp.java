package com.example.realestate_2;

import android.net.Uri;

public class SellProp {

    private String address,rent_sale,rating_comment,elevator,balcony,area,price,rating,floors,rooms;;
    private String img1_uri,img2_uri;


    public SellProp() {
    }

    public SellProp(String address,String rent_sale,String rating_comment, String elevator,String balcony, String price,String area, String rating, String floors, String rooms,String img1_uri,String img2_uri){

            this.address= address;
            this.rent_sale=rent_sale;
            this.rating_comment=rating_comment;
            this.elevator=elevator;
            this.balcony=balcony;
            this.price=price;
            this.area=area;
            this.rating=rating;
            this.floors=floors;
            this.rooms = rooms;
            this.img1_uri = img1_uri;
            this.img2_uri= img2_uri;

    }

    public String getImg1_uri() {
        return img1_uri;
    }

    public void setImg1_uri(String img1_uri) {
        this.img1_uri = img1_uri;
    }

    public String getImg2_uri() {
        return img2_uri;
    }

    public void setImg2_uri(String img2_uri) {
        this.img2_uri = img2_uri;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRent_sale() {
        return rent_sale;
    }

    public void setRent_sale(String rent_sale) {
        this.rent_sale = rent_sale;
    }

    public String getRating_comment() {
        return rating_comment;
    }

    public void setRating_comment(String rating_comment) {
        this.rating_comment = rating_comment;
    }

    public String getElevator() {
        return elevator;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }
}
