package clothing4you;

import com.sun.jdi.JDIPermission;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class History extends JDialog {
    private JPanel historyPanel;
    private JDialog previousDialog;

    public History(JFrame parent, JDialog previousDialog, ArrayList<Order> orderList){
        super(parent);
        setTitle("");
        historyPanel = new JPanel(new BorderLayout());
        setContentPane(historyPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);
        this.previousDialog = previousDialog;

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
                previousDialog.setVisible(true);
                //Go back to the catalog page
            }
        });
        buttonPanel.add(backBtn);

        /*
        JButton returnBtn = new JButton("Return");
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // should open the return page for the chosen order
            }
        });
        buttonPanel.add(returnBtn);

         */

        String[] columnNames = {"Date", "Order Number", "Amount"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // If the order list is empty, add a row with "No order yet" message
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                if (orderList.isEmpty()) {
                    JOptionPane.showMessageDialog(History.this, "No orders yet.", "Order History", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (Order order : orderList) {
                        String[] rowData = {order.getDate(), order.getOrderNumber(), order.getAmount()};
                        model.addRow(rowData);
                    }
                }
            }
        });

        // Create the table and add it to a scroll pane
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(30);

        // Center the content in the cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        // Add the components to the main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        historyPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    /*Just to test the code with order array list, example of how to order list would work
    public static void main(String[] args) {
    JFrame parentFrame = new JFrame();
    JDialog previousDialog = new JDialog();

    // Create an ArrayList of orders
    ArrayList<Order> orderList = new ArrayList<>();

    // Create some Order objects
    Order order1 = new Order("2023-04-01", "1001", "50.00");
    Order order2 = new Order("2023-04-02", "1002", "35.00");
    Order order3 = new Order("2023-04-03", "1003", "75.00");

    // Add the orders to the ArrayList
    orderList.add(order1);
    orderList.add(order2);
    orderList.add(order3);

    // Create a new instance of the History class, passing the ArrayList
    History history = new History(parentFrame, previousDialog, orderList);
    }
    
     */


}

