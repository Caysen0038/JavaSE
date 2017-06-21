package im.view.module;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import im.entity.Person;
import im.entity.Record;
import im.util.view.Theme;
import im.view.service.QueryName;
import im.view.service.QueryScore;

public class ScoreModule extends Module{
	
	/**
	 * 成绩查询模板
	 */
	private static final long serialVersionUID = -1220831538466799564L;
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=700;

	private final String NAME="成绩";
	private Person person=null;
	private List<Record> list=null;
	private JLabel[][] score=null;
	private View view=null;
	private int hspace=200,vspace=100;
	private int w=200,h=50;
	private JLabel item=null;
	private JComboBox<String> select=null;
	private Theme theme=null;
	public ScoreModule(){
		
	}
	public ScoreModule(Person person){
		/**
		 * @param 传入需要查询成绩的用户实例
		 */
		this.person=person;
		theme=Theme.getTheme();
		this.setBounds(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
		this.setBackground(Color.white);
	}
	@Override
	public void load(){
		if(!person.getType().equals("student")){
			return;
		}
		/**
		 * 在重复切换时本不应该重复初始化，但考虑到数据的刷新，故每次加载都会初始化一次
		 */
		init();
	}
	private void init(){
		
		this.removeAll();
		
		list=QueryScore.getQuery().queryByStudentId(person.getId());
		loadItem();
		loadScore();
	}
	private void loadItem(){
		item=new JLabel("选择学期:");
		item.setBounds(40,20,150,40);
		item.setHorizontalAlignment(JLabel.CENTER);
		Font font=new Font(theme.getFontType(),Font.BOLD,22);
		item.setFont(font);
		item.setForeground(theme.getBgColor());
		select=new JComboBox<>();
		select.setMaximumRowCount(3);
		for(int i=1;i<=8;i++){
			select.addItem(String.valueOf(i));
		}
		select.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				remove(view);
				loadScore();
				repaint();
			}
		});
		select.setFont(font);
		select.setForeground(theme.getBgColor());
		select.setBounds(item.getX()+item.getWidth(),item.getY(),100,40);
		this.add(item);
		this.add(select);
	}
	private void loadScore(){
		int xtemp=0,ytemp=0+2*h;
		score=new JLabel[list.size()+2][4];
		view=new View("view");
		view.setBounds(hspace,vspace,4*w,score.length*h);
		loadTitle();
		String[] name={"course_id","mark","teacher_id","term"};
		Record record=null;
		String fieldName=null;
		for(int i=1,n=score.length-1;i<n;i++){
			record=list.get(i-1);
			if(!record.getField("term").getValue().equals(select.getSelectedItem())){
				continue;
			}
			for(int j=0,m=score[i].length;j<m;j++){
				if(j!=1&&j!=3){
					fieldName=QueryName.getQuery().query(name[j].split("_")[0]
						, record.getField(name[j]).getValue());
				}else{
					fieldName=record.getField(name[j]).getValue();
				}
				score[i][j]=new JLabel();
				score[i][j].setBounds(xtemp,ytemp,w,h);
				score[i][j].setText(fieldName);
				score[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
				score[i][j].setHorizontalAlignment(JLabel.CENTER);
				view.add(score[i][j]);
				xtemp+=w;
			}
			xtemp=0;
			ytemp+=h;
		}
		this.add(view);
	}
	private void loadTitle(){
		String[] name={"课程名称","成绩","阅卷老师","学期"};
		int xtemp=0;
		int ytemp=0;
		Font font=new Font(theme.getFontType(),Font.BOLD,20);
		String[] info={"学号:",person.getId(),"姓名:"
				,QueryName.getQuery().query("student", person.getId())};
		for(int i=0,j=0,m=score[j].length;i<m;i++){
			score[j][i]=new JLabel(info[i]);
			score[j][i].setBounds(xtemp,ytemp, w, h);
			score[j][i].setForeground(theme.getBgColor());
			score[j][i].setFont(font);
			if((i&1)==0){
				score[j][i].setHorizontalAlignment(JLabel.RIGHT);
			}else{
				score[j][i].setHorizontalAlignment(JLabel.LEFT);
			}
			view.add(score[j][i]);
			xtemp+=w;
		}
		xtemp=0;
		ytemp=0+h;
		for(int i=0,n=score[1].length;i<n;i++){
			score[1][i]=new JLabel(name[i]);
			score[1][i].setBounds(xtemp,ytemp, w, h);
			score[1][i].setHorizontalAlignment(JLabel.CENTER);
			score[1][i].setBorder(BorderFactory.createLineBorder(Color.black,2));
			score[1][i].setForeground(theme.getBgColor());
			score[1][i].setFont(font);
			view.add(score[1][i]);
			xtemp+=w;
		}
		
	}
	@Override
	public String getName(){
		return this.NAME;
	}
}
