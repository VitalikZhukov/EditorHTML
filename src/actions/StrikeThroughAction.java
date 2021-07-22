package com.javarush.task.task32.task3209.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

// отвечает за стиль текста "Зачеркнутый"
public class StrikeThroughAction extends StyledEditorKit.StyledTextAction {
    public StrikeThroughAction() {
        super(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
