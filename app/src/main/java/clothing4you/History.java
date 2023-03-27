package clothing4you;

import javax.swing.*;
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

        JPanel contentPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel dateLabel = new JLabel("Date");
        JLabel orderNumberLabel = new JLabel("Order Number");
        JLabel amountLabel = new JLabel("Amount");

        contentPanel.add(dateLabel);
        contentPanel.add(orderNumberLabel);
        contentPanel.add(amountLabel);

        // Assume that we have an ArrayList<Order> called orderList
        for (Order order : orderList) {
            JLabel dateValueLabel = new JLabel(order.getDate());
            JLabel orderNumberValueLabel = new JLabel(order.getOrderNumber());
            JLabel amountValueLabel = new JLabel(order.getAmount());

            contentPanel.add(dateValueLabel);
            contentPanel.add(orderNumberValueLabel);
            contentPanel.add(amountValueLabel);
        }

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


        historyPanel.add(buttonPanel, BorderLayout.SOUTH);


        JScrollPane scrollPane = new JScrollPane(contentPanel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

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
