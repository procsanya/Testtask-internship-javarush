package com.game.repository;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    @Query("SELECT u FROM Player u WHERE "
            + "(:name is null or u.name LIKE concat('%',:name,'%')) AND (:title is null or u.title LIKE concat('%',:title,'%')) "
            + "AND (:race is null or :race = u.race) AND (:profession is null or :profession = u.profession) "
            + "AND (:start is null or u.birthday >= :start ) AND (:end is null or u.birthday <= :end) "
            + "AND (:minExp is null or u.experience >= :minExp ) AND (:maxExp is null or u.experience <= :maxExp) "
            + "AND (:minLevel is null or u.level >= :minLevel ) AND (:maxLevel is null or u.level <= :maxLevel) "
            + "AND (:banned is null or u.banned = :banned)")
    List<Player> findAllBy(@Param("name") String name, @Param("title") String title,
                         @Param("race") Race race, @Param("profession") Profession profession,
                         @Param("start") Date start, @Param("end") Date end,
                         @Param("minExp") Integer minExp, @Param("maxExp") Integer maxExp,
                         @Param("minLevel") Integer minLevel, @Param("maxLevel") Integer maxLevel,
                         @Param("banned") Boolean banned, Pageable pageable);

    @Query("SELECT COUNT(u) FROM Player u WHERE "
            + "(:name is null or u.name LIKE concat('%',:name,'%')) AND (:title is null or u.title LIKE concat('%',:title,'%')) "
            + "AND (:race is null or :race = u.race) AND (:profession is null or :profession = u.profession) "
            + "AND (:start is null or u.birthday >= :start ) AND (:end is null or u.birthday <= :end) "
            + "AND (:minExp is null or u.experience >= :minExp ) AND (:maxExp is null or u.experience <= :maxExp) "
            + "AND (:minLevel is null or u.level >= :minLevel ) AND (:maxLevel is null or u.level <= :maxLevel) "
            + "AND (:banned is null or u.banned = :banned)")
    long countAllBy(@Param("name") String name, @Param("title") String title,
                         @Param("race") Race race, @Param("profession") Profession profession,
                         @Param("start") Date start, @Param("end") Date end,
                         @Param("minExp") Integer minExp, @Param("maxExp") Integer maxExp,
                         @Param("minLevel") Integer minLevel, @Param("maxLevel") Integer maxLevel,
                         @Param("banned") Boolean banned);
}
