package clothing4you;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class History extends JDialog {
    private JPanel historyPanel;

    public History(JFrame parent, ArrayList<Order> orderList){
        super(parent);
        setTitle("");
        historyPanel = new JPanel(new BorderLayout());
        setContentPane(historyPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("Order History");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        JPanel buttonPanel = new JPanel();

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //Go back to the catalog page
            }
        });
        buttonPanel.add(backBtn);

        JButton returnBtn = new JButton("Return");
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // should open the return page for the chosen order
            }
        });
        buttonPanel.add(returnBtn);

        // Set up the table model
        String[] columnNames = {"Date", "Order Number", "Amount"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Order order : orderList) {
            String[] rowData = {order.getDate(), order.getOrderNumber(), order.getAmount()};
            model.addRow(rowData);
        }

        // Create the table and add it to a scroll pane
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setDefaultEditor(Object.class, null);

        // Add the components to the main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        historyPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    //Just to test the code with order array list
    public static void main(String[] args) {
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(new Order("2022-01-01", "1001", "$25.00"));
        orderList.add(new Order("2022-01-02", "1002", "$32.50"));
        orderList.add(new Order("2022-01-03", "1003", "$17.75"));
        orderList.add(new Order("2022-01-04", "1004", "$50.00"));
        orderList.add(new Order("2022-01-05", "1005", "$21.25"));

        History myHistory = new History(null, orderList);
    }
}
