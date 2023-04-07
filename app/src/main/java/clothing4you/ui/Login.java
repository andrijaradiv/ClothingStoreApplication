package clothing4you.ui;


import clothing4you.backend.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Login extends JDialog {

    private JPanel loginPanel;
    private JTextField tfUsername;
    private JButton btnLogin;
    private JButton btnRegister;
    private JPasswordField pfPassword;
    private JButton btnGuest;
    private JLabel iconLabel;
    private JButton btnJob;

    ImageIcon icon = new ImageIcon("img/Icon.png");

    //creates the frame for the login page
    public Login(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);

        iconLabel.setText("");
        iconLabel.setIcon(icon);

        //login button and its actions
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                char[] password = pfPassword.getPassword();
                // dispose();
                try {
                    UserManager.login(username, password.toString());
                    dispose();
                    Catalog myCatalog = new Catalog(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        
        //register button and its actions
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Register myRegister = new Register(null);
            }
        });
        
        //gives you the option to login as a guest if you dont want to login
        btnGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    Catalog myCatalog = new Catalog(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        
        //opens the job page where you can apply to jobs 
        btnJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Job myJob = new Job(null);
            }
        });

        pack();
        setVisible(true);
    }
}
