package ch.bzz.roomlist.model;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 * Hotel with a name and rooms
 */
public class Hotel {
    //private List<Room> roomList;

    @FormParam("number")
    @NotNull
    @Min(1)
    @Max(300000)
    private Integer number;

    @FormParam("hotelName")
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]{2,20}")
    private String hotelName;


    public Hotel(){

    }

    /**
     * get name
     * @return hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * get number
     * @return number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * set number
     * @param number value to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * set name
     * @param hotelName value to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
