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
import DTO.*;
import DAO.*;

//직원으로 로그인시 정보 관리 선택창
public class SelectInfoStaffGUI {
	Frame frame;// 윈도우 화면 전체 큰화면 1개
	Panel[] panel;
	Button btnMember, btnProgram;
	
	public SelectInfoStaffGUI() {
		frame = new Frame("헬스장 관리시스템 - 직원");
		
		panel = new Panel[6];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		btnMember = new Button("회 원 정 보");
		btnProgram = new Button("운 동 프 로 그 램");
		
		btnMember.addActionListener(new ActionListener() { // 회원정보 버튼 화면 전환
            @Override
            public void actionPerformed(ActionEvent e) {
            	MemberGUI meb = new MemberGUI();
                meb.launchFrame();
                frame.setVisible(false); // 창 안보이게 하기 
            }
        });
		
		
		btnProgram.addActionListener(new ActionListener() { // 프로그램 정보 버튼 화면 전환
            @Override
            public void actionPerformed(ActionEvent e) {
                ProgramGUI pg = new ProgramGUI();
                pg.launchFrame();
                frame.setVisible(false);
            }
        });
		
		frame.addWindowListener(new WindowAdapter() { // 프레임 종료 문법
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	} // end SelectUserGUI() { }

	void launchFrame() { // 화면을 구성하는 메소드
		
		panel[0].setLayout(new BorderLayout());
		panel[1].add(btnMember);
		panel[2].add(btnProgram);
		panel[3].setLayout(new GridLayout(2, 1));
		panel[3].add(panel[1]);
		panel[3].add(panel[2]);
		panel[4].setLayout(new BorderLayout());
		panel[4].add(panel[3], "Center");
		panel[5].setLayout(new BorderLayout());
		
		// 버튼 사이즈 조정
		panel[0].setPreferredSize(new Dimension(300,35));
		btnMember.setPreferredSize(new Dimension(160,70));
		btnProgram.setPreferredSize(new Dimension(160,70));
		
		frame.add(panel[0], "North");
		frame.add(panel[4], "Center");
		frame.add(panel[5], "South");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth()) / 2-25,
				((frame.getToolkit().getScreenSize()).height - frame.getHeight()) / 2 -70);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
	} // end private launchFrame()

	public static void main(String[] args) {
		SelectInfoStaffGUI sisg = new SelectInfoStaffGUI();
		sisg.launchFrame();

	} // end main()

} // end class SelectUserGUI
