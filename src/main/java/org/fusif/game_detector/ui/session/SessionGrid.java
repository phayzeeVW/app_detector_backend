package org.fusif.game_detector.ui.session;

import com.vaadin.flow.component.grid.Grid;
import org.fusif.game_detector.entity.Session;
import org.fusif.game_detector.utils.Utils;

import java.util.Comparator;

import static org.fusif.game_detector.ui.session.SessionGridHeaders.*;

public class SessionGrid extends Grid<Session> {
    public SessionGrid() {
        setSizeFull();
        setColumnReorderingAllowed(true);

        addColumn(Session::getId).setHeader(ID.getValue())
                .setAutoWidth(true).setResizable(true).setSortable(true).setKey("id");
        addColumn(s -> s.getApplication().getAlias()).setHeader(APPLICATION_NAME.getValue())
                .setAutoWidth(true).setSortable(true).setResizable(true).setKey("application");
        addColumn(s -> s.getApplication().getPath()).setHeader(APPLICATION_PATH.getValue())
                .setWidth("20rem").setSortable(true).setResizable(true).setKey("path");
        addColumn(Session::getSessionStart).setHeader(START_TIME.getValue())
                .setAutoWidth(true).setSortable(true).setResizable(true).setKey("start");
        addColumn(Session::getSessionStop).setHeader(END_TIME.getValue())
                .setAutoWidth(true).setSortable(true).setResizable(true).setKey("stop");
        addColumn(s -> Utils.prettyPrintTimeBetweenInstants(s.getSessionStart(), s.getSessionStop())).setHeader(DURATION.getValue())
                .setAutoWidth(true).setSortable(true).setResizable(true).setKey("duration")
                .setComparator(Comparator.comparing(o -> Utils.getDurationBetweenInstants(o.getSessionStart(), o.getSessionStop())));
    }

    public Session getSelectedSession() {
        return asSingleSelect().getValue();
    }

    public void refresh(Session session) {
        getDataCommunicator().refresh(session);
    }
}
