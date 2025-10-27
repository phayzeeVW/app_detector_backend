package org.fusif.game_detector.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.router.PageTitle;

@PageTitle("Game Detector")
public class MainLayout extends AppLayout {
    public MainLayout() {
        final DrawerToggle drawerToggle = new DrawerToggle();
        addToNavbar(drawerToggle);
    }
}
