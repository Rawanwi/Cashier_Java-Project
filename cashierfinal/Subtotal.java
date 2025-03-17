package cashierfinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
class SubTotal extends JFrame implements KeyListener {
    //ตัวแปรเพื่อสะสมการชำระเงินทั้งหมด
	// Variable to cumulate total payments
	double perItemTotal, grandTotal;

	// GUI components
	private JFrame subTotalFrame;
	private JPanel subTotalNorthPanel,
				   subTotalSouthPanel,
				   subTotalCenterPanel,
				   subTotalTitlePanel,
				   subTotalDetailPanel,
				   subTotalOrderedPanel,
				   subTotalHeaderPanel;

	private JLabel subTotalStoreLabel,
				   subTotalTitleLabel,
				   subTotalCashierLabel,
				   subTotalHeaderLabel,
				   subTotalOrderedLabel[],
				   subTotalGrandTotalLabel,
				   subTotalGuideLabel;
							   					
	
	 //This constructor initialize GUI components
	 
	public SubTotal() {
		subTotalFrame = new JFrame("Total");
		subTotalFrame.getContentPane().setLayout(new BorderLayout(0,0));

		subTotalNorthPanel = new JPanel();
		subTotalNorthPanel.setLayout(new FlowLayout());
		subTotalNorthPanel.setBackground(new Color(255,255,204));

		subTotalSouthPanel = new JPanel();
		subTotalSouthPanel.setBackground(new Color(255,153,204));

		subTotalTitlePanel = new JPanel();
		subTotalTitlePanel.setLayout(new BorderLayout(10,10));
		subTotalTitlePanel.setBackground(new Color(255,204,255));

		subTotalDetailPanel = new JPanel();
		subTotalDetailPanel.setLayout(new GridLayout(2,1));
		subTotalDetailPanel.setBackground(new Color(255,204,229));

		subTotalCenterPanel = new JPanel();
		subTotalCenterPanel.setLayout(new BorderLayout(0,0));
		subTotalCenterPanel.setBackground(new Color(160,255,150));
//ชื่อร้าน
		subTotalStoreLabel = new JLabel(" Sweet dreams Bakery ");
		subTotalStoreLabel.setForeground(new Color(255,0,127));
		subTotalStoreLabel.setFont(new Font("Harlow Solid Italic",Font.BOLD+Font.ITALIC,36));//font

		subTotalTitleLabel = new JLabel(" SUB TOTAL ", JLabel.CENTER);
		subTotalTitleLabel.setForeground(new Color(255,0,127));
		subTotalTitleLabel.setFont(new Font("Waffle Regular",Font.BOLD,40));


		// Acquire current cashier logged in
		subTotalCashierLabel = new JLabel("   Casheir Name : " + Login.cashierName);
		subTotalCashierLabel.setForeground(new Color(255,0,127));
		subTotalCashierLabel.setFont(new Font("TH Sarabun New",Font.BOLD,20));


		// Header
		subTotalHeaderLabel = new JLabel("    Food Item"
										+"           Price/Unit"
										+"    Ordered"
										+"    Total(THB)   ");
		subTotalHeaderLabel.setForeground(Color.black);
		subTotalHeaderLabel.setFont(new Font("Verdana",Font.BOLD,22));

		//แสดงรายการอาหารที่สั่งในเมนู
		// Display the food items ordered on the menu
		subTotalOrderedPanel = new JPanel();
		subTotalOrderedPanel.setBackground(new Color(255,255,255));
		subTotalOrderedPanel.add(subTotalHeaderLabel);

		// Extra 2 element for Quit and SubTotal options
		subTotalOrderedLabel = new JLabel[Menu.choice.length];
		perItemTotal = 0;
		grandTotal = 0;
		int count = 0;

		for (int i = 0; i < Menu.choice.length; i++)	{

			if (Menu.ordered[i] > 0) {

			perItemTotal = Menu.ordered[i] * Menu.price[i];//รวมราคา สินค้า*จำนวน
			grandTotal+= perItemTotal;
			count++;

			// Populating the menu table
			subTotalOrderedLabel[i] = new JLabel("  " + Menu.choice[i]
										   	   +"    " + Menu.price[i] + "0"
										       +"          " + Menu.ordered[i]
										       +"          " + perItemTotal + "0");
			subTotalOrderedLabel[i].setForeground(new Color(153,0,153));
			subTotalOrderedLabel[i].setFont(new Font("Courier New",Font.BOLD,18));

			subTotalOrderedPanel.add(subTotalOrderedLabel[i]);

			// Reset the ordered amount of all food type ordered to 0	
			Menu.ordered[i] = 0;
			}
		}

		// Show grand total figure
		subTotalGrandTotalLabel = new JLabel(" Total : THB " + grandTotal + "0");
		subTotalGrandTotalLabel.setForeground(new Color(0,102,51));
		subTotalGrandTotalLabel.setFont(new Font("Courier New",Font.BOLD,30));			

		subTotalOrderedPanel.add(subTotalGrandTotalLabel);

		// Simple instructions added for user cashier
		subTotalGuideLabel = new JLabel("   Press [ Esc ] to return to menu");
		subTotalGuideLabel.setForeground(Color.white);
		subTotalGuideLabel.setFont(new Font("Verdana",Font.BOLD,14));	

		subTotalOrderedPanel.setLayout(new GridLayout(count+2,1,0,0));
	}

	//This method consist of frame launch events 
	
	public void launchFrame() {	

		subTotalFrame.setSize(200,350);

		//การจัดเรียงส่วนประกอบ GUI ในพาเนลไปยังเฟรม
		// Arranging GUI components in Panel onto Frame
		subTotalDetailPanel.add(subTotalCashierLabel);
		subTotalTitlePanel.add(subTotalTitleLabel, BorderLayout.WEST);
		subTotalTitlePanel.add(subTotalDetailPanel, BorderLayout.EAST);
		subTotalNorthPanel.add(subTotalStoreLabel, BorderLayout.NORTH);
		subTotalSouthPanel.add(subTotalGuideLabel, BorderLayout.SOUTH);		
		subTotalCenterPanel.add(subTotalTitlePanel, BorderLayout.NORTH);
		subTotalCenterPanel.add(subTotalOrderedPanel, BorderLayout.CENTER);
		subTotalFrame.getContentPane().add(subTotalNorthPanel, BorderLayout.NORTH);
		subTotalFrame.getContentPane().add(subTotalCenterPanel, BorderLayout.CENTER);
		subTotalFrame.getContentPane().add(subTotalSouthPanel, BorderLayout.SOUTH);

		subTotalFrame.pack();		

		// Centering the screen on the desktop
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = subTotalFrame.getSize();
		subTotalFrame.setLocation(((screenSize.width - frameSize.width) / 2),
							((screenSize.height - frameSize.height) / 2));		

		subTotalFrame.addKeyListener(this);

		subTotalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		subTotalFrame.setVisible(true);
	}
    
	//เมธอดอินเตอร์เฟสที่ไม่ได้ใช้
	// Unused interface methods
	public void keyTyped(KeyEvent e) { }
	public void keyReleased(KeyEvent e) { }

	public void keyPressed(KeyEvent e) { 

		// [Escape] key is pressed
		if (e.getKeyCode() == 27) {
			subTotalFrame.setVisible(false);
			Menu menu = new Menu();
			menu.launchFrame();
		}
	}
}// End of SubTotal class 
