package com.javarush.task.task32.task3209.actions;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

// отвечает за стиль "Надстрочный знак"
public class SuperscriptAction extends StyledEditorKit.StyledTextAction {
    public SuperscriptAction() {
        super(StyleConstants.Superscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JEditorPane editorPane = getEditor(actionEvent);
        if (editorPane != null) {
            // получаем набор атрибутов панели для настройки стиля
            MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editorPane).getInputAttributes();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
            // устанавливает атрибут надстрочный знак
            StyleConstants.setSuperscript(simpleAttributeSet, !StyleConstants.isSuperscript(mutableAttributeSet));
            // устанавливаем его параметры
            setCharacterAttributes(editorPane, simpleAttributeSet, false);
        }
    }
}
