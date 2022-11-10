package de.fhws.business.rooms.boundary;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.fhws.business.rooms.control.BuildingService;
import de.fhws.business.rooms.control.RoomService;
import de.fhws.business.rooms.entity.CreateRoomDTO;
import de.fhws.business.rooms.entity.RoomDTO;
import de.fhws.business.rooms.entity.UpdateRoomDTO;

@Produces(MediaType.APPLICATION_JSON)
@Path("rooms")
public class RoomResource {

	@Inject
	RoomService roomService;
	
	@Inject
	BuildingService buildingService;

	@GET
	@Path("ping")
	public String ping() {
		return "I'm here at FHWS 👍😊 - new block, new luck " + new Date();
	}

	@POST
	public void addRoom(@Valid CreateRoomDTO room) {
		roomService.addRoom(room);
	}
	
	@PUT
	@Path("{id}")
	public void updateRoom(@PathParam("id") Long id, @Valid UpdateRoomDTO room) {
		roomService.updateRoom(id, room);
	}

	@GET
	public List<RoomDTO> getRooms(@QueryParam("limit") @DefaultValue("100") Long limit,
			@QueryParam("offset") @DefaultValue("0") Long offset) {
		return roomService.getRooms(limit, offset);
	}

	@GET
	@Path("{id}")
	public RoomDTO getRoom(@PathParam("id") Long id) {
		System.out.println("id: " + id);
		return roomService.getRoom(id);
	}
}
