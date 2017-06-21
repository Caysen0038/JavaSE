package im.view.module.em.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import im.entity.Record;
import im.util.view.Img;
import im.util.view.Theme;
import im.view.module.IMButton;
import im.view.module.Module;
import im.view.module.TableModule;
import im.view.module.em.EM;
import im.view.service.QueryPart;
import im.view.service.QueryTeacher;

public class TeacherInfo extends IMButton{
	
	
	/**
	 * 教师信息
	 */
	private static final long serialVersionUID = -6955908802821826421L;
	private EM em=null;
	private Module edit=null;
	private Theme theme=null;
	private static TeacherInfo function=new TeacherInfo();
	private TeacherInfo(){
		/**
		 * 采用单例模式，不需要通过构造实例化，所以设为private
		 */
	}
	private TeacherInfo(EM em){
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
		this.add(Img.getImg("sources/img/teacherInfo.png", 0, 0,EM.ICON_WIDTH, EM.ICON_HEIGHT));
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
		edit.add(new SearchClass());
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
	private class SearchClass extends Module{
		/**
		 * 教师信息页面
		 */
		private static final long serialVersionUID = 8203703184905990652L;
		private JComboBox<String>[] selectName=null;
		private String[] majorName=null;
		private JLabel[] selectLabel=null;
		private List<Record> record=null;
		private TableModule table=null;
		public SearchClass(){
			init();
		}
		private void init(){
			this.setBounds(0,0,edit.getWidth(),edit.getHeight());
			this.setBackground(Color.WHITE);
			loadSelect();
		}
		@SuppressWarnings("unchecked")
		private void loadSelect(){
			selectName=new JComboBox[2];
			selectLabel=new JLabel[2];
			majorName=QueryPart.getQuery().queryAllName();
			String[] labelText={"请选择专业","请选择班级"};
			int xtemp=30;
			for(int i=0,n=selectLabel.length;i<n;i++){
				selectLabel[i]=new JLabel(labelText[i]);
				selectLabel[i].setHorizontalAlignment(JLabel.RIGHT);
				selectLabel[i].setFont(new Font(theme.getFontType(),Font.BOLD,22));
				selectLabel[i].setForeground(theme.getBgColor());
				selectLabel[i].setBounds(xtemp,20,150,40);
				this.add(selectLabel[i]);
				selectName[i]=new JComboBox<>();
				selectName[i].setBounds(selectLabel[i].getX()+selectLabel[i].getWidth()+10,20,200,40);
				selectName[i].setMaximumRowCount(4);
				this.add(selectName[i]);
				xtemp=selectName[i].getX()+selectName[i].getWidth()+30;
			}
			for(String str:majorName){
				selectName[0].addItem(str);
			}
			this.repaint();
			loadSelectEvent();
		}
		private void loadSelectEvent(){
			selectName[0].addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
//					List<Record> temp=QueryClass.getQuery().queryByMajor((String)e.getItem());
//					className=new String[temp.size()];
//					int i=0;
//					selectName[1].removeAllItems();
//					System.out.println(className.length);
//					for(Record r:temp){
//						className[i]=r.getField("name").getValue();
//						selectName[1].addItem(className[i++]);
//					}
					selectName[1].removeAllItems();
					selectName[1].addItem("全部");
					selectName[1].repaint();
				}
			});
			selectName[1].addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					 record=QueryPart.getQuery()
							.queryByName((String)selectName[0].getSelectedItem());
					String id=record.get(0).getField("id").getValue();
					record=QueryTeacher.getQuery().queryByPart(id);
					System.out.println(id);
					loadTable();
				}
				
			});
			this.repaint();
		}
		private void loadTable(){
			if(table!=null){
				this.remove(table);
			}
			//Person person=new Person(info.getField(1).getValue(),info.getTable());
			table=new TableModule(record);
			//table.load();
			//table.load();
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
