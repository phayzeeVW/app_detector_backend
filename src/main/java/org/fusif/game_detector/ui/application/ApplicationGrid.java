package org.fusif.game_detector.ui.application;

import com.vaadin.flow.component.grid.Grid;
import org.fusif.game_detector.entity.Application;

public class ApplicationGrid extends Grid<Application> {
    public ApplicationGrid() {
        setSizeFull();
        setColumnReorderingAllowed(true);

        addColumn(Application::getId).setHeader("ID").setWidth("1rem").setResizable(true).setSortable(true).setKey("id");
        addColumn(Application::getAlias).setHeader("Alias").setWidth("10rem").setResizable(true).setSortable(true).setKey("alias");
        addColumn(Application::getPath).setHeader("Path").setWidth("15rem").setResizable(true).setSortable(true).setKey("path");
        addColumn(Application::getTitle).setHeader("Title").setWidth("30rem").setResizable(true).setSortable(true).setKey("title");
        addColumn(Application::getSaveSession).setHeader("Save Session").setAutoWidth(true).setResizable(true).setSortable(true).setKey("saveSession");
    }

    public Application getSelectedItem() {
        return asSingleSelect().getValue();
    }

    public void refresh(Application app) {
        getDataCommunicator().refresh(app);
    }
}
