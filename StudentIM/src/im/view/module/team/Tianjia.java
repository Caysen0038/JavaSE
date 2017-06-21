package im.view.module.team;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowStateListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

import com.mysql.jdbc.Statement;
public class Tianjia extends JFrame {
	JButton AddContent;
	JLabel ID,name,sex,birthday,phone,ID_card,label,classM,major,jion_time,email;
	JTextField IDText,sexText,birthdayText,nameText,phoneText,Id_cardText,classText,majorText,jionText,emailText;
	public Tianjia(){
		 AddContent=new JButton("添加");
		 ID=new JLabel("学号：");
		 name=new JLabel("姓名：");
		 classM=new JLabel("班级：");
		 major=new JLabel("专业：");
		 jion_time=new JLabel("入学时间：");
		 sex=new JLabel("性别：");
		 birthday=new JLabel("出生日期：");
		 phone=new JLabel("电话：");
		 ID_card=new JLabel("身份证号码：");
		 email=new JLabel("Email：");
		 
		 IDText=new JTextField();
		 nameText=new JTextField();
		 classText=new JTextField();
		 majorText=new JTextField();
		 jionText=new JTextField();
		 sexText=new JTextField();
		 birthdayText=new JTextField();
		 phoneText=new JTextField();
		 Id_cardText=new JTextField();
		 emailText=new JTextField();
		 
		 emailText=new JTextField();
		 IDText=new JTextField(10);
		 sexText=new JTextField(10);
		 birthdayText=new JTextField(10);
		 nameText=new JTextField(10);
		 phoneText=new JTextField(10);
		 birthdayText=new JTextField(10);
		 majorText=new JTextField();
		 Id_cardText=new JTextField();
		 jionText=new JTextField();
		 ID.setBounds(40, 10, 80, 20);
		 name.setBounds(40, 40, 80, 20);
		 classM.setBounds(40, 70, 80, 20);
		 major.setBounds(40, 100, 80, 20);
		 jion_time.setBounds(40, 130, 80, 20);
		 sex.setBounds(40, 160, 80, 20);
		 birthday.setBounds(40, 190, 80, 20);
		 phone.setBounds(40, 220, 80, 20);
		 ID_card.setBounds(40, 250, 80, 20);
		 email.setBounds(40, 280, 80, 20);
		 
		 IDText.setBounds(120, 10, 120, 20);
		 nameText.setBounds(120, 40, 120, 20);
		 classText.setBounds(120, 70, 120, 20);
		 majorText.setBounds(120, 100, 120, 20);
		 jionText.setBounds(120, 130, 120, 20);
		 sexText.setBounds(120, 160, 120, 20);
		 birthdayText.setBounds(120, 190, 120, 20);
		 phoneText.setBounds(120, 220, 120, 20);
		 Id_cardText.setBounds(120, 250, 120, 20);
		 emailText.setBounds(120, 280, 120, 20);
		 AddContent.setBounds(0, 320, 300, 51);
		 AddContent.setBorder(null);
		 AddContent.setBackground(Color.gray);
		 AddContent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TESTDB tb=new TESTDB();
				Connection con=tb.getCon();
				Statement stmt;
				try {
					stmt=(Statement) con.createStatement();
					stmt.executeUpdate("insert into student values('"+IDText.getText()+"','"+nameText.getText()+"','"+00000000+"','"+sexText.getText()+"','"+birthdayText.getText()+"','"+jionText.getText()+"','"+majorText.getText()+"','"+Id_cardText.getText()+"','"+phoneText.getText()+"','"+emailText.getText()+"','"+classText.getText()+"')");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "保存成功");
			}
		});	
		 this.setLayout(null);
		 this.add(ID);
		 this.add(name);
		 this.add(classM);
		 this.add(major);
		 this.add(jion_time);
		 this.add(sex);
		 this.add(birthday);
		 this.add(phone);
		 this.add(ID_card);
		 this.add(email);
		 this.add(IDText);
		 this.add(nameText);
		 this.add(classText);
		 this.add(majorText);
		 this.add(jionText);
		 this.add(sexText);
		 this.add(birthdayText);
		 this.add(phoneText);
		 this.add(Id_cardText);
		 this.add(emailText);
		 this.add(AddContent);

		this.setTitle("添加数据");
		this.setLocation(200, 100);
		this.setSize(300,400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tianjia();
	}

}
