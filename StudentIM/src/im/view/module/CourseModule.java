package im.view.module;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import im.entity.Field;
import im.entity.Person;
import im.entity.Record;
import im.util.view.Theme;
import im.view.service.QueryName;
import im.view.service.QuerySchedule;

public class CourseModule  extends Module{
	/**
	 * 课程表
	 */
	private static final long serialVersionUID = -6039443349515377305L;
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=700;
	private final String NAME="课程";
	private Person person=null;
	private Record record=null;
	private JLabel[][] course=null;
	private Field[] field=null;
	private int vspace=50,hspace=200;
	private int w=120,h=90;
	private Theme theme=null;
	public CourseModule(){

	}
	public CourseModule(Person person){
		/**
		 * @param person 需要查询的用户实体
		 */
		this.person=person;
		theme=Theme.getTheme();
		this.setBounds(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
		this.setBackground(theme.getFontColor());
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
		/**
		 * 初始化
		 * 因为不确定是否为第一次初始化，所以首先移出容器中的全部组件
		 */
		this.removeAll();
		record=QuerySchedule.getCourse().query(person.getId()).get(0);
		loadTitle();
		loadCourse();
	}
	private void loadTitle(){
		/**
		 * 课程表标题
		 */
		field=record.getFields();
		course=new JLabel[6][6];
		int x1=hspace,y1=vspace;
		int x2=x1,y2=y1;
		Font font=new Font(theme.getFontType(),Font.BOLD,20);
		for(int i=0,n=course.length;i<n;i++){
			course[0][i]=new JLabel();
			course[0][i].setBounds(x1,y1,w,h);
			course[0][i].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
			course[0][i].setHorizontalAlignment(JLabel.CENTER);
			course[0][i].setFont(font);
			course[0][i].setForeground(theme.getBgColor());
			x1+=w;
			if(i!=0){
				course[0][i].setText("周"+i);
			}
			this.add(course[0][i]);
			course[i][0]=new JLabel();
			course[i][0].setBounds(x2,y2,w,h);
			course[i][0].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
			course[i][0].setHorizontalAlignment(JLabel.CENTER);
			course[i][0].setFont(font);
			course[i][0].setForeground(theme.getBgColor());
			y2+=h;
			if(i!=0){
				course[i][0].setText("第"+i+"节课");
			}
			this.add(course[i][0]);
		}
	}
	private void loadCourse(){
		/**
		 * 加载课程
		 */
		int xtemp=hspace+w,ytemp=vspace+h;
		String name=null;
		for(int i=1,n=course.length;i<n;i++){
			for(int j=1,m=course[i].length;j<m;j++){
				course[i][j]=new JLabel();
				course[i][j].setBounds(xtemp,ytemp,w,h);
				course[i][j].setHorizontalAlignment(JLabel.CENTER);
				course[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
				course[i][j].setForeground(theme.getBgColor());
				xtemp+=w;
				name=QueryName.getQuery().query("course",field[(j-1)*5+i].getValue());
				course[i][j].setText(name);
				this.add(course[i][j]);
			}
			ytemp+=h;
			xtemp=hspace+w;
		}
	}
	@Override
	public String getName(){
		return this.NAME;
	}
}
