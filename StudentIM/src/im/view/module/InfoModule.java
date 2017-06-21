package im.view.module;

import static im.util.view.Exist.exist;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import im.entity.Field;
import im.entity.Person;
import im.entity.Record;
import im.util.view.Img;
import im.util.view.Theme;
import im.view.module.em.EM;
import im.view.service.Query;
import im.view.service.Save;

public class InfoModule extends Module implements Runnable{
	/**
	 * ���˻�����Ϣģ��
	 */
	private static final long serialVersionUID = 1158924664581317849L;
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=700;
	public static final int INFO_WIDTH=230;
	public static final int LABEL_WIDTH=180;
	public static final int INFO_HEIGHT=50;
	private final String name="������Ϣ";
	private Person person=null;
	private Record record=null;
	private View photo=null;
	private int width=DEFAULT_WIDTH,height=DEFAULT_HEIGHT;
	private JComponent[] info=null;
	private JButton save=null;
	private JButton cancel=null;
	private String saveInfo=null;
//	private JLabel state=null;
	private Theme theme=null;
	public InfoModule(){

	}
	public InfoModule(Person person){
		/**
		 * @param person ������Ҫ���ص��û�ʵ��
		 */
		this.person=person;
		theme=Theme.getTheme();
		this.setLocation(0,0);
		this.setBackground(Color.white);
	}
	public void load(){
		init();
	}
	private void init(){
		setSize(width,height);
		record=Query.getQuery().queryById(person.getType(), person.getId());
		loadInfo();
	}
	@Override
	public String getName(){
		return this.name;
	}
	private void loadInfo(){
		Field[] field=record.getFields();
		int n=field.length;
		info=new JComponent[n];
		boolean change=false;
		if(n>8){
			change=true;
			loadPhoto();
		}
		int xtemp=60;
		int ytemp=30;
		Font bold=new Font(theme.getFontType(),Font.BOLD,22);
		Font plain=new Font(theme.getFontType(),Font.PLAIN,22);
		JLabel name=null;
		String fieldName=null;
		for(int i=0;i<n;i++){
			fieldName=field[i].getFieldName();
			if(fieldName.equals("photo")){
				//����ʾͼƬ·��
				continue;
			}
			if(fieldName.equals("last_time")){
				//����ʾ�ϴε�¼ʱ��
				continue;
			}
			if(fieldName.equals("password")){
				//����ʾ����
				continue;
			}
			name=new JLabel(field[i].getMean()+":");
			name.setBounds(xtemp,ytemp,LABEL_WIDTH,INFO_HEIGHT);
			name.setHorizontalAlignment(JLabel.RIGHT);
			name.setForeground(theme.getBgColor());
			name.setFont(bold);
			if(!exist(fieldName)){
				/**
				 * ����ǲ����޸��ֶΣ�����ΪLabel
				 */
				
				info[i]=new JLabel();
				((JLabel)info[i]).setText(field[i].getValue());
			}else{
				/**
				 * ���޸��ֶΣ�����Ϊ�ı���
				 */
				info[i]=new JTextField();
				((JTextField)info[i]).setText(field[i].getValue());
			}
			info[i].setBounds(xtemp+name.getWidth()+10,ytemp,INFO_WIDTH
					,INFO_HEIGHT);
			info[i].setForeground(theme.getBgColor());
			info[i].setFont(plain);
			this.add(info[i]);
			this.add(name);
			ytemp+=info[i].getHeight()+10;
			if(i>=8&&change){
				System.out.println("����");
				change=false;
				xtemp+=400;
				ytemp=(8-n+13)*INFO_HEIGHT+(8-n+13)*10+30;
			}
		}
		loadButton();
		
	}
	private void loadPhoto(){
		String path=null;
		try{
			/**
			 * �ж��Ƿ����photo�ֶΣ����Ϊ��ָ�룬�򲻴���
			 */
			path=record.getField("photo").getValue();
		}catch(NullPointerException e){
			return;
		}
		photo=new View("photo");
		photo.setBounds(550,30,200,250);
		photo.setBorder(BorderFactory.createLineBorder(Color.black));
		if(path==null||path.length()<4){
			/**
			 * ·��Ϊ��
			 */
			JLabel temp=new JLabel("����");
			temp.setBounds(0,0,photo.getWidth(),photo.getHeight());
			temp.setFont(new Font(theme.getFontType(),Font.BOLD,24));
			temp.setHorizontalAlignment(JLabel.CENTER);
			photo.add(temp);
		}else{
			try{
				photo.add(Img.getImg(path, 0, 0, photo.getWidth(), photo.getHeight()));
			}catch(Exception e){
				/**
				 * ��ȡ·������
				 */
				JLabel temp=new JLabel("����");
				temp.setBounds(0,0,photo.getWidth(),photo.getHeight());
				temp.setFont(new Font(theme.getFontType(),Font.BOLD,24));
				temp.setHorizontalAlignment(JLabel.CENTER);
				photo.add(temp);
			}
		}
		this.add(photo);
	}
	
	private void loadButton(){
		save=new JButton("����");
		cancel=new JButton("ȡ��");
		Font font=new Font(theme.getFontType(),Font.BOLD,20);
		save.setFont(font);
		cancel.setFont(font);
		save.setForeground(theme.getBgColor());
		cancel.setForeground(theme.getBgColor());
		save.setSize(100,50);
		cancel.setSize(100,50);
		int xtemp=this.getWidth()-save.getWidth()-cancel.getWidth()-50>>1;
		save.setLocation(xtemp,EM.EDIT_HEIGHT-80);
		xtemp+=save.getWidth()+50;
		cancel.setLocation(xtemp, EM.EDIT_HEIGHT-80);
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				saveInfo();
			}
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetInfo();
			}
		});
		this.add(save);
		this.add(cancel);
	}
	private void resetInfo(){
		Field[] field=record.getFields();
		int n=field.length;
		for(int i=0;i<n;i++){
			if(info[i]==null){
				continue;
			}
			if(!exist(field[i].getFieldName())){
				((JLabel)info[i]).setText(field[i].getValue());
			}else{
				((JTextField)info[i]).setText(field[i].getValue());
			}
		}
	}
	private void saveInfo(){
		Field[] field=record.getFields();
		int n=field.length;
		for(int i=0;i<n;i++){
			if(info[i]==null){
				continue;
			}
			if(!exist(field[i].getFieldName())){
				field[i].setValue(((JLabel)info[i]).getText());
			}else{
				field[i].setValue(((JTextField)info[i]).getText());
			}
		}
		record.setField(field);
		Save.getSave().save(record);
		if(Save.getSave().save(record)){
			saveInfo="����ɹ�!";
		}else{
			saveInfo="����ʧ��!";
		}
		System.out.println(saveInfo);
		new Thread(this).start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
//		loadState();
	}
//	public JLabel getState(){
//		
//	}
//	private void loadState(){
//		state=new JLabel(saveInfo);
//		state.setForeground(Color.RED);
//		state.setBounds(30, 0,EM.WIDTH, EM.STATE_HEIGHT);
//		try{
//			/**
//			 * �����β�������Ϊˢ��״̬
//			 */
//			em.getStateBarModule().remove(state);
//		}catch(Exception e){
//			//��������
//		}
//		em.getStateBarModule().add(state);
//		em.getStateBarModule().repaint();
//		try{
//			/**
//			 * ״̬��Ϣֻ���3��
//			 */
//			Thread.sleep(3000);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		em.getStateBarModule().remove(state);
//		em.getStateBarModule().repaint();
//	}
}
