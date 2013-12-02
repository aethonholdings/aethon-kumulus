package  com.scannerapp.common;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import com.scannerapp.apps.application.ApplicationConstants;
import com.scannerapp.apps.utils.ConstantUtil;
import com.scannerapp.resources.IconRepository;


public class HelpPopup extends JDialog 
	{
		JPanel panel = new JPanel();
		// JLabel help= new JLabel();
		JTextArea help = new JTextArea();
		JScrollPane helpScroll;
		JLabel heading = new JLabel();

		JButton ok = new JButton(ConstantUtil.getApplicationConstant("ok"));
		
		public HelpPopup() {

			initHelpPanelContent();
			initHandler(this);

			this.add(panel);
			this.setTitle(ConstantUtil.getApplicationConstant("help"));
			this.setSize(750, 450);

			// Get the size of the screen
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			// Determine the new location of the window
			int w = this.getSize().width;
			int h = this.getSize().height;
			int x = (dim.width - w) / 2;
			int y = (dim.height - h) / 2;
			// Move the window
			this.setLocation(x, y);
			this.setVisible(true);

		}

		private void initHandler(final HelpPopup helpPopup) 
		{
			ok.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					helpPopup.hide();
					helpPopup.dispose();
					
				}
			});
		}

		private void initHelpPanelContent() 
		{
			panel.setLayout(new GridBagLayout());
			
			help.setEditable(false);
			help.setLineWrap(true);
			help.setBackground(panel.getBackground());
			
			helpScroll = new JScrollPane(help);
			
			helpScroll.setBorder(null);
			
			ok.setIcon(IconRepository.APPLY_ICON);
			
			heading.setFont(new Font(panel.getFont().getName(), Font.BOLD, 20));

			panel.add(heading, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(10, 0, 10, 0), 0, 0));
			panel.add(new JSeparator(),new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.BOTH,
					new Insets(0, 0, 20, 0), 0, 0));
			panel.add(helpScroll, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 10, 0), 0, 0));
			panel.add(ok, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.CENTER,
					new Insets(0, 0, 3, 0), 70, 0));
		}

		public void setHelpText(int selectedTab) 
		{
			if (selectedTab == 0) {
				String helpText=readHelpFromPropertyFile("collectionPanelHelp");
				this.heading.setText("Collection Tab");
				this.help.setText(helpText);
				System.out.println("help text set");
				help.setCaretPosition(0);
				
			}
			if (selectedTab == 1) 
			{
				// readFromPropertyFile();
				this.heading.setText("Import aand Separation tab Header");
				this.help.setText("Import andSeperation tab Help");
			}
		}

		private String readHelpFromPropertyFile(String getHelp) 
		{
			String collectionPanelHelp="";
			try {
				Properties properties = new Properties();
				properties.load(new FileInputStream(ApplicationConstants.HELP_PROPERTY_FILE));
				collectionPanelHelp=properties.getProperty(getHelp);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return collectionPanelHelp;
		}
	}