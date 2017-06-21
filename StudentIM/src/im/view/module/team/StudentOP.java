package im.view.module.team;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import im.view.module.Module;

public class StudentOP extends Module{
		JMenuBar menubar;
		JMenu menuStudent,menuBianji,menuHelp,menuExit;
		JMenuItem menuchaxun,close,xiugai,shanchu,tianjia;
		JButton chaxun,delete,revise,addButton;
		JPanel content,imagePanel,tablePanel,photopanel;
		ImageIcon background;
		JLabel PersonDocument,PersonMark,ID,name,sex,birthday,phone,ID_card,label,classM,major,jion_time,email,photo;
		JTextField perDoctText,perMackText,IDText,sexText,birthdayText,nameText,phoneText,Id_cardText,chaxunText,classText,majorText,jionText,emailText;
		JTable scoreTable;
		JScrollPane jsp;
		Vector vc;
		public StudentOP(){
			this.setBackground(Color.WHITE);
			xiugai=new JMenuItem("修改");
			shanchu=new JMenuItem("删除");
			tianjia=new JMenuItem("添加");
			close=new JMenuItem("关闭");
			menuchaxun=new JMenuItem("查询");
			chaxunText=new JTextField("请输入你要查询的学号",10);
			chaxunText.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if(chaxunText.getText().equals("请输入你要查询的学号")){
						chaxunText.setText("");
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(chaxunText.getText().equals("")||chaxunText.getText().length()==0){
						chaxunText.setText("请输入你要查询的学号");
					}
				}
			});
			content=new JPanel();
			chaxun=new JButton("查询");
			delete=new JButton("删除");
			revise=new JButton("修改");
			addButton=new JButton("添加");
			photopanel=new JPanel();
			tablePanel=new JPanel();
			tablePanel.setOpaque(false);
			menubar=new JMenuBar();
			menubar.setBackground(Color.blue);
			menubar.setBorder(null);
			 menuStudent=new JMenu("档案(C)");
			 menuBianji=new JMenu("编辑(D)");
			 menuHelp=new JMenu("帮助(H)");
			 menuExit=new JMenu("退出(E)");
			 PersonDocument=new JLabel("个人档案：");
			 PersonMark=new JLabel("学生成绩：");
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
			 photo=new JLabel("照片：");
			 emailText=new JTextField();
			 perDoctText=new JTextField(10);
			 perMackText=new JTextField(10);
			 IDText=new JTextField(10);
			 sexText=new JTextField(10);
			 birthdayText=new JTextField(10);
			 nameText=new JTextField(10);
			 phoneText=new JTextField(10);
			 birthdayText=new JTextField(10);
			 classText=new JTextField();
			 majorText=new JTextField();
			 Id_cardText=new JTextField();
			 jionText=new JTextField();
			 background=new ImageIcon("src/images/img.jpg");
			 label=new JLabel(background);
			 label.setBounds(0, 0,background.getIconWidth(), background.getIconHeight());
			 this.add(label,new Integer(Integer.MIN_VALUE));
			 this.setLayout(null);
			 content.setLayout(null);
			 content.setOpaque(false);
			 
			 chaxunText.setBounds(350, 250, 210, 40);
			 chaxunText.setBackground(Color.GRAY);
			 content.setBounds(0, 0, 1000, 600);
			 chaxun.setBackground(Color.blue);
			 chaxun.setBounds(560, 250, 60, 40);
			 delete.setBounds(570, 90, 60, 30);
			 delete.setBackground(Color.blue);
			 revise.setBounds(500, 90, 60, 30);
			 revise.setBackground(Color.blue);
			 addButton.setBounds(640, 90, 60, 30);
			 addButton.setBackground(Color.BLUE);
			 addButton.setVisible(false);
			 revise.setVisible(false);
			 delete.setVisible(false);
			 menuBianji.setVisible(false);
			 content.setVisible(false);
			 
			 PersonDocument.setBounds(40, 30, 100, 20);
			 ID.setBounds(60, 90, 50, 20);
			 sex.setBounds(60, 140, 50, 20);
			 classM.setBounds(60, 190, 50, 20);
			 major.setBounds(60, 230, 50, 20);
			 jion_time.setBounds(60, 280, 70, 20);
			 birthday.setBounds(60, 330, 70, 20);
			 phone.setBounds(60, 380, 50, 20);
			 ID_card.setBounds(60, 430, 90, 20);
			 name.setBounds(240, 230, 50, 20);
			 email.setBounds(240, 270, 50, 20);
			 photo.setBounds(240, 90, 50, 20);
			 PersonMark.setBounds(500, 180,100 , 20);
			 
			 IDText.setBounds(110, 90, 80, 20);
			 sexText.setBounds(110, 140, 80, 20);
			 classText.setBounds(110, 190, 80, 20);
			 majorText.setBounds(110, 230, 80, 20);		 
			 jionText.setBounds(130, 280, 80, 20);	 
			 birthdayText.setBounds(130, 330, 80, 20);	 
			 phoneText.setBounds(110, 380, 80, 20);
			 Id_cardText.setBounds(150, 430, 118, 20);
			 nameText.setBounds(300, 230, 110, 20);
			 emailText.setBounds(300, 270,130, 20);
			 tablePanel.setBounds(498, 200, 450, 148);
			 photopanel.setBounds(280, 90, 80, 100);
			 
			 setBoder_false();
			 setBianji_fales();
			 setTouMing_false();
			 
			 menuStudent.add(menuchaxun);
			 menuStudent.add(close);
			 menubar.add(menuStudent);
			 menubar.add(menuHelp);
			 menubar.add(menuExit);
			 menubar.add(menuBianji);
			 menuBianji.add(xiugai);
			 menuBianji.add(shanchu);
			 menuBianji.add(tianjia);
			 menuchaxun.setMnemonic('F');
			 close.setMnemonic('W');
			 xiugai.setMnemonic('X');
			 shanchu.setMnemonic('D');
			 tianjia.setMnemonic('A');
			 menuchaxun.setAccelerator(KeyStroke.getKeyStroke('F',Event.CTRL_MASK));
			 close.setAccelerator(KeyStroke.getKeyStroke('W',Event.CTRL_MASK));
			 xiugai.setAccelerator(KeyStroke.getKeyStroke('X',Event.CTRL_MASK));
			 shanchu.setAccelerator(KeyStroke.getKeyStroke('D',Event.CTRL_MASK));
			 tianjia.setAccelerator(KeyStroke.getKeyStroke('A',Event.CTRL_MASK));
			 menuchaxun.addActionListener(new menuAction());
			 menuExit.addActionListener(new menuAction());
			 tianjia.addActionListener(new menuAction());
			 xiugai.addActionListener(new menuAction());
			 shanchu.addActionListener(new menuAction());
			 
			 menuStudent.setMnemonic('C');
			 menuBianji.setMnemonic('D');
			 menuHelp.setMnemonic('H');
			 menuExit.setMnemonic('E');
			 chaxun.addActionListener(new ActionPerson());
			 delete.addActionListener(new ActionPerson());
			 revise.addActionListener(new ActionPerson());
			 addButton.addActionListener(new ActionPerson());
			 close.addActionListener(new menuAction());
			
			 this.add(chaxunText);
			 this.add(chaxun);
			 this.add(delete);
			 this.add(revise);
			 this.add(addButton);
			 content.add(photo);
			 content.add(photopanel);
			 content.add(PersonMark);
			 content.add(jion_time);
			 content.add(major);
			 content.add(classM);
			 content.add(majorText);
			 content.add(classText);
			 content.add(jionText);
			 content.add(tablePanel);
			 content.add(PersonDocument);
			 content.add(ID);
			 content.add(name);
			 content.add(sex);
			 content.add(birthday);
			 content.add(phone);
			 content.add(ID_card);
			 content.add(IDText);
			 content.add(email);
			 content.add(nameText);
			 content.add(emailText);
			 content.add(sexText);
			 content.add(birthdayText);
			 content.add(phoneText);
			 content.add(Id_cardText);
			 
			this.add(content);
			this.setSize(1000, 600);
			this.setLocation(200, 70);
			this.setVisible(true);	
		}

	public void setTouMing_false(){
		 IDText.setOpaque(false);
		 nameText.setOpaque(false);
		 emailText.setOpaque(false);
		 sexText.setOpaque(false);
		 classText.setOpaque(false);
		 majorText.setOpaque(false);
		 jionText.setOpaque(false);
		 birthdayText.setOpaque(false);
		 phoneText.setOpaque(false);
		 Id_cardText.setOpaque(false);
	}
	public void setBianji_true(){
		 IDText.setEditable(true);
		 nameText.setEditable(true);
		 sexText.setEditable(true);
		 classText.setEditable(true);
		 majorText.setEditable(true);
		 jionText.setEditable(true);
		 birthdayText.setEditable(true);
		 phoneText.setEditable(true);
		 Id_cardText.setEditable(true);
		 emailText.setEditable(true);
	}
	public void setBianji_fales(){
		 IDText.setEditable(false);
		 nameText.setEditable(false);
		 emailText.setEditable(false);
		 sexText.setEditable(false);
		 classText.setEditable(false);
		 majorText.setEditable(false);
		 jionText.setEditable(false);
		 birthdayText.setEditable(false);
		 phoneText.setEditable(false);
		 Id_cardText.setEditable(false);
	}
	public void setBoder_false(){
		 addButton.setBorder(null);
		 chaxun.setBorder(null);
		 chaxunText.setBorder(null);
		 IDText.setBorder(null);
		 nameText.setBorder(null);
		 emailText.setBorder(null);
		 sexText.setBorder(null);
		 classText.setBorder(null);
		 majorText.setBorder(null);
		 jionText.setBorder(null);
		 birthdayText.setBorder(null);
		 phoneText.setBorder(null);
		 Id_cardText.setBorder(null);
	}
	class menuAction implements ActionListener{
		TESTDB tb=new TESTDB();
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==menuchaxun){
				String a=JOptionPane.showInputDialog(null,"请输入要查询的学号：");
				ChaxunShuju(a);
			}
			if(e.getSource()==tianjia){
				new im.view.module.team.Tianjia();
			}
			if(e.getSource()==xiugai){
				new im.view.module.team.XiuGai();
			}
			if(e.getSource()==shanchu){
				ShanchuShuju();
			}
			if(e.getSource()==close){
				System.exit(1);
			}
		}	
	}		
	public void CreateTable( String n){
		vc=new Vector();
		vc.add("学期");
		vc.add("课程编号");
		vc.add("成绩");
		vc.add("教师编号");
		vc.add("是否及格");
		DefaultTableModel dftm = new DefaultTableModel(new Vector(),vc);
		TESTDB tb=new TESTDB();
		ResultSet rs=tb.query("select * from score");
		
		try {
			rs.last();
			int i=rs.getRow();
			rs.beforeFirst();
			String[] xx=new String[i];
			int j=0;
			Vector value=new Vector();
			while (rs.next()) {
				Vector vc=new Vector();	
				if(n.equals(rs.getString("id"))){
				xx[j]=rs.getString("id");
				vc.add(rs.getString("term"));
				vc.add(rs.getString("course_id"));
				vc.add(rs.getString("mark"));
				vc.add(rs.getString("teacherName"));
				}
				j++;
				value.add(vc);	
			}
			dftm.setDataVector(value,vc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scoreTable=new JTable(dftm);
		jsp=new JScrollPane(scoreTable);
		tablePanel.add(jsp);
		scoreTable.setRowHeight(30);//设置表格行高
		DefaultTableModel tableModel=(DefaultTableModel)scoreTable.getModel();
		tableModel.setRowCount(4);//设置表格行数
		scoreTable.setGridColor(new Color(255,200,200));
		scoreTable.setBackground(Color.gray);//设置背景颜色
		scoreTable.setRowSelectionAllowed(false);//是否可以选择行
		DefaultTableCellRenderer aligin=new DefaultTableCellRenderer();
		aligin.setHorizontalAlignment(JLabel.CENTER);//设置对齐方式
		scoreTable.setDefaultRenderer(Object.class, aligin);//使表格内容居中显示
		repaint();
	}
	public void addShuju(){
		TESTDB tb=new TESTDB();
		Connection con=tb.getCon();
		Statement stmt;
		addButton.setText("添加");
		try {
			stmt=(Statement) con.createStatement();
			stmt.executeUpdate("insert into student values('"+IDText.getText()+"','"+nameText.getText()+"','"+00000000+"','"+sexText.getText()+"','"+birthdayText.getText()+"','"+jionText.getText()+"','"+majorText.getText()+"','"+Id_cardText.getText()+"','"+phoneText.getText()+"','"+emailText.getText()+"','"+classText.getText()+"')");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setBianji_fales();
		JOptionPane.showMessageDialog(null, "保存成功");
	}
	public void XiugaiShuju(){
		TESTDB tb=new TESTDB();
		Connection con=tb.getCon();
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate("update student set name='"+nameText.getText()+"'where id="+IDText.getText()+"");
			stmt.executeUpdate("update student set sex='"+sexText.getText()+"'where id="+IDText.getText()+"");
			stmt.executeUpdate("update student set grade='"+classText.getText()+"'where id="+IDText.getText()+"");
			stmt.executeUpdate("update student set major_id='"+majorText.getText()+"'where id="+IDText.getText()+"");
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
		delete.setVisible(true);
		revise.setText("修改");
		setBianji_fales();
		addButton.setVisible(true);
		repaint();
	}
	public void ShanchuShuju(){
		TESTDB tb=new TESTDB();
		Connection con=tb.getCon();
		Statement stmt;
		int True=JOptionPane.showConfirmDialog(null,"确认要删除吗？","删除", JOptionPane.YES_NO_OPTION);
		if(True==JOptionPane.YES_OPTION){
			try {
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate("delete from student where id="+IDText.getText()+"");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "删除成功");
		}
		repaint();
	} 
	public void ChaxunShuju(String n){
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
						if(xx[j].equals(n)){
							content.setVisible(true);
							IDText.setText(rs.getString("id"));
							nameText.setText(rs.getString("name"));
							sexText.setText(rs.getString("sex"));
							classText.setText(rs.getString("grade"));
							majorText.setText(rs.getString("major_id"));
							jionText.setText(rs.getString("join_time"));
							birthdayText.setText(rs.getString("birthday"));
							phoneText.setText(rs.getString("phone"));
							Id_cardText.setText(rs.getString("id_card"));
							emailText.setText(rs.getString("email"));
							j++;
							menuBianji.setVisible(true);
							chaxun.setText("退出");
							chaxun.setBounds(710, 90, 60, 30);
							chaxun.setBackground(Color.red);
							chaxunText.setVisible(false);
							revise.setVisible(true);
							delete.setVisible(true);
							addButton.setVisible(true);
							CreateTable(chaxunText.getText());
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			repaint();
				
	}
	class ActionPerson implements ActionListener{
		TESTDB tb=new TESTDB();
		Connection con=tb.getCon();
		Statement stmt;
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==chaxun){
				if(chaxun.getText().equals("查询")){
					ChaxunShuju(chaxunText.getText());
				}else{ 	
					menuBianji.setVisible(false);
					chaxun.setBounds(560, 250, 60, 40);
					content.setVisible(false);
					chaxunText.setVisible(true);
					chaxun.setText("查询");
					chaxunText.setText("");
					chaxun.setBackground(Color.black);
					delete.setVisible(false);
					revise.setVisible(false);
					addButton.setVisible(false);
				}
			}	
			if(e.getSource()==revise){
				if(revise.getText().equals("修改")){
					setBianji_true();
					delete.setVisible(false);
					addButton.setVisible(false);
					revise.setText("保存");	
				}else{
					XiugaiShuju();	
				}
			}
			if(e.getSource()==delete){
				ShanchuShuju();
			}
			if(e.getSource()==addButton){
				if(addButton.getText().equals("添加")){
					setBianji_true();
					addButton.setText("保存");
				}else{
					addShuju();
				}
			}
		}
	}
	
}
