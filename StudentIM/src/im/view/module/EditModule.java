package im.view.module;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import im.entity.*;
import im.util.view.Theme;
import im.view.service.Save;
import im.view.window.MainWindow;

public class EditModule extends JDialog{
		/**
		 * 编辑信息对话框，增删改查
		 */
	private static final long serialVersionUID = 8334419026191657641L;
	public static final int DEFAULT_WIDTH=600;
	public static final int DEFAULT_HEIGHT=700;
	public static final int TEXT_WIDTH=200;
	public static final int TEXT_HEIGHT=40;
	public static final int LABEL_WIDTH=180;
	private int x,y;
	private int width,height;
	private int labelWidth;
	private int textHeight,textWidth;
	private Container content=null;
	private Record record=null;
	private JLabel[] label=null;
	private JTextField[] text=null;
	private boolean isNew=false;
	private int length=-1;
	private Theme theme=null;
	private JLabel title=null;
	private Button[] button=null;
	private int col=1,row;
	private JLabel cue=null;
	private boolean hasChanged=false;
	@SuppressWarnings("unused")
	private EditModule(){
		
	}
	public EditModule(Record record){
		this(record,record.getSize());
	}
	public EditModule(Record record,int length){
		super(MainWindow.getWindow(),true);
		this.record=record;
		this.length=length;
		if(record!=null&&length<1){
			/**
			 * 主要为新增模块提供该方法，table模块不会调用此方法
			 * 至于为什么使用int而不用length判断，主要是为了让外部能够控制显示的行数
			 */
			isNew=true;
			this.length=record.getSize();
		}
		init();
	}
		
