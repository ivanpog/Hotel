package ch.bzz.roomlist.model;

/**
 * Hotel with a name and rooms
 */
public class Hotel {
    //private List<Room> roomList;
    private String hotelName;

    /**
     * gets name of hotel
     * @return hotelName
     */
    public String getHotelName() {
        return hotelName;
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
