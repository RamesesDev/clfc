/*
 * GroovyScriptEditor.java
 *
 * Created on May 25, 2011, 2:11 PM
 * @author jaycverg
 */

package dev.ui;

import com.rameses.rcp.framework.Binding;
import com.rameses.rcp.ui.UIInput;
import com.rameses.rcp.util.UIControlUtil;
import groovy.ui.ConsoleTextEditor;
import groovy.ui.text.TextEditor;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class GroovyScriptEditor extends JPanel implements UIInput {    
    
    private int index;
    private Binding binding;
    private TextEditor editor;
    
    
    public GroovyScriptEditor() {
        
    }
    
    public void refresh() {
        try {
            Object value = UIControlUtil.getBeanValue(this);
            int caretPos = editor.getCaretPosition();
            editor.setText( value == null ? "" : value+"" );
            editor.setCaretPosition(caretPos);
        }
        catch(Exception e) {;}
    }

    public void load() {
        ConsoleTextEditor cte = new ConsoleTextEditor();
        
        removeAll();
        super.setLayout(new BorderLayout());
        add( cte );
        
        editor = cte.getTextEditor();
    }
    
    public void setBinding(Binding binding) {
        this.binding = binding;
    }

    public Binding getBinding() {
        return binding;
    }

    public void setValue(Object value) {
    }
    
    public Object getValue() {
        return editor != null ? editor.getText() : "";
    }

    public boolean isNullWhenEmpty() { return false; }

    public void setReadonly(boolean readonly) {}
    public boolean isReadonly() { return false; }

    public void setRequestFocus(boolean focus) {
        requestFocus();
    }
    
    public void requestFocus() {
        if( editor != null ) editor.requestFocus();
    }
    
    public boolean requestFocusInWindow() {
        if( editor != null ) return editor.requestFocusInWindow();
        
        return super.requestFocusInWindow();
    }

    public boolean isImmediate() { return false; }    
    public String[] getDepends() { return null; }
    
    public int getIndex()           { return index; }    
    public void setIndex(int index) { this.index = index; }

    public int compareTo(Object o) {
        return UIControlUtil.compare(this, o);
    }
    
}
