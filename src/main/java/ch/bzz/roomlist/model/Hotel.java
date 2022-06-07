package ch.bzz.roomlist.model;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 * Hotel with a name and rooms
 */
public class Hotel {
    //private List<Room> roomList;

    //here
    @FormParam("number")
    @NotNull
    @Min(value = 1)
    @Max(value = 300000)
    private int number;

    //here
    @FormParam("hotelName")
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]{2,20}")
    private String hotelName;

    /**
     * default constructor
     */
    public Hotel(){

    }

    /**
     * gets name of hotel
     * @return hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * sets name of hotel
     *
     * @param hotelName the value to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
