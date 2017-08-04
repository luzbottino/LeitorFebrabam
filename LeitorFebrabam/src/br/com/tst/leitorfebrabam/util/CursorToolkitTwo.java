/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.util;

import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.RootPaneContainer;

/**
 *
 * @author luis
 */
public class CursorToolkitTwo implements Cursors {

    private final static MouseAdapter mouseAdapter
            = new MouseAdapter() {
    };

    private CursorToolkitTwo() {
    }

    /**
     * Sets cursor for specified component to Wait cursor
     */
    public static void startWaitCursor(JComponent component) {
        RootPaneContainer root
                = ((RootPaneContainer) component.getTopLevelAncestor());
        root.getGlassPane().setCursor(WAIT_CURSOR);
        root.getGlassPane().addMouseListener(mouseAdapter);
        root.getGlassPane().setVisible(true);
    }

    /**
     * Sets cursor for specified component to normal cursor
     */
    public static void stopWaitCursor(JComponent component) {
        RootPaneContainer root
                = ((RootPaneContainer) component.getTopLevelAncestor());
        root.getGlassPane().setCursor(DEFAULT_CURSOR);
        root.getGlassPane().removeMouseListener(mouseAdapter);
        root.getGlassPane().setVisible(false);
    }

}
