package im.view.module.team;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.mysql.jdbc.Statement;

public class XiuGai extends JFrame{
	JButton AddContent;
	JLabel ID,name,sex,birthday,phone,ID_card,label,classM,major,jion_time,email;
	JTextField IDText,sexText,birthdayText,nameText,phoneText,Id_cardText,classText,majorText,jionText,emailText;
	public XiuGai(){
		 AddContent=new JButton("保存");
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
		 
		 AddContent.setHorizontalAlignment(SwingConstants.CENTER);
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
		 AddContent.setBounds(0, 320, 300, 50);
		 AddContent.setBorder(null);
		 AddContent.setBackground(Color.gray);
		 TESTDB tb=new TESTDB();
			ResultSet rs=tb.query("select * from student");
				try {
					rs.last();
					int i=rs.getRow();
					rs.beforeFirst();
					String[] xx=new String[i];
					int j=0;
					
					while(rs.next()){
						xx[j]=rs.getString("id");
						if(xx[j].equals(rs.getString(j)))
							IDText.setText(rs.getString("id"));
							nameText.setText(rs.getString("name"));
							sexText.setText(rs.getString("sex"));
							classText.setText(rs.getString("grade"));
							majorText.setText(rs.getString("major"));
							jionText.setText(rs.getString("join_time"));
							birthdayText.setText(rs.getString("birthday"));
							phoneText.setText(rs.getString("phone"));
							Id_cardText.setText(rs.getString("id_card"));
							emailText.setText(rs.getString("email"));
					}}catch (Exception e) {
						// TODO: handle exception
					}
				
		 AddContent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TESTDB tb=new TESTDB();
				Connection con=tb.getCon();
				Statement stmt;
				try {
					stmt = (Statement) con.createStatement();
					stmt.executeUpdate("update student set name='"+nameText.getText()+"'where id="+IDText.getText()+"");
					stmt.executeUpdate("update student set sex='"+sexText.getText()+"'where id="+IDText.getText()+"");
					stmt.executeUpdate("update student set grade='"+classText.getText()+"'where id="+IDText.getText()+"");
					stmt.executeUpdate("update student set major='"+majorText.getText()+"'where id="+IDText.getText()+"");
					stmt.executeUpdate("update student set join_time='"+jionText.getText()+"'where id="+IDText.getText()+"");
					stmt.executeUpdate("update student set birthday='"+birthdayText.getText()+"'where id="+IDText.getText()+"");
					stmt.executeUpdate("update student set phone='"+phoneText.getText()+"'where id="+IDText.getText()+"");
					stmt.executeUpdate("update student set id_card='"+Id_cardText.getText()+"'where id="+IDText.getText()+"");
					stmt.executeUpdate("update student set email='"+emailText.getText()+"'where id="+IDText.getText()+"");
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
		 
		this.setTitle("修改数据");
		this.setLocation(200, 100);
		this.setResizable(false);
		this.setSize(300,400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[]args){
		new XiuGai();
	}
}
