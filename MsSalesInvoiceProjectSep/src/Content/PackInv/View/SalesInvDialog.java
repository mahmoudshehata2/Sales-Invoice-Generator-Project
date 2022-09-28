
package Content.PackInv.View;

/**
 * @author IslamAmin
*/
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SalesInvDialog extends JDialog {
    private JTextField customerNAMEF;
    private JTextField invDateF;
    private JLabel customerNAMELab;
    private JLabel invDateLab;
    private JButton okInvBTN;
    private JButton canceInvBTN;

    public SalesInvDialog(ViewSInvJFrame InvFrame) {
        customerNAMELab = new JLabel("Customer Name:");
        customerNAMEF = new JTextField(20);
        invDateLab = new JLabel("Invoice Date:");
        invDateF = new JTextField(20);
        okInvBTN = new JButton("OK");
        canceInvBTN = new JButton("Cancel");
        
        okInvBTN.setActionCommand("oKNewInvD");
        canceInvBTN.setActionCommand("cancelNewInvD");
        
        okInvBTN.addActionListener(InvFrame.getExecuteActionController());
        canceInvBTN.addActionListener(InvFrame.getExecuteActionController());
        setLayout(new GridLayout(4,3));
        
        add(invDateLab);
        add(invDateF);
        add(customerNAMELab);
        add(customerNAMEF);
        add(okInvBTN);
        add(canceInvBTN);
        
        pack();
        
    }

    public JTextField getCustomerNAMEF() {
        return customerNAMEF;
    }

    public JTextField getInvDateF() {
        return invDateF;
    }   
}