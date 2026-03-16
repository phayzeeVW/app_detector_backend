package org.fusif.game_detector.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;

import java.util.List;

@PageTitle("Game Detector")
public class MainLayout extends AppLayout {
    public MainLayout() {
        final DrawerToggle drawerToggle = new DrawerToggle();

        MenuBar menuBar = new MenuBar();

        List<MenuEntry> menuEntries = MenuConfiguration.getMenuEntries();
        menuEntries.forEach(entry -> menuBar.addItem(new Anchor(entry.path(), entry.title())));

        addToNavbar(drawerToggle);
        addToNavbar(menuBar);
    }
}
