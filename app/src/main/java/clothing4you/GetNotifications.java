package clothing4you;

import com.google.protobuf.NullValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class GetNotifications extends JDialog {
    private JLabel titleLabel;
    private JPanel notifyPanel;
    private JPanel formPanel;
    private JTextField emailField;
    private JButton submitButton;

    public GetNotifications (JFrame parent) {
        super(parent, "Get email notifications", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Get notified Panel
        titleLabel = new JLabel("Get notified:");
        notifyPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        notifyPanel.add(titleLabel, gbc);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            try {
                Catalog previousCatalog = new Catalog(null);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        notifyPanel.add(backButton, gbc);


        // Add notify panel to the dialog box
        add(notifyPanel, gbc);

        // Form Panel
        formPanel = new JPanel(new GridBagLayout());
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.setBorder(BorderFactory.createTitledBorder("Add an email to get notified"));

        // Email Field
        JLabel emailLabel = new JLabel("Email address:");
        emailField = new JTextField(20);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.add(emailLabel, gbc);
        gbc.gridx++;
        gbc.insets = new Insets(10, 0, 10, 10);
        formPanel.add(emailField, gbc);

        // Send notification Button
        JButton sendButton = new JButton("Send Notifications");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.add(sendButton, gbc);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle the send button click
                String emailAdd = emailField.getText();

                if (emailAdd.isEmpty()) {
                    JOptionPane.showMessageDialog(new Reviews(null),
                            "Please enter a valid email address",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // Insert email address in the database
                    JOptionPane.showMessageDialog(notifyPanel, "You will be notified soon!");
                }

                // Reset the form fields
                emailField.setText("");
            }
        });

        // Add form panel to the dialog
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(formPanel, gbc);

        // Set dialog size and make it visible
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

}


