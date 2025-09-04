package org.fusif.game_detector.ui.sessions;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.fusif.game_detector.service.SessionService;
import org.fusif.game_detector.ui.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "sessions", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class SessionView extends VerticalLayout {
    private final SessionService sessionService;
    private final SessionGrid sessionGrid = new SessionGrid();
    private final TextField filterText = createSearchField();

    public static final String VIEW_NAME = "Sessions";

    @Autowired
    public SessionView(SessionService sessionService) {
        this.sessionService = sessionService;

        setSizeFull();
        add(filterText);
        add(sessionGrid);

        sessionGrid.setItems(sessionService.getAllSaveSessionTrue());
        sessionGrid.getListDataView().addFilter(session -> {
            String searchTerm = filterText.getValue().trim();

            if (searchTerm.isEmpty()) {
                return true;
            }

            boolean matchesApplicationName = matchesTerm( session.getApplication().getAlias(), searchTerm);
            boolean matchesPathName = matchesTerm(session.getApplication().getPath(), searchTerm);

            return matchesApplicationName || matchesPathName;
        });
    }

    private boolean matchesTerm(String value, String term) {
        if (value != null && term != null) {
            return value.toLowerCase().replace("-", "").replace(".", "").contains(term.toLowerCase());
        }

        return false;
    }

    private TextField createSearchField() {
        TextField searchField = new TextField();
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(e -> sessionGrid.getListDataView().refreshAll());

        return searchField;
    }
}
