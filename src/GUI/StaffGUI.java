package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
//import fit_exception.RecordNotFoundException;
import DTO.*;
import DAO.*;

// 직원 관리 gui
public class StaffGUI {

	Frame frame;
	Panel[] panel;
	Label lbTitle, lbCode, lbName, lbGender, lbAge, lbPhone, lbProgram, lbStatus;
	TextField tCode, tName, tGender, tAge, tPhone, tProgram;
	Button bSearch, bSave, bUpdate, bDelete, bBack, bReset;
	List list;
	Checkbox chMale, chFemale;
	CheckboxGroup chGroup;
	
	
	//FGUI 생성자
	
	public StaffGUI() {
		frame = new Frame("헬스장 관리시스템 - 직원 관리");
		panel = new Panel[20];
		for(int i=0; i<panel.length; i++) {
			panel[i]= new Panel(); 
		}
		lbTitle = new Label("직원 관리", Label.CENTER);
		Font font = new Font("dialog", Font.BOLD, 20);
		lbTitle.setFont(font);
		lbTitle.setForeground(new Color(142,24,232));
	
		lbCode = new Label("직원코드");
		lbName = new Label("이름");
		lbGender = new Label("성별");
		lbAge = new Label("나이");
		lbPhone = new Label("전화번호");
		lbProgram = new Label("담당프로그램");
		
		lbStatus = new Label("");
		lbStatus.setBackground(Color.LIGHT_GRAY);
		
		tCode = new TextField(10);
		tName = new TextField(10);
		tAge = new TextField(5);
		tPhone = new TextField(15);
		tProgram = new TextField(15);
		
		bSearch = new Button("검색");
		bSave = new Button("저장");
		bUpdate = new Button("수정");
		bDelete = new Button("삭제");
		bReset = new Button("초기화");
		bBack = new Button("뒤로가기");
		
		chGroup = new CheckboxGroup();
		chMale = new Checkbox("남", chGroup, false);
		chFemale = new Checkbox("여", chGroup, false);
		
		list = new List(20, false);
		
		bBack.addActionListener(new ActionListener() { // 뒤로가기 버튼 화면 전환
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectInfoMasterGUI simg = new SelectInfoMasterGUI();
                simg.launchFrame();
                frame.setVisible(false);
            }
        });
		
		
		//프레임 종료버튼 활성화
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	public void launchFrame() {
		
		
		panel[1].setLayout(new GridLayout(2,1));
		panel[1].add(lbName);
		panel[1].add(lbCode);
		
		panel[2].setLayout(new GridLayout(2,1));
		panel[2].add(tName);
		panel[2].add(tCode);
		
		panel[3].setLayout(new GridLayout(2,1));
		panel[3].add(lbPhone);
		panel[3].add(lbProgram);
		
		panel[4].setLayout(new GridLayout(2,1));
		panel[4].add(tPhone);
		panel[4].add(tProgram);
		
		panel[5].setLayout(new GridLayout(2,1));
		panel[5].add(lbAge);
		panel[5].add(lbGender);
		
		panel[6].setLayout(new BorderLayout());
		panel[6].add(tAge, "North");
		panel[6].add(chMale, "West");
		panel[6].add(chFemale, "Center");
		
		panel[7].setLayout(new BorderLayout());
		panel[7].add(panel[1], "West");
		panel[7].add(panel[2], "Center");
		panel[7].add(new Label(""), "East");
		
		panel[8].setLayout(new BorderLayout());
		panel[8].add(panel[3], "West");
		panel[8].add(panel[4], "Center");
		panel[8].add(new Label(""), "East");
		
		panel[9].setLayout(new BorderLayout());
		panel[9].add(panel[5], "West");
		panel[9].add(panel[6], "Center");
		panel[9].add(new Label(""), "East");
		
		// 라벨 + 텍스트필드 패널
		panel[10].setLayout(new BorderLayout());
		panel[10].add(panel[7], "West");
		panel[10].add(panel[8], "Center");
		panel[10].add(panel[9], "East");
		
		panel[11].add(bSearch);
		panel[11].add(new Label(" "));
		panel[11].add(bSave);
		panel[11].add(new Label(" "));
		panel[11].add(bUpdate);
		panel[11].add(new Label(" "));
		panel[11].add(bDelete);
		panel[11].add(new Label(" "));
		panel[11].add(bReset);
		panel[11].add(new Label(" "));
		panel[11].add(bBack);
		
		panel[16].setLayout(new BorderLayout());
		panel[16].add(lbTitle, "North");
		panel[16].add(panel[10], "Center");
		panel[16].add(panel[11], "South");
		
		panel[12].setLayout(new GridLayout(1,6));
		panel[12].setBackground(Color.black);
		panel[12].setForeground(Color.white);
		panel[12].add(new Label("직원코드"));
		panel[12].add(new Label("이름"));
		panel[12].add(new Label("성별"));
		panel[12].add(new Label("나이"));
		panel[12].add(new Label("전화번호"));
		panel[12].add(new Label("담당프로그램"));
		
		panel[13].setLayout(new BorderLayout());
		panel[13].add(panel[12], "North");
		panel[13].add(list, "Center");
		
		panel[14].setLayout(new BorderLayout());
		panel[14].add(panel[16], "North");
		panel[14].add(panel[13], "Center");
		
		frame.add(panel[14]);
		frame.add(lbStatus, "South");
		
		frame.pack();
		frame.setResizable(false);
		
		//윈도우에서의 화면의 위치
		frame.setLocation(((frame.getToolkit().getScreenSize()).width -
				frame.getWidth())/2,
				((frame.getToolkit().getScreenSize()).height - 
						frame.getHeight())/2);
		
		frame.setSize(600,450);
		frame.setVisible(true);
		
		displayAll();
		
	
		//회원정보 저장하기. 저장하기 버튼을 누르면 동작하게 만들기
		bSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String sCode = tCode.getText();
				String sName = tName.getText();
				int sAge = Integer.parseInt(tAge.getText());  //String? Int?
				String sPhone = tPhone.getText();
				String sProgram = tProgram.getText();
				
				String sGender = "여";
				if (chMale.getState()) {
					sGender = "남";
				}
				
				StaffDAO fa = new StaffDAO();
				fa.insert(sCode, sName, sGender, sAge, sPhone, sProgram);
				
				displayAll();

			}
		});
		
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

				String str = list.getSelectedItem(); //"유관순 17..."
				StringTokenizer st = new StringTokenizer(str);
				
				tCode.setText(st.nextToken());
				tName.setText(st.nextToken());
				String gender = st.nextToken();
				
				if (gender.equals("남")) {					
					chMale.setState(true);					
				} else { chFemale.setState(true);

				}
				tAge.setText(st.nextToken());
				tPhone.setText(st.nextToken());
				tProgram.setText(st.nextToken());
				
			}
		});
		
		
		//회원정보 데이터 삭제하기
		
		bDelete.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				String sCode = tCode.getText();	
				StaffDAO fa = new StaffDAO();						
				fa.delete(sCode);
				displayAll();			
			}
		});
				
		bUpdate.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				String sCode = tCode.getText();
				String sName = tName.getText();
				String sGender = "여";		
				if (chMale.getState()) {	
					sGender = "남";
				}
				int sAge = Integer.parseInt(tAge.getText());
				String sPhone = tPhone.getText();
				String sProgram = tProgram.getText();	
				
				
						
				StaffDAO fa = new StaffDAO();		
				fa.update(sCode, sName, sGender, sAge, sPhone, sProgram);		
				displayAll();						
			}
		});
				
				
		//검색하기 버튼 구현
		bSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String sName = tName.getText();

				
				StaffDAO fa = new StaffDAO();				
				display(fa, sName);
				
			}
		});
		
		//초기화 버튼 구현
		bReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tCode.setText("");
				tName.setText("");
				tAge.setText("");
				tPhone.setText("");
				tProgram.setText("");
				chGroup.setSelectedCheckbox(null);
				displayAll();
			}
			
		});
		
	}//end launch
	
		//회원정보 보기
		
		private void displayAll() {

		list.removeAll();  //화면에 있는 중간의 여러줄이 나오는 리스트의 내용을 지우는 것
		StaffDAO fa = new StaffDAO();
		ArrayList<StaffDTO> allData = fa.select();
		for(StaffDTO ft : allData) {
			
			String code = ft.getsCode();
			String name = ft.getsName();
			String gender = ft.getsGender();
			int age = ft.getsAge();
			String phone = ft.getsPhone();
			String program = ft.getsProgram();
			
			list.add(code+"          "+
					name+"                        "+
					gender+"                             "+
					age+"                      "+
					phone+"            "+
					program	);
			
		}
	} 
	
		private void display(StaffDAO fa, String sName) {
			
			list.removeAll();
			ArrayList<StaffDTO> allData = fa.search(sName);
		
			for(StaffDTO ft : allData) {				
				String sCode = ft.getsCode();
				String sName1 = ft.getsName();
				String sGender = ft.getsGender();
				int sAge = ft.getsAge();
				String sPhone = ft.getsPhone();
				String sProgram = ft.getsProgram();
			
				list.add(sCode+"          "+
						sName1+"                        "+
						sGender+"                             "+
						sAge+"                      "+
						sPhone+"            "+
						sProgram	);
			}
		}
		
		public static void main(String[] args) {
			StaffGUI sg = new StaffGUI();
			sg.launchFrame();
			
	}

}



