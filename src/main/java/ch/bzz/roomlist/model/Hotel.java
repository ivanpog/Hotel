package ch.bzz.roomlist.model;

/**
 * Hotel with a name and rooms
 */
public class Hotel {
    //private List<Room> roomList;
    private int number;
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
