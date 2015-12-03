/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author katie
 */
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.text.*;
public class FacultyEdit extends javax.swing.JFrame {

    /**
     * Creates new form FacultyEdit
     */
    String facultyName;
    BLFaculty faculty;
    BLPaper displayPaper;
    String studentName;
    String helpText;
    ArrayList<Integer> paperIDs;
    String title;
    String pAbstract;
    String citation;
    String keywords;
    String titleText;
    String abstractText;
    String citationText;
    String keywordsText;
    int facID;
    ArrayList paperList;
    
    public FacultyEdit(int facultyID) {
        faculty = new BLFaculty(facultyID);
        facID = facultyID;
        try{
            faculty.fetch();
        }
        catch(DLException e){
            
        }
        facultyName = faculty.getFN() + " " + faculty.getLN();
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

        jDialog1 = new javax.swing.JDialog();
        facultyEditPanel = new javax.swing.JPanel();
        profNameLabel = new javax.swing.JLabel();
        ArrayList<ArrayList<String>> papers = new ArrayList();
        try {
            papers = faculty.profPapersList();
        }
        catch(DLException e){
            //blah
        }
        paperList = new ArrayList();
        paperIDs = new ArrayList();
        paperList.add("");  //should be a blank option first
        paperList.add("Add New Paper"); //Add option for new paper to combo box list
        for(int i = 0; i < papers.size(); i++)
        {
            if((papers.get(i).get(1)).length() > 30)
            paperList.add((papers.get(i).get(1)).substring(0, 30) + "...");
            else
            paperList.add((papers.get(i).get(1)) + "...");

            paperIDs.add(Integer.parseInt(papers.get(i).get(0)));
        }
        facultyPapersList = new javax.swing.JComboBox();
        paperInfoPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        abstractLabel = new javax.swing.JLabel();
        citationLabel = new javax.swing.JLabel();
        keywordLabel = new javax.swing.JLabel();
        keywordInstructions = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        abstractTextArea = new javax.swing.JTextArea();
        keywordsTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        citationTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        titleTextArea = new javax.swing.JTextArea();
        editButton = new javax.swing.JButton();
        deletePaperButton = new javax.swing.JButton();
        addNewPaperButton = new javax.swing.JButton();
        saveChangesButton = new javax.swing.JButton();
        returnToSearchButton = new javax.swing.JButton();
        addStudentButton = new javax.swing.JButton();
        studentNameTextBox = new javax.swing.JTextField(20);
        needStudentLabel = new javax.swing.JLabel();
        String helpTag = faculty.getHelp();
        needStudentTextBox = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        facultyEditPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Faculty Information"));
        facultyEditPanel.setPreferredSize(new java.awt.Dimension(612, 489));

        profNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        profNameLabel.setText(facultyName);

        facultyPapersList.setModel(new javax.swing.DefaultComboBoxModel(paperList.toArray()));
        facultyPapersList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyPapersListActionPerformed(evt);
            }
        });

        paperInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Paper Information"));

        titleLabel.setText("Title");

        abstractLabel.setText("Abstract");

        citationLabel.setText("Citation");

        keywordLabel.setText("Keywords");

        keywordInstructions.setText("(Separate with commas)");

        abstractTextArea.setEditable(false);
        abstractTextArea.setColumns(20);
        abstractTextArea.setLineWrap(true);
        abstractTextArea.setRows(5);
        jScrollPane1.setViewportView(abstractTextArea);
        abstractTextArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent evt) {
                abstractTextAreaDocumentUpdated(evt);
            }
            public void removeUpdate(DocumentEvent evt) {
                abstractTextAreaDocumentUpdated(evt);
            }
            public void changedUpdate(DocumentEvent evt) {
                abstractTextAreaDocumentUpdated(evt);
            }
        });

        keywordsTextField.setEditable(false);

        citationTextArea.setEditable(false);
        citationTextArea.setColumns(20);
        citationTextArea.setLineWrap(true);
        citationTextArea.setRows(5);
        jScrollPane2.setViewportView(citationTextArea);
        citationTextArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent evt) {
                citationTextAreaDocumentUpdated(evt);
            }
            public void removeUpdate(DocumentEvent evt) {
                citationTextAreaDocumentUpdated(evt);
            }
            public void changedUpdate(DocumentEvent evt) {
                citationTextAreaDocumentUpdated(evt);
            }
        });

        titleTextArea.setEditable(false);
        titleTextArea.setColumns(20);
        titleTextArea.setLineWrap(true);
        titleTextArea.setRows(5);
        jScrollPane3.setViewportView(titleTextArea);
        titleTextArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent evt) {
                titleTextAreaDocumentUpdated(evt);
            }
            public void removeUpdate(DocumentEvent evt) {
                titleTextAreaDocumentUpdated(evt);
            }
            public void changedUpdate(DocumentEvent evt) {
                titleTextAreaDocumentUpdated(evt);
            }
        });

        javax.swing.GroupLayout paperInfoPanelLayout = new javax.swing.GroupLayout(paperInfoPanel);
        paperInfoPanel.setLayout(paperInfoPanelLayout);
        paperInfoPanelLayout.setHorizontalGroup(
            paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paperInfoPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paperInfoPanelLayout.createSequentialGroup()
                        .addGroup(paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paperInfoPanelLayout.createSequentialGroup()
                                .addGroup(paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(citationLabel)
                                    .addComponent(titleLabel))
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paperInfoPanelLayout.createSequentialGroup()
                                .addComponent(abstractLabel)
                                .addGap(18, 18, 18)))
                        .addGroup(paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)))
                    .addComponent(keywordInstructions)
                    .addGroup(paperInfoPanelLayout.createSequentialGroup()
                        .addComponent(keywordLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keywordsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paperInfoPanelLayout.setVerticalGroup(
            paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paperInfoPanelLayout.createSequentialGroup()
                .addGroup(paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paperInfoPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(titleLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paperInfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paperInfoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(paperInfoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(abstractLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(citationLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paperInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keywordLabel)
                    .addComponent(keywordsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keywordInstructions)
                .addGap(102, 102, 102))
        );

        keywordsTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent evt) {
                keywordsTextFieldDocumentUpdated(evt);
            }
            public void removeUpdate(DocumentEvent evt) {
                keywordsTextFieldDocumentUpdated(evt);
            }
            public void changedUpdate(DocumentEvent evt) {
                keywordsTextFieldDocumentUpdated(evt);
            }
        });

        editButton.setText("Edit Paper");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deletePaperButton.setText("Delete Paper");
        deletePaperButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePaperButtonActionPerformed(evt);
            }
        });

        addNewPaperButton.setText("Add New Paper");
        addNewPaperButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewPaperButtonActionPerformed(evt);
            }
        });

        saveChangesButton.setText("Save Changes");
        saveChangesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveChangesButtonActionPerformed(evt);
            }
        });

        returnToSearchButton.setText("Return to Search");
        returnToSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnToSearchButtonActionPerformed(evt);
            }
        });

        addStudentButton.setText("Add Student");
        addStudentButton.setEnabled(false);
        addStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentButtonActionPerformed(evt);
            }
        });

        studentNameTextBox.setText("<student name>");
        studentNameTextBox.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent evt) {
                studentNameTextBoxDocumentUpdated(evt);
            }
            public void removeUpdate(DocumentEvent evt) {
                studentNameTextBoxDocumentUpdated(evt);
            }
            public void changedUpdate(DocumentEvent evt) {
                studentNameTextBoxDocumentUpdated(evt);
            }
        });

        needStudentLabel.setText("Student Researcher Text");

        needStudentTextBox.setText(helpTag);
        needStudentTextBox.setCaretPosition(0);
        needStudentTextBox.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent evt) {
                needStudentTextBoxDocumentUpdated(evt);
            }
            public void removeUpdate(DocumentEvent evt) {
                needStudentTextBoxDocumentUpdated(evt);
            }
            public void changedUpdate(DocumentEvent evt) {
                needStudentTextBoxDocumentUpdated(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout facultyEditPanelLayout = new javax.swing.GroupLayout(facultyEditPanel);
        facultyEditPanel.setLayout(facultyEditPanelLayout);
        facultyEditPanelLayout.setHorizontalGroup(
            facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facultyEditPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(facultyEditPanelLayout.createSequentialGroup()
                        .addGroup(facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(facultyEditPanelLayout.createSequentialGroup()
                                .addComponent(editButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deletePaperButton))
                            .addComponent(profNameLabel)
                            .addComponent(facultyPapersList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(facultyEditPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(needStudentLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateButton))
                            .addComponent(needStudentTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(facultyEditPanelLayout.createSequentialGroup()
                                .addComponent(studentNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addStudentButton)))
                        .addGap(20, 20, 20))
                    .addGroup(facultyEditPanelLayout.createSequentialGroup()
                        .addComponent(addNewPaperButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveChangesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(returnToSearchButton)
                        .addGap(40, 40, 40))
                    .addGroup(facultyEditPanelLayout.createSequentialGroup()
                        .addComponent(paperInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        facultyEditPanelLayout.setVerticalGroup(
            facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facultyEditPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(facultyEditPanelLayout.createSequentialGroup()
                        .addComponent(profNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(facultyPapersList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editButton)
                            .addComponent(deletePaperButton))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(facultyEditPanelLayout.createSequentialGroup()
                        .addGroup(facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(studentNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addStudentButton))
                        .addGap(18, 18, 18)
                        .addComponent(needStudentTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(needStudentLabel)
                            .addComponent(updateButton))))
                .addComponent(paperInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(facultyEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addNewPaperButton)
                    .addComponent(saveChangesButton)
                    .addComponent(returnToSearchButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(facultyEditPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(facultyEditPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facultyPapersListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyPapersListActionPerformed
        int titleListIdx = facultyPapersList.getSelectedIndex();
        if(titleListIdx == 1){
            titleTextArea.setText("");
            abstractTextArea.setText("");
            citationTextArea.setText("");
            keywordsTextField.setText("");
            titleTextArea.setEditable(true);
            abstractTextArea.setEditable(true);
            citationTextArea.setEditable(true);
            keywordsTextField.setEditable(true);
        }
        else
        {
            int paperId = paperIDs.get(titleListIdx-2);
            displayPaper = new BLPaper(paperId);
            try{
                displayPaper.fetch();
            }
            catch(DLException e){

            }
            titleTextArea.setEditable(false);
            abstractTextArea.setEditable(false);
            citationTextArea.setEditable(false);
            keywordsTextField.setEditable(false);
            title = displayPaper.getTitle();
            pAbstract = displayPaper.getPAbstract();
            citation = displayPaper.getCitation();
            try{
                keywords = displayPaper.getPaperKeywords();
            }
            catch(DLException e){

            }
            titleTextArea.setText(title);
            titleTextArea.setCaretPosition(0);
            abstractTextArea.setText(pAbstract);
            abstractTextArea.setCaretPosition(0);
            citationTextArea.setText(citation);
            citationTextArea.setCaretPosition(0);
            keywordsTextField.setText(keywords);
            keywordsTextField.setCaretPosition(0);
        }
    }//GEN-LAST:event_facultyPapersListActionPerformed

    private void deletePaperButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePaperButtonActionPerformed
        int titleListIdx = facultyPapersList.getSelectedIndex();
        int paperID = paperIDs.get(titleListIdx-2);
        BLPaper delPaper = new BLPaper(paperID);
        Object[] options = { "OK", "CANCEL" };
        int question = JOptionPane.showOptionDialog(null, "Click OK to delete paper", "Warning",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if(question == JOptionPane.YES_OPTION)
        {
            try{
                delPaper.deletePaper();
                System.out.println(paperID + " size:" + paperIDs.size());
                paperIDs.remove(titleListIdx-2);
                paperList.remove(titleListIdx);
                facultyPapersList.setModel(new javax.swing.DefaultComboBoxModel(paperList.toArray()));
                titleTextArea.setText("");
                abstractTextArea.setText("");
                citationTextArea.setText("");
                keywordsTextField.setText("");
                JOptionPane.showMessageDialog(null, "Paper Deleted");

               }
            catch(DLException e){
                JOptionPane.showMessageDialog(null, "Could not complete operation. Details written to log file.");
            }
        }
    }//GEN-LAST:event_deletePaperButtonActionPerformed

    private void saveChangesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesButtonActionPerformed
        int titleListIdx = facultyPapersList.getSelectedIndex();
        int paperID = paperIDs.get(titleListIdx-2);
        try{
            BLPaper updatePaper = new BLPaper(paperID);
            updatePaper.save(titleText, abstractText, citationText, keywordsText, facID);

            updatePaper.fetch();

            if(updatePaper.getTitle().length() > 30)
                paperList.set(titleListIdx, (updatePaper.getTitle()).substring(0, 30) + "...");
            else
                paperList.set(titleListIdx, updatePaper.getTitle() + "...");

            facultyPapersList.setModel(new javax.swing.DefaultComboBoxModel(paperList.toArray()));
             
            JOptionPane.showMessageDialog(null, "Paper saved.");

        }
        catch(DLException d){
            JOptionPane.showMessageDialog(null, "Could not complete operation. Details written to log file.");
        }
    }//GEN-LAST:event_saveChangesButtonActionPerformed

    private void returnToSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnToSearchButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_returnToSearchButtonActionPerformed

    private void addStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentButtonActionPerformed
        try{
            boolean newStudent = faculty.addStudent(studentName);
            if(!newStudent)
            {    
                JOptionPane.showMessageDialog(null, "Student already added.");
                studentNameTextBox.setText("");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Student added.");
		studentNameTextBox.setText("");
            }
        }
        catch(DLException e){
            JOptionPane.showMessageDialog(null, "Could not complete operation. Details written to log file.");
        }
		
    }//GEN-LAST:event_addStudentButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try{
            faculty.needHelp(helpText);
            
        }
        catch(DLException e){
            JOptionPane.showMessageDialog(null, "Could not complete operation. Details written to log file.");
        }
        JOptionPane.showMessageDialog(null, "Help Statement Changed");

    }//GEN-LAST:event_updateButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        titleTextArea.setEditable(true);
        abstractTextArea.setEditable(true);
        citationTextArea.setEditable(true);
        keywordsTextField.setEditable(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void addNewPaperButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewPaperButtonActionPerformed
        int titleListIdx = facultyPapersList.getSelectedIndex();
        if(titleListIdx == 1){
            try{
                BLPaper newPaper = new BLPaper();
                int newID = newPaper.addPaper(titleText, abstractText, citationText, keywordsText, facID);
                newPaper.fetch();
                paperIDs.add(newID);
                
                if(newPaper.getTitle().length() > 30)
                    paperList.add((newPaper.getTitle()).substring(0, 30) + "...");
                else
                    paperList.add(newPaper.getTitle() + "...");

                facultyPapersList.setModel(new javax.swing.DefaultComboBoxModel(paperList.toArray()));
                titleTextArea.setText("");
                abstractTextArea.setText("");
                citationTextArea.setText("");
                keywordsTextField.setText("");                
                JOptionPane.showMessageDialog(null, "Paper added.");
                
            }
            catch(DLException d){
                JOptionPane.showMessageDialog(null, "Could not complete operation. Details written to log file.");
            }
        }
    }//GEN-LAST:event_addNewPaperButtonActionPerformed

	private void studentNameTextBoxDocumentUpdated(DocumentEvent evt) {
		Document studentNameTextBoxDocument = (Document)evt.getDocument();
		if (studentNameTextBoxDocument.getLength() > 0) {
			this.addStudentButton.setEnabled(true);
		}
		else {
			this.addStudentButton.setEnabled(false);
		}
		int studentNameLength = studentNameTextBoxDocument.getLength();
		try {
			studentName = studentNameTextBoxDocument.getText(0, studentNameLength);
		}
		catch (BadLocationException e) {}
	}
	
	private void needStudentTextBoxDocumentUpdated(DocumentEvent evt) {
		Document needStudentTextBoxDocument = (Document)evt.getDocument();
		int needStudentLength = needStudentTextBoxDocument.getLength();
		try {
			helpText = needStudentTextBoxDocument.getText(0, needStudentLength);
		}
		catch (BadLocationException e) {}
	}
        
        
        private void titleTextAreaDocumentUpdated(DocumentEvent evt) {
		Document titleTextAreaDocument = (Document)evt.getDocument();
		int titleTextAreaLength = titleTextAreaDocument.getLength();
		try {
			titleText = titleTextAreaDocument.getText(0, titleTextAreaLength);
		}
		catch (BadLocationException e) {}
	}
        
        
        private void abstractTextAreaDocumentUpdated(DocumentEvent evt) {
		Document abstractTextAreaDocument = (Document)evt.getDocument();
		int abstractTextAreaLength = abstractTextAreaDocument.getLength();
		try {
			abstractText = abstractTextAreaDocument.getText(0, abstractTextAreaLength);
		}
		catch (BadLocationException e) {}
	}
        
        private void citationTextAreaDocumentUpdated(DocumentEvent evt) {
		Document citationTextAreaDocument = (Document)evt.getDocument();
		int citationTextAreaLength = citationTextAreaDocument.getLength();
		try {
			citationText = citationTextAreaDocument.getText(0, citationTextAreaLength);
		}
		catch (BadLocationException e) {}
	}
        
        private void keywordsTextFieldDocumentUpdated(DocumentEvent evt) {
		Document keywordsTextFieldDocument = (Document)evt.getDocument();
		int keywordsTextFieldLength = keywordsTextFieldDocument.getLength();
		try {
			keywordsText = keywordsTextFieldDocument.getText(0, keywordsTextFieldLength);
		}
		catch (BadLocationException e) {}
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel abstractLabel;
    private javax.swing.JTextArea abstractTextArea;
    private javax.swing.JButton addNewPaperButton;
    private javax.swing.JButton addStudentButton;
    private javax.swing.JLabel citationLabel;
    private javax.swing.JTextArea citationTextArea;
    private javax.swing.JButton deletePaperButton;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel facultyEditPanel;
    private javax.swing.JComboBox facultyPapersList;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel keywordInstructions;
    private javax.swing.JLabel keywordLabel;
    private javax.swing.JTextField keywordsTextField;
    private javax.swing.JLabel needStudentLabel;
    private javax.swing.JTextField needStudentTextBox;
    private javax.swing.JPanel paperInfoPanel;
    private javax.swing.JLabel profNameLabel;
    private javax.swing.JButton returnToSearchButton;
    private javax.swing.JButton saveChangesButton;
    private javax.swing.JTextField studentNameTextBox;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextArea titleTextArea;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
