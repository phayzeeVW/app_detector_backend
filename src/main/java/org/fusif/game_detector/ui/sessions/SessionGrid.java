package org.fusif.game_detector.ui.sessions;

import com.vaadin.flow.component.grid.Grid;
import org.fusif.game_detector.entity.Session;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class SessionGrid extends Grid<Session> {
    public SessionGrid() {
        setSizeFull();
        setColumnReorderingAllowed(true);

        addColumn(Session::getId).setHeader("ID").setAutoWidth(true).setResizable(true).setSortable(true).setKey("id");
        addColumn(s -> s.getApplication().getAlias()).setHeader("Application Name").setAutoWidth(true).setSortable(true).setResizable(true).setKey("application");
        addColumn(s -> s.getApplication().getPath()).setHeader("Application Path").setAutoWidth(true).setSortable(true).setResizable(true).setKey("path");
        addColumn(s -> getLocalDateTimeFromInstant(s.getSessionStart())).setHeader("Start").setAutoWidth(true).setSortable(true).setResizable(true).setKey("start");
        addColumn(s -> getLocalDateTimeFromInstant(s.getSessionStop())).setHeader("Stop").setAutoWidth(true).setSortable(true).setResizable(true).setKey("stop");
    }

    private String getLocalDateTimeFromInstant(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        );
    }

    public Session getSelectedSession() {
        return asSingleSelect().getValue();
    }

    public void refresh(Session session) {
        getDataCommunicator().refresh(session);
    }
}
