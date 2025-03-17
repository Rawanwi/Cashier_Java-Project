package cashierfinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
class Login extends JFrame implements KeyListener { //เป็นอินเตอร์เฟสสำหรับ object ของคลาส keyevent

	
	public static String cashierName;

	// ตัวสร้างหน้าต่างข้อความ  สร้างตัวแปรขึ้นมา
	private JFrame loginFrame;  //หน้าต่างทั้งหมด
	private JLabel loginLabel; //ข้อความ
	private JTextField loginText; //กล่องข้อความ

	
	 //This constructor initialize GUI components
	 //กำหนดค่าเริ่มต้นให้ GUI
	public Login() { //ตัวชื่อข้างบน
		loginFrame = new JFrame("Cashier Login");
		loginLabel = new JLabel("Cashier Name :");
		loginText = new JTextField("UserName", 10);
		loginText.setFont(new Font("",Font.BOLD,12));
	}

	
	//This method consist of frame launch events 
	
	public void launchFrame() {	
		loginFrame.setSize(1250,1000); //ขนาดหน้าต่าง
//กำหนดหน้าต่าง
		loginFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		loginFrame.getContentPane().add(loginLabel);
		loginFrame.getContentPane().add(loginText);
		loginFrame.pack();

		// Centering the screen on the desktop
		//จัดหน้าให้อยู่กึ่งกลางเดสก์ทอป
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = loginFrame.getSize();
		loginFrame.setLocation(((screenSize.width - frameSize.width) / 2),
							((screenSize.height - frameSize.height) / 2));		

		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setVisible(true);

		loginText.selectAll();
		loginText.addKeyListener(this);
	}

	// ใช้คำสั่ง enter เพื่อที่จะไปหน้าต่อไป
	public void keyTyped(KeyEvent e) { }
	public void keyReleased(KeyEvent e) { }

	public void keyPressed(KeyEvent e) { 

		// Key Enter is pressed
		// แป้นenter ถูกกด
		if (e.getKeyCode() == 10) {

			cashierName = loginText.getText();
			loginFrame.setVisible(false);

			Menu menu = new Menu();
			menu.launchFrame();

		}
	}
}// End of Login class
