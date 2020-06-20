package com.game.roullet.repository;

import com.game.roullet.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {


}
