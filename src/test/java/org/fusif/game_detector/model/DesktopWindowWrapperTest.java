package org.fusif.game_detector.model;

import com.sun.jna.platform.DesktopWindow;
import com.sun.jna.platform.win32.WinDef;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DesktopWindowWrapperTest {

    @Test
    void testEquals() {
        DesktopWindowWrapper desktopWindowWrapper1 = new DesktopWindowWrapper(
                new DesktopWindow(new WinDef.HWND(), "title1", "filePath", new Rectangle())
        );

        DesktopWindowWrapper desktopWindowWrapper2 = new DesktopWindowWrapper(
                new DesktopWindow(new WinDef.HWND(), "title2", "filePath", new Rectangle())
        );

        assertEquals(desktopWindowWrapper1, desktopWindowWrapper2);
    }
}