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
import org.fusif.game_detector.entity.Application;
import org.fusif.game_detector.service.ApplicationService;
import org.fusif.game_detector.ui.MainLayout;
import org.fusif.game_detector.ui.application.ApplicationGrid;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "applications", layout = MainLayout.class)
@RouteAlias(value = "apps", layout = MainLayout.class)
@Menu(order = 2, title = "Applications")
public class ApplicationView extends VerticalLayout {
    ApplicationGrid applicationsGrid = new ApplicationGrid();

    public static final String VIEW_NAME = "applications";

    @Autowired
    public ApplicationView(ApplicationService applicationService) {
        setSizeFull();

        MultiSelectComboBox<String> columnFilter = createColumnFilter();
        TextField searchField = createSearchField();

        add(searchField);
        add(columnFilter);
        add(applicationsGrid);

        applicationsGrid.setItems(applicationService.findAll());
        applicationsGrid.getListDataView().addFilter(app -> {
            String searchTerm = searchField.getValue().trim();

            if (searchTerm.isEmpty()) {
                return true;
            }

            boolean matchesAlias = matchesTerm(app.getAlias(), searchTerm);
            boolean matchesName = matchesTerm(app.getTitle(), searchTerm);
            boolean matchesPath = matchesTerm(app.getPath(), searchTerm);

            return matchesAlias || matchesName || matchesPath;
        });
    }

    public boolean matchesTerm(String value, String term) {
        if (value != null) {
            return value.toLowerCase().replace("-", "").replace(".", "")
                    .contains(term.toLowerCase().replace("-", "").replace(".", ""));
        }

        return false;
    }

    public TextField createSearchField() {
        TextField textField = new TextField();
        textField.setPlaceholder("Search");
        textField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.addValueChangeListener(e -> this.applicationsGrid.getListDataView().refreshAll());

        return textField;
    }

    public MultiSelectComboBox<String> createColumnFilter() {
        MultiSelectComboBox<String> comboFilter = new MultiSelectComboBox<>();
        List<Grid.Column<Application>> columns = new ArrayList<>(this.applicationsGrid.getColumns());

        comboFilter.setItems(columns.stream().map(Grid.Column::getKey).toList());
        comboFilter.addValueChangeListener(e -> columns.forEach(
                column -> {
                    this.applicationsGrid.getColumnByKey(column.getKey()).setVisible(e.getValue().contains(column.getKey()));
                }
        ));

        return comboFilter;
    }
}
