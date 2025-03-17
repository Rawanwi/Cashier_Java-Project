package cashierfinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
class Menu extends JFrame implements KeyListener {

	// GUI components
	//ส่วนประกอบ GUI
	private JFrame menuFrame;
	private JPanel menuNorthPanel,
				   menuSouthPanel,
				   menuCenterPanel,
				   menuTitlePanel,
				   menuDetailPanel,
				   menuChoicePanel,
				   menuHeaderPanel;

	private JLabel menuStoreLabel,
				   menuTitleLabel,
				   menuCashierLabel,
				   menuHeaderLabel,				 
				   menuChoiceLabel[],
				   menuGuideLabel;
//ชื่อเมนู
	public static String choice[] = {"[A] Brownie        ",//1
								   	"[B] Chocolate Cake ",//2
								   	"[C] Honey Toast   ",//3
								   	"[D] Crape Cake     ",//4
					   				"[E] Cheese Cake    ",//5
					   				"[F] Cookie         ",//6
					   				"[G] Croissant      ",//7
					   				"[H] Waffle         ",//8
					   				"[I] Macaron        ",};//9
					   				

	
	 //Element listing of all the food PRICE
	 // องค์ประกอบของราคาอาหาร
	public static double price[] = {65.00,//Brownie
					  				99.00,//Chocolate Cake
					  				129.00,//Honey Toast
					  				79.00,//Crape Cake
					  				99.00,//Cheese Cake
					  				49.00,//Cookie
					  				89.00,//Croissant
					  				59.00,//Waffle
					  				70.00,};//Macaron
					  			

	
	//To keep track of the amount of food ordered
	//ติดตามปริมาณอาหารที่สั่ง
	public static int ordered[];
				   
	String option[] = {"Total",
	                   "Log Out"};

	
	//This constructor initialize GUI components
	
	public Menu() {
		menuFrame = new JFrame("Menu"); 
		menuFrame.getContentPane().setLayout(new BorderLayout(0,0));
//พื้นหลังข้างบนสุด
		menuNorthPanel = new JPanel();
		menuNorthPanel.setLayout(new FlowLayout());
		menuNorthPanel.setBackground(new Color(255,255,204));
// เปลี่ยนสีแถบล่าง
		menuSouthPanel = new JPanel();
		menuSouthPanel.setBackground(new Color(255,153,204));
//แถบเมนู
		menuTitlePanel = new JPanel();
		menuTitlePanel.setLayout(new BorderLayout(10,10));
		menuTitlePanel.setBackground(new Color(255,204,255));
//แถบ cashier name
		menuDetailPanel = new JPanel();
		menuDetailPanel.setLayout(new GridLayout(2,1));
		menuDetailPanel.setBackground(new Color(255,204,229));
//ส่วนเมนู (มองสีไม่เห็น)
		menuCenterPanel = new JPanel(); 
		menuCenterPanel.setLayout(new BorderLayout(0,0));
		menuCenterPanel.setBackground(new Color(255,255,255));
//ชื่อร้าน
		menuStoreLabel = new JLabel(" Sweet dreams Bakery ");
		menuStoreLabel.setForeground(new Color(255,0,127));
		menuStoreLabel.setFont(new Font("Harlow Solid Italic",Font.BOLD+Font.ITALIC,36));//font
		menuTitleLabel = new JLabel("          Menu Bakery", JLabel.CENTER);
		menuTitleLabel.setForeground(new Color(255,0,127));//สีตัวอักษร
		menuTitleLabel.setFont(new Font("Waffle Regular",Font.BOLD,40));

		
//รับแคชเชียร์ปัจจุบันเข้าสู่ระบบ
// Acquire current cashier logged in
		menuCashierLabel = new JLabel("Casheir Name : " + Login.cashierName);
		menuCashierLabel.setForeground(new Color(255,0,127));
		menuCashierLabel.setFont(new Font("TH Sarabun New",Font.BOLD,20));


//ส่วนหัว

		menuHeaderLabel = new JLabel("             Food Item"
									+"              Price(THB)"
									+"    Ordered");
		menuHeaderLabel.setForeground(Color.black);
		menuHeaderLabel.setFont(new Font("Verdana",Font.BOLD,18));
		
//กำหนดรายการอาหารและตัวเลือกเมนู
// Set the food items and menu choices
		menuChoicePanel = new JPanel();
		menuChoicePanel.setLayout(new GridLayout(choice.length+4,1,0,0));
		menuChoicePanel.setBackground(new Color(255,255,255)); //สี background
		menuChoicePanel.add(menuHeaderLabel);

		// Extra 2 element for Quit and SubTotal options
		menuChoiceLabel = new JLabel[choice.length + 2];
		ordered = new int[choice.length];

		for (int i = 0; i < choice.length; i++)	{

			//ตั้งค่าเริ่มต้นการสั่งซื้อทั้งหมดเป็น 0
			// Set default ordered amount of all food to 0	
			ordered[i] = 0;
			
            //ดูตารางเมนู
			// Populating the menu table
			menuChoiceLabel[i] = new JLabel(choice[i]
										   +"   "+ price[i] + "0"
										   +"    "+ ordered[i]);
			menuChoiceLabel[i].setForeground(new Color(153,0,153));
			menuChoiceLabel[i].setFont(new Font("Courier New",Font.BOLD,22));

			menuChoicePanel.add(menuChoiceLabel[i]);
		}
       
		//เพิ่มตัวเลือกให้เมนู
		// Add options to menu
		menuChoiceLabel[choice.length] = new JLabel("  [0]  " + option[0]);
		menuChoiceLabel[choice.length+1] = new JLabel("  [Q]  " 
													  + option[1] + " " 
													  + Login.cashierName);
		menuChoiceLabel[choice.length].setForeground(new Color(0,102,51));
		menuChoiceLabel[choice.length+1].setForeground(new Color(0,102,51));
		menuChoiceLabel[choice.length].setFont(new Font("Courier New",Font.BOLD,22));
		menuChoiceLabel[choice.length+1].setFont(new Font("Courier New",Font.BOLD,22));
		menuChoicePanel.add(menuChoiceLabel[choice.length]);
		menuChoicePanel.add(menuChoiceLabel[choice.length+1]);
        
		//เพิ่มคำแนะนำง่ายๆ สำหรับผู้ใช้แคชเชียร์
		// Simple instructions added for user cashier
		menuGuideLabel = new JLabel("   Use the keyboard and press the appropriate keys in [ ]");
		menuGuideLabel.setForeground(Color.white);
		menuGuideLabel.setFont(new Font("Verdana",Font.BOLD,14));	
	}

	
	//This method consist of frame launch events 
	 
