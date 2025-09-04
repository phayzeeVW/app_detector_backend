package org.fusif.game_detector.repository;

import org.fusif.game_detector.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISessionRepository extends JpaRepository<Session, Long> {
    @Query("""
           select s, a
           from Session s, Application a
           where a.id = s.application.id
           and a.saveSession = true
           order by s.sessionStart desc
           """)

    List<Session> findAllBySaveSessionTrue();
}
