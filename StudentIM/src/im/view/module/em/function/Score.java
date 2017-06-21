package im.view.module.em.function;

import im.entity.User;
import im.util.view.Img;
import im.util.view.Theme;
import im.entity.Person;
import im.view.module.IMButton;
import im.view.module.Module;
import im.view.module.ScoreOption;
import im.view.module.View;
import im.view.module.em.EM;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Score extends IMButton{

	/**
	 * Œ¥≤…”√
	 */
	private static final long serialVersionUID = 5665865195928980168L;
	private EM em=null;
	private Module edit=null;
	private Module group=null;
	private Module[] fun=null;
	private View[] option=null;
	private JLabel[] optionLabel=null;
	private Theme theme=null;
	private int count=1;
	private static Score function=new Score();
	@Deprecated
	private Score(){
		init();
	}
	public Score(EM em){
		this.em=em;
		init();
	}
	public static Module getFun(EM em){
		function.setEM(em);
		return function;
	}
	public void setEM(EM em){
		this.em=em;
	}
	public void init(){
		super.init();
		theme=Theme.getTheme();
		this.setSize(EM.ICON_WIDTH,EM.ICON_HEIGHT);
		this.setBackground(Color.BLACK);
		this.add(Img.getImg("sources/img/100.png", 0
				, 0,EM.ICON_WIDTH, EM.ICON_HEIGHT));
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				load();
			}
		});
	}
	private void loadGroup(){
		group=new Module("group");
		group.setBounds(0,0,200,EM.EDIT_HEIGHT);
		group.setBorder(BorderFactory.createLineBorder(theme.getBgColor(),2));
		group.setBackground(theme.getFontColor());
		loadOption();
	}
	private void loadOption(){
		loadEdit();
		option=new View[count];
		optionLabel=new JLabel[count];
		fun=new Module[count];
		Person person=new Person(User.getUser().getId(),User.getUser().getType());
		fun[0]=new ScoreOption();
		for(int i=0;i<count;i++){
			fun[i].setSize(EM.WIDTH-group.getWidth(),EM.EDIT_HEIGHT);
		}
		int ytemp=3;
		for(int i=0,n=option.length;i<n;i++){
			option[i]=new View(String.valueOf(i));
			option[i].setBounds(0,ytemp,group.getWidth(),40);
			option[i].setBackground(theme.getBgColor());
			optionLabel[i]=new JLabel(fun[i].getName());
			optionLabel[i].setBounds(0,0,option[i].getWidth(),option[i].getHeight());
			optionLabel[i].setHorizontalAlignment(JLabel.CENTER);
			optionLabel[i].setForeground(theme.getFontColor());
			optionLabel[i].setFont(new Font(theme.getFontType(),Font.PLAIN,20));
			option[i].add(optionLabel[i]);
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
					optionLabel[index].setForeground(theme.getFontClickColor());
					view.repaint();
				}
				@Override
				public void mouseClicked(MouseEvent e){
					edit.removeAll();
					fun[index].load();
					edit.add(fun[index]);
					edit.repaint();
				}
				@Override
				public void mouseReleased(MouseEvent e){
					view.setBackground(theme.getFloatColor());
					optionLabel[index].setForeground(theme.getFontColor());
					view.repaint();
				}
				@Override
				public void mouseExited(MouseEvent e){
					view.setBackground(theme.getBgColor());
					view.repaint();
				}
			});
			group.add(option[i]);
			ytemp+=option[i].getHeight()+1;
		}
	}
	private void loadEdit(){
		edit=new Module("edit");
		edit.setBounds(group.getWidth(),0,EM.WIDTH,EM.EDIT_HEIGHT);
		edit.setBackground(theme.getFontColor());
	}
	@Override
	public void load(){
		if(group==null){
			loadGroup();
		}
		if(edit==null){
			loadEdit();
		}
		em.getEditModule().removeAll();
		edit.removeAll();
		em.getEditModule().add(edit);
		em.getEditModule().add(group);
		fun[0].load();
		edit.add(fun[0]);
		edit.repaint();
		em.getEditModule().repaint();
	}
}
