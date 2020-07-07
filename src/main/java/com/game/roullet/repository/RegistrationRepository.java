package com.game.roullet.repository;

import com.game.roullet.entity.Registration;
import com.game.roullet.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {


    Optional<Registration> findByPlayerId(Integer playerId);

    Optional<Registration> findByPlayerIdAndRoomId(Integer playerId, Integer roomId);

    @Modifying
    @Query(value = "delete from Registration registration where registration.room=:room")
    void deleteAllByRoom(@Param("room") Room room);

    @Modifying
    @Query(value = "delete from Registration registration where registration.id=:id")
    void deleteById(@Param("id") Integer id);
}
