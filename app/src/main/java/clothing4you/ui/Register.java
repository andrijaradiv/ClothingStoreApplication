package clothing4you.ui;

import clothing4you.backend.UserManager;
import clothing4you.ui.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
//this is a frame that contains the regsitration page 
public class Register extends JDialog {

    private JPanel registerPanel;
    private JTextField tfName;
    private JTextField tfUsername;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPass;
    private JButton btnRegister;
    private JButton btnBack;

    public Register(JFrame parent) {
        super(parent);
        setTitle("Register");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);
        
        //register button that takes all your inforamtion and stores everything in the database as well as creating an account you can use to login 
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tfName.getText();
                String username = tfUsername.getText();
                String email = tfEmail.getText();
                char[] password = pfPassword.getPassword();
                char[] confirmPassword = pfConfirmPass.getPassword();
                //if the password doesnt match a message pops up that your password does not match
                if (Arrays.equals(password, confirmPassword)) {
                    UserManager.register(name, email, username, password.toString());
                    dispose();
                    Login myLogin = new Login(null);
                } else {
                    JOptionPane.showMessageDialog(registerPanel, "Password do not match", "Erorr", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        //back button
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login myLogin = new Login(null);
            }
        });
        setVisible(true);
    }
}



