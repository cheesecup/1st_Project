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

//프로그램 정보 관리 gui(관리자 이용)
public class ProgramMasterGUI {
	// 화면 만드는 클래스 들
	Frame frame;
	Panel[] panel;
	Label lblTitle, lblproName, lblproCode, lblsCode, lblproTime, lblproDay, lblproMember, lblStatus;
	TextField tfproName, tfproCode, tfsCode, tfproTime, tfproDay, tfproMember;
	Button btnSave, btnDelete, btnUpdate, btnSearch, btnBack, btnReset;
	List list;
	
	
	public ProgramMasterGUI() {
		frame = new Frame("헬스장 관리시스템 - 프로그램 관리");
		panel = new Panel[15];
		for(int i=0;i<panel.length;i++) {
			panel[i] = new Panel();
		}
		lblTitle = new Label("프로그램 관리", Label.CENTER);
		Font font = new Font("dialog", Font.BOLD, 20);
		lblTitle.setFont(font);
		lblTitle.setForeground(new Color(142,24,232));
		lblproName = new Label("프로그램명");
		lblproCode = new Label("프로그램코드");
		lblsCode = new Label("직원코드");
		lblproTime = new Label("시간");
		lblproDay = new Label("요일");
		lblproMember = new Label("인원수");
		lblStatus = new Label("");
		lblStatus.setBackground(Color.LIGHT_GRAY);
		tfproName = new TextField("");
		tfproCode = new TextField("");
		tfsCode = new TextField("");
		tfproTime = new TextField("");
		tfproDay = new TextField("");
		tfproMember = new TextField("");
		btnSave = new Button("저장");
		btnSearch = new Button("검색");
		btnDelete = new Button("삭제");
		btnUpdate = new Button("수정");
		btnReset = new Button("초기화");
		btnBack = new Button("뒤로가기");
		list = new List(15,false);
		
		btnBack.addActionListener(new ActionListener() { // 뒤로가기 버튼 화면 전환
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectInfoMasterGUI simg = new SelectInfoMasterGUI();
                simg.launchFrame();
                frame.setVisible(false);
            }
        });
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public void launchFrame() {
		// 화면설계하기
		panel[1].setLayout(new GridLayout(2,1));
		panel[1].add(lblproName);
		panel[1].add(lblproCode);
		panel[2].setLayout(new GridLayout(2,1));
		panel[2].add(tfproName);
		panel[2].add(tfproCode);
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[1], "West");
		panel[3].add(panel[2], "Center");
		panel[3].add(new Label(""), "East");
		panel[4].setLayout(new GridLayout(2,1));
		panel[4].add(lblsCode);
		panel[4].add(lblproTime);
		panel[5].setLayout(new GridLayout(2,1));
		panel[5].add(tfsCode);
		panel[5].add(tfproTime);
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[4], "West");
		panel[6].add(panel[5], "Center");
		panel[6].add(new Label(""), "East");
		panel[7].setLayout(new GridLayout(2,1));
		panel[7].add(lblproDay);
		panel[7].add(lblproMember);
		panel[8].setLayout(new GridLayout(2,1));
		panel[8].add(tfproDay);
		panel[8].add(tfproMember);
		panel[9].setLayout(new BorderLayout());
		panel[9].add(panel[7], "West");
		panel[9].add(panel[8], "Center");
		panel[9].add(new Label(""), "East");
		panel[10].setLayout(new GridLayout(1,3));
		panel[10].add(panel[3]);
		panel[10].add(panel[6]);
		panel[10].add(panel[9]);
		panel[11].setLayout(new BorderLayout());
		panel[11].add(lblTitle, "North");
		panel[11].add(panel[10], "Center");
		panel[11].add(panel[0], "South");
		
		
		panel[12].setLayout(new GridLayout(1,6));
		panel[12].setBackground(Color.black);
		panel[12].setForeground(Color.white);
		panel[12].add(new Label("프로그램명 "));
		panel[12].add(new Label("프로그램코드 "));
		panel[12].add(new Label("직원코드 "));
		panel[12].add(new Label("시 간 "));
		panel[12].add(new Label("요 일 "));
		panel[12].add(new Label("인원수 "));
		
		panel[13].setLayout(new BorderLayout());
		panel[13].add(panel[12], "North");
		panel[13].add(list, "Center");
		panel[0].add(btnSave);
		panel[0].add(new Label(" "));
		panel[0].add(btnSearch);
		panel[0].add(new Label(" "));
		panel[0].add(btnDelete);
		panel[0].add(new Label(" "));
		panel[0].add(btnUpdate);
		panel[0].add(new Label(" "));
		panel[0].add(btnReset);
		panel[0].add(new Label(" "));
		panel[0].add(btnBack);
		
		panel[14].setLayout(new BorderLayout());
		panel[14].add(panel[11], "North");
		panel[14].add(panel[13], "Center");
		frame.add(panel[14], "Center");
		frame.add(lblStatus, "South");
		frame.pack();
		frame.setResizable(false);
		//윈도우에서 화면의 위치
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth())/2,
				((frame.getToolkit().getScreenSize()).height - frame.getHeight())/2);
		//화면의 크기
		frame.setSize(600,450);
		frame.setVisible(true);

		displayAll();
		
		//회원정보 저장하기. 저장하기 버튼을 누르면 동작하게 만들기
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//저장버튼을 누르면 수행해줄 코드를 구현 //tfAge에 입력된 것은 모두 문자열 처리됨 숫자도.
				String proName = tfproName.getText();
				String proCode = tfproCode.getText();
				String sCode = tfsCode.getText();
				String proTime = tfproTime.getText();
				String proDay = tfproDay.getText();
				int proMember = Integer.parseInt(tfproMember.getText());

				ProgramDAO dao = new ProgramDAO();
				dao.insert(proName, proCode, sCode, proTime, proDay, proMember);//삽입해줄 쿼리문이 있는 메소드로 호출
				displayAll();
				
			}
		});
		
		
	
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// 리스트의 항목들을 누르면 처리해줄 내용의 코드를 구현
				String str = list.getSelectedItem();//"유관순 17 .."
				StringTokenizer st = new StringTokenizer(str);
				tfproName.setText( st.nextToken() );
				tfproCode.setText( st.nextToken() );
				tfsCode.setText( st.nextToken() );
				tfproTime.setText( st.nextToken() );
				tfproDay.setText( st.nextToken() );
				tfproMember.setText( st.nextToken() );
				
				
			}
		});
		
		//데이터 삭제하기
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String proCode = tfproCode.getText();
				ProgramDAO dao = new ProgramDAO();
				dao.delete(proCode); 
				displayAll();
	
			}
		});
		
		//데이터 수정하기
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//수정하기
				String proName = tfproName.getText();
				String proCode = tfproCode.getText();
				String sCode = tfsCode.getText();
				String proTime = tfproTime.getText();
				String proDay = tfproDay.getText();
				int proMember = Integer.parseInt(tfproMember.getText());
				
				ProgramDAO dao = new ProgramDAO();
				dao.update(proName, proCode, sCode, proTime, proDay, proMember);
				displayAll();

			}
		});
		
		//검색하기 버튼 구현
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String proName = tfproName.getText();
				ProgramDAO dao = new ProgramDAO();
				display(dao, proName);
				
			}
		});
		
		//초기화 버튼 구현
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tfproName.setText("");
				tfproCode.setText("");
				tfsCode.setText("");
				tfproTime.setText("");
				tfproDay.setText("");
				tfproMember.setText("");
				displayAll();
			}
			
		});
		
		
		
	}//end launch()

	private void displayAll() {
		//회원 정보보기
		list.removeAll();// 화면에 있는 중간의 여러줄이 나오는 리스트의 내용을 지우는 것
		ProgramDAO dao = new ProgramDAO();
		ArrayList<ProgramDTO> allData = dao.select();
		for(ProgramDTO dto : allData) {
			String proName = dto.getProName();
			String sCode = dto.getsCode();
			String proCode = dto.getProCode();
			String proTime = dto.getProTime();
			char proDay = dto.getProDay();
			int proMember = dto.getProMember();
			list.add(proName + "                     " + proCode + "               " + sCode + "                   " + proTime + "                          " + proDay + "                              " + proMember);//화면에 있는 리스트에 붙임(add)
		}
		
		
	}
	
	private void display(ProgramDAO dao, String proName) {
		list.removeAll();
		ArrayList<ProgramDTO> allData = dao.search(proName);
		for(ProgramDTO dto : allData) {
			String proName1 = dto.getProName();
			String sCode = dto.getsCode();
			String proCode = dto.getProCode();
			String proTime = dto.getProTime();
			char proDay = dto.getProDay();
			int proMember = dto.getProMember();
			list.add(proName1 + "                     " + proCode + "               " + sCode + "                   " + proTime + "                          " + proDay + "                              " + proMember);//화면에 있는 리스트에 붙임(add)
		}
	}

	public static void main(String[] args) {
		
		ProgramMasterGUI pro = new ProgramMasterGUI();
		pro.launchFrame();
		
		
	}

	

}