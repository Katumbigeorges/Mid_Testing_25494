package view;

import model.Room;
import model.Shelf;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Controller.RoomController;
import Controller.ShelfController; // Import the ShelfController

public class CreateAndSaveRoom {

	public void createAndSaveRooms() {
		// Create a Room instance
		Room room = new Room();
		room.setId(UUID.randomUUID()); // Generate a unique room ID
		room.setRoom_code("Room104"); // Set a room code

		// Fetch existing shelves from the database
		ShelfController shelfController = new ShelfController();
		List<Shelf> shelves = new ArrayList<>();

		// Example: Fetch a specific shelf by ID
		UUID shelfId = UUID.fromString("da0e8c6c-41af-48a3-a685-863671a24fcd");
		Shelf shelf = shelfController.getShelfById(shelfId);

		if (shelf != null) {
			// Set the room reference for the shelf
			shelf.setRoom(room);
			shelves.add(shelf);
		} else {
			System.out.println("Shelf not found for ID: " + shelfId);
		}

//	    // Optionally, fetch more shelves as needed
//	    UUID anotherShelfId = UUID.fromString("078a4d69-dde2-44f1-84e8-e27397b03cfb");
//	    Shelf anotherShelf = shelfController.getShelfById(anotherShelfId);
//	    if (anotherShelf != null) {
//	        anotherShelf.setRoom(room); // Set the room reference for the shelf
//	        shelves.add(anotherShelf);
//	    }
//
//	    room.setShelves(shelves); // Associate fetched shelves with the room

		// Save the room
		RoomController roomController = new RoomController();
		String result = roomController.saveRoom(room);
		System.out.println(result);
	}

	public void saveRoom() {
		Room room = new Room();
		room.setId(UUID.randomUUID()); // Generate a unique room ID
		room.setRoom_code("Room102"); // Set a room code

		// Save the room
		RoomController roomController = new RoomController();
		String result = roomController.saveRoom(room);
		System.out.println(result);
	}
}
