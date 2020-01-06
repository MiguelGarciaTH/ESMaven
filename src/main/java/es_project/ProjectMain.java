package es_project;


import es_project.gui.ProjectGUI;
import es_project.model.Model;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProjectMain {

    public static void main(String[] args) {

        Model model = new Model("es_project", "es_maven", "es_maven");
        ProjectGUI gui = new ProjectGUI(model);

        JFrame frame = new JFrame("ProjectGUI");
        frame.setContentPane(gui.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(950,650);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                model.disconnect();
                System.exit(0);
            }
        });
    }
}
