package com.javarush.task.task32.task3209.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

// отвечает за стиль "Надстрочный знак"
public class SuperscriptAction extends StyledEditorKit.StyledTextAction {
    public SuperscriptAction() {
        super(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}