	private void init(){
		if(length<1){
			return;
		}
		theme=Theme.getTheme();
		labelWidth=LABEL_WIDTH;
		textWidth=TEXT_WIDTH;
		textHeight=TEXT_HEIGHT;
		height=DEFAULT_HEIGHT;
		setWidth(length);
		/**
		 * 因为本类为对话框衍生类，所以加载坐标为当前窗口居中
		 */
		x=MainWindow.getWindow().screenWidth-width>>1;
		y=MainWindow.getWindow().screenHeight-height>>1;
		setBounds(x,y,width,height);
		content=this.getContentPane();
		content.setLayout(null);
		loadTitle();
		loadInfo();
		loadButton();
		loadCue();
		setVisible(true);
	}
	private void setWidth(int n){
		/**
		 * 设置宽度，因为不确定被加载的记录属于哪个表，所以不能确定字段数量，所以需要更具字段数量变化宽度
		 */
		width=DEFAULT_WIDTH;
		/**
		 * @param row 需要显示的行数
		 * @param col 显示的列数
		 * @param height 对话框高度
		 * @param textHeight 文本框高度
		 * 对话框高度-标题和按钮等高度，在除（文本框高度+文本框间距)，得到可显示的行数
		 */
		row=(height-(textHeight+10)*5)/(textHeight+10);
		width=(n/row==1&&n%row==0)?width:(n/row+1)*width;
		col=width%DEFAULT_WIDTH==0?width/DEFAULT_WIDTH:width/DEFAULT_WIDTH+1;
	}
	private void loadTitle(){
		title=new JLabel("信息编辑");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(theme.getBgColor());
		title.setFont(new Font(theme.getFontType(),Font.BOLD,26));
		title.setSize(200,50);
		int xtemp=width-title.getWidth()>>1;
		title.setLocation(xtemp,10);
		content.add(title);
	}
	private void loadInfo(){
		/**
		 * 加载字段数据
		 */
		text=new JTextField[length];
		label=new JLabel[length];
		Field[] field=record.getFields();
		int xtemp=width-(labelWidth+textWidth+10)*col>>1;
		int ytemp=title.getY()+title.getHeight()+10;
		Font labelFont=new Font(theme.getFontType(),Font.BOLD,20);
		Font textFont=new Font(theme.getFontType(),Font.PLAIN,18);
		boolean change=true;
		for(int i=0;i<length;i++){
			label[i]=new JLabel(field[i].getMean()+":");
			label[i].setFont(labelFont);
			label[i].setHorizontalAlignment(JLabel.RIGHT);
			label[i].setForeground(theme.getBgColor());
			label[i].setBounds(xtemp,ytemp,labelWidth,textHeight);
			text[i]=new JTextField();
			text[i].setBounds(xtemp+labelWidth+10,ytemp,textWidth,textHeight);
			if(!isNew){
				/**
				 * 如果不为新建，则添加数据进文本框，同时禁止编辑，需点按钮开启编辑，防止误操作
				 */
				text[i].setText(field[i].getValue());
				text[i].setEditable(false);
			}
			text[i].setFont(textFont);
			/**
			 * 获得编辑器模型，并添加编辑器监听
			 * 只有在数据修改后才会重新保存
			 */
			text[i].getDocument().addDocumentListener(new DocumentListener(){

				@Override
				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					hasChanged=true;
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					hasChanged=true;
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			content.add(label[i]);
			content.add(text[i]);
			if(change&&i>=row){
				ytemp=title.getY()+title.getHeight()+10;
				xtemp+=label[i].getX()+text[i].getWidth()+20;
				change=false;
			}else{
				ytemp+=textHeight+10;
			}
		}
	}
	private void loadButton(){
		String[] temp={"修改","取消","删除"};
		if(isNew){
			temp[0]="保存";
		}
		button=new Button[3];
		int w=100,h=40;
		int xtemp=width-w*3-30>>1;
		int ytemp=height-h*2-20;
		for(int i=0;i<3;i++){
			button[i]=new Button(temp[i]);
			button[i].setType(String.valueOf(i));
			button[i].setLocation(xtemp,ytemp);
			button[i].addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					int index=Integer.parseInt(((Button)e.getSource()).getType());
					response(index);
				}
			});
			content.add(button[i]);
			xtemp+=w+10;
		}
	}
	private void loadCue(){
		cue=new JLabel();
		cue.setFont(new Font("黑体",Font.PLAIN,18));
		cue.setForeground(Color.RED);
		cue.setBounds(button[0].getX(),button[0].getY()-button[0].getHeight()+10,button[0].getWidth()*3,30);
		cue.setHorizontalAlignment(JLabel.CENTER);
		this.add(cue);
	}
	private void response(int index){
		switch(index){
			case 0:
				if(User.getUser().getType().equals("teacher")
						&&record.getTable().equals("student")){
					/**
					 * 不赋予教师编辑学生信息的权限
					 */
					return;
				}
				if(button[0].getText().equals("修改")){
					for(int i=0;i<length;i++){
						text[i].setEditable(true);
						button[0].setText("保存");
					}
				}else{
					button[0].setText("修改");
					if(hasChanged){
						save();
					}
				}
				break;
			case 1:
				if(button[0].getText().equals("保存")){
					button[0].setText("修改");
					for(int i=0;i<length;i++){
						text[i].setText(record.getField(i).getValue());
						text[i].setEditable(false);
					}
				};
				break;
			case 2:
				button[0].setText("修改");
				setCue(Save.getSave().remove(record));
		}
	}
	private void setCue(boolean success){
		new Thread(new Runnable(){
			@Override
			public void run() {
				if(success){
					cue.setText("操作成功");
				}else{
					cue.setText("操作失败，请重试");
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
	private void save(){
		/**
		 * 保存当前记录
		 */
		new Thread(new Runnable(){
			@Override
			public void run() {
				if(record==null){
					return;
				}
				for(int i=0;i<length;i++){
					record.getField(i).setValue(text[i].getText());
					text[i].setEditable(false);
				}
				setCue(Save.getSave().save(record));
			}
		}).start();
	}
}
