package ch.bzz.roomlist.model;

import ch.bzz.roomlist.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;

/**
 * a room in a hotel
 */
public class Room {
    @JsonIgnore
    private Hotel hotel;

    @FormParam("roomNumber")
    @NotNull
    @Min(1)
    @Max(10000)
    private Integer roomNumber;

    @FormParam("size")
    @NotEmpty
    @Pattern(regexp = "[\\D]{3,10}")
    private String size;

    @FormParam("priceNight")
    @DecimalMin(value="10.00")
    @DecimalMax(value="99999.99")
    private BigDecimal priceNight;

    @FormParam("places")
    @Max(value=20)
    private Integer places;


    public String getHotelName() {
        return getHotel().getHotelName();
    }


    /**
     * set name of hotel
     * @param hotelName value to set
     */
    public void setHotelName(String hotelName) {
        setHotel( new Hotel());
        Hotel hotel = DataHandler.readHotelByName(hotelName);
        getHotel().setHotelName(hotelName);
        getHotel().setHotelName(hotel.getHotelName());

    }

    /**
     * get hotel
     * @return hotel
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * set hotel of room
     * @param hotel value to set
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * get number
     * @return roomNumber
     */
    public Integer getRoomNumber() {
        return roomNumber;
    }

    /**
     * set number
     * @param roomNumber value to set
     */
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * get size
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * set size
     * @param size value to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * get price per night
     * @return priceNight
     */
    public BigDecimal getPriceNight() {
        return priceNight;
    }

    /**
     * set price per night
     * @param priceNight value to set
     */
    public void setPriceNight(BigDecimal priceNight) {
        this.priceNight = priceNight;
    }

    /**
     * get places
     * @return places
     */
    public Integer getPlaces() {
        return places;
    }

    /**
     * set places
     * @param places value to set
     */
    public void setPlaces(Integer places) {
        this.places = places;
    }
}