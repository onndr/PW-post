import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

import entity.*;

public class MainWindowForm extends JFrame {
    private JButton clickMEButton;
    private JPanel mainWindowPanel;
    private JTabbedPane mainWindowTabbedPane;
    private JTextField senderBuildingNumberTextField;
    private JTextField senderStreetTextField;
    private JLabel senderLabel;
    private JTextField senderCityTextField;
    private JTextField senderPostalCodeTextField;
    private JLabel receiverLabel;
    private JComboBox packageSizeComboBox;
    private JButton addPackageButton;
    private JLabel packageLabel;
    private JList allPackagesList;
    private JTextArea allPackagesInfoTextArea;
    private JTextField mainPagePackageIdTextField;
    private JButton mainPageTrackButton;
    private JTextArea mainPagePackageInfoTextArea;
    private JTextField receiverFirstNameTextField;
    private JTextField receiverLastNameTextField;
    private JTextField receiverEmailTextField;
    private JTextField receiverStreetTextField;
    private JLabel packageSizeLabel;
    private JLabel priorityLabel;
    private JComboBox priorityComboBox;
    private JTextField receiverCityTextField;
    private JTextField receiverPostalCodeTextField;
    private JTextField receiverBuildingNumberTextField;
    private JLabel receiverLastNameLabel;
    private JLabel receiverFirstNameLabel;
    private JLabel receiverEmailLabel;
    private JLabel receiverStreetLabel;
    private JLabel receiverBuildingNumberLabel;
    private JLabel receiverCityLabel;
    private JLabel receiverPostalCodeLabel;
    private JLabel senderPostalCodeLabel;
    private JLabel senderBuildingNumberLabel;
    private JLabel senderCityLabel;
    private JLabel senderStreetLabel;
    private JLabel mainPageEnterPackageIdLabel;
    private JPanel mainPagePanel;
    private JPanel addPackagePagePanel;
    private JPanel showAllPackagesPagePanel;
    private JLabel receiverPhoneNumberLabel;
    private JTextField receiverPhoneNumberTextField;
    private JPanel loginPagePanel;
    private JTextField loginLoginPageTextField;
    private JLabel loginLoginPageLabel;
    private JPasswordField passwordLoginPagePasswordField;
    private JLabel passwordLoginPageLabel;
    private JButton logInLoginPageButton;
    private JButton registerLoginPageButton;
    private JPanel createNewAccountPagePanel;
    private JTextField nameTextFieldNewAccountPage;
    private JTextField surnameTextFieldNewAccountPage;
    private JTextField mobileNumberTextFieldNewAccountPage;
    private JTextField emailTextFieldNewAccountPage;
    private JPasswordField passwordPasswordFieldNewAccountPage;
    private JCheckBox showPasswordCheckBox;
    private JTextField countryTextFieldNewAccountPage;
    private JTextField townTextFieldNewAccountPage;
    private JTextField buildingNumberTextFieldNewAccountPage;
    private JTextField streetTextFieldNewAccountPage;
    private JTextField postalCodeTextFieldNewAccountPage;
    private JSpinner dayNewAccountPageSpinner;
    private JComboBox monthNewAccountPageComboBox;
    private JSpinner yearNewAccountPageSpinner;
    private JButton createAccountButton;
    private JButton logInButton;
    private JTabbedPane loginTabbedPane;
    private JPanel courierPagePanel;
    private JList courierAllPackagesList;
    private JTextArea courierAllPackagesInfoTextArea;
    private JPanel logOutTab;
    private JPasswordField passwordAgainPasswordFieldNewAccountPage;
    private JLabel weightLabel;
    private JTextField weightTextField;
    private JTextField senderCountryTextField;
    private JTextField receiverCountryTextField;
    private JTabbedPane courierTabbedPane;
    private JPanel logOutCourierTab;
    private JTabbedPane adminTabbedPane;
    private JPanel adminPagePanel;
    private JPanel logOutAdminTab;
    private JButton receivedFromClientButton;
    private JButton deliveredToClientButton;
    private JButton deliveredToNodeButton;
    private JButton receivedFromNodeButton;
    private JList adminAllPackagesList;
    private JList adminAllCouriersList;
    private JTextArea adminPackageInfoTextArea;
    private JButton assignPackageToCourierButton;
    private JPanel adminManagePackagesTab;
    private JLabel PWPostLogo;
    private JTextField textOnClick;
    private DBManager dbManager;

