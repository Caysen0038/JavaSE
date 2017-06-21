package im.view.module;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTextField;

import im.util.Config;
import im.util.enums.DBInfo;
import im.util.view.Img;
import im.util.view.Theme;
import im.view.module.em.FileEM;
public class SetModule extends Module{

	/**
	 * FileEM下的设置功能
	 */
	private static final long serialVersionUID = -2598648020534889440L;
	public static final int OPTION_WIDTH=60;
	public static final int OPTION_HEIGHT=60;
	private FileEM em=null;
	private Module menuCase=null;
	private View[] option=null;
	private View edit=null;
	private View back=null;
	private int count=3;
	private Theme theme=null;
	@Deprecated
	@SuppressWarnings("unused")
	private SetModule(){
		/*
		 * 禁止使用
		 */
	}
	public SetModule(FileEM em){
		this.em=em;
		setName("设置");
		setType("set");
	}
	private void init(){
		theme=Theme.getTheme();
		menuCase=em.getMenu();
		this.setBounds(0,0,menuCase.getWidth(),menuCase.getHeight());
		this.setBackground(Color.GRAY);
		loadOption();
		loadEdit();
	}
	private void loadOption(){
		String[] path={"","sources/img/set.png","sources/img/reset.png"};
		option=new View[count];
		int xtemp=0,ytemp=0;
		for(int i=0;i<count;i++){
			option[i]=new View(String.valueOf(i));
			option[i].setBounds(xtemp,ytemp,OPTION_WIDTH,OPTION_HEIGHT);
			option[i].setBackground(theme.getBgColor());
			if(path[i].length()!=0){
				option[i].add(Img.getImg(path[i], 2, 2
						, option[i].getWidth()-4, option[i].getHeight()-4));
			}
			option[i].addMouseListener(new MouseAdapter(){
				View view=null;
				int index;
				@Override
				public void mouseEntered(MouseEvent e){
					view=(View)e.getSource();
					index=Integer.parseInt(view.getType());
					view.setBackground(theme.getFloatColor());
					view.repaint();
				}
				@Override
				public void mousePressed(MouseEvent e){
					view.setBackground(theme.getClickBg());
					view.repaint();
				}
				@Override
				public void mouseClicked(MouseEvent e){
					response(index);
				}
				@Override
				public void mouseReleased(MouseEvent e){
					view.setBackground(theme.getFloatColor());
					view.repaint();
				}
				@Override
				public void mouseExited(MouseEvent e){
					view.setBackground(theme.getBgColor());
					view.repaint();
				}
			});
			this.add(option[i]);
			ytemp+=OPTION_HEIGHT;
		}
		loadBack();
		option[0].add(back);
	}
	private void loadBack(){
		back=new View("back");
		back.setBounds(0,0,56,56);
		back.add(Img.getImg("sources/img/back.png",10,10,OPTION_WIDTH-20,OPTION_HEIGHT-20));
	}
	private void loadEdit(){
		edit=new View("edit");
		edit.setBounds(OPTION_WIDTH,0,menuCase.getWidth()-OPTION_WIDTH
				,menuCase.getHeight());
		edit.setBackground(Color.WHITE);
		this.add(edit);
		response(1);
	}
	@Override
	public void load(){
		this.removeAll();
		init();
	}
	private void response(int index){
		switch(index){
			case 0:
				em.display();
				break;
			case 1:
				/**
				 * 加载设置数据库页面
				 */
				edit.removeAll();
				edit.add(new DBSet());
				edit.repaint();
				break;
			case 2:
				/**
				 * 加载重置页面
				 */
				edit.removeAll();
				edit.add(new Reset());
				edit.repaint();
				break;
		}
	}
	private class DBSet extends Module{
		
