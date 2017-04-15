package form;
import javax.swing.*;
import java.awt.*;
import event.*;
import java.awt.event.*;
import calculate.*;
public class Form{
	private JFrame main=new JFrame("B-1计算器         version1.1");
	private JTextField text=new JTextField(20);
	public Form(){
		setElement();
		setEvent();
		main.setVisible(true);
	}
	public void setElement(){
		JPanel numKey=getNumKey();
		JPanel signKey=getSignKey();
		JPanel cleanKey=getCleanKey();
		JPanel bracketKey=getBracket();
		cleanKey.setBounds(10, 70, 220,50 );
		bracketKey.setBounds(240,70,130,50);
		numKey.setBounds(10,130,220,300);
		signKey.setBounds(240,130,130,300);
		text.setEditable(false);
		text.setBackground(Color.cyan);
		main.setSize(450,500);
		main.setBackground(Color.gray);
		main.setLocation(600, 300);
		main.setLayout(null);
		Font font=new Font("",Font.BOLD,26);
		text.setBounds(7, 5,main.getWidth()-30,50);
		text.setFont(font);
		main.add(cleanKey);
		main.add(bracketKey);
		main.add(numKey);
		main.add(signKey);
		main.add(text);
	}
	public void setEvent(){
		main.addWindowListener(new FormEvent());
		text.addKeyListener(new KeyEvent());
	}
	public JPanel getNumKey(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(4,3,4,4));
		JButton[] num=new JButton[10];
		for(int i=0;i<num.length;i++){
			num[i]=new JButton(String.valueOf(i));
			num[i].setFont(Style.keyStyle());
		}
		JButton dot=new JButton(".");
		JButton dZero=new JButton("00");
		for(JButton temp:num){
			temp.addActionListener(new ButtonEvent());
		}
		dot.addActionListener(new ButtonEvent());
		dot.setFont(Style.keyStyle());
		dZero.addActionListener(new ButtonEvent());
		dZero.setFont(Style.keyStyle());
		for(int i=7,j=0;;i++,j++){
			panel.add(num[i]);
			if(i==3){
				break;
			}
			if(j==2){
				i=i-6;
				j=-1;
			}
		}
		panel.add(dot);
		panel.add(num[0]);
		panel.add(dZero);
		return panel;
	}
	public JPanel getSignKey(){
		JPanel panelLeft=new JPanel();
		JPanel panelRight=new JPanel();
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2,4,4));
		panelLeft.setLayout(new GridLayout(4,1,4,4));
		panelRight.setLayout(new GridLayout(2,1,4,4));
		JButton equal=new JButton("=");
		JButton add=new JButton("+");
		JButton sub=new JButton("-");
		JButton mul=new JButton("*");
		JButton div=new JButton("/");
		equal.addActionListener(new ButtonEvent());
		add.addActionListener(new ButtonEvent());
		sub.addActionListener(new ButtonEvent());
		mul.addActionListener(new ButtonEvent());
		div.addActionListener(new ButtonEvent());
		equal.setFont(Style.keyStyle());
		add.setFont(Style.keyStyle());
		sub.setFont(Style.keyStyle());
		mul.setFont(Style.keyStyle());
		div.setFont(Style.keyStyle());
		panelLeft.add(add);
		panelLeft.add(sub);
		panelLeft.add(mul);
		panelLeft.add(div);
		panelRight.add(new JLabel());
		panelRight.add(equal);
		panel.add(panelLeft);
		panel.add(panelRight);
		return panel;
	}
	public JPanel getCleanKey(){
		JPanel panel=new JPanel();
		JButton clean=new JButton("c");
		JButton back=new JButton("←");
		clean.addActionListener(new ButtonEvent());
		back.addActionListener(new ButtonEvent());
		clean.setFont(Style.keyStyle());
		back.setFont(Style.keyStyle());
		panel.setLayout(new GridLayout(1,2,4,4));
		panel.add(clean);
		panel.add(back);
		return panel;
	}
	public JPanel getBracket(){
		JPanel panel=new JPanel();
		JButton left=new JButton("(");
		JButton right=new JButton(")");
		left.addActionListener(new ButtonEvent());
		right.addActionListener(new ButtonEvent());
		left.setFont(Style.keyStyle());
		right.setFont(Style.keyStyle());
		panel.setLayout(new GridLayout(1,2,4,4));
		panel.add(left);
		panel.add(right);
		return panel;
	}
	public void setTextValue(String temp){
		this.text.setText(temp);
	}
	private class ButtonEvent implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if("c".equals(e.getActionCommand())){
				setTextValue("");
			}else if("=".equals(e.getActionCommand())){
				setTextValue(new Calculate().countData(text.getText().toCharArray()).toString());
			}else if("←".equals(e.getActionCommand())){
				String temp=text.getText();
				if(temp.length()>0){
					temp =temp.substring(0, temp.length()-1);
					setTextValue(temp);
				}
			}else{
				String temp=text.getText();
				temp=temp+e.getActionCommand();
				setTextValue(temp);
			}
		}
	}
	private class KeyEvent extends KeyAdapter{
		private boolean flag=false;
		public void keyPressed(java.awt.event.KeyEvent e) {
			if(e.getKeyCode()>=16&&e.getKeyCode()<=18){
				flag=true;	//验证是否按的组合键
			}else if(!flag){	//非组合键的操作
				if(127==e.getKeyCode()){	//delete清空
					setTextValue("");
				}else if(10==e.getKeyCode()||13==e.getKeyCode()){	//enter计算
					setTextValue(new Calculate().countData(text.getText().toCharArray()).toString());
				}else if(8==e.getKeyCode()){
					String temp=text.getText();
					if(temp.length()>0){		//删除退格
						temp =temp.substring(0, temp.length()-1);
						setTextValue(temp);
					}
				}else if(checkNum(e.getKeyCode())){		//输入有效数值
					String temp=text.getText();
					temp=temp+e.getKeyChar();
					setTextValue(temp);
				}
			}else if(flag){		//组合键的操作	shift/ctrl/alt
				if(e.getKeyCode()==61){		
					String temp=text.getText();
					temp=temp+"+";
					setTextValue(temp);
				}else if(e.getKeyCode()==57){
					String temp=text.getText();
					temp=temp+"(";
					setTextValue(temp);
				}else if(e.getKeyCode()==48){
					String temp=text.getText();
					temp=temp+")";
					setTextValue(temp);
				}else if(e.getKeyCode()==56){
					String temp=text.getText();
					temp=temp+"*";
					setTextValue(temp);
				}
			}
			//System.out.println(e.getKeyCode());
		}
		public void keyReleased(java.awt.event.KeyEvent e){	//当组合键松开时标志位false;
			if(e.getKeyCode()>=16&&e.getKeyCode()<=18){
				flag=false;
			}
		}
		private boolean checkNum(int n){	//判断事件按键是否为有效数值
			if((n>=96&&n<=107)||(n>=109&&n<=111)||(n>=45&&n<=57)||(n>=40&&n<=43)){
				return true;
			}else{
				return false;
			}
		}
	}
}	

