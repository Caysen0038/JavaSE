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
		 * �༭��Ϣ�Ի�����ɾ�Ĳ�
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
			 * ��ҪΪ����ģ���ṩ�÷�����tableģ�鲻����ô˷���
			 * ����Ϊʲôʹ��int������length�жϣ���Ҫ��Ϊ�����ⲿ�ܹ�������ʾ������
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
		 * ��Ϊ����Ϊ�Ի��������࣬���Լ�������Ϊ��ǰ���ھ���
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
		 * ���ÿ�ȣ���Ϊ��ȷ�������صļ�¼�����ĸ������Բ���ȷ���ֶ�������������Ҫ�����ֶ������仯���
		 */
		width=DEFAULT_WIDTH;
		/**
		 * @param row ��Ҫ��ʾ������
		 * @param col ��ʾ������
		 * @param height �Ի���߶�
		 * @param textHeight �ı���߶�
		 * �Ի���߶�-����Ͱ�ť�ȸ߶ȣ��ڳ����ı���߶�+�ı�����)���õ�����ʾ������
		 */
		row=(height-(textHeight+10)*5)/(textHeight+10);
		width=(n/row==1&&n%row==0)?width:(n/row+1)*width;
		col=width%DEFAULT_WIDTH==0?width/DEFAULT_WIDTH:width/DEFAULT_WIDTH+1;
	}
	private void loadTitle(){
		title=new JLabel("��Ϣ�༭");
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
		 * �����ֶ�����
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
				 * �����Ϊ�½�����������ݽ��ı���ͬʱ��ֹ�༭����㰴ť�����༭����ֹ�����
				 */
				text[i].setText(field[i].getValue());
				text[i].setEditable(false);
			}
			text[i].setFont(textFont);
			/**
			 * ��ñ༭��ģ�ͣ�����ӱ༭������
			 * ֻ���������޸ĺ�Ż����±���
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
		String[] temp={"�޸�","ȡ��","ɾ��"};
		if(isNew){
			temp[0]="����";
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
		cue.setFont(new Font("����",Font.PLAIN,18));
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
					 * �������ʦ�༭ѧ����Ϣ��Ȩ��
					 */
					return;
				}
				if(button[0].getText().equals("�޸�")){
					for(int i=0;i<length;i++){
						text[i].setEditable(true);
						button[0].setText("����");
					}
				}else{
					button[0].setText("�޸�");
					if(hasChanged){
						save();
					}
				}
				break;
			case 1:
				if(button[0].getText().equals("����")){
					button[0].setText("�޸�");
					for(int i=0;i<length;i++){
						text[i].setText(record.getField(i).getValue());
						text[i].setEditable(false);
					}
				};
				break;
			case 2:
				button[0].setText("�޸�");
				setCue(Save.getSave().remove(record));
		}
	}
	private void setCue(boolean success){
		new Thread(new Runnable(){
			@Override
			public void run() {
				if(success){
					cue.setText("�����ɹ�");
				}else{
					cue.setText("����ʧ�ܣ�������");
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
		 * ���浱ǰ��¼
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
