package im.view.module.em.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

import im.entity.Record;
import im.util.view.Img;
import im.util.view.Theme;
import im.view.module.Button;
import im.view.module.IMButton;
import im.view.module.Module;
import im.view.module.TableModule;
import im.view.module.em.EM;
import im.view.service.QueryScore;
import im.view.service.QueryStudent;

public class ScoreEdit extends IMButton{
	/**
	 * 成绩查询编辑
	 */
	private static final long serialVersionUID = 7796527218519669841L;
	private EM em=null;
	private Module edit=null;
	private Theme theme=null;
	private static ScoreEdit function=new ScoreEdit();
	private ScoreEdit(){
		/**
		 * 采用单例模式，不需要通过构造实例化，所以设为private
		 */
	}
	private ScoreEdit(EM em){
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
		this.add(Img.getImg("sources/img/scoreEdit.png", 0, 0,EM.ICON_WIDTH, EM.ICON_HEIGHT));
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
		edit.add(new SearchScore());
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
	private class SearchScore extends Module{
		/**
		 * 成绩查询页面
		 */
		private static final long serialVersionUID = -6229714304698060107L;
		private List<Record> record=null;
		private TableModule table=null;
		private JTextField input=null;
		private Button button=null;
		public SearchScore(){
			init();
		}
		private void init(){
			this.setBounds(0,0,edit.getWidth(),edit.getHeight());
			this.setBackground(Color.WHITE);
			loadInput();
		}
		private void loadInput(){
			input=new JTextField();
			int xtemp=this.getWidth()-300>>1;
			int ytemp=30;
			input.setText("输入学生学号");
			input.setBounds(xtemp, ytemp, 200, 40);
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
					if(input.getText().equals("输入学生学号")){
						input.setText("");
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if(input.getText().equals("")||null==input.getText()){
						input.setText("输入学生学号");
					}
					
				}
				
			});
			button.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					boolean isNum=input.getText().matches("\\d+");
					record=new ArrayList<>();
					if(isNum){
						Record temp=QueryStudent.getQuery().queryById(input.getText()).get(0);
						record=QueryScore.getQuery().queryByStudentId(temp.getField("id").getValue());
					}else{
						return;
					}
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
