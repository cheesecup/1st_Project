package GUI;

import java.awt.*;
import DTO.*;
import DAO.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LeaveGUI {
	Frame frame;
	Panel[] panel;
	Label lblTitle, lblId, lblPd;
	TextField tfId, tfPd, tfOk;
	Button btnLeave, btnBack;
	
	public LeaveGUI() {
		frame = new Frame("헬스장 관리시스템 - 탈퇴하기");
		
		panel = new Panel[11];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblTitle = new Label("탈퇴하기", Label.CENTER);
		Font fontTitle = new Font("dialog", Font.BOLD, 30);
		lblTitle.setFont(fontTitle);
		lblTitle.setForeground(new Color(123, 54, 32));
		lblId = new Label("아 이 디");
		lblPd = new Label("비 밀 번 호");
		Font fontIdPd = new Font("dialog", Font.BOLD, 15);
		lblId.setFont(fontIdPd);
		lblPd.setFont(fontIdPd);
		tfId = new TextField("");
		tfPd = new TextField("");
		btnLeave = new Button("탈퇴하기");
		btnBack = new Button("뒤로가기");
		
		btnLeave.addActionListener(new ActionListener() {
			// 회원가입을 누르면 수행해줄 코드를 구현	
			@Override
			public void actionPerformed(ActionEvent e) {
				MasterDAO mdao = new MasterDAO();
				String deleteId = tfId.getText();
				String deletePd = tfPd.getText();			
				mdao.delete(deleteId, deletePd);
				LoginGUI lg = new LoginGUI();
                lg.launchFrame();
                frame.setVisible(false);
				
				} 
			
		});
		
		btnBack.addActionListener(new ActionListener() { // 뒤로가기 버튼 화면 전환
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI lg = new LoginGUI();
                lg.launchFrame();
                frame.setVisible(false); // 창 안보이게 하기 
            }
        });
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	void launchFrame() {
		panel[0].setLayout(new BorderLayout());
		panel[0].add(lblTitle, "South");
		panel[1].setLayout(new GridLayout(2, 1));
		panel[1].add(lblId);
		panel[1].add(lblPd);
		panel[2].setLayout(new GridLayout(2, 1));
		panel[2].add(tfId);
		panel[2].add(tfPd);
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[1], "West");
		panel[3].add(panel[2], "Center");
		panel[3].add(new Label(""), "East");
		panel[4].add(btnLeave);
		panel[4].add(new Label(" "));
		panel[4].add(btnBack);
		panel[5].setLayout(new BorderLayout());
		panel[5].add(panel[0], "South");
		panel[8].setLayout(new BorderLayout());
		panel[9].setLayout(new BorderLayout());
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[8], "West");
		panel[6].add(panel[3], "Center");
		panel[6].add(panel[9], "East");
		panel[7].setLayout(new BorderLayout());
		panel[7].add(panel[4], "North");
		
		panel[5].setPreferredSize(new Dimension(350,120));
		panel[7].setPreferredSize(new Dimension(350,180));
		panel[8].setPreferredSize(new Dimension(120,80));
		panel[9].setPreferredSize(new Dimension(120,80));
		btnLeave.setPreferredSize(new Dimension(100,40));
		btnBack.setPreferredSize(new Dimension(100,40));
		
		frame.add(panel[5], "North");
		frame.add(panel[6], "Center");
		frame.add(panel[7], "South");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth()) / 2 - 50,
				((frame.getToolkit().getScreenSize()).height - frame.getHeight()) / 2);
		frame.setSize(500,400);
		frame.setVisible(true);	
	}
	
	public static void main(String[] args) {
		LeaveGUI lg = new LeaveGUI();
		lg.launchFrame();

	}
}
