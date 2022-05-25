package ch.bzz.roomlist.data;

import ch.bzz.roomlist.model.Room;
import ch.bzz.roomlist.model.Hotel;
import ch.bzz.roomlist.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static List<Room> roomList;
    private static List<Hotel> hotelList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {

    }



    /**
     * reads all rooms
     * @return list of rooms
     */
    public static List<Room> readAllRooms() {
        return getRoomList();
    }

    /**
     * reads a room by its number
     * @param roomNumber
     * @return room
     */
    public static Room readRoomByNumber(int roomNumber) {
        Room room = null;
        for (Room entry : getRoomList()) {
            if (entry.getRoomNumber()==roomNumber) {
                room = entry;
            }
        }
        return room;
    }

    /**
     * reads all Hotels
     * @return list of hotels
     */
    public static List<Hotel> readAllHotels() {

        return getHotelList();
    }

    /**
     * reads a hotel by its name
     * @param hotelName
     * @return hotel
     */
    public static Hotel readHotelByName(String hotelName) {
        Hotel hotel = null;
        for (Hotel entry : getHotelList()) {
            if (entry.getHotelName().equals(hotelName)) {
                hotel = entry;
            }
        }
        return hotel;
    }

    /**
     * reads rooms from the JSON-file
     */
    private static void readRoomJSON() {
        try {
            String path = Config.getProperty("roomJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Room[] rooms = objectMapper.readValue(jsonData, Room[].class);
            for (Room room : rooms) {
                getRoomList().add(room);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads hotels from the JSON-file
     */
    private static void readHotelJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("hotelJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Hotel[] hotels = objectMapper.readValue(jsonData, Hotel[].class);
            for (Hotel hotel : hotels) {
                getHotelList().add(hotel);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets roomList
     *
     * @return value of roomList
     */
    private static List<Room> getRoomList() {
        if (roomList == null) {
            setRoomList(new ArrayList<>());
            readRoomJSON();
        }
        return roomList;
    }

    /**
     * sets roomList
     *
     * @param roomList the value to set
     */
    private static void setRoomList(List<Room> roomList) {
        DataHandler.roomList = roomList;
    }

    /**
     * gets hotelList
     *
     * @return value of hotelList
     */
    private static List<Hotel> getHotelList() {
        if (hotelList == null) {
            setHotelList(new ArrayList<>());
            readHotelJSON();
        }
        return hotelList;
    }

    /**
     * sets hotelList
     *
     * @param hotelList the value to set
     */
    private static void setHotelList(List<Hotel> hotelList) {
        DataHandler.hotelList = hotelList;
    }


}