    private Clients loggedInClient;
    private Employees loggedInEmployee;
    ImageIcon logo = new ImageIcon(getClass().getResource("PW_Logo.png"));

    private String DescribedPackageAttributes(Object[] attrs){
        String resp = "";
        resp += "\tPackage:\nID: " + attrs[0].toString();
        resp += "; Size: " + attrs[1].toString();
        resp += "; Priority: " + attrs[2].toString() + "\n\n\tSender info:";
        resp += "\nFirst name: " + attrs[14].toString();
        resp += "; Last name: " + attrs[15].toString();
        resp += "; Phone number: " + attrs[16].toString();
        resp += "\n\tAddress:\nStreet: " + attrs[7].toString() + " " + attrs[8].toString();
        resp += "; City: " + attrs[9].toString() + " " + attrs[10].toString() + "\n\n\tReceiver info:\n";
        resp += "First name: " + attrs[11].toString();
        resp += "; Last name: " + attrs[12].toString();
        resp += "; Phone number: " + attrs[13].toString();
        resp += "\n\tAddress:\nStreet: " + attrs[3].toString() + " " + attrs[4].toString();
        resp += "; City: " + attrs[5].toString() + " " + attrs[6].toString() + "\n\n";
        if (attrs[17] != null && attrs[18] != null){
            resp += "\tStatus:\nStatus description: " + attrs[17].toString() + "\n";
            resp += "Date time: " + attrs[18].toString();
        }
        return resp;
    }

    public MainWindowForm() {
        dbManager = new DBManager();
        setContentPane(mainWindowPanel);
        setTitle("Post");
        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createNewAccountPagePanel.setVisible(false);
        mainWindowTabbedPane.setVisible(false);
        courierTabbedPane.setVisible(false);
        adminTabbedPane.setVisible(false);
        setVisible(true);
        setIconImage(logo.getImage());
        senderLabel.setFont(new Font("", Font.PLAIN, 16));
        receiverLabel.setFont(new Font("", Font.PLAIN, 16));
        packageLabel.setFont(new Font("", Font.PLAIN, 16));
        mainPageTrackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = new String(mainPagePackageIdTextField.getText());
                List<String> idList = Arrays.asList(id.split("\\s*,\\s*"));
                String attrsString = new String();
                for (int i = 0; i < idList.size(); i++) {
                    BigInteger oneId = new BigInteger(idList.get(i));
                    Object[] attributes = (Object[]) dbManager.getFullPackageInfo(oneId).get(0);
                    attrsString += DescribedPackageAttributes(attributes);
                    if (idList.size() > 1 && i < (idList.size() - 1)) {
                        attrsString += new String("\n---------------------------\n");
                    }
                }
                mainPagePackageInfoTextArea.setText(attrsString);
            }
        });
        mainWindowTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainWindowTabbedPane.getSelectedIndex() == 1){
                    allPackagesList.setModel(new DefaultListModel());
                    List infos = dbManager.getAllPackagesInfoForClient(loggedInClient.getClientId());
                    for(int i = 0; i < infos.size(); i++){
                        Object[] o = (Object[]) infos.get(i);
                        String info = o[0] + ", " + o[1] + " " + o[2];
                        DefaultListModel newModel = new DefaultListModel();
                        newModel.addElement(info);
                        for (int j = 0; j < allPackagesList.getModel().getSize(); j++){
                            newModel.addElement(allPackagesList.getModel().getElementAt(j));
                        }
                        allPackagesList.setModel(newModel);
                    }
                }
                else if (mainWindowTabbedPane.getSelectedIndex() == 2){
                    courierTabbedPane.setVisible(false);
                    mainWindowTabbedPane.setVisible(false);
                    loginTabbedPane.setVisible(true);
                    loginPagePanel.setVisible(true);
                    loggedInClient = null;
                    loggedInEmployee = null;
                    mainWindowTabbedPane.setSelectedIndex(0);
                }
            }
        });
        allPackagesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object selectedValue = ((JList) e.getSource()).getSelectedValue();
                if (selectedValue == null) return;
                String value = selectedValue.toString();
                String idStr = "";
                for (int i = 0; i < value.length(); i++){
                    if (value.charAt(i) == ','){
                        break;
                    }
                    idStr += value.charAt(i);
                }
                BigInteger packageId = new BigInteger(idStr);
                Object[] attributes = (Object[]) dbManager.getFullPackageInfo(packageId).get(0);
                allPackagesInfoTextArea.setText(DescribedPackageAttributes(attributes));
            }
        });
        courierAllPackagesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object selectedValue = ((JList) e.getSource()).getSelectedValue();
                if (selectedValue == null) return;
                String value = selectedValue.toString();
                String idStr = "";
                for (int i = 0; i < value.length(); i++){
                    if (value.charAt(i) == ','){
                        break;
                    }
                    idStr += value.charAt(i);
                }
                BigInteger packageId = new BigInteger(idStr);
                Object[] attributes = (Object[]) dbManager.getFullPackageInfo(packageId).get(0);
                courierAllPackagesInfoTextArea.setText(DescribedPackageAttributes(attributes));
            }
        });
        addPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromCountry = senderCountryTextField.getText();
                BigInteger fromCountryId = dbManager.getCountryIdByName(fromCountry);
                BigInteger senderAddressId = dbManager.addAddress(
                        senderCityTextField.getText(),
                        senderStreetTextField.getText(),
                        senderBuildingNumberTextField.getText(),
                        senderPostalCodeTextField.getText(),
                        fromCountryId
                );
                String toCountry = receiverCountryTextField.getText();
                BigInteger toCountryId = dbManager.getCountryIdByName(toCountry);
                BigInteger receiverAddressId = dbManager.addAddress(
                        receiverCityTextField.getText(),
                        receiverStreetTextField.getText(),
                        receiverBuildingNumberTextField.getText(),
                        receiverPostalCodeTextField.getText(),
                        toCountryId
                );
                BigInteger senderId = loggedInClient.getClientId();

