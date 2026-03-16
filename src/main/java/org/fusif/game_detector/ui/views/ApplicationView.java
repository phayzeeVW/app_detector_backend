package org.fusif.game_detector.ui.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.fusif.game_detector.service.ApplicationService;
import org.fusif.game_detector.ui.MainLayout;
import org.fusif.game_detector.ui.application.ApplicationGrid;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "applications", layout = MainLayout.class)
@RouteAlias(value = "apps", layout = MainLayout.class)
@Menu(order = 2, title = "Applications")
public class ApplicationView extends VerticalLayout {
    private final ApplicationGrid applicationsGrid = new ApplicationGrid();

    public static final String VIEW_NAME = "applications";


    @Autowired
    public ApplicationView(ApplicationService applicationService) {
        setSizeFull();
        add(applicationsGrid);

        applicationsGrid.setItems(applicationService.findAll());
    }
}
