package im.view.module;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import im.entity.Person;
import im.util.view.Theme;
import im.view.service.ChangePassword;

public class ChangePassModule extends Module{
	/**
	 * 
	 */
	private static final long serialVersionUID = -823171360132682375L;
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=700;
	private final String NAME="修改密码";
	@SuppressWarnings("unused")
	private Person person=null;
	private JLabel[] label=null;
	private JPasswordField[] pass=null;
	private JLabel cue=null;
	private Button[] button=null;
	private Theme theme=null;
	private int count=3;
	public ChangePassModule(){
		
	}
	public ChangePassModule(Person person){
		this.person=person;
		this.theme=Theme.getTheme();
		this.setBounds(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
		this.setBackground(Color.white);
	}
	@Override
	public String getName(){
		return this.NAME;
	}
	private void init(){
		this.removeAll();
		loadLabel();
		loadCue();
		loadButton();
	}
	private void loadLabel(){
		String[] name={"原密码:","新密码:","确认密码:"};
		label=new JLabel[count];
		pass=new JPasswordField[count];
		int labelW=120,h=50,passW=200;
		int xtemp=this.getWidth()-labelW-passW-100>>1;
		int ytemp=170;
		Font labelFont=new Font(theme.getFontType(),Font.BOLD,22);
		Font passFont=new Font(theme.getFontType(),Font.PLAIN,18);
		for(int i=0;i<count;i++){
			label[i]=new JLabel(name[i]);
			label[i].setForeground(theme.getBgColor());
			label[i].setFont(labelFont);
			label[i].setHorizontalAlignment(JLabel.RIGHT);
			label[i].setBounds(xtemp,ytemp,labelW,h);
			label[i].setForeground(theme.getBgColor());
			pass[i]=new JPasswordField();
			pass[i].setEchoChar('*');
			pass[i].setFont(passFont);
			pass[i].setBounds(xtemp+labelW+20,ytemp,passW,h);
			this.add(label[i]);
			this.add(pass[i]);
			ytemp+=h+20;
		}
	}
	private void loadCue(){
		/**
		 * 提示框
		 */
		cue=new JLabel();
		int w=label[0].getWidth()+pass[0].getWidth()-30;
		int h=35;
		Font font=new Font(theme.getFontType(),Font.PLAIN,20);
		int ytemp=label[label.length-1].getY()+label[0].getHeight()+10;
		cue.setBounds(label[0].getX()+20,ytemp,w,h);
		cue.setFont(font);
		cue.setForeground(Color.RED);
		cue.setHorizontalAlignment(JLabel.LEFT);
		this.add(cue);
	}
	private void loadButton(){
		/**
		 * 加载按钮
		 */
		String[] buttonText={"修改","取消"};
		button=new Button[2];
		int xtemp=this.getWidth()-Button.DEFAULT_WIDTH*2-40>>1;
		int ytemp=cue.getY()+cue.getHeight()+40;
		for(int i=0,n=button.length;i<n;i++){
			button[i]=new Button(buttonText[i]);
			button[i].setLocation(xtemp,ytemp);
			this.add(button[i]);
			xtemp+=Button.DEFAULT_WIDTH+40;
			button[i].addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					String text=((Button)e.getSource()).getText();
					if(text.equals("修改")){
						String[] password=new String[3];
						int i=0;
						for(JPasswordField temp:pass){
							
							password[i++]=String.valueOf(temp.getPassword());
						}
						/**
						 * 调用view业务类修改，并显示修改结果
						 */
						cue.setText(ChangePassword.getQuery().change(password));
					}else{
						for(JPasswordField temp:pass){
							temp.setText("");
						}
						pass[0].requestFocus();
					}
				}
			});
		}
		
	}
	public void load(){
		init();
	}
}
