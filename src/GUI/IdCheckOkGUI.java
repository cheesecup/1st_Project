package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import DTO.*;
import DAO.*;

// 아이디 사용가능 창 gui
public class IdCheckOkGUI {
	Frame frame;
	Panel[] panel;
	Label lblTitle;
	
	public IdCheckOkGUI() {
		frame = new Frame("중복확인");
		
		panel = new Panel[1];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblTitle = new Label("사용가능한 아이디입니다.", Label.CENTER);
		Font fontTitle = new Font("dialog", Font.BOLD, 15);
		lblTitle.setFont(fontTitle);
		lblTitle.setForeground(new Color(123, 54, 32));
		
		frame.addWindowListener(new WindowAdapter() { // 프레임 종료 문법
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
		
	}
	
	void launchFrame() {
		panel[0].add(lblTitle); 
		
		frame.add(panel[0], "Center");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth()) / 2,
				((frame.getToolkit().getScreenSize()).height - frame.getHeight()) / 2);
		frame.setSize(300,100);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		IdCheckOkGUI icog = new IdCheckOkGUI();
		icog.launchFrame();


	}
}
