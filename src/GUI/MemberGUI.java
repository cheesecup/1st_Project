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
import DAO.*;
import DTO.*;

// 회원 정보 관리 gui(직원 이용)
public class MemberGUI {
	Frame frame;
	Panel[] panel;
	Label lblTitle, lblmCode, lblproCode, lblmName, lblmGender, lblmAge, lblmPhone, lblmLocker, lblmVaccine, lblmPro, lblStatus;
	TextField tfmCode, tfproCode, tfmName, tfmAge, tfmPhone, tfmLocker, tfmPro;
	Button btnSearch, btnSave, btnUpdate, btnDelete, btnBack, btnReset;
	List list;
	Checkbox chMale, chFemale, chYes, chNo;
	CheckboxGroup chGroup1, chGroup2;
	
	public MemberGUI() {
		frame = new Frame("헬스장 관리시스템 - 회원관리");
		panel = new Panel[21];
		for (int i=0; i<panel.length; i++) {
			panel[i]=new Panel();	
		}
		lblTitle = new Label ("헬스장 회원관리", Label.CENTER);
		Font font = new Font ("dialog", Font.BOLD, 30);
		lblTitle.setFont(font);
		lblTitle.setForeground(new Color(142,24,232));
		lblmCode = new Label("회원번호");
		lblproCode = new Label("프로그램코드");
		lblmName = new Label("이 름");
		lblmAge = new Label("나 이");
		lblmPhone = new Label("전화번호");
		lblmLocker = new Label("락커룸");
    	lblmGender = new Label("성 별");
    	lblmVaccine = new Label("백신여부");
    	lblmPro = new Label("프로그램명");
    	lblStatus = new Label("");
    	lblStatus.setBackground(Color.LIGHT_GRAY);
    	
    	tfmCode = new TextField("");
    	tfmName = new TextField("");
    	tfproCode = new TextField("");
    	tfmPhone = new TextField("");
    	tfmPro = new TextField("");
    	tfmLocker = new TextField("");
    	tfmAge = new TextField("");
    
    	btnSearch = new Button("검색");
    	btnSave = new Button("저장");
    	btnUpdate = new Button("수정");
    	btnDelete = new Button("삭제");
    	btnReset = new Button("초기화");
    	btnBack = new Button("뒤로가기");
    
    	chGroup1 = new CheckboxGroup();
    	chGroup2 = new CheckboxGroup();
    	chMale = new Checkbox("Male", chGroup1,false);
    	chFemale = new Checkbox("Female", chGroup1,false);
    	chYes = new Checkbox("Yes", chGroup2, false);
    	chNo = new Checkbox("No", chGroup2, false);
    	list = new List(11,false);
    	
    	btnBack.addActionListener(new ActionListener() { // 뒤로가기 버튼 화면 전환
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectInfoStaffGUI sisg = new SelectInfoStaffGUI();
                sisg.launchFrame();
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
		//이름 회원번호
		panel[1].setLayout(new GridLayout(2,1));
		panel[1].add(lblmName);
		panel[1].add(lblmCode);
		panel[2].setLayout(new GridLayout(2,1));
		panel[2].add(tfmName);
		panel[2].add(tfmCode);
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[1], "West");
		panel[3].add(panel[2], "Center");
		panel[3].add(new Label(""), "East");
		//프로그램명 프로그램코드
		panel[4].setLayout(new GridLayout(2,1));
		panel[4].add(lblmPro);
		panel[4].add(lblproCode);
		panel[5].setLayout(new GridLayout(2,1));
		panel[5].add(tfmPro);
		panel[5].add(tfproCode);
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[4], "West");
		panel[6].add(panel[5], "Center");
		panel[6].add(new Label(""), "East");
		//전화번호 나이
		panel[7].setLayout(new GridLayout(2,1));
		panel[7].add(lblmPhone);
		panel[7].add(lblmAge);
		panel[8].setLayout(new GridLayout(2,1));
		panel[8].add(tfmPhone);
		panel[8].add(tfmAge);
		panel[9].setLayout(new BorderLayout());
		panel[9].add(panel[7], "West");
		panel[9].add(panel[8], "Center");
		panel[9].add(new Label(""), "East");
		//락커룸 백신접종 성별
		panel[10].setLayout(new GridLayout(1,2));
		panel[10].add(lblmLocker);
		panel[10].add(tfmLocker);
		panel[11].setLayout(new GridLayout(2,1));
		panel[11].add(lblmVaccine);
		panel[11].add(lblmGender);
		panel[12].setLayout(new GridLayout(2,1));
		panel[12].add(chYes);
		panel[12].add(chMale);
		panel[13].setLayout(new GridLayout(2,1));
		panel[13].add(chNo);
		panel[13].add(chFemale);
		panel[14].add(panel[11]);
		panel[14].add(panel[12]);
		panel[14].add(panel[13]);
		panel[15].setLayout(new BorderLayout());
		panel[15].add(panel[10], "North");
		panel[15].add(panel[14], "Center");
		//3 6 9 15 종합
		panel[16].setLayout(new GridLayout(1,4));
		panel[16].add(panel[3]);
		panel[16].add(panel[6]);
		panel[16].add(panel[9]);
		panel[16].add(panel[15]);
		//타이틀
		panel[17].setLayout(new BorderLayout());
		panel[17].add(lblTitle, "North");
		panel[17].add(panel[16], "Center");
		panel[17].add(panel[0], "South");
		//열 이름
		panel[18].setLayout(new GridLayout(1,8));
		panel[18].setBackground(Color.black);
		panel[18].setForeground(Color.white);
		panel[18].add(new Label(" 회원번호 "));
		panel[18].add(new Label(" 프로그램코드  "));
		panel[18].add(new Label(" 이름 "));
		panel[18].add(new Label(" 성별 "));
		panel[18].add(new Label(" 나이 "));
		panel[18].add(new Label(" 전화번호 "));
		panel[18].add(new Label(" 프로그램명 "));
		panel[18].add(new Label(" 락커룸 "));
		panel[18].add(new Label(" 백신 "));
		panel[19].setLayout(new BorderLayout());
		panel[19].add(panel[18], "North");
		panel[19].add(list, "Center");
		//버튼
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
		
		panel[20].setLayout(new BorderLayout());
		panel[20].add(panel[17], "North");
		panel[20].add(panel[19], "Center");
		
		frame.add(panel[20], "Center");
		frame.add(lblStatus, "South");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - 
		frame.getWidth())/2 -100,
		((frame.getToolkit().getScreenSize()).height - frame.getHeight())/2 -100);
		frame.setSize(1200,700);
		frame.setVisible(true);
		displayAll();
		
		//회원정보 저장하기 (저장하기 버튼을 누르면 동작하기 이벤트)
				btnSave.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//저장버튼을 누르면 수행해줄 코드를 구현
						String mCode = tfmCode.getText();
						String proCode = tfproCode.getText(); //tfAge에 입력한 것은 모두 문자열 처리됨(int x, String o)
						String mName = tfmName.getText();
						String mGender = "여";
						if(chMale.getState()) {
							mGender = "남";
						}
						String mAge = tfmAge.getText();
						String mPhone = tfmPhone.getText();
						String mPro = tfmPro.getText();
						String mLocker = tfmLocker.getText();
						String mVaccine = "접종";
						if(chNo.getState()) {
							mVaccine = "미접종";
						}
						
						MemberDAO mdao = new MemberDAO();
						mdao.insert(mCode, proCode, mName, mGender, mAge , mPhone, mLocker, mVaccine, mPro ); // 삽입해줄 쿼리문이 있는 메소드로 호출
						displayAll();
					}
				});
				
				
				list.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						// 리스트의 항목들을 누르면 처리해줄 내용의 코드를 구현
						String str = list.getSelectedItem();//"유관순  17 50 150 여"
						StringTokenizer st = new StringTokenizer(str);
						tfmCode.setText(st.nextToken());
						tfproCode.setText(st.nextToken());
						tfmName.setText(st.nextToken());
						String mGender = st.nextToken();
						if(mGender.equals("남")) chMale.setState(true);
						else chFemale.setState(true);
						tfmAge.setText(st.nextToken());
						tfmPhone.setText(st.nextToken());
						tfmPro.setText(st.nextToken());
						tfmLocker.setText(st.nextToken());
						String mVaccine = st.nextToken();
						if(mVaccine.equals("미접종")) chNo.setState(true);
						else chYes.setState(true);
					}
				});
				
				//데이터 삭제하기
				btnDelete.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// 삭제하기
						String mName = tfmName.getText();
						MemberDAO mdao = new MemberDAO();
						mdao.delete(mName);
						displayAll();
					}
				});
				
				//수정하기
				btnUpdate.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String mCode = tfmCode.getText();
						String proCode = tfproCode.getText(); //tfAge에 입력한 것은 모두 문자열 처리됨(int x, String o)
						String mName = tfmName.getText();
						String mGender = "여";
						if(chMale.getState()) {
							mGender = "남";
						}
						String mAge = tfmAge.getText();
						String mPhone = tfmPhone.getText();
						String mPro = tfmPro.getText();
						String mLocker = tfmLocker.getText();
						String mVaccine = "접종";
						if(chNo.getState()) {
							mVaccine = "미접종";
						}						
						MemberDAO mdao = new MemberDAO();
						mdao.update(mCode, proCode, mName, mGender, mAge, mPhone, mPro, mLocker, mVaccine);
						displayAll();
					}
				});
				
				//검색하기 버튼 구현
				btnSearch.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String mName = tfmName.getText();
						MemberDAO mdao = new MemberDAO();
						display(mdao, mName);		
					}
				});
				
				//초기화 버튼 구현
				btnReset.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						tfmCode.setText("");
						tfproCode.setText("");
						tfmName.setText("");
						tfmAge.setText("");
						tfmPhone.setText("");
						tfmLocker.setText("");
						tfmPro.setText("");
						chGroup1.setSelectedCheckbox(null);
						chGroup2.setSelectedCheckbox(null);
						displayAll();
					}
					
				});
	     }//end launch()
				
				// 회원정보 보기
				private void displayAll() {
					list.removeAll(); // 화면에 있는 중간의 여러줄이 나오는 리스트의 내용을 지우라는 것
					MemberDAO mdao = new MemberDAO();
					ArrayList<MemberDTO> allDate = mdao.select();
					for(MemberDTO mdto : allDate) {
						int mCode = mdto.getmCode();
					    String proCode = mdto.getProCode();
						String mName = mdto.getmName();	
						String mGender = mdto.getmGender();
						int mAge = mdto.getmAge();
						String mPhone = mdto.getmPhone();
						String mPro = mdto.getmPro();
						String mLocker = mdto.getmLocker();
						String mVaccine = mdto.getmVaccine();
						list.add(mCode + "                                         " +proCode + "                          " + 
								mName + "                                       " + mGender + 
								"                                     " + mAge + "                               " + mPhone + 
								"                          " + mPro + "                                 " + mLocker + 
								"                                  "+ mVaccine ); //화면에 있는 리스트에 붙여라(add)
					}
					
				}
				
				private void display(MemberDAO mdao, String mName) {
					list.removeAll();
					ArrayList<MemberDTO> allData = mdao.search(mName);
					for(MemberDTO mdto : allData) {
					int mCode = mdto.getmCode();
						String proCode = mdto.getProCode();
						String mName1 = mdto.getmName();	
						String mGender = mdto.getmGender();
						int mAge = mdto.getmAge();
						String mPhone = mdto.getmPhone();
						String mPro = mdto.getmPro();
						String mLocker = mdto.getmLocker();
						String mVaccine = mdto.getmVaccine();
						list.add(mCode + "                                         " +proCode + "                          " + 
								mName1 + "                                       " + mGender + 
								"                                     " + mAge + "                               " + mPhone + 
								"                          " + mPro + "                                 " + mLocker + 
								"                                  "+ mVaccine ); //화면에 있는 리스트에 붙여라(add)
				}
				
		}

				
	public static void main(String[] args) {
		
		MemberGUI meb = new MemberGUI();
		meb.launchFrame();
	}

}