	public void launchFrame() {	

		menuFrame.setSize(250,350);
        
		//การจัดเรียงส่วนประกอบ GUI ในพาเนลไปยังเฟรม
		// Arranging GUI components in Panel onto Frame
		menuDetailPanel.add(menuCashierLabel);
		menuTitlePanel.add(menuTitleLabel, BorderLayout.WEST);
		menuTitlePanel.add(menuDetailPanel, BorderLayout.EAST);
		menuNorthPanel.add(menuStoreLabel, BorderLayout.NORTH);
		menuSouthPanel.add(menuGuideLabel, BorderLayout.SOUTH);		
		menuCenterPanel.add(menuTitlePanel, BorderLayout.NORTH);
		menuCenterPanel.add(menuChoicePanel, BorderLayout.CENTER);
		menuFrame.getContentPane().add(menuNorthPanel, BorderLayout.NORTH);
		menuFrame.getContentPane().add(menuCenterPanel, BorderLayout.CENTER);
		menuFrame.getContentPane().add(menuSouthPanel, BorderLayout.SOUTH);

		menuFrame.pack();		

		//การจัดหน้าจอให้อยู่กึ่งกลางบนเดสก์ท็อป
		// Centering the screen on the desktop
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = menuFrame.getSize();
		menuFrame.setLocation(((screenSize.width - frameSize.width) / 2),
							((screenSize.height - frameSize.height) / 2));		

		menuFrame.addKeyListener(this);

		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setVisible(true);

	}

	private void refresh() {
		for (int i = 0; i < choice.length; i++){//เช็คว่าปุ่มที่กดมันมีในช้อยเมนูไหม ถ้ามีละกดก็จะทำให้เพิ่มจำนวนชิ้นที่สั่งในเมนูนั้น
			

			//กำลังเติมตารางเมนู
			// Populating the menu table
			menuChoiceLabel[i].setText(choice[i]
										   +"      "+ price[i] + "0"

										   +"       "+ ordered[i]);
		}		
	}
    //เมธอดอินเตอร์เฟสที่ไม่ได้ใช้
	// Unused interface methods
	public void keyTyped(KeyEvent e) { }
	public void keyReleased(KeyEvent e) { }

	public void keyPressed(KeyEvent e) { 		
		switch(e.getKeyCode()) {
		case 65:
		case 66:
		case 67:
		case 68:
		case 69:
		case 70:
		case 71:
		case 72:
		case 73:
		case 74:
			//สะสมรายการอาหาร
			// Accumulate food items
			ordered[e.getKeyCode() - 65]++;
			break;

		case 48: //เลข 0
			//คำนวณผลรวม
			// Calculate total
			menuFrame.setVisible(false);
			SubTotal subTotal = new SubTotal();
			subTotal.launchFrame();
			break;

		case 81:
			//ออกจากโปรแกรม
			// Quit program
			menuFrame.setVisible(false);
			JOptionPane.showMessageDialog(this, "User Log Out",
				"Goodbye and have a nice day!", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
			break;
		}

		//รีเฟรชเมนู
		// Refreshes the menu;
		this.refresh();
	}
}
