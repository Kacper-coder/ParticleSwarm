package PSO_GUI;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;

public class ShowHtmlMessageDialog {
    public void showHtmlMessageDialog(String title, String message) {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(message);
        editorPane.setEditable(false);
        editorPane.setOpaque(false);

        editorPane.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JOptionPane.showMessageDialog(null, editorPane, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
