package im.view.module.em.function;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import im.entity.Record;
import im.util.view.Img;
import im.view.module.IMButton;
import im.view.module.Module;
import im.view.module.TableModule;
import im.view.module.em.EM;
import im.view.service.QueryAdmin;

public class AdminInfo extends IMButton{
	/**
	 * 管理员信息
	 */
	private static final long serialVersionUID = -1192806113012523095L;
	private EM em=null;
	private Module edit=null;
	private static AdminInfo function=new AdminInfo();
	private AdminInfo(){
		/**
		 * 采用单例模式，不需要通过构造实例化，所以设为private
		 */
	}
	private AdminInfo(EM em){
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
		this.setSize(EM.ICON_WIDTH,EM.ICON_HEIGHT);
		this.setBackground(Color.BLACK);
		this.add(Img.getImg("sources/img/adminInfo.png", 0, 0,EM.ICON_WIDTH, EM.ICON_HEIGHT));
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
		edit.add(new AdminEdit());
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
	private class AdminEdit extends Module{
		/**
		 * edit加载页面
		 */
		private static final long serialVersionUID = 5815311909153713349L;
		private List<Record> record=null;
		private TableModule table=null;
		public AdminEdit(){
			init();
		}
		private void init(){
			this.setBounds(0,0,edit.getWidth(),edit.getHeight());
			this.setBackground(Color.WHITE);
			loadTable();
		}
		private void loadTable(){
			record=QueryAdmin.getQuery().queryAll();
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
