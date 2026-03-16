package org.fusif.game_detector.ui.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.Route;
import org.fusif.game_detector.ui.MainLayout;

@Route(value = "logs", layout = MainLayout.class)
@Menu(order = 3, title = "Logs")
public class LogView extends VerticalLayout {

}
