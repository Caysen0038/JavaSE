package im.view.module;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import im.util.Config;
import im.util.enums.MouseEnum;
import im.util.view.Img;
import im.util.view.Theme;
import im.view.module.em.FileEM;

public class ThemeSetModule extends Module{

	/**
	 * 主题设置界面
	 */
	private static final long serialVersionUID = 5947822349393906L;
	public static final int OPTION_WIDTH=60;
	public static final int OPTION_HEIGHT=60;
	private FileEM em=null;
	private Module menuCase=null;
	private Theme theme=null;
	private View edit=null;
	private View back=null;
	private View[] option=null;
	private JLabel title=null;
	private View[] tempCase=null;
	private List<String> id=null;
	private View[] button=null;
	private JLabel[] buttonText=null;
	private View cache=null;
	private ThemeTemplet[] templet=null;
	private int select=-1;
	private int count=2;
	private JLabel cue=null;
	@Deprecated
	@SuppressWarnings("unused")
	private ThemeSetModule(){
		/*
		 * 禁止使用
		 */
	}
	public ThemeSetModule(FileEM em){
		this.em=em;
		setName("主题");
		setType("themeSet");
	}
	private void init(){
		theme=Theme.getTheme();
		menuCase=em.getMenu();
		this.setBounds(0,0,menuCase.getWidth(),menuCase.getHeight());
		this.setBackground(Color.GRAY);
		id=Config.getDOM().getThemeID();
		loadBack();
		loadOption();
		loadEdit();
		
	}
	private void loadOption(){
		/**
		 * 设置选项按钮的背景图片，back特殊，单独加载
		 */
		String[] path={"","sources/img/theme.png"};
		option=new View[count];
		int xtemp=0,ytemp=0;
		for(int i=0;i<count;i++){
			option[i]=new View(String.valueOf(i));
			option[i].setBounds(xtemp,ytemp,OPTION_WIDTH-5,OPTION_HEIGHT);
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
		back.add(Img.getImg("sources/img/back.png",10,10,back.getWidth()-20
				,back.getHeight()-20));
	}
	private void loadEdit(){
		edit=new View("edit");
		edit.setBounds(back.getWidth(),0,menuCase.getWidth()-back.getWidth()
				,menuCase.getHeight());
		edit.setBackground(Color.WHITE);
		this.add(edit);
		loadTitle();
		loadTemplet();
		loadButton();
	}
	private void loadTitle(){
		title=new JLabel("主题设置");
		title.setForeground(theme.getBgColor());
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(theme.getFontType(),Font.BOLD,26));
		title.setSize(150,40);
		int xtemp=edit.getWidth()-title.getWidth()>>1;
		title.setLocation(xtemp, 20);
		edit.add(title);
	}
	private void loadTemplet(){
		templet=new ThemeTemplet[id.size()-1];
		tempCase=new View[id.size()-1];
		Map<String,String> info=null;
		int xtemp=40,ytemp=title.getY()+title.getHeight()+20;
		for(int i=0,n=templet.length;i<n;i++){
			tempCase[i]=new View(String.valueOf(i));
			info=Config.getDOM().getTheme(id.get(i+1));
			templet[i]=new ThemeTemplet(info);
			templet[i].setLocation(3,3);
			tempCase[i].add(templet[i]);
			tempCase[i].setBackground(Color.white);
			tempCase[i].setBounds(xtemp,ytemp,templet[i].getWidth()+6
					,templet[i].getHeight()+6);
			tempCase[i].addMouseListener(new MouseAdapter(){
				View view=null;
				@Override
				public void mouseEntered(MouseEvent e){
					view=(View)e.getSource();
					if(view.getState()==MouseEnum.CLICKED.getState()){
						return;
					}
					view.setState(MouseEnum.ENTERED.getState());
					view.setBorder(BorderFactory.createLineBorder(Color.PINK,4));
					edit.repaint();
				}
				@Override
				public void mousePressed(MouseEvent e){
					
				}
				@Override
				public void mouseClicked(MouseEvent e){
					if(cache!=null){
						cache.setState(MouseEnum.NONE.getState());
						cache.setBorder(null);
					}
					select=Integer.parseInt(view.getType());
					view.setState(MouseEnum.CLICKED.getState());
					cache=view;
					edit.repaint();
				}
				@Override
				public void mouseReleased(MouseEvent e){
					
				}
				@Override
				public void mouseExited(MouseEvent e){
					if(view.getState()==MouseEnum.CLICKED.getState()){
						return;
					}
					view.setState(MouseEnum.NONE.getState());
					view.setBorder(null);
					edit.repaint();
				}
			});
			edit.add(tempCase[i]);
			if(edit.getWidth()-xtemp-tempCase[i].getWidth()
					>tempCase[i].getWidth()+40){
				xtemp+=tempCase[i].getWidth()+40;
			}else{
				ytemp+=tempCase[i].getHeight()+20;
				xtemp=40;
			}
		}
	}
	private void loadButton(){
		button=new View[1];
		buttonText=new JLabel[1];
		String[] value={"应用","修改"};
		int w=100,h=40;
		int xtemp=edit.getWidth()-w-20>>1;
		int ytemp=tempCase[tempCase.length-1].getY()
				+tempCase[tempCase.length-1].getHeight()+50;
		Font font=new Font(theme.getFontType(),Font.PLAIN,20);
		for(int i=0;i<1;i++){
			button[i]=new View(String.valueOf(i));
			buttonText[i]=new JLabel(value[i]);
			buttonText[i].setBounds(0,0,w,h);
			buttonText[i].setForeground(theme.getFontColor());
			buttonText[i].setFont(font);
			buttonText[i].setHorizontalAlignment(JLabel.CENTER);
			button[i].setBackground(theme.getBgColor());
			button[i].setSize(w,h);
			button[i].add(buttonText[i]);
			button[i].setLocation(xtemp,ytemp);
			button[i].addMouseListener(new MouseAdapter(){
				int index;
				@Override
				public void mouseEntered(MouseEvent e){
					index=Integer.parseInt(((View)e.getSource()).getType());
					button[index].setBackground(theme.getFloatColor());
					button[index].repaint();
				}
				@Override
				public void mousePressed(MouseEvent e){
					button[index].setBackground(theme.getClickBg());
					buttonText[index].setForeground(theme.getFontClickColor());
					button[index].repaint();
				}
				@Override
				public void mouseReleased(MouseEvent e){
					button[index].setBackground(theme.getFloatColor());
					buttonText[index].setForeground(theme.getFontColor());
					button[index].repaint();
				}
				@Override
				public void mouseExited(MouseEvent e){
					button[index].setBackground(theme.getBgColor());
					button[index].repaint();
				}
			});
			edit.add(button[i]);
			xtemp+=w+20;
		}
		button[0].addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(select==-1){
					return;
				}
				String id=templet[select].getName();
				setCue(Config.getDOM().changeTheme(id));
			}
		});
		loadCue();
	}
	private void setCue(boolean success){
		new Thread(new Runnable(){
			
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(success){
				cue.setText("修改成功，重启后生效");
				
			}else{
				cue.setText("修改失败，请重试");
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
	private void loadCue(){
		cue=new JLabel();
		cue.setForeground(Color.red);
		cue.setHorizontalAlignment(JLabel.CENTER);
		cue.setFont(new Font(theme.getFontType(),Font.PLAIN,18));
		cue.setSize(200,40);
		int xtemp=edit.getWidth()-cue.getWidth()>>1;
		cue.setLocation(xtemp,button[0].getY()-button[0].getHeight()-10);
		edit.add(cue);
	}
	private void response(int index){
		switch(index){
			case 0:
				em.display();
				break;
			case 1:
//				edit.removeAll();
//				edit.add(new DBSet());
//				edit.repaint();
			case 2:
		}
	}
	@Override
	public void load(){
		this.removeAll();
		init();
	}
}
