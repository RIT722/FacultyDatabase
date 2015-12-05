import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Chris
 */
public class MainMenuUI extends javax.swing.JFrame {

	private ArrayList<ArrayList<String>> profs;
	private MySQLDatabase MySQLDB;
	
	/**
	 * Creates new form FacultyDatabaseUI
	 */
	public MainMenuUI() {		
		//ADAPTED FROM: http://stackoverflow.com/a/8881235
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter database password:");
		JPasswordField pass = new JPasswordField(16);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[]{"OK", "Cancel"};
		int option = JOptionPane.showOptionDialog(null, panel, "Enter Password",
								 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
								 null, options, pass);
		//pass.requestFocusInWindow();
		if (option == 0) // pressing OK button
		{
			String pw = new String(pass.getPassword());
			this.connectToDatabase(pw);
		}
		else { //Cancel
			System.exit(0);
			System.out.println("UH NEVER MIND");
		}
		
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchByButtonGroup = new javax.swing.ButtonGroup();
        mainMenuPanel = new javax.swing.JPanel();
        userNameField = new javax.swing.JTextField();
        userNameLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        facultyMembersLabel = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        keywordField = new javax.swing.JTextField();
        try {
            this.profs = BLFaculty.profList();
        }
        catch(DLException e){
            //blah
        }
        String[] profNames = new String[this.profs.size()+1];
        profNames[0] = "";  //should be a blank option first
        for (int i=1;i<profNames.length;i++) {
            profNames[i] = this.profs.get(i-1).get(1);
        }
        facultyNameComboBox = new javax.swing.JComboBox(profNames);
        titleLabel = new javax.swing.JLabel();
        facultyNameLabel = new javax.swing.JLabel();
        keywordLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        searchByPanel = new javax.swing.JPanel();
        titleRadioButton = new javax.swing.JRadioButton();
        searchByLabel = new javax.swing.JLabel();
        facultyNameRadioButton = new javax.swing.JRadioButton();
        keywordRadioButton = new javax.swing.JRadioButton();
        adminPanelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Faculty Database");

        mainMenuPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Main Menu"));

        userNameLabel.setText("User Name");

        passwordLabel.setText("Password");

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        facultyMembersLabel.setText("Faculty Members");

        titleField.setEnabled(false);
        titleField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent evt) {
                titleFieldDocumentUpdated(evt);
            }
            public void removeUpdate(DocumentEvent evt) {
                titleFieldDocumentUpdated(evt);
            }
            public void changedUpdate(DocumentEvent evt) {
                titleFieldDocumentUpdated(evt);
            }
        });

        keywordField.setEnabled(false);
        keywordField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent evt) {
                keywordFieldDocumentUpdated(evt);
            }
            public void removeUpdate(DocumentEvent evt) {
                keywordFieldDocumentUpdated(evt);
            }
            public void changedUpdate(DocumentEvent evt) {
                keywordFieldDocumentUpdated(evt);
            }
        });

        facultyNameComboBox.setEnabled(false);
        facultyNameComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                facultyNameComboBoxSelected(evt);
            }
        });

        titleLabel.setText("Title");

        facultyNameLabel.setText("Choose Faculty Name");

        keywordLabel.setText("Keyword");

        searchButton.setText("Search");
        searchButton.setEnabled(false);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        searchByPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        searchByButtonGroup.add(titleRadioButton);
        titleRadioButton.setText("Title");
        titleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleRadioButtonActionPerformed(evt);
            }
        });

        searchByLabel.setText("Search By");

        searchByButtonGroup.add(facultyNameRadioButton);
        facultyNameRadioButton.setText("Faculty Name");
        facultyNameRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyNameRadioButtonActionPerformed(evt);
            }
        });

        searchByButtonGroup.add(keywordRadioButton);
        keywordRadioButton.setText("Keyword");
        keywordRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keywordRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchByPanelLayout = new javax.swing.GroupLayout(searchByPanel);
        searchByPanel.setLayout(searchByPanelLayout);
        searchByPanelLayout.setHorizontalGroup(
            searchByPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchByPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(searchByPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchByLabel)
                    .addGroup(searchByPanelLayout.createSequentialGroup()
                        .addComponent(titleRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(facultyNameRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(keywordRadioButton)))
                .addContainerGap())
        );
        searchByPanelLayout.setVerticalGroup(
            searchByPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchByPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchByLabel)
                .addGap(26, 26, 26)
                .addGroup(searchByPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleRadioButton)
                    .addComponent(facultyNameRadioButton)
                    .addComponent(keywordRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        adminPanelButton.setText("Admin Panel");
        adminPanelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminPanelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainMenuPanelLayout = new javax.swing.GroupLayout(mainMenuPanel);
        mainMenuPanel.setLayout(mainMenuPanelLayout);
        mainMenuPanelLayout.setHorizontalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addComponent(searchByPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainMenuPanelLayout.createSequentialGroup()
                        .addGap(0, 395, Short.MAX_VALUE)
                        .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuPanelLayout.createSequentialGroup()
                                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(userNameLabel)
                                    .addComponent(passwordLabel))
                                .addGap(15, 15, 15)
                                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(loginButton)
                                    .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(userNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuPanelLayout.createSequentialGroup()
                                .addComponent(facultyMembersLabel)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuPanelLayout.createSequentialGroup()
                        .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                                .addComponent(adminPanelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchButton))
                            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(titleLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(facultyNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(keywordLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(titleField)
                                    .addComponent(keywordField)
                                    .addComponent(facultyNameComboBox, 0, 177, Short.MAX_VALUE))))
                        .addGap(45, 45, 45))))
        );
        mainMenuPanelLayout.setVerticalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(facultyMembersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchByPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleLabel))
                .addGap(33, 33, 33)
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facultyNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facultyNameLabel))
                .addGap(34, 34, 34)
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keywordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(keywordLabel))
                .addGap(20, 20, 20)
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(adminPanelButton))
                .addContainerGap())
        );

        passwordLabel.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenuPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (this.titleField.isEnabled() && !this.titleField.getText().isEmpty()){
                        ArrayList<BLPaper> ret = new ArrayList<>();
                        try{
                        ret = BLPaper.searchPapersByTitle(this.titleField.getText().trim());
                        }catch(DLException e){
                            System.out.println(e.toString());
			}
			System.out.println("ret = "+ret);
                        PaperUI paperUI = new PaperUI();
                        if(ret != null)
                            for(BLPaper paper : ret){
                                paperUI.getTitleCBX().addItem(paper);
                            }
                        
                        paperUI.setVisible(true);      
		}
		else if (this.facultyNameComboBox.isEnabled() && this.facultyNameComboBox.getSelectedIndex() > 0) {
			int index = this.facultyNameComboBox.getSelectedIndex() - 1; //because first "prof" in list is blank
			int id = Integer.parseInt(this.profs.get(index).get(0));
			new facultyInforUI(id).setVisible(true);
		}
		else if (this.keywordField.isEnabled() && !this.keywordField.getText().isEmpty()) {
                        ArrayList<BLPaper> ret = new ArrayList<>();
                        try{
                        ret = BLPaper.searchPapersByKeywords(this.keywordField.getText());
                        }catch(DLException e){
                            System.out.println(e.toString());
                        }
			System.out.println("ret = "+ret);
                        PaperUI paperUI = new PaperUI();
                        if(ret != null)
                            for(BLPaper paper : ret){
                                paperUI.getTitleCBX().addItem(paper);
                            }
                        
                        paperUI.setVisible(true);
		}
		else {
			this.searchButton.setEnabled(false); //if none of the above is true this button should not be enabled to begin with!
		}
    }//GEN-LAST:event_searchButtonActionPerformed

    private void keywordRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keywordRadioButtonActionPerformed
		if (this.facultyNameComboBox.isEnabled()){
			this.facultyNameComboBox.setEnabled(false);
		}
		if (this.titleField.isEnabled()){
			this.titleField.setEnabled(false);
		}
		this.keywordField.setEnabled(true);
		if (this.keywordField.getDocument().getLength() == 0 && this.searchButton.isEnabled()) {
			this.searchButton.setEnabled(false);
		}
    }//GEN-LAST:event_keywordRadioButtonActionPerformed

    private void facultyNameRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyNameRadioButtonActionPerformed
		if (this.titleField.isEnabled()){
			this.titleField.setEnabled(false);
		}
		if (this.keywordField.isEnabled()){
			this.keywordField.setEnabled(false);
		}
		this.facultyNameComboBox.setEnabled(true);
		if (this.facultyNameComboBox.getSelectedIndex() == 0 && this.searchButton.isEnabled()) {
			this.searchButton.setEnabled(false);
		}
    }//GEN-LAST:event_facultyNameRadioButtonActionPerformed

    private void titleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleRadioButtonActionPerformed
		if (this.facultyNameComboBox.isEnabled()){
			this.facultyNameComboBox.setEnabled(false);
		}
		if (this.keywordField.isEnabled()){
			this.keywordField.setEnabled(false);
		}
		this.titleField.setEnabled(true);
		if (this.titleField.getDocument().getLength() == 0 && this.searchButton.isEnabled()) {
			this.searchButton.setEnabled(false);
		}
    }//GEN-LAST:event_titleRadioButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String username = this.userNameField.getText();
		String password = new String(this.passwordField.getPassword());
		if (username.length() > 0 && password.length() > 0) {
			int facultyId;
			try {
				facultyId = BLFaculty.login(username,password);
				new FacultyEdit(facultyId).setVisible(true);
			}
			catch (DLException e) {
				JOptionPane.showMessageDialog(null, "Invalid login credentials.");
				this.userNameField.setText("");
				this.passwordField.setText("");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Please enter username and password.");
		}
    }//GEN-LAST:event_loginButtonActionPerformed

    private void adminPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminPanelButtonActionPerformed
        JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter admin username:");
		JTextField uname = new JTextField(16);
		JPasswordField pass = new JPasswordField(16);
		panel.add(label);
		panel.add(uname);
		panel.add(pass);
		String[] options = new String[]{"OK", "Cancel"};
		int option = JOptionPane.showOptionDialog(null, panel, "Enter Admin Username and Password",
								 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
								 null, options, uname);
		String username = uname.getText();
		String pw = new String(pass.getPassword());
		if (option == 0 && !pw.isEmpty()) // pressing OK button
		{
			try {
				BLAdmin.login(username,pw);
				new AdminEdit().setVisible(true);
			}
			catch (DLException e) {
				JOptionPane.showMessageDialog(null, "Invalid login credentials.");
				this.userNameField.setText("");
				this.passwordField.setText("");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Please enter username and password.");
		}
    }//GEN-LAST:event_adminPanelButtonActionPerformed

	private void titleFieldDocumentUpdated(DocumentEvent evt) {
		Document titleFieldDocument = (Document)evt.getDocument();
		if (titleFieldDocument.getLength() > 0) {
			this.searchButton.setEnabled(true);
		}
		else {
			this.searchButton.setEnabled(false);
		}
	}
	
	private void facultyNameComboBoxSelected(ActionEvent evt) {
		JComboBox cb = (JComboBox)evt.getSource();
		if (cb.getSelectedIndex() > 0) {
			this.searchButton.setEnabled(true);
		}
		else {
			this.searchButton.setEnabled(false);
		}
	}
	
	private void keywordFieldDocumentUpdated(DocumentEvent evt) {
		Document keywordFieldDocument = (Document)evt.getDocument();
		if (keywordFieldDocument.getLength() > 0) {
			this.searchButton.setEnabled(true);
		}
		else {
			this.searchButton.setEnabled(false);
		}
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainMenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainMenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainMenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainMenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>
        //</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainMenuUI().setVisible(true);
			}
		});
	}
	
	private void connectToDatabase(String pw) {
		MySQLDB = MySQLDatabase.getInstance();
		try{
			MySQLDB.setPassword(pw);
			MySQLDB.connect();
		} //attempt connection to database
		catch(DLException e){
			JOptionPane.showMessageDialog(null, "Could not complete operation. Details written to log file.");
			System.exit(1);
		}
		System.out.println("connection successful"); //Will print if connection is successful
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminPanelButton;
    private javax.swing.JLabel facultyMembersLabel;
    private javax.swing.JComboBox facultyNameComboBox;
    private javax.swing.JLabel facultyNameLabel;
    private javax.swing.JRadioButton facultyNameRadioButton;
    private javax.swing.JTextField keywordField;
    private javax.swing.JLabel keywordLabel;
    private javax.swing.JRadioButton keywordRadioButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel mainMenuPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton searchButton;
    private javax.swing.ButtonGroup searchByButtonGroup;
    private javax.swing.JLabel searchByLabel;
    private javax.swing.JPanel searchByPanel;
    private javax.swing.JTextField titleField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JRadioButton titleRadioButton;
    private javax.swing.JTextField userNameField;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