		/**
		 * 数据库配置修改界面
		 */
		private static final long serialVersionUID = 3193009984400502708L;
		private JLabel[] name=null;
		private JTextField[] text=null;
		private Button button=null;
		private DBInfo[] info=null;
		private Map<String,String> db=null;
		public DBSet(){
			db=Config.getDOM().getDB();
			init();
		}
		private void init(){
			System.out.println("load");
			this.setBounds(0,0,edit.getWidth(),500);
			this.setBackground(Color.WHITE);
			loadLabel();
			loadButton();
		}
		private void loadLabel(){
			info=DBInfo.values();
			name=new JLabel[info.length+1];
			name[0]=new JLabel("数据库设置");
			name[0].setFont(new Font(theme.getFontType(),Font.BOLD,24));
			name[0].setSize(150,40);
			int xtemp=this.getWidth()-name[0].getWidth()>>1;
			name[0].setLocation(xtemp, 20);
			name[0].setHorizontalAlignment(JLabel.CENTER);
			name[0].setForeground(theme.getBgColor());
			this.add(name[0]);
			Font font=new Font(theme.getFontType(),Font.PLAIN,20);
			xtemp=0;
			int ytemp=name[0].getY()+name[0].getHeight()+30;
			int w=130,h=30;
			for(int i=1,n=name.length;i<n;i++){
				name[i]=new JLabel(info[i-1].getMean());
				name[i].setForeground(theme.getBgColor());
				name[i].setHorizontalAlignment(JLabel.CENTER);
				name[i].setFont(font);
				name[i].setBounds(xtemp,ytemp,w,h);
				ytemp+=h+10;
				this.add(name[i]);
			}
			loadText();
		}
		private void loadText(){
			text=new JTextField[info.length];
			int xtemp=name[1].getX()+name[1].getWidth();
			int ytemp=name[0].getY()+name[0].getHeight()+30;
			int w=200,h=30;
			Font font=new Font(theme.getFontType(),Font.PLAIN,18);
			for(int i=0,n=text.length;i<n;i++){
				text[i]=new JTextField();
				text[i].setEditable(false);
				text[i].setBounds(xtemp,ytemp,w,h);
				text[i].setText(db.get(info[i].getName()));
				text[i].setFont(font);
				text[i].setForeground(theme.getBgColor());
				this.add(text[i]);
				ytemp+=h+10;
			}
		}
		private void loadButton(){
			button=new Button("修改");
//			buttonText=new JLabel("修改");
//			button.setSize(100,40);
//			button.setBackground(theme.getBgColor());
			int xtemp=this.getWidth()-button.getWidth()>>1;
			button.setLocation(xtemp,this.getHeight()-80);
//			buttonText.setBounds(0,0,button.getWidth(),button.getHeight());
//			buttonText.setForeground(theme.getFontColor());
//			buttonText.setFont(new Font(theme.getFontType(),Font.PLAIN,20));
//			buttonText.setHorizontalAlignment(JLabel.CENTER);
//			button.add(buttonText);
			button.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					if(button.getText().equals("修改")){
						button.setText("保存");
						for(int i=0,n=text.length;i<n;i++){
							text[i].setEditable(true);
						}
					}else{
						for(int i=0,n=text.length;i<n;i++){
							text[i].setEditable(false);
						}
						button.setText("修改");
						saveInfo();
					}
				}
			});
			this.add(button);
		}
		private void saveInfo(){
			new Thread(new Runnable(){
				@Override
				public void run(){
					db.clear();
					for(int i=0,n=text.length;i<n;i++){
						db.put(info[i].getName(), text[i].getText());
					}
					System.out.println("配置修改结果:"+Config.getDOM().updateDB(db));
				}
			}).start();
		}
	}
	private class Reset extends Module{
		/**
		 * 重置配置文件修改界面
		 */
		private static final long serialVersionUID = 8838292077426981953L;
		private final String data="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
						+"\n<config>\n<database>\n<db-driver>com.mysql.jdbc.Driver</db-driver>"
						+"\n<db-address>localhost</db-address>\n<db-port>3306</db-port>\n<db-name>imdb</db-name>"
						+"\n<db-username>root</db-username>\n<db-userpassword>admin</db-userpassword>"
						+"\n</database>\n<theme>\n<default-theme>dark</default-theme>\n<theme-set id=\"blue\">"
						+"\n<theme-name>blue</theme-name>\n<bg-color>\n<red>0</red>\n<green>0</green>"
						+"\n<blue>255</blue>\n</bg-color>\n<font-color>\n<red>255</red>"
						+"\n<green>255</green>\n<blue>255</blue>\n</font-color>\n<float-color>"
						+"\n<red>54</red>\n<green>146</green>\n<blue>236</blue>\n</float-color>"
						+"\n<font-click>\n<red>0</red>\n<green>0</green>\n<blue>255</blue>"
						+"\n</font-click>\n<bg-click>\n<red>255</red>\n<green>255</green>"
						+"\n<blue>255</blue>\n</bg-click>\n<font-type>黑体</font-type>"
						+"\n</theme-set>\n<theme-set id=\"green\">\n<theme-name>green</theme-name>"
						+"\n<bg-color>\n<red>0</red>\n<green>255</green>\n<blue>0</blue>"
						+"\n</bg-color>\n<font-color>\n<red>255</red>\n<green>255</green>\n<blue>255</blue>"
						+"\n</font-color>\n<float-color>\n<red>73</red>\n<green>242</green>"
						+"\n<blue>67</blue>\n</float-color>\n<font-click>\n<red>0</red>"
						+"\n<green>255</green>\n<blue>0</blue>\n</font-click>\n<bg-click>"
						+"\n<red>255</red>\n<green>255</green>\n<blue>255</blue>\n</bg-click>"
						+"\n<font-type>黑体</font-type>\n</theme-set>\n<theme-set id=\"dark\">"
						+"\n<theme-name>dark</theme-name>\n<bg-color>\n<red>0</red>"
						+"\n<green>0</green>\n<blue>0</blue>\n</bg-color>\n<font-color>"
						+"\n<red>255</red>\n<green>255</green>\n<blue>255</blue>\n</font-color>"
						+"\n<float-color>\n<red>88</red>\n<green>88</green>\n<blue>88</blue>"
						+"\n</float-color>\n<font-click>\n<red>0</red>\n<green>0</green>"
						+"\n<blue>0</blue>\n</font-click>\n<bg-click>\n<red>255</red>"
						+"\n<green>255</green>\n<blue>255</blue>\n</bg-click>"
						+"\n<font-type>黑体</font-type>\n</theme-set>\n</theme>\n</config>";
		private Button button=null;
		private JLabel text=null;
		private JLabel cue=null;
		public Reset(){
			init();
		}
		private void init(){
			System.out.println("load");
			this.setBounds(0,0,edit.getWidth(),500);
			this.setBackground(Color.WHITE);
			loadLabel();
			loadButton();
			loadCue();
		}
		private void loadLabel(){
			text=new JLabel("<html><center>初始化软件配置<center><br>"
					+ "<h2>提示:初始化之后所有自定义配置都将消失</h2></html>");
			text.setHorizontalAlignment(JLabel.CENTER);
			text.setFont(new Font(theme.getFontType(),Font.PLAIN,22));
			text.setForeground(Color.BLACK);
			text.setSize(300,200);
			int xtemp=this.getWidth()-text.getWidth()>>1;
			text.setLocation(xtemp, 100);
			this.add(text);
		}
		
		private void loadButton(){
			button=new Button("重置");
			//button.setSize(100,40);
			button.setBackground(theme.getBgColor());
			int xtemp=this.getWidth()-button.getWidth()>>1;
			button.setLocation(xtemp,this.getHeight()-180);
			button.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseClicked(MouseEvent e){
					saveInfo();
				}
			});
			this.add(button);
		}
		private void loadCue(){
			cue=new JLabel();
			cue.setHorizontalAlignment(JLabel.CENTER);
			cue.setFont(new Font(theme.getFontType(),Font.PLAIN,18));
			cue.setForeground(Color.RED);
			cue.setSize(300,40);
			int xtemp=this.getWidth()-cue.getWidth()>>1;
			cue.setLocation(xtemp, button.getY()-button.getHeight()-10);
			this.add(cue);
		}
		private void setCue(boolean success){
			new Thread(new Runnable(){
				@Override
				public void run() {
					if(success){
						cue.setText("配置文件重置成功，重启后生效");
						
					}else{
						cue.setText("重置失败，请重试");
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					cue.setText("");
				}
				
				}).start();
		}
		private void saveInfo(){
			new Thread(new Runnable(){
				@Override
				public void run(){
					File file=new File("config/config.xml");
					if(!file.exists()){
						try {
							file.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					OutputStream out=null;
					try {
						out=new FileOutputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					byte[] temp=data.getBytes();
					try {
						out.write(temp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setCue(true);
				}
			}).start();
		}
	
	}
}
