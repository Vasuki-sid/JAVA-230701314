import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

public class Main {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // HashMap to store medicine details
    private HashMap<String, Medicine> medicines;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Pharmacy Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        initializeMedicines(); // Initialize medicines HashMap

        mainPanel.add(createLoginPanel(), "LoginPanel");
        mainPanel.add(createMedicinePanel(), "MedicinePanel");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void initializeMedicines() {
        medicines = new HashMap<>();

        // Adding sample medicines to the HashMap with image paths
        medicines.put("Aspirin", new Medicine("Aspirin", 10.99, 100, "2025-12-31", "images/aspirin.png"));
        medicines.put("Ibuprofen", new Medicine("Ibuprofen", 15.49, 50, "2024-06-30", "images/ibuprofen.png"));
        medicines.put("Paracetamol", new Medicine("Paracetamol", 5.99, 200, "2023-11-15", "images/paracetamol.png"));
        medicines.put("Amoxicillin", new Medicine("Amoxicillin", 25.00, 30, "2026-01-20", "images/amoxicillin.png"));
        medicines.put("Cetirizine", new Medicine("Cetirizine", 8.99, 150, "2025-07-15", "images/cetirizine.png"));
        medicines.put("Loratadine", new Medicine("Loratadine", 12.49, 80, "2024-05-22", "images/loratadine.png"));
        medicines.put("Metformin", new Medicine("Metformin", 18.00, 60, "2026-03-10", "images/metformin.png"));
        medicines.put("Simvastatin", new Medicine("Simvastatin", 22.50, 90, "2025-09-30", "images/simvastatin.png"));
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(30, 30, 30)); // Dark background for login
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("PHARMACY MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 215, 0)); // Gold color

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE); // White text

        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE); // White text

        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0x007BFF)); // Bootstrap blue
        loginButton.setForeground(Color.WHITE);

       // Add hover effect for button
       loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseEntered(java.awt.event.MouseEvent evt) {
               loginButton.setBackground(new Color(0x0056b3)); // Darker blue on hover
           }
           public void mouseExited(java.awt.event.MouseEvent evt) {
               loginButton.setBackground(new Color(0x007BFF)); // Original blue
           }
       });

       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.gridwidth = 2;
       loginPanel.add(titleLabel, gbc);

       gbc.gridwidth = 1;
       gbc.gridy++;
       gbc.gridx = 0;
       loginPanel.add(usernameLabel, gbc);

       gbc.gridx = 1;
       loginPanel.add(usernameField, gbc);

       gbc.gridx = 0;
       gbc.gridy++;
       loginPanel.add(passwordLabel, gbc);

       gbc.gridx = 1;
       loginPanel.add(passwordField, gbc);

       gbc.gridy++;
       gbc.gridwidth = 2;
       loginPanel.add(loginButton, gbc);

       loginButton.addActionListener(e -> {
           String username = usernameField.getText();
           String password = new String(passwordField.getPassword());

           if (validateLogin(username, password)) {
               cardLayout.show(mainPanel, "MedicinePanel");
               showWelcomeMessage(); // Show welcome message after logging in
           } else {
               JOptionPane.showMessageDialog(frame, "Invalid login!", "Login Failed", JOptionPane.ERROR_MESSAGE);
           }
       });

       return loginPanel;
   }

   private boolean validateLogin(String username, String password) {
      return "admin".equals(username) && "password".equals(password); // Simplified validation
   }

   private JPanel createMedicinePanel() {
       JPanel medicinePanel = new JPanel(new BorderLayout());

      medicinePanel.setBackground(new Color(240, 240, 240)); // Light gray background

      JLabel headerLabel = new JLabel("MEDICINE INVENTORY", JLabel.CENTER);
      headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
      headerLabel.setForeground(new Color(0x007BFF)); // Blue color

      medicinePanel.add(headerLabel, BorderLayout.NORTH);

      JPanel medicineListPanel = new JPanel(new GridLayout(0, 1));
      medicineListPanel.setBackground(Color.WHITE); 

      loadMedicines(medicineListPanel); // Load medicines from HashMap

      JScrollPane scrollPane = new JScrollPane(medicineListPanel);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scrollbar
      medicinePanel.add(scrollPane, BorderLayout.CENTER);

      JButton logoutButton = new JButton("Logout");
      logoutButton.setBackground(new Color(0xDC3545)); // Bootstrap red
      logoutButton.setForeground(Color.WHITE);

     logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "LoginPanel"));
     medicinePanel.add(logoutButton, BorderLayout.SOUTH);

     return medicinePanel;
   }

   private void loadMedicines(JPanel medicineListPanel) {
      // Iterate through the HashMap and display each medicine name only
      for (Medicine medicine : medicines.values()) {
          JButton medicineButton = new JButton(medicine.getName());
          medicineButton.setBackground(new Color(220, 220, 220)); // Light gray button background
          medicineButton.addActionListener(e -> showMedicineDetails(medicine));
          medicineListPanel.add(medicineButton);
      }
   }

   private void showMedicineDetails(Medicine medicine) {
      JOptionPane.showMessageDialog(frame,
              String.format("<html><b>Medicine:</b> %s<br><b>Price:</b> $%.2f<br><b>Quantity:</b> %d<br><b>Expiry:</b> %s</html>",
                      medicine.getName(), medicine.getPrice(), medicine.getQuantity(), medicine.getExpiry()),
              "Medicine Details",
              JOptionPane.INFORMATION_MESSAGE);
   }

   private void showWelcomeMessage() {
      JOptionPane.showMessageDialog(frame,
              "Welcome to the Pharmacy Management System!",
              "Welcome",
              JOptionPane.INFORMATION_MESSAGE);
   }
}

// Medicine class to represent each medicine
class Medicine {
   private String name;
   private double price; 
   private int quantity;
   private String expiry;
   private String imagePath; // Path to the image

   public Medicine(String name, double price, int quantity, String expiry) {
         this.name = name;
         this.price = price;
         this.quantity = quantity;
         this.expiry = expiry;
         this.imagePath = ""; // Default empty path
     }

     public Medicine(String name, double price, int quantity, String expiry, String imagePath) {
         this.name = name;
         this.price = price;
         this.quantity = quantity;
         this.expiry = expiry;
         this.imagePath = imagePath; // Set image path
     }

     public String getName() {
         return name;
     }

     public double getPrice() {
         return price;
     }

     public int getQuantity() {
         return quantity;
     }

     public String getExpiry() {
         return expiry;
     }

     public String getImagePath() { 
         return imagePath; 
     }

     @Override
     public String toString() {
         return String.format("%s: Price: $%.2f | Quantity: %d | Expiry: %s", name, price, quantity, expiry);
     }
}
