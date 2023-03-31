package clothing4you;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Job extends JDialog{
    private JPanel jobPanel;
    private JTable jobTable;

    public Job(JFrame parent){
        super(parent);
        setTitle("");
        jobPanel = new JPanel(new BorderLayout());
        setContentPane(jobPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);

        JPanel userInputPanel = new JPanel(new GridBagLayout());
        userInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name:");
        userInputPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField nameField = new JTextField();
        userInputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        JLabel emailLabel = new JLabel("Email:");
        userInputPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField emailField = new JTextField();
        userInputPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        JLabel phoneLabel = new JLabel("Phone:");
        userInputPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField phoneField = new JTextField();
        userInputPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        JLabel resumeLabel = new JLabel("Resume:");
        userInputPanel.add(resumeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JFileChooser resumeChooser = new JFileChooser();
        userInputPanel.add(resumeChooser, gbc);

        jobPanel.add(userInputPanel, BorderLayout.WEST);


        JLabel titleLabel = new JLabel("Job Openings", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        jobPanel.add(titleLabel, BorderLayout.NORTH);


        String[] columnNames = {"Job Title", "Location", "Salary"};
        Object[][] rowData = {
                {"Sales Associate", "Toronto", "60,000"},
                {"Manger", "Los Angeles", "80,000"},
                {"App Developer", "New York", "150,000"},
                {"Software Developer", "Boston", "150,000"},
                {"Janitor", "Montreal", "40,000"},
                {"Marketing Manger", "Toronto", "100,000"}
        };
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);


        JTable jobTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(jobTable);
        jobPanel.add(scrollPane, BorderLayout.EAST);
        jobTable.setDefaultEditor(Object.class, null);


        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login myLogin = new Login(null);
            }
        });
        buttonPanel.add(backButton);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Apply to the selected job
                int row = jobTable.getSelectedRow();
                if (row >= 0) {
                    Object[] selectedRowData = tableModel.getDataVector().elementAt(row).toArray();
                    String jobTitle = (String) selectedRowData[0];
                    String jobLocation = (String) selectedRowData[1];
                    String jobSalary = (String) selectedRowData[2];
                    JOptionPane.showMessageDialog(jobPanel, "Applying to job: " + jobTitle + " in " + jobLocation + " with salary " + jobSalary + "$");
                    // TODO: Add apply to job logic here
                } else {
                    JOptionPane.showMessageDialog(Job.this, "Please select a job to apply to.");
                }
            }
        });
        buttonPanel.add(applyButton);

        jobPanel.add(buttonPanel, BorderLayout.SOUTH);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int dialogWidth = getWidth();
        int dialogHeight = getHeight();
        setLocation((screenSize.width - dialogWidth) / 2, (screenSize.height - dialogHeight) / 2);
        setVisible(true);
    }

}
