package clothing4you;

import com.google.protobuf.NullValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Reviews extends JDialog {
    private JLabel titleLabel;
    private JPanel reviewPanel;
    private JPanel formPanel;
    private JTextField nameField;
    private JComboBox<Integer> ratingComboBox;
    private JTextArea reviewTextArea;
    private JButton submitButton;
    //this is a page were you can leave a review if you would like too 
    public Reviews (JFrame parent) {
        super(parent, "Product Review", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Review Panel
        titleLabel = new JLabel("Product Reviews:");
        reviewPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        reviewPanel.add(titleLabel, gbc);

        // Sample Reviews
        JLabel authorLabel1 = new JLabel("John Doe");
        JLabel ratingLabel1 = new JLabel("Rating: 5/5");
        JLabel reviewLabel1 = new JLabel("<html>This product is amazing. I would definitely recommend it to anyone.</html>");
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 10, 10);
        reviewPanel.add(authorLabel1, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 10, 10);
        reviewPanel.add(ratingLabel1, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 10, 10);
        reviewPanel.add(reviewLabel1, gbc);

        JLabel authorLabel2 = new JLabel("Jane Smith");
        JLabel ratingLabel2 = new JLabel("Rating: 4/5");
        JLabel reviewLabel2 = new JLabel("<html>This product is pretty good, but could be better. I would still recommend it though.</html>");
        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 10, 10);
        reviewPanel.add(authorLabel2, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 10, 10);
        reviewPanel.add(ratingLabel2, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 20, 10);
        reviewPanel.add(reviewLabel2, gbc);

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
        reviewPanel.add(backButton, gbc);


        // Add review panel to the dialog
        add(reviewPanel, gbc);

        // Form Panel
        formPanel = new JPanel(new GridBagLayout());
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.setBorder(BorderFactory.createTitledBorder("Add a Review"));

        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.add(nameLabel, gbc);
        gbc.gridx++;
        gbc.insets = new Insets(10, 0, 10, 10);
        formPanel.add(nameField, gbc);

        // Rating Combo Box
        JLabel ratingLabel = new JLabel("Rating:");
        Integer[] ratings = {1, 2, 3, 4, 5};
        ratingComboBox = new JComboBox<>(ratings);
        gbc.gridx++;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.add(ratingLabel, gbc);
        gbc.gridx++;
        gbc.insets = new Insets(10, 0, 10, 10);
        formPanel.add(ratingComboBox, gbc);

        // Review Text Area
        JLabel reviewLabel = new JLabel("Review:");
        reviewTextArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(reviewTextArea);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.add(reviewLabel, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 10, 10);
        formPanel.add(scrollPane, gbc);

        // Submit Button
        submitButton = new JButton("Submit");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle the submit button click
                String name = nameField.getText();
                int rating = (Integer) ratingComboBox.getSelectedItem();
                String review = reviewTextArea.getText();

                if (name.isEmpty() || review.isEmpty()) {
                    JOptionPane.showMessageDialog(Reviews.this,
                            "Please enter all fields",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // Insert review in the database and update the review panel
                    JOptionPane.showMessageDialog(reviewPanel, "Review submitted!");
                }

                // Reset the form fields
                nameField.setText("");
                ratingComboBox.setSelectedIndex(0);
                reviewTextArea.setText("");
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


