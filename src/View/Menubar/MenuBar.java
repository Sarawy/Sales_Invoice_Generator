package View.Menubar;

import javax.swing.*;

public class MenuBar extends JMenuBar   {
    JMenuBar menuBar;
    JMenu fileMenu;
    public JMenuItem saveFileMenu;
    public JMenuItem loadFileMenu;
    public MenuBar(){
        fileMenu = new JMenu("File");
        saveFileMenu = new JMenuItem("Save File",'S');
        loadFileMenu = new JMenuItem("Load File",'L');
        fileMenu.add(saveFileMenu);
        fileMenu.add(loadFileMenu);
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
    }
    public JMenuBar getMenuBar(){
        return menuBar;
    }
    public JMenuItem getSaveFileMenu(){
        return saveFileMenu;
    }
    public void getLoadFileMenu(String t){
         loadFileMenu.setActionCommand(t);
    }


}
