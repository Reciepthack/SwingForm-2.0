package main.java.example.swing.form;




import main.java.example.swing.model.UserInfo;
import main.java.example.swing.model.validator.InfoValidator;
import main.java.example.swing.service.UserInfoRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserForm implements ActionListener {

    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private UserInfoRepository writer;
    private JFrame frame = new JFrame("User Form");
    private JLabel firstNameLabel = new JLabel("First name:");
    private JLabel lastNameLabel = new JLabel("Last name:");
    private JLabel emailLabel = new JLabel("Email:");
    private JTextField firstNameText = new JTextField();
    private JTextField lastNameText = new JTextField();
    private JTextField emailText = new JTextField();
    private JButton button = new JButton("Enter");


    public UserForm(UserInfoRepository writer) throws IOException {
        this.writer = writer;

        tableModel = getTableModel();
        table = new JTable(tableModel);
        scrollPane = getTableScrollPane();
        firstNameLabel.setBounds(20, 20, 80, 30);
        lastNameLabel.setBounds(20, 60, 80, 30);
        emailLabel.setBounds(20, 100, 80, 30);
        firstNameText.setBounds(100, 20, 180, 25);
        lastNameText.setBounds(100, 60, 180, 25);
        emailText.setBounds(100, 100, 180, 25);
        button.setBounds(380, 20, 300, 100);


        button.addActionListener(this);
        frame.add(firstNameText);
        frame.add(lastNameText);
        frame.add(emailText);
        frame.add(firstNameLabel);
        frame.add(lastNameLabel);
        frame.add(emailLabel);
        frame.add(button);
        frame.add(scrollPane);
        frame.setSize(800, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == button) {
            if (firstNameText.getText().trim().isEmpty() || emailText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Incorrect form information");
            } else {
                boolean statusFistName = InfoValidator.isValidName(firstNameText.getText());
                String firstName = firstNameText.getText();
                boolean statusLastName = InfoValidator.isValidName(lastNameText.getText());
                String lastName = lastNameText.getText();
                boolean statusEmail = InfoValidator.isValidEmail(emailText.getText());
                String email = emailText.getText();
                while (!statusEmail && !statusFistName && !statusLastName) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid information");
                    return;
                }
                UserInfo info = new UserInfo(firstName, lastName, email);

                try {
                    writer.write(info);
                    firstNameText.setText("");
                    lastNameText.setText("");
                    emailText.setText("");
                    tableModel.addRow(toToObjectArray(info));
                    tableModel.fireTableDataChanged();
                    JOptionPane.showMessageDialog(frame, info.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private JScrollPane getTableScrollPane() {
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBounds(20, 150, 740, 200);
        table.setFillsViewportHeight(true);
        return scrollPane;
    }

    private DefaultTableModel getTableModel() throws IOException{
        String[] columnNames = {"First name", "Last name", "Email"};
        Object[][] data = writer.findAll()
                .stream()
                .map(this::toToObjectArray)
                .toArray(Object[][]::new);

        return new DefaultTableModel(data, columnNames);
    }

    private Object[] toToObjectArray(UserInfo info) {
        return new Object[]{info.getFirstName(), info.getLastName(), info.getEmail()};
    }
}