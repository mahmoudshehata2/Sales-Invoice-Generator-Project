
package Content.PackInv.Model;


import Content.PackInv.Model.InvDataModels;
import Content.PackInv.Model.InvItemModels;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class FileOperationClassA {
    
    private ArrayList<InvDataModels> invHeader;
      
    public ArrayList<InvDataModels> readFile(){
        
        
        JFileChooser fselection = new JFileChooser();

        try {
            JOptionPane.showMessageDialog(null, "Select Inv File","Invoice Header", JOptionPane.INFORMATION_MESSAGE);
                    
            int res = fselection.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File hFile = fselection.getSelectedFile();
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
                        JOptionPane.showMessageDialog(null, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                        
                    }
                }
              
                JOptionPane.showMessageDialog(null, "Select Line File","Invoice Line", JOptionPane.INFORMATION_MESSAGE);
                        
                res = fselection.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File lineF = fselection.getSelectedFile();
                    Path lineP = Paths.get(lineF.getAbsolutePath());
                    List<String> lRLines = Files.readAllLines(lineP);
                    for (String itemContent : lRLines) {
                        
                        try {
                            String iContent[] = itemContent.split(",");
                            int invNumber = Integer.parseInt(iContent[0]);
                            String itemName = iContent[1];
                            double itemPrice = Double.parseDouble(iContent[2]);
                            int count = Integer.parseInt(iContent[3]);
                            InvDataModels invNum = null;
                            for (InvDataModels invDataModels : invArray) {
                                if (invDataModels.getNumberOfCustomer() == invNumber) {
                                    invNum = invDataModels;
                                    break;
                                }
                            }

                            InvItemModels invItemModels = new InvItemModels(itemName, itemPrice, count, invNum);
                            invNum.getInvItemMod().add(invItemModels);
                            
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Incorrect line Format", "Error", JOptionPane.ERROR_MESSAGE);
                               
                           }
                        }
                  }
              
                this.invHeader = invArray;  
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "The File is not read", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return invHeader;
    }
    
    public void writeData(ArrayList<InvDataModels> invData)
    {
        for(InvDataModels invModel : invData)
      {
          int invNum = invModel.getNumberOfCustomer();
          String date = invModel.getDate0fInvoice();
          String customer = invModel.getCustomerName();
          System.out.println("\n Invoice " + invNum + "\n {\n " + date + "," + customer);
          ArrayList<InvItemModels> invItemMod = invModel.getInvItemMod();
          
          for(InvItemModels invItemModels : invItemMod)
          {
                   System.out.println( invItemModels.getNameOfItems() + "," + invItemModels.getPriceOfItems() + "," + invItemModels.getCountOfItems());
          }
      }
    }
    
    
     public static void main(String[] args)
   {
       FileOperationClassA fileOP = new FileOperationClassA();
       ArrayList<InvDataModels> invoices = fileOP.readFile();
       fileOP.writeData(invoices);
              
   }
    
     
}
