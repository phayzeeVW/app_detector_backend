package org.fusif.game_detector.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.fusif.game_detector.entity.converter.SessionDateConverter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "session_start", nullable = false)
    @Convert(converter = SessionDateConverter.class)
    private Instant sessionStart;

    @Column(name = "session_stop")
    @Convert(converter = SessionDateConverter.class)
    private Instant sessionStop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

}