package org.fusif.game_detector.ui.views;

import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.fusif.game_detector.entity.Session;
import org.fusif.game_detector.service.SessionService;
import org.fusif.game_detector.ui.MainLayout;
import org.fusif.game_detector.ui.session.SessionGrid;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "sessions", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Menu(order = 1, title = "Sessions")
public class SessionView extends VerticalLayout {
    private final SessionGrid sessionGrid = new SessionGrid();

    public static final String VIEW_NAME = "Sessions";

    @Autowired
    public SessionView(SessionService sessionService) {
        setSizeFull();

        MultiSelectComboBox<String> columnFilter = createColumnFilter();
        TextField searchField = createSearchField();

        add(searchField);
        add(columnFilter);
        add(sessionGrid);


        sessionGrid.setItems(sessionService.getAllSaveSessionTrue());
        sessionGrid.getListDataView().addFilter(session -> {
            String searchTerm = searchField.getValue().trim();

            if (searchTerm.isEmpty()) {
                return true;
            }

            boolean matchesApplicationName = matchesTerm(session.getApplication().getAlias(), searchTerm);
            boolean matchesPathName = matchesTerm(session.getApplication().getPath(), searchTerm);

            return matchesApplicationName || matchesPathName;
        });
    }

    private boolean matchesTerm(String value, String term) {
        if (value != null && term != null) {
            return value.toLowerCase().replace("-", "").replace(".", "")
                    .contains(term.toLowerCase().replace("-", "").replace(".", ""));
        }

        return false;
    }

    private TextField createSearchField() {
        TextField textField = new TextField();
        textField.setPlaceholder("Search");
        textField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.addValueChangeListener(e -> sessionGrid.getListDataView().refreshAll());

        return textField;
    }

    private MultiSelectComboBox<String> createColumnFilter() {
        MultiSelectComboBox<String> comboFilter = new MultiSelectComboBox<>();
        List<Grid.Column<Session>> columns = new ArrayList<>(this.sessionGrid.getColumns());

        comboFilter.setItems(columns.stream().map(Grid.Column::getHeaderText).toList());
//        comboFilter.addValueChangeListener(e -> e.getValue().forEach(columnToBeShown -> columns.forEach(
//                column -> {
//                    if (column.getHeaderText().equals(columnToBeShown)) {
//                        this.sessionGrid.getColumnByKey(column.getKey()).setVisible(true);
//                    } else {
//                        this.sessionGrid.getColumnByKey(column.getKey()).setVisible(false);
//                    }
//                }))
//        );

        return comboFilter;
    }
}