//                dbManager.addClient(
//                        senderFirstNameTextField.getText(),
//                        senderLastNameTextField.getText(),
//                        senderEmailTextField.getText(),
//                        senderPhoneNumberTextField.getText(),
//                        senderAddressId, loggedInClient.getBirthDate(),
//                        loggedInClient.getClientId()
//                );

                BigInteger receiverId = dbManager.addClient(
                        receiverFirstNameTextField.getText(),
                        receiverLastNameTextField.getText(),
                        receiverEmailTextField.getText(),
                        receiverPhoneNumberTextField.getText(),
                        receiverAddressId, null, null
                );
                String size = "";
                String sizePrize = packageSizeComboBox.getSelectedItem().toString();
                for (int i = 0; i < sizePrize.length(); i++){
                    if (sizePrize.charAt(i) == ' '){
                        break;
                    }
                    size += sizePrize.charAt(i);
                }
                String pri = "";
                String priPrize = priorityComboBox.getSelectedItem().toString();
                for (int i = 0; i < priPrize.length(); i++){
                    if (priPrize.charAt(i) == ' '){
                        break;
                    }
                    pri += priPrize.charAt(i);
                }
                String weight = weightTextField.getText();
                dbManager.addPackage(
                        senderId, receiverId, senderAddressId,
                        receiverAddressId, size, pri, weight
                );
                senderCountryTextField.setText("");
                senderCityTextField.setText("");
                senderStreetTextField.setText("");
                senderBuildingNumberTextField.setText("");
                senderPostalCodeTextField.setText("");
                receiverCityTextField.setText("");
                receiverStreetTextField.setText("");
                receiverBuildingNumberTextField.setText("");
                receiverPostalCodeTextField.setText("");
                receiverCountryTextField.setText("");
                receiverFirstNameTextField.setText("");
                receiverLastNameTextField.setText("");
                receiverEmailTextField.setText("");
                receiverPhoneNumberTextField.setText("");
                weightTextField.setText("");
            }
        });
        logInLoginPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginLoginPageTextField.getText();
                String password = new String(passwordLoginPagePasswordField.getPassword());
                String passwordFromDb = dbManager.getPasswordByLogin(login);
                Integer hash = password.hashCode();
                boolean passwordIsValid = password.hashCode() == Integer.parseInt(passwordFromDb);
                //ACCOUNT_TYPE: 0 - ADMIN, 1 - COURIER, 2 - CLIENT
                BigInteger accType = dbManager.getAccountTypeByLogin(login);

                if (passwordIsValid && accType == BigInteger.valueOf(2)) {
                    loggedInClient = dbManager.getClientByLogin(login);
                    mainWindowTabbedPane.setVisible(true);
                    loginTabbedPane.setVisible(false);
                    loginPagePanel.setVisible(false);
                    courierTabbedPane.setVisible(false);
                    loginLoginPageTextField.setText("");
                    passwordLoginPagePasswordField.setText("");
                }
                else if (passwordIsValid && accType == BigInteger.valueOf(1)) {
                    loggedInEmployee = dbManager.getEmployeeByLogin(login);
                    courierAllPackagesList.setModel(new DefaultListModel());
                    List infos = dbManager.getAllPackagesInfoForCourier(loggedInEmployee.getEmployeeId());
                    for(int i = 0; i < infos.size(); i++){
                        Object[] o = (Object[]) infos.get(i);
                        String info = o[0] + ", " + o[1] + " " + o[2];
                        DefaultListModel newModel = new DefaultListModel();
                        newModel.addElement(info);
                        for (int j = 0; j < courierAllPackagesList.getModel().getSize(); j++){
                            newModel.addElement(courierAllPackagesList.getModel().getElementAt(j));
                        }
                        courierAllPackagesList.setModel(newModel);
                    }
                    courierTabbedPane.setVisible(true);
                    courierPagePanel.setVisible(true);
                    loginTabbedPane.setVisible(false);
                    loginPagePanel.setVisible(false);
                    loginLoginPageTextField.setText("");
                    passwordLoginPagePasswordField.setText("");
                }
                else if (passwordIsValid && accType == BigInteger.valueOf(0)) {
                    loginTabbedPane.setVisible(false);
                    loginPagePanel.setVisible(false);
                    adminAllPackagesList.setModel(new DefaultListModel());
                    List packagesInfos = dbManager.getAllPackagesInfo();
                    for (int i = 0; i < packagesInfos.size(); i++) {
                        Object[] o = (Object[]) packagesInfos.get(i);
                        String info = o[0] + ", " + o[1] + " " + o[2];
                        DefaultListModel newModel = new DefaultListModel();
                        newModel.addElement(info);
                        for (int j = 0; j < adminAllPackagesList.getModel().getSize(); j++) {
                            newModel.addElement(adminAllPackagesList.getModel().getElementAt(j));
                        }
                        adminAllPackagesList.setModel(newModel);
                    }

                    adminAllCouriersList.setModel(new DefaultListModel());
                    List couriersInfos = dbManager.getAllCouriersInfo();
                    for (int i = 0; i < couriersInfos.size(); i++) {
                        Object[] o = (Object[]) couriersInfos.get(i);
                        String info = o[0] + ", " + o[1] + " " + o[2];
                        DefaultListModel newModel = new DefaultListModel();
                        newModel.addElement(info);
                        for (int j = 0; j < adminAllCouriersList.getModel().getSize(); j++) {
                            newModel.addElement(adminAllCouriersList.getModel().getElementAt(j));
                        }
                        adminAllCouriersList.setModel(newModel);
                    }
                    adminTabbedPane.setVisible(true);
                    adminPagePanel.setVisible(true);
                    loginTabbedPane.setVisible(false);
                    loginPagePanel.setVisible(false);
                    loginLoginPageTextField.setText("");
                    passwordLoginPagePasswordField.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Password is not valid! Try again");
                }
            }
        });
        registerLoginPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPagePanel.setVisible(false);
                loginTabbedPane.setVisible(false);
                createNewAccountPagePanel.setVisible(true);
                courierTabbedPane.setVisible(false);
                adminTabbedPane.setVisible(false);
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewAccountPagePanel.setVisible(false);
                loginTabbedPane.setVisible(true);
                courierTabbedPane.setVisible(false);
                adminTabbedPane.setVisible(false);
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextFieldNewAccountPage.getText();
                String surname = surnameTextFieldNewAccountPage.getText();
                String email = emailTextFieldNewAccountPage.getText();
                String phoneNumber = mobileNumberTextFieldNewAccountPage.getText();

                int day = (int) dayNewAccountPageSpinner.getValue();
                int month = (int) monthNewAccountPageComboBox.getSelectedIndex() + 1;
                int year = (int) yearNewAccountPageSpinner.getValue();
                LocalDate birthDate = LocalDate.of(year, month, day);


                char[] password1 = passwordPasswordFieldNewAccountPage.getPassword();
                char[] password2 = passwordAgainPasswordFieldNewAccountPage.getPassword();

