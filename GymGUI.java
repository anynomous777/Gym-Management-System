import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GymGUI class represents the graphical user interface for Bishwash Gym Management System.
 * This class manages the creation and handling of gym member information through a Swing-based interface.
 * 
 * @author Bishwash
 */
public class GymGUI extends JFrame {
    private ArrayList<GymMember> members; 
    // Text Fields
    private JTextField idField, nameField, locationField, phoneField, emailField;
    private JTextField referralField, paidAmountField, removalReasonField, trainerField;
    
    // Read Fields
    private JTextField planPriceField, premiumChargeField, discountField;
    
    // Date Combo Box
    private JComboBox<String> dobYearComboBox, dobMonthComboBox, dobDayComboBox;
    private JComboBox<String> msYearComboBox, msMonthComboBox, msDayComboBox;
    
    // Radio Buttons
    private JRadioButton maleButton, femaleButton;
    
    // Combo Box
    private JComboBox<String> planComboBox;
    
    // File constants
    private static final String FILE_NAME = "gym_members.txt";
    
    public GymGUI() {
        members = new ArrayList<>();
        setTitle("BISHWASH GYM MANAGEMENT SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupUI();
        pack();
        setLocationRelativeTo(null);
    }
    
    private void setupDateComboBoxes() {
        String[] years = new String[75];
        for (int i = 0; i < 75; i++) {
            years[i] = String.valueOf(1950 + i);
        }
        
        String[] months = {"January", "February", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November", "December"};
        
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        
        dobYearComboBox = new JComboBox<>(years);
        dobMonthComboBox = new JComboBox<>(months);
        dobDayComboBox = new JComboBox<>(days);
        
        msYearComboBox = new JComboBox<>(years);
        msMonthComboBox = new JComboBox<>(months);
        msDayComboBox = new JComboBox<>(days);
    }
    
    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Personal Information Panel
        JPanel personalInfoPanel = createPersonalInfoPanel();
        mainPanel.add(personalInfoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Membership Details Panel
        JPanel membershipPanel = createMembershipPanel();
        mainPanel.add(membershipPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel);
        
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        setPreferredSize(new Dimension(1100, 750));
    }

    private JPanel createPersonalInfoPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Personal Information"));
        
        panel.add(new JLabel("Member ID:"));
        idField = new JTextField(10);
        panel.add(idField);
        
        panel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        panel.add(nameField);
        
        panel.add(new JLabel("Location:"));
        locationField = new JTextField(20);
        panel.add(locationField);
        
        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField(15);
        panel.add(phoneField);
        
        panel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        panel.add(emailField);
        
        panel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup genderGroup = new ButtonGroup();
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        panel.add(genderPanel);
        
        setupDateComboBoxes();
        
        panel.add(new JLabel("Date of Birth:"));
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.add(dobDayComboBox);
        dobPanel.add(dobMonthComboBox);
        dobPanel.add(dobYearComboBox);
        panel.add(dobPanel);
        
        panel.add(new JLabel("Membership Start Date:"));
        JPanel msPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        msPanel.add(msDayComboBox);
        msPanel.add(msMonthComboBox);
        msPanel.add(msYearComboBox);
        panel.add(msPanel);
        
        return panel;
    }

    private JPanel createMembershipPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Membership Details"));
        
        panel.add(new JLabel("Referral Source:"));
        referralField = new JTextField(20);
        panel.add(referralField);
        
        panel.add(new JLabel("Plan:"));
        planComboBox = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});
        panel.add(planComboBox);
        
        panel.add(new JLabel("Regular Plan Price:"));
        planPriceField = new JTextField("6500");
        planPriceField.setEditable(false);
        panel.add(planPriceField);
        
        panel.add(new JLabel("Premium Charge:"));
        premiumChargeField = new JTextField("50000");
        premiumChargeField.setEditable(false);
        panel.add(premiumChargeField);
        
        panel.add(new JLabel("Paid Amount:"));
        paidAmountField = new JTextField(10);
        panel.add(paidAmountField);
        
        panel.add(new JLabel("Discount Amount:"));
        discountField = new JTextField("0");
        discountField.setEditable(false);
        panel.add(discountField);
        
        panel.add(new JLabel("Trainer's Name:"));
        trainerField = new JTextField(20);
        panel.add(trainerField);
        
        panel.add(new JLabel("Removal Reason:"));
        removalReasonField = new JTextField(20);
        panel.add(removalReasonField);
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Actions"));
        
        JButton addRegularButton = new JButton("Add Regular Member");
        JButton addPremiumButton = new JButton("Add Premium Member");
        JButton activateButton = new JButton("Activate Membership");
        JButton displayButton = new JButton("Display Members");
        JButton deactivateButton = new JButton("Deactivate Membership");
        JButton markAttendanceButton = new JButton("Mark Attendance");
        JButton upgradePlanButton = new JButton("Upgrade Plan");
        JButton calculateDiscountButton = new JButton("Calculate Discount");
        JButton revertPremiumButton = new JButton("Revert Premium Member");
        JButton revertRegularButton = new JButton("Revert Regular Member");
        JButton payDueButton = new JButton("Pay Due Amount");
        JButton clearButton = new JButton("Clear");
        JButton saveToFileButton = new JButton("Save to File");
        JButton readFromFileButton = new JButton("Load from File");
        
        // Add action listeners
        addRegularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRegularMember();
            }
        });
        addPremiumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPremiumMember();
            }
        });
        activateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activateMembership();
            }
        });
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayMembers();
            }
        });
        deactivateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deactivateMembership();
            }
        });
        markAttendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });
        upgradePlanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upgradePlan();
            }
        });
        calculateDiscountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateDiscount();
            }
        });
        revertPremiumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removePremiumMember();
            }
        });
        revertRegularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeRegularMember();
            }
        });
        payDueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payDueAmount();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        saveToFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveMembersToFile();
            }
        });
        readFromFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadMembersFromFile();
            }
        });
        
        panel.add(addRegularButton);
        panel.add(addPremiumButton);
        panel.add(activateButton);
        panel.add(displayButton);
        panel.add(deactivateButton);
        panel.add(markAttendanceButton);
        panel.add(upgradePlanButton);
        panel.add(calculateDiscountButton);
        panel.add(revertPremiumButton);
        panel.add(revertRegularButton);
        panel.add(payDueButton);
        panel.add(clearButton);
        panel.add(saveToFileButton);
        panel.add(readFromFileButton);
        
        return panel;
    }
    
    /**
     * Saves all members to a text file in table format
     */
    private void saveMembersToFile() {
        if (members.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No members to save! Please add members first.");
            return;
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // Write header
            writer.println("------------------------------------------------------------------------------------------------------------------- ");
            writer.println("                                          BISHWASH GYM MEMBER DETAILS                                               ");
            writer.println(" -------- ------------------ ------------------ ------------------ ------------------ ------------------ ----------- ");
            writer.println("|   ID   |       Name       |     Location     |      Phone       |      Email       |     Status       |   Type     ");
            writer.println(" -------- ------------------ ------------------ ------------------+------------------ ------------------ ----------- ");
            
            // Write member data
            for (GymMember member : members) {
                String type = (member instanceof PremiumMember) ? "Premium" : "Regular";
                writer.printf("| %-6d | %-16s | %-16s | %-16s | %-16s | %-16s | %-9s |\n",
                             member.getId(),
                             member.getName(),
                             member.getLocation(),
                             member.getPhone(),
                             member.getEmail(),
                             member.getActiveStatus() ? "Active" : "Inactive",
                             type);
            }
            
            // Write footer
            writer.println("--------  ------------------  ------------------  ------------------  ----------------- ------------------ -----------");
            writer.println("Total Members: " + members.size());
            writer.println("Report Generated on: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            
            JOptionPane.showMessageDialog(this, "Members saved to " + FILE_NAME + " successfully");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving members: " + e.getMessage());
        }
    }
    
    /**
     * Loads members from a text file
     */
    private void loadMembersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "No saved members file found!");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            members.clear();
            // Skip the header lines
            for (int i = 0; i < 5; i++) {
                reader.readLine();
            }
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("+") || line.isEmpty() || line.contains("Total Members") || line.contains("Report Generated")) {
                    continue;
                }
                
                // Parse the table row
                String[] parts = line.split("\\|");
                if (parts.length >= 8) {
                    int id = Integer.parseInt(parts[1].trim());
                    String name = parts[2].trim();
                    String location = parts[3].trim();
                    String phone = parts[4].trim();
                    String email = parts[5].trim();
                    boolean activeStatus = parts[6].trim().equals("Active");
                    String type = parts[7].trim();
                    
                    // Create dummy member (details will be incomplete)
                    if (type.equals("Premium")) {
                        PremiumMember member = new PremiumMember(id, name, location, phone, email, 
                                "Unknown", "Unknown", "Unknown", "Unknown");
                        member.setActiveStatus(activeStatus);
                        members.add(member);
                    } else {
                        RegularMember member = new RegularMember(id, name, location, phone, email, 
                                "Unknown", "Unknown", "Unknown", "Unknown");
                        member.setActiveStatus(activeStatus);
                        members.add(member);
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Members loaded from " + FILE_NAME + " successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading members: " + e.getMessage());
        }
    }
    
    /**
     * Add regular member to system.
     */
    private void addRegularMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            if (isIdDuplicate(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!");
                return;
            }
            
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = dobDayComboBox.getSelectedItem() + "-" + 
                        dobMonthComboBox.getSelectedItem() + "-" + 
                        dobYearComboBox.getSelectedItem();
            String startDate = msDayComboBox.getSelectedItem() + "-" + 
                             msMonthComboBox.getSelectedItem() + "-" + 
                             msYearComboBox.getSelectedItem();
            String referral = referralField.getText();
            
            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || 
                email.isEmpty() || (!maleButton.isSelected() && !femaleButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!");
                return;
            }
            
            RegularMember member = new RegularMember(id, name, location, phone, email, 
                                                   gender, dob, startDate, referral);
            members.add(member);
            JOptionPane.showMessageDialog(this, "Regular Member added successfully!");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Add premium member to system.
     */
    private void addPremiumMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            if (isIdDuplicate(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!");
                return;
            }
            
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = dobDayComboBox.getSelectedItem() + "-" + 
                        dobMonthComboBox.getSelectedItem() + "-" + 
                        dobYearComboBox.getSelectedItem();
            String startDate = msDayComboBox.getSelectedItem() + "-" + 
                             msMonthComboBox.getSelectedItem() + "-" + 
                             msYearComboBox.getSelectedItem();
            String trainer = trainerField.getText();
            
            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || 
                email.isEmpty() || (!maleButton.isSelected() && !femaleButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!");
                return;
            }
            
            PremiumMember member = new PremiumMember(id, name, location, phone, email, 
                                                   gender, dob, startDate, trainer);
            members.add(member);
            JOptionPane.showMessageDialog(this, "Premium Member added successfully!");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Checks if the provided ID already exists in the system.
     * @param id The member ID to check
     * @return true if ID exists, otherwise false
     */
    private boolean isIdDuplicate(int id) {
        for (GymMember member : members) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Activate membership for a specific member.
     */
    private void activateMembership() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    member.setActiveStatus(true);
                    JOptionPane.showMessageDialog(this, "Membership activated successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Deactivate membership for a specific member.
     */
    private void deactivateMembership() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    member.setActiveStatus(false);
                    JOptionPane.showMessageDialog(this, "Membership deactivated successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Marks attendance for a specific member.
     */
    private void markAttendance() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member.getActiveStatus()) {
                        member.markAttendance();
                        JOptionPane.showMessageDialog(this, "Attendance marked successfully!\nLoyalty Points: " + member.getLoyaltyPoints());
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot mark attendance - membership is not active!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Upgrade membership plan for a regular member.
     */
    private void upgradePlan() {
        try {
            int id = Integer.parseInt(idField.getText());
            String selectedPlan = (String) planComboBox.getSelectedItem();
            
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof RegularMember) {
                        RegularMember regularMember = (RegularMember) member;
                        if (member.getActiveStatus()) {
                            String result = regularMember.upgradePlan(selectedPlan);
                            JOptionPane.showMessageDialog(this, result);
                        } else {
                            JOptionPane.showMessageDialog(this, "Membership is not active!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Regular Member!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Calculates discount for a premium member.
     */
    private void calculateDiscount() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        premiumMember.calculateDiscount();
                        discountField.setText(String.valueOf(premiumMember.getDiscountAmount()));
                        JOptionPane.showMessageDialog(this, "Discount calculated!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Remove a regular member from the system.
     */
    private void removeRegularMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String reason = removalReasonField.getText();
            
            if (reason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a removal reason!");
                return;
            }
            
            for (int i = 0; i < members.size(); i++) {
                GymMember member = members.get(i);
                if (member.getId() == id && member instanceof RegularMember) {
                    ((RegularMember)member).setRemovalReason(reason);
                    members.remove(i);
                    JOptionPane.showMessageDialog(this, "Regular Member reverted successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Regular Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Remove a premium member from the system.
     */
    private void removePremiumMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (int i = 0; i < members.size(); i++) {
                GymMember member = members.get(i);
                if (member.getId() == id && member instanceof PremiumMember) {
                    ((PremiumMember)member).revertPremiumMember();
                    members.remove(i);
                    JOptionPane.showMessageDialog(this, "Premium Member reverted successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Premium Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Processes payment of due amount for a premium member.
     */
    private void payDueAmount() {
        try {
            int id = Integer.parseInt(idField.getText());
            double amount = Double.parseDouble(paidAmountField.getText());
            
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        String result = premiumMember.payDueAmount(amount);
                        JOptionPane.showMessageDialog(this, result);
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }
    
    /**
     * Displays all members' details in a new window.
     */
    private void displayMembers() {
        JFrame displayFrame = new JFrame("Member Details");
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        
        if (members.isEmpty()) {
            textArea.setText("No members found!");
        } else {
            StringBuilder displayText = new StringBuilder();
            for (GymMember member : members) {
                if (member instanceof RegularMember) {
                    displayText.append("--- REGULAR MEMBER ---\n");
                } else if (member instanceof PremiumMember) {
                    displayText.append("--- PREMIUM MEMBER ---\n");
                }
                
                displayText.append("ID: ").append(member.getId()).append("\n");
                displayText.append("Name: ").append(member.getName()).append("\n");
                displayText.append("Location: ").append(member.getLocation()).append("\n");
                displayText.append("Phone: ").append(member.getPhone()).append("\n");
                displayText.append("Email: ").append(member.getEmail()).append("\n");
                displayText.append("Gender: ").append(member.getGender()).append("\n");
                displayText.append("DOB: ").append(member.getDOB()).append("\n");
                displayText.append("Start Date: ").append(member.getMembershipStartDate()).append("\n");
                displayText.append("Attendance: ").append(member.getAttendance()).append("\n");
                displayText.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
                displayText.append("Active Status: ").append(member.getActiveStatus() ? "Active" : "Inactive").append("\n");
                
                if (member instanceof RegularMember) {
                    RegularMember regMember = (RegularMember) member;
                    displayText.append("Plan: ").append(regMember.getPlan()).append("\n");
                    displayText.append("Price: ").append(regMember.getPrice()).append("\n");
                    if (!regMember.getRemovalReason().isEmpty()) {
                        displayText.append("Removal Reason: ").append(regMember.getRemovalReason()).append("\n");
                    }
                } else if (member instanceof PremiumMember) {
                    PremiumMember premMember = (PremiumMember) member;
                    displayText.append("Personal Trainer: ").append(premMember.getPersonalTrainer()).append("\n");
                    displayText.append("Paid Amount: ").append(premMember.getPaidAmount()).append("\n");
                    displayText.append("Payment Status: ").append(premMember.getIsFullPayment() ? "Complete" : "Incomplete").append("\n");
                    double remainingAmount = premMember.getPremiumCharge() - premMember.getPaidAmount();
                    displayText.append("Remaining Amount: ").append(remainingAmount).append("\n");
                    if (premMember.getIsFullPayment()) {
                        displayText.append("Discount Amount: ").append(premMember.getDiscountAmount()).append("\n");
                    }
                }
                
                displayText.append("\n---------------------------\n\n");
            }
            textArea.setText(displayText.toString());
        }
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        displayFrame.add(scrollPane);
        displayFrame.pack();
        displayFrame.setLocationRelativeTo(this);
        displayFrame.setVisible(true);
    }
    
    /**
     * Clears all input fields in the GUI.
     */
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralField.setText("");
        paidAmountField.setText("");
        removalReasonField.setText("");
        trainerField.setText("");
        discountField.setText("0");
        planPriceField.setText("6500");
        premiumChargeField.setText("50000");
        
        // Reset combo boxes
        planComboBox.setSelectedIndex(0);
        dobDayComboBox.setSelectedIndex(0);
        dobMonthComboBox.setSelectedIndex(0);
        dobYearComboBox.setSelectedIndex(0);
        msDayComboBox.setSelectedIndex(0);
        msMonthComboBox.setSelectedIndex(0);
        msYearComboBox.setSelectedIndex(0);
        
        // Clear gender selection
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.clearSelection();
    }
    
    /**
     * Main method to launch the application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new GymGUI().setVisible(true);
            }
        });
    }
}

/**
 * Abstract class representing a Gym Member
 */
abstract class GymMember implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String location;
    private String phone;
    private String email;
    private String gender;
    private String dob;
    private String membershipStartDate;
    private int attendance;
    private int loyaltyPoints;
    private boolean activeStatus;
    
    public GymMember(int id, String name, String location, String phone, String email, 
                    String gender, String dob, String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = true;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getDOB() { return dob; }
    public String getMembershipStartDate() { return membershipStartDate; }
    public int getAttendance() { return attendance; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public boolean getActiveStatus() { return activeStatus; }
    
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setAttendance(int attendance) { this.attendance = attendance; }
    public void setLoyaltyPoints(int points) { this.loyaltyPoints = points; }
    public void setActiveStatus(boolean status) { this.activeStatus = status; }
    
    public void markAttendance() { 
        if (activeStatus) {
            attendance++; 
            loyaltyPoints += 2; // Increase loyalty points by 2 for each attendance
            if (attendance % 10 == 0) {
                loyaltyPoints += 5; // Bonus 5 points for every 10 attendances
            }
        } else {
            throw new IllegalStateException("Cannot mark attendance - membership is not active");
        }
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Status: " + (activeStatus ? "Active" : "Inactive");
    }
}

/**
 * Class representing a Regular Member
 */
class RegularMember extends GymMember {
    private static final long serialVersionUID = 1L;
    private String plan;
    private double price;
    private String referralSource;
    private String removalReason;
    
    public RegularMember(int id, String name, String location, String phone, String email, 
                        String gender, String dob, String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, dob, membershipStartDate);
        this.plan = "Basic";
        this.price = 6500.0;
        this.referralSource = referralSource;
        this.removalReason = "";
    }
    
    public String getPlan() { return plan; }
    public double getPrice() { return price; }
    public String getReferralSource() { return referralSource; }
    public String getRemovalReason() { return removalReason; }
    
    public void setPlan(String plan) { this.plan = plan; }
    public void setPrice(double price) { this.price = price; }
    public void setRemovalReason(String reason) { this.removalReason = reason; }
    
    public String upgradePlan(String newPlan) {
        if (!getActiveStatus()) {
            return "Cannot upgrade plan - membership is inactive";
        }
        
        switch (newPlan) {
            case "Basic":
                this.plan = "Basic";
                this.price = 6500.0;
                return "Plan changed to Basic (Rs. 6500)";
            case "Standard":
                this.plan = "Standard";
                this.price = 8500.0;
                return "Plan upgraded to Standard (Rs. 8500)";
            case "Deluxe":
                this.plan = "Deluxe";
                this.price = 12000.0;
                return "Plan upgraded to Deluxe (Rs. 12000)";
            default:
                return "Invalid plan selection";
        }
    }
}

/**
 * Class representing a Premium Member
 */
class PremiumMember extends GymMember {
    private static final long serialVersionUID = 1L;
    private String personalTrainer;
    private double premiumCharge;
    private double paidAmount;
    private double discountAmount;
    private boolean isFullPayment;
    
    public PremiumMember(int id, String name, String location, String phone, String email, 
                        String gender, String dob, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, dob, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.premiumCharge = 50000.0;
        this.paidAmount = 0.0;
        this.discountAmount = 0.0;
        this.isFullPayment = false;
    }
    
    public String getPersonalTrainer() { return personalTrainer; }
    public double getPremiumCharge() { return premiumCharge; }
    public double getPaidAmount() { return paidAmount; }
    public double getDiscountAmount() { return discountAmount; }
    public boolean getIsFullPayment() { return isFullPayment; }
    
    public void setPersonalTrainer(String trainer) { this.personalTrainer = trainer; }
    public void setPremiumCharge(double charge) { this.premiumCharge = charge; }
    public void setPaidAmount(double amount) { this.paidAmount = amount; }
    public void setDiscountAmount(double amount) { this.discountAmount = amount; }
    public void setIsFullPayment(boolean status) { this.isFullPayment = status; }
    
    public String payDueAmount(double amount) {
        paidAmount += amount;
        if (paidAmount >= premiumCharge) {
            isFullPayment = true;
            calculateDiscount();
            return "Payment complete! Discount applied: Rs. " + discountAmount;
        }
        return "Payment received. Remaining amount: Rs. " + (premiumCharge - paidAmount);
    }
    
    public void calculateDiscount() {
        if (isFullPayment) {
            discountAmount = premiumCharge * 0.10; // 10% discount
            paidAmount -= discountAmount;
        }
    }

    public void revertPremiumMember() {
        setActiveStatus(false);
        paidAmount = 0;
        discountAmount = 0;
        isFullPayment = false;
    }
}