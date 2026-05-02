package org.fusif.game_detector.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jspecify.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rawg_game_id", referencedColumnName = "id")
    @Nullable
    private RawgGame rawgGame;

    @Column(name = "path", nullable = false, length = 512, unique = true)
    private String path;

    @Column(name = "title", nullable = false, length = 512)
    private String title;

    @Column(name = "alias", length = 64)
    @Nullable
    private String alias;

    @ColumnDefault("1")
    @Column(name = "save_session")
    private Boolean saveSession;

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private Set<Session> sessions = new LinkedHashSet<>();

}