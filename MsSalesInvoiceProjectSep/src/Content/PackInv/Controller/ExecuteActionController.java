package Content.PackInv.Controller;

import Content.PackInv.Model.InvDataModels;
import Content.PackInv.Model.InvTableDisplay;
import Content.PackInv.Model.InvItemModels;
import Content.PackInv.Model.ItemTableDisplay;
import Content.PackInv.View.SalesInvDialog;

import Content.PackInv.View.SalesLineIDialog;
import Content.PackInv.View.ViewSInvJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ExecuteActionController implements ActionListener , ListSelectionListener {

    private ViewSInvJFrame InvFrame;
    private SalesInvDialog salesInvDialog;
    private SalesLineIDialog salesLineIDialog;

    public ExecuteActionController(ViewSInvJFrame InvFrame) {
        this.InvFrame = InvFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Action: " + actionCommand);
        switch (actionCommand) {
            case "Load File":
                loadFileDialog();
                break;
     
            case "Create New Invoice":
                createNewInvoiceExec();
                break;
            
            case "Delete Invoice":
                deleteInvoiceExec();
                break;
            case "Create New Item":
                createNewItemExec();
                break;
            case "Delete Item":
                deleteItemExec();
                break;
            
            case "cancelNewInvD":
                CancelNewInvD();
                break;
            case "oKNewInvD":
                OKNewInvD();
                break;
            
            case "oKNewLineD":
                OKNewLineD();
                break;
            
            case "cancelNewLineD":
                CancelNewLineD();
                break;
                
            case "Save File":
                saveFileDialog();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int indexSelection = InvFrame.getSalesInvTableDisplay().getSelectedRow();
        if (indexSelection != -1) {
            System.out.println("select Row Index : " + indexSelection);
            InvDataModels currentInv = InvFrame.getInvData().get(indexSelection);
            InvFrame.getInvNumLabel1().setText("" + currentInv.getNumberOfCustomer());
            InvFrame.getInvDateLabel2().setText("" + currentInv.getDate0fInvoice());
            InvFrame.getInvCusNameLabel3().setText(currentInv.getCustomerName());
            InvFrame.getInvTotalLabel4().setText("" + currentInv.salesInvTotals());
            ItemTableDisplay invItemMod = new ItemTableDisplay(currentInv.getInvItemMod());
            InvFrame.getSalesInvItemInfo().setModel(invItemMod);
            invItemMod.fireTableDataChanged();
        }
    }

    private void loadFileDialog() {
        JFileChooser fchooser = new JFileChooser();

        try {
         JOptionPane.showMessageDialog(InvFrame, "Select Invoice Header File","User Message", JOptionPane.INFORMATION_MESSAGE);
                    
            int res = fchooser.showOpenDialog(InvFrame);
            if (res == JFileChooser.APPROVE_OPTION) {
                File hFile = fchooser.getSelectedFile();
                Path hPath = Paths.get(hFile.getAbsolutePath());
                List<String> hLines = Files.readAllLines(hPath);
                ArrayList<InvDataModels> invArray = new ArrayList<>();
                for (String headerLine : hLines) {
                    try {
                        String[] headerParts = headerLine.split(",");
                        int invoiceNum = Integer.parseInt(headerParts[0]);
                        String invoiceDate = headerParts[1];
                        String customerName = headerParts[2];

                        InvDataModels invDataModels = new InvDataModels(invoiceNum, invoiceDate, customerName);
                        invArray.add(invDataModels);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(InvFrame, "Error in line format", "Error Message", JOptionPane.ERROR_MESSAGE);
                        
                    }
                }
               
                JOptionPane.showMessageDialog(InvFrame, "Select Invoice Line File","User Message", JOptionPane.INFORMATION_MESSAGE);
                
                res = fchooser.showOpenDialog(InvFrame);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File lRFile = fchooser.getSelectedFile();
                    Path lRPath = Paths.get(lRFile.getAbsolutePath());
                    List<String> lRLines = Files.readAllLines(lRPath);
                    
                  
                    for (String itemContent : lRLines) {
                        try {
                            String iContent[] = itemContent.split(",");
                            int invNumber = Integer.parseInt(iContent[0]);
                            String itemInvName = iContent[1];
                            double itemInvPrice = Double.parseDouble(iContent[2]);
                            int countItem = Integer.parseInt(iContent[3]);
                            
                            InvDataModels invNum = null;
                            for (InvDataModels invDataModels : invArray) {
                                if (invDataModels.getNumberOfCustomer() == invNumber) {
                                    invNum = invDataModels;
                                    break;
                                }
                            }

                            InvItemModels invItemModels = new InvItemModels(itemInvName, itemInvPrice, countItem , invNum);
                            invNum.getInvItemMod().add(invItemModels);
                        } catch (Exception ex) {
                            
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(InvFrame, "incorrect  File Format  ","Error", JOptionPane.ERROR_MESSAGE);
                               
                    
                        }
                    }
                  
                }
                InvFrame.setInvData(invArray);
                InvTableDisplay invTableDisplay = new InvTableDisplay(invArray);
                InvFrame.setInvTableDisplay(invTableDisplay);
                InvFrame.getSalesInvTableDisplay().setModel(invTableDisplay);
                InvFrame.getInvTableDisplay().fireTableDataChanged();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(InvFrame, "Cannot read file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFileDialog() {
        ArrayList<InvDataModels> invData = InvFrame.getInvData();
        String Invheader = "";
        String linesItem = "";
        for (InvDataModels invDataModels : invData) {
            String invCSV = invDataModels.getCSVFile();
            Invheader += invCSV;
            Invheader += "\n";

            for (InvItemModels line : invDataModels.getInvItemMod()) {
                String lineCSV = line.getCSVFile();
                linesItem += lineCSV;
                linesItem += "\n";
            }
        }
       
        
        try {
            JFileChooser fc = new JFileChooser();
            int oP = fc.showSaveDialog(InvFrame);
            if (oP == JFileChooser.APPROVE_OPTION) {
                File hFile = fc.getSelectedFile();
                FileWriter fileWrite = new FileWriter(hFile);
                fileWrite.write(Invheader);
                fileWrite.flush();
                fileWrite.close();
                oP = fc.showSaveDialog(InvFrame);
                JOptionPane.showMessageDialog(InvFrame, "File saved successfully","User Message", JOptionPane.INFORMATION_MESSAGE);
           
                if (oP == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    FileWriter fileWrit = new FileWriter(lineFile);
                    fileWrit.write(linesItem);
                    fileWrit.flush();
                    fileWrit.close();
                }
            }
        } catch (Exception ex) {
            

        }
    }

    private void createNewInvoiceExec() {
        salesInvDialog = new SalesInvDialog(InvFrame);
        salesInvDialog.setVisible(true);
    }
    

    private void deleteInvoiceExec() {
        int selRow = InvFrame.getSalesInvTableDisplay().getSelectedRow();
        if (selRow != -1) {
            InvFrame.getInvData().remove(selRow);
            InvFrame.getInvTableDisplay().fireTableDataChanged();
        }
    }
   

    private void createNewItemExec() {   
        salesLineIDialog = new SalesLineIDialog(InvFrame);
        salesLineIDialog.setVisible(true);
    }

    private void deleteItemExec() {
        int chooseRow = InvFrame.getSalesInvItemInfo().getSelectedRow();

        if (chooseRow != -1) {
            ItemTableDisplay itemTableDisplay = (ItemTableDisplay) InvFrame.getSalesInvItemInfo().getModel();  
            itemTableDisplay.getInvItemMod().remove(chooseRow);
            itemTableDisplay.fireTableDataChanged();
            InvFrame.getInvTableDisplay().fireTableDataChanged();
        }
    }

    private void CancelNewInvD() { 
        salesInvDialog.setVisible(false);
        salesInvDialog.dispose();
        salesInvDialog = null;
    }

    private void OKNewInvD() {
        String date = salesInvDialog.getInvDateF().getText();
        String customer = salesInvDialog.getCustomerNAMEF().getText();
        int number = InvFrame.getNextInvNumber();                                   
        try {
            String[] splitDate = date.split("-");
            
            if (splitDate.length < 3) {
                JOptionPane.showMessageDialog(InvFrame, "Incorrect Date format", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int d = Integer.parseInt(splitDate[0]);
                int m = Integer.parseInt(splitDate[1]);
                int year = Integer.parseInt(splitDate[2]);
                
                if (d > 31 || m > 12) {
                    
                    JOptionPane.showMessageDialog(InvFrame, "Incorrect Date format", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else 
                {
                    InvDataModels invDataModels = new InvDataModels(number, date , customer);
                    InvFrame.getInvData().add(invDataModels);
                    InvFrame.getInvTableDisplay().fireTableDataChanged();
                    salesInvDialog.setVisible(false);
                    salesInvDialog.dispose();
                    salesInvDialog = null;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(InvFrame, "Incorrect Date format", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void OKNewLineD() {
        String itemNAME = salesLineIDialog.getItemNameF().getText();
        String itemCOUNT = salesLineIDialog.getItemCountF().getText();
        String itemPRICE = salesLineIDialog.getItemPriceF().getText();
        int count = Integer.parseInt(itemCOUNT);
        double price = Double.parseDouble(itemPRICE);
        
        int SelectINVOICE = InvFrame.getSalesInvTableDisplay().getSelectedRow();
        if (SelectINVOICE != -1) {
            InvDataModels invDataModels = InvFrame.getInvData().get(SelectINVOICE);
            InvItemModels invItemModels = new InvItemModels(itemNAME, price, count, invDataModels);
            invDataModels.getInvItemMod().add(invItemModels);
            
            ItemTableDisplay itemTableDisplay = (ItemTableDisplay) InvFrame.getSalesInvItemInfo().getModel(); 
            itemTableDisplay.fireTableDataChanged();
            InvFrame.getInvTableDisplay().fireTableDataChanged();
        }
        salesLineIDialog.setVisible(false);
        salesLineIDialog.dispose();
        salesLineIDialog = null;
    }

    private void CancelNewLineD() {
        salesLineIDialog.setVisible(false);
        salesLineIDialog.dispose();
        salesLineIDialog = null;
    }

}
