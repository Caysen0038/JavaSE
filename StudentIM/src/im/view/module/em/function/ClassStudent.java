package im.view.module.em.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import im.entity.Record;
import im.util.view.Img;
import im.util.view.Theme;
import im.view.module.Button;
import im.view.module.IMButton;
import im.view.module.Module;
import im.view.module.TableModule;
import im.view.module.em.EM;
import im.view.service.QueryClass;
import im.view.service.QueryMajor;
import im.view.service.QueryStudent;

public class ClassStudent extends IMButton{

	/**
	 * 查询指定班级学生
	 */
	private static final long serialVersionUID = -7169630451160834453L;
	
	private EM em=null;
	private Module edit=null;
	private Theme theme=null;
	private static ClassStudent function=new ClassStudent();
	
	private ClassStudent(){
		/**
		 * 采用单例模式，不需要通过构造实例化，所以设为private
		 */
	}
	private ClassStudent(EM em){
		setEM(em);
		
	}
	public void setEM(EM em){
		this.em=em;
		init();
	}
	public static Module getFun(EM em){
		function.setEM(em);
		return function;
	}
	public void init(){
		super.init();
		theme=Theme.getTheme();
		this.setSize(EM.ICON_WIDTH,EM.ICON_HEIGHT);
		this.setBackground(Color.BLACK);
		this.add(Img.getImg("sources/img/classStudent.png", 0, 0,EM.ICON_WIDTH, EM.ICON_HEIGHT));
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				load();
			}
		});
		
	}
	private void loadEdit(){
		edit=new Module("edit");
		edit.setBounds(0, 0, EM.WIDTH, EM.EDIT_HEIGHT);
		edit.setBackground(Color.WHITE);
		edit.add(new SearchStudent());
		edit.repaint();
	}
	@Override
	public void load(){
		if(edit==null){
			loadEdit();
		}
		em.getEditModule().removeAll();
		em.getEditModule().add(edit);
		em.getEditModule().repaint();
	}
	private class SearchStudent extends Module{

		/**
		 * 
		 */
		private static final long serialVersionUID = -711666950212391158L;
		
		private JComboBox<String>[] select=null;
		private String[] className=null;
		private String[] majorName=null;
		private JLabel[] selectLabel=null;
		private List<Record> record=null;
		private TableModule table=null;
		private JTextField input=null;
		private Button button=null;
		public SearchStudent(){
			init();
		}
		private void init(){
			this.setBounds(0,0,edit.getWidth(),edit.getHeight());
			this.setBackground(Color.WHITE);
			loadSelect();
			loadInput();
		}
		@SuppressWarnings("unchecked")
		private void loadSelect(){
			select=new JComboBox[2];
			selectLabel=new JLabel[2];
			majorName=QueryMajor.getQuery().queryAllName();
			String[] labelText={"请选择专业","请选择班级"};
			int xtemp=30;
			for(int i=0,n=selectLabel.length;i<n;i++){
				selectLabel[i]=new JLabel(labelText[i]);
				selectLabel[i].setHorizontalAlignment(JLabel.RIGHT);
				selectLabel[i].setFont(new Font(theme.getFontType(),Font.BOLD,22));
				selectLabel[i].setForeground(theme.getBgColor());
				selectLabel[i].setBounds(xtemp,20,150,40);
				this.add(selectLabel[i]);
				select[i]=new JComboBox<>();
				select[i].setBounds(selectLabel[i].getX()+selectLabel[i].getWidth()+10,20,200,40);
				select[i].setMaximumRowCount(4);
				this.add(select[i]);
				xtemp=select[i].getX()+select[i].getWidth()+30;
			}
			for(String str:majorName){
				select[0].addItem(str);
			}
			this.repaint();
			loadSelectEvent();
		}
		private void loadSelectEvent(){
			select[0].addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					List<Record> temp=QueryClass.getQuery().queryByMajor((String)e.getItem());
					className=new String[temp.size()];
					int i=0;
					select[1].removeAllItems();
					System.out.println(className.length);
					for(Record r:temp){
						className[i]=r.getField("name").getValue();
						select[1].addItem(className[i++]);
					}
					select[1].repaint();
				}
			});
			select[1].addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					record=QueryClass.getQuery()
							.querybyName((String)select[1].getSelectedItem());
					if(record.size()==0){
						return;
					}
					String id=record.get(0).getField("id").getValue();
					record=QueryStudent.getQuery().queryByClass(id);
					System.out.println(id);
					loadTable();
					
				}
				
			});
			this.repaint();
		}
		private void loadInput(){
			input=new JTextField();
			int xtemp=select[1].getX()+select[1].getWidth()+100;
			int ytemp=select[1].getY();
			input.setText("输入班级ID或名称");
			input.setBounds(xtemp, ytemp, select[1].getWidth(), select[1].getHeight());
			input.setFont(new Font(theme.getFontType(),Font.PLAIN,20));
			this.add(input);
			button=new Button("搜索");
			button.setLocation(xtemp+input.getWidth()+10,ytemp);
			this.add(button);
			loadEvent();
		}
		private void loadEvent(){
			input.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent e) {
					if(input.getText().equals("输入班级ID或名称")){
						input.setText("");
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if(input.getText().equals("")||null==input.getText()){
						input.setText("输入班级ID或名称");
					}
					
				}
				
			});
			button.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					boolean isNum=input.getText().matches("\\d+");
					Record temp=null;
					if(isNum){
						temp=QueryClass.getQuery().queryById(input.getText());
						
					}else{
						temp=QueryClass.getQuery().querybyName(input.getText()).get(0);
					}
					record=QueryStudent.getQuery().queryByClass(temp.getField("id").getValue());
					loadTable();
				}
			});
		}
		private void loadTable(){
			if(table!=null){
				this.remove(table);
			}
			table=new TableModule(record);
			int xtemp=this.getWidth()-table.getWidth()>>1;
			table.setLocation(xtemp, 100);
			this.add(table);
			this.repaint();
		}
		@Override
		public void load(){
			
		}
	}
}
