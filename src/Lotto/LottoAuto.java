package Lotto;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LottoAuto extends JFrame{
	
	JButton start;
	JTextArea resultTxt;
	JTextField gameNum;
	JLabel lotto;
	JPanel number_panel,result_panel;	
	JMenu lottoNum;
	JMenuItem item1,item2;
	
	public LottoAuto(){
		
		MyButtonHandler handler;
		
		setTitle("�ζǹ�ȣ �ڵ�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu();
		
		setLayout(new BorderLayout());
		
		lotto = new JLabel("Ƚ�� �Է� :");
		gameNum = new JTextField("5",5);
		start = new JButton("����");
		
		number_panel = new JPanel();
		number_panel.add(lotto);
		number_panel.add(gameNum);
		number_panel.add(start);
		
		result_panel = new JPanel();
		resultTxt = new JTextArea();
		result_panel.add(resultTxt);
		
		handler = new MyButtonHandler();
		start.addActionListener(handler);
		
		add(number_panel,BorderLayout.NORTH);		
		add(result_panel,BorderLayout.CENTER);
		
		number_panel.setVisible(false);
		
		setSize(700,700);
		setVisible(true);
	}
	
	void createMenu(){
		
		JMenuBar mb = new JMenuBar();
		lottoNum = new JMenu("�ζ�");
		item1 = new JMenuItem("��ȣ �ڵ� ����");
		item2 = new JMenuItem("��÷ Ȯ��");
		
		item1.addActionListener(new MenuActionListener());
		item2.setEnabled(false);
		item2.addActionListener(new MenuActionListener());
		
		lottoNum.add(item1);
		lottoNum.add(item2);
		
		mb.add(lottoNum);
		setJMenuBar(mb);
	}
	
	public static void main(String[] args) {
		new LottoAuto();
	}
	
	class MyButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			
			LottoNumber l = new LottoNumber();
			
			for(int x = 0; x < Integer.parseInt(gameNum.getText()); x++){
				
				l.createNumber();
				l.getBonus(l.getRandom());
				int bonus = l.getBouns();
				l.sortNumber();
				
				resultTxt.setText(resultTxt.getText()+(x+1)+"��° ��"+"------------------------------------------------\n");  
				
				for(int i = 0; i < 6; i++){
					resultTxt.setText(resultTxt.getText()+" "+l.getNumber(i)+"\t"); //6���� ���ڸ� ȭ�鿡 ������ �Ѵ�.
				}
				
				resultTxt.setText(resultTxt.getText()+"���ʽ� : "+bonus+"\n\n"); //���ʽ� ���ڸ� ȭ�鿡 ������ �Ѵ�.
			}
		}
		
	}
	
	class MenuActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("��ȣ �ڵ� ����")){
				number_panel.setVisible(true);
				item2.setEnabled(true);
			}else if(e.getActionCommand().equals("��÷ Ȯ��")){
				gameNum.setVisible(false);
				start.setVisible(false);
				
				lotto.setText("�̹��� ��÷��ȣ�� 10,22,27,31,42,43 ���ʽ� ��ȣ�� 12�Դϴ�.");
				Font tf = new Font("Curier", Font.PLAIN, 20);
				lotto.setFont(tf);
			}
		}
		
	}
	
	class LottoNumber{
		
		private int number;
		private int bonus;
		private boolean flag;
		int num[] = new int[6];
		
		public LottoNumber(){
			for(int i = 0; i < num.length; i++){
				num[i] = 0;
				bonus = 0;
			}
		}
		
		int getNumber(int i){
			return num[i];
		}
		
		int getBouns(){
			return bonus;
		}
		
		int getRandom(){
			Random r = new Random();
			int r_num = (int)(r.nextInt()*46);
			return r_num;
		}
		
		void sortNumber(){
			Arrays.sort(num);
		}
		
		void getBonus(int r_num){
			Random r = new Random();
			for(int i = 0; i<6; i++){
				
				flag = false;
				number = r.nextInt(46);
				if(number == 0){
					i--;
					continue;
				}
				for(int j =0; j < i; j++){
					if(num[j] == number) flag = true;
				}
				if(flag == true){
					i--;
					continue;
				}else{
					bonus = number;
				}
				
			}
		}
		
		void createNumber(){
			for(int i = 0; i < 6; i++){
				num[i] = (int)(Math.random()*100) % 45 + 1;
				for(int j = 0; j < i; j++){
					if(num[j] == num[i]){
						while(num[j] == num[i]){
							num[i] = (int)(Math.random()*100) % 45 + 1;
						}
					}
				}
			}
		}
	}
}



