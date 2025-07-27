package mapkeygen;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class MapKeygenGui extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  
  private final JButton browse = new JButton("Browse");
  
  private final JButton generate = new JButton("Generate");
  
  private final JTextFieldLimit jVin = new JTextFieldLimit(17);
  
  private final JTextField jFile = new JTextField();
  
  private final JTextField jActivation = new JTextField();
  
  private final Container pane;
  
  public MapKeygenGui() {
    super("");
    setDefaultCloseOperation(3);
    this.pane = getContentPane();
    this.pane.setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.anchor = 19;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    gridBagConstraints.weightx = 0.0D;
    gridBagConstraints.weighty = 0.0D;
    gridBagConstraints.fill = 0;
    this.pane.add(new JLabel("VIN:"), gridBagConstraints);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = 0;
    this.pane.add(new JLabel("CCT.DAT.inf:"), gridBagConstraints);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = 0;
    this.pane.add(new JLabel("Activation code:"), gridBagConstraints);
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = 0;
    this.pane.add(this.browse, gridBagConstraints);
    this.browse.addActionListener(this);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = 0;
    this.pane.add(this.generate, gridBagConstraints);
    this.generate.addActionListener(this);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 3.0D;
    gridBagConstraints.fill = 2;
    this.pane.add(this.jVin, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.pane.add(this.jFile, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.pane.add(this.jActivation, gridBagConstraints);
    pack();
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    Object object = paramActionEvent.getSource();
    if (object == this.browse) {
      JFileChooser jFileChooser = new JFileChooser();
      if (jFileChooser.showOpenDialog(this) == 0) {
        File file = jFileChooser.getSelectedFile();
        this.jFile.setText(file.getAbsolutePath());
      } 
    } else if (object == this.generate) {
      try {
        String str = MapKeygen.generate(new File(this.jFile.getText()), this.jVin.getText());
        JOptionPane.showMessageDialog(this, "Your activation code is \"" + str + "\"", "Activation generated!", 1);
        this.jActivation.setText(str);
      } catch (Exception exception) {
        JOptionPane.showMessageDialog(this, exception.getMessage(), "Error!", 0);
      } 
    } 
  }
  
  private class JTextFieldLimit extends JTextField {
    private static final long serialVersionUID = 1L;
    
    private final int limit;
    
    public JTextFieldLimit(int param1Int) {
      this.limit = param1Int;
    }
    
    protected Document createDefaultModel() {
      return new LimitDocument(null);
    }
    
    private class LimitDocument extends PlainDocument {
      private static final long serialVersionUID = 1L;
      
      private LimitDocument() {}
      
      public void insertString(int param2Int, String param2String, AttributeSet param2AttributeSet) throws BadLocationException {
        if (param2String == null)
          return; 
        if (getLength() + param2String.length() <= MapKeygenGui.JTextFieldLimit.this.limit)
          super.insertString(param2Int, param2String, param2AttributeSet); 
      }
    }
  }
}


/* Location:              C:\Users\Linus\Downloads\generator.jar!\mapkeygen\MapKeygenGui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */