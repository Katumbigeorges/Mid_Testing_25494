package Controller;

import Service.RoomDao;
import model.Room;

import java.util.List;
import java.util.UUID;

public class RoomController {

	private final RoomDao roomDao = new RoomDao();

	public String saveRoom(Room room) {
		return roomDao.saveRoom(room);
	}

	public List<Room> getAllRooms() {
		return roomDao.getAllRooms();
	}

	public Room getRoomById(UUID id) {
		return roomDao.getRoomById(id);
	}

	public String deleteRoom(UUID id) {
		return roomDao.deleteRoom(id);
	}
}
