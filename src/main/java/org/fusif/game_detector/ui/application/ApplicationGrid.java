package org.fusif.game_detector.ui.application;

import com.vaadin.flow.component.grid.Grid;
import org.fusif.game_detector.entity.Application;

import static org.fusif.game_detector.ui.application.ApplicationGridHeaders.*;

public class ApplicationGrid extends Grid<Application> {
    public ApplicationGrid() {
        setSizeFull();
        setColumnReorderingAllowed(true);

        addColumn(Application::getId).setHeader(ID.getValue()).setWidth("1rem")
                .setResizable(true).setSortable(true).setKey("id");
        addColumn(Application::getAlias).setHeader(ALIAS.getValue()).setWidth("7rem")
                .setResizable(true).setSortable(true).setKey("alias");
        addColumn(Application::getPath).setHeader(PATH.getValue()).setWidth("15rem")
                .setResizable(true).setSortable(true).setKey("path");
        addColumn(Application::getTitle).setHeader(TITLE.getValue()).setWidth("30rem")
                .setResizable(true).setSortable(true).setKey("title");
        addColumn(Application::getSaveSession).setHeader(SAVE_SESSION.getValue()).setAutoWidth(true)
                .setResizable(true).setSortable(true).setKey("saveSession");
    }

    public Application getSelectedItem() {
        return asSingleSelect().getValue();
    }

    public void refresh(Application app) {
        getDataCommunicator().refresh(app);
    }
}