//                address
                String countryName = countryTextFieldNewAccountPage.getText();
                String town = townTextFieldNewAccountPage.getText();
                String buildingNumber = buildingNumberTextFieldNewAccountPage.getText();
                String street = streetTextFieldNewAccountPage.getText();
                String postalCode = postalCodeTextFieldNewAccountPage.getText();

                BigInteger countryId = dbManager.getCountryIdByName(countryName);
                BigInteger addressId = dbManager.addAddress(town, street, buildingNumber, postalCode, countryId);
                if (Arrays.equals(password1, password2)) {
                    BigInteger loginDataId = dbManager.addLoginData(email, String.valueOf(password1), 2);
                    BigInteger clientId = dbManager.addClient(name, surname, email, phoneNumber, addressId, birthDate, loginDataId);
                    loggedInClient = dbManager.getClient(clientId);

                    createNewAccountPagePanel.setVisible(false);
                    loginTabbedPane.setVisible(false);
                    mainWindowTabbedPane.setVisible(true);

                    nameTextFieldNewAccountPage.setText("");
                    surnameTextFieldNewAccountPage.setText("");
                    emailTextFieldNewAccountPage.setText("");
                    mobileNumberTextFieldNewAccountPage.setText("");
                    passwordPasswordFieldNewAccountPage.setText("");
                    passwordAgainPasswordFieldNewAccountPage.setText("");
                    countryTextFieldNewAccountPage.setText("");
                    townTextFieldNewAccountPage.setText("");
                    buildingNumberTextFieldNewAccountPage.setText("");
                    streetTextFieldNewAccountPage.setText("");
                    postalCodeTextFieldNewAccountPage.setText("");
                    SpinnerModel sm = new SpinnerNumberModel(1, 1, 31, 1);
                    dayNewAccountPageSpinner.setModel(sm);
                    sm = new SpinnerNumberModel(2023, 1900, 2023, 1);
                    yearNewAccountPageSpinner.setModel(sm);
                    monthNewAccountPageComboBox.setSelectedIndex(0);
                    showPasswordCheckBox.setSelected(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Passwords you entered are not the same, try again!");
                }
            }
        });
        courierTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (courierTabbedPane.getSelectedIndex() == 0){
                    courierAllPackagesList.setModel(new DefaultListModel());
                    List infos = dbManager.getAllPackagesInfoForCourier(loggedInEmployee.getEmployeeId());
                    for(int i = 0; i < infos.size(); i++){
                        Object[] o = (Object[]) infos.get(i);
                        String info = o[0] + ", " + o[1] + " " + o[2];
                        DefaultListModel newModel = new DefaultListModel();
                        newModel.addElement(info);
                        for (int j = 0; j < courierAllPackagesList.getModel().getSize(); j++){
                            newModel.addElement(courierAllPackagesList.getModel().getElementAt(j));
                        }
                        courierAllPackagesList.setModel(newModel);
                    }
                }
                else if (courierTabbedPane.getSelectedIndex() == 1){
                    courierTabbedPane.setVisible(false);
                    loginTabbedPane.setVisible(true);
                    loginPagePanel.setVisible(true);
                    loggedInClient = null;
                    loggedInEmployee = null;
                    courierTabbedPane.setSelectedIndex(0);
                    mainWindowTabbedPane.setSelectedIndex(0);
                }
            }
        });
        adminTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (adminTabbedPane.getSelectedIndex() == 0){
                    adminAllPackagesList.setModel(new DefaultListModel());
                    List packagesInfos = dbManager.getAllPackagesInfo();
                    for (int i = 0; i < packagesInfos.size(); i++) {
                        Object[] o = (Object[]) packagesInfos.get(i);
                        String info = o[0] + ", " + o[1] + " " + o[2];
                        DefaultListModel newModel = new DefaultListModel();
                        newModel.addElement(info);
                        for (int j = 0; j < adminAllPackagesList.getModel().getSize(); j++) {
                            newModel.addElement(adminAllPackagesList.getModel().getElementAt(j));
                        }
                        adminAllPackagesList.setModel(newModel);
                    }

                    adminAllCouriersList.setModel(new DefaultListModel());
                    List couriersInfos = dbManager.getAllCouriersInfo();
                    for (int i = 0; i < couriersInfos.size(); i++) {
                        Object[] o = (Object[]) couriersInfos.get(i);
                        String info = o[0] + ", " + o[1] + " " + o[2];
                        DefaultListModel newModel = new DefaultListModel();
                        newModel.addElement(info);
                        for (int j = 0; j < adminAllCouriersList.getModel().getSize(); j++) {
                            newModel.addElement(adminAllCouriersList.getModel().getElementAt(j));
                        }
                        adminAllCouriersList.setModel(newModel);
                    }
                }
                else if (adminTabbedPane.getSelectedIndex() == 1){
                    adminTabbedPane.setSelectedIndex(1);
                    adminTabbedPane.setVisible(false);
                    loginTabbedPane.setVisible(true);
                    loginPagePanel.setVisible(true);
                    loggedInClient = null;
                    loggedInEmployee = null;
                    mainWindowTabbedPane.setSelectedIndex(0);
                }
            }
        });
        adminAllPackagesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object selectedValue = ((JList) e.getSource()).getSelectedValue();
                if (selectedValue == null) return;
                String value = selectedValue.toString();
                String idStr = "";
                for (int i = 0; i < value.length(); i++){
                    if (value.charAt(i) == ','){
                        break;
                    }
                    idStr += value.charAt(i);
                }
                BigInteger packageId = new BigInteger(idStr);
                Object[] attributes = (Object[]) dbManager.getFullPackageInfo(packageId).get(0);
                adminPackageInfoTextArea.setText(DescribedPackageAttributes(attributes));
            }
        });
        receivedFromClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedValue = courierAllPackagesList.getSelectedValue();
                if (selectedValue == null) return;
                String value = selectedValue.toString();
                String idStr = "";
                for (int i = 0; i < value.length(); i++){
                    if (value.charAt(i) == ','){
                        break;
                    }
                    idStr += value.charAt(i);
                }
                BigInteger packageId = new BigInteger(idStr);

                dbManager.addSPPacksHistory(packageId, "Package going to node.");

                Object[] attributes = (Object[]) dbManager.getFullPackageInfo(packageId).get(0);
                courierAllPackagesInfoTextArea.setText(DescribedPackageAttributes(attributes));
            }
        });
        deliveredToNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedValue = courierAllPackagesList.getSelectedValue();
                if (selectedValue == null) return;
                String value = selectedValue.toString();
                String idStr = "";
                for (int i = 0; i < value.length(); i++){
                    if (value.charAt(i) == ','){
                        break;
                    }
                    idStr += value.charAt(i);
                }
                BigInteger packageId = new BigInteger(idStr);

                dbManager.addSPPacksHistory(packageId, "Package delivered to node.");

                Object[] attributes = (Object[]) dbManager.getFullPackageInfo(packageId).get(0);
                courierAllPackagesInfoTextArea.setText(DescribedPackageAttributes(attributes));
            }
        });
        receivedFromNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedValue = courierAllPackagesList.getSelectedValue();
                if (selectedValue == null) return;
                String value = selectedValue.toString();
                String idStr = "";
                for (int i = 0; i < value.length(); i++){
                    if (value.charAt(i) == ','){
                        break;
                    }
                    idStr += value.charAt(i);
                }
                BigInteger packageId = new BigInteger(idStr);

                dbManager.addSPPacksHistory(packageId, "Package going to receiver.");

                Object[] attributes = (Object[]) dbManager.getFullPackageInfo(packageId).get(0);
                courierAllPackagesInfoTextArea.setText(DescribedPackageAttributes(attributes));
            }
        });
        deliveredToClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedValue = courierAllPackagesList.getSelectedValue();
                if (selectedValue == null) return;
                String value = selectedValue.toString();
                String idStr = "";
                for (int i = 0; i < value.length(); i++){
                    if (value.charAt(i) == ','){
                        break;
                    }
                    idStr += value.charAt(i);
                }
                BigInteger packageId = new BigInteger(idStr);

                dbManager.addSPPacksHistory(packageId, "Package delivered to receiver.");

                Object[] attributes = (Object[]) dbManager.getFullPackageInfo(packageId).get(0);
                courierAllPackagesInfoTextArea.setText(DescribedPackageAttributes(attributes));
            }
        });
        assignPackageToCourierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedValue1 = adminAllPackagesList.getSelectedValue();
                if (selectedValue1 == null) return;
                String value1 = selectedValue1.toString();
                String idStr1 = "";
                for (int i = 0; i < value1.length(); i++){
                    if (value1.charAt(i) == ','){
                        break;
                    }
                    idStr1 += value1.charAt(i);
                }
                BigInteger packageId = new BigInteger(idStr1);

                Object selectedValue2 = adminAllCouriersList.getSelectedValue();
                if (selectedValue2 == null) return;
                String value2 = selectedValue2.toString();
                String idStr2 = "";
                for (int i = 0; i < value2.length(); i++) {
                    if (value2.charAt(i) == ','){
                        break;
                    }
                    idStr2 += value2.charAt(i);
                }
                BigInteger courierId = new BigInteger(idStr2);

                Packages a_package = dbManager.getPackageById(packageId);

                dbManager.addPackageToCourier(a_package, courierId);
            }
        });
        showPasswordCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordPasswordFieldNewAccountPage.setEchoChar((char) 0);
                    passwordAgainPasswordFieldNewAccountPage.setEchoChar((char) 0);
                }
                else {
                    passwordPasswordFieldNewAccountPage.setEchoChar('*');
                    passwordAgainPasswordFieldNewAccountPage.setEchoChar('*');
                }
            }
        });
        courierAllPackagesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object selectedValue = courierAllPackagesList.getSelectedValue();
                if (selectedValue == null) return;
                String value = selectedValue.toString();
                String idStr = "";
                for (int i = 0; i < value.length(); i++){
                    if (value.charAt(i) == ','){
                        break;
                    }
                    idStr += value.charAt(i);
                }
                BigInteger packageId = new BigInteger(idStr);
                Object[] attributes = (Object[]) dbManager.getFullPackageInfo(packageId).get(0);
                courierAllPackagesInfoTextArea.setText(DescribedPackageAttributes(attributes));
            }
        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme");
        MainWindowForm form = new MainWindowForm();
        SpinnerModel sm = new SpinnerNumberModel(1, 1, 31, 1);
        form.dayNewAccountPageSpinner.setModel(sm);
        sm = new SpinnerNumberModel(2023, 1900, 2023, 1);
        form.yearNewAccountPageSpinner.setModel(sm);
    }

    private void createUIComponents() throws IOException {
        PWPostLogo = new JLabel();
        File file = new File("PW_Post_640_360.jpg");
        BufferedImage jpg = ImageIO.read(file);
        Image scaledJpg = jpg.getScaledInstance(240, 135, Image.SCALE_SMOOTH);
        ImageIcon PWPostIcon = new ImageIcon(scaledJpg);
        PWPostLogo.setIcon(PWPostIcon);
        // PWPostLogo.setHorizontalAlignment(JLabel.CENTER);
    }
}
