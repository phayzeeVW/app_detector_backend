package org.fusif.game_detector.ui;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@PWA(name = "Game Detector", shortName = "Game Detector")
@Theme(variant = Lumo.DARK)
public class AppShell implements AppShellConfigurator {
}
