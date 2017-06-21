package im.view.module;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import im.entity.Field;
import im.entity.Record;
import im.util.view.Theme;
import im.view.service.QueryName;

public class TableModule extends Module {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4063699887743130419L;
	/*
	 * 默认单元格宽高以及单页最大显示数量
	 */
	public static final int DEFAULT_CELL_WIDTH=100;
	public static final int DEFAULT_CELL_HEIGHT=30;
	public static final int DEFAULT_MAX_LIST=10;
	public static final int MIN_WIDTH=430;
	/**
	 * 屏蔽的字段
	 * 虽然数组引用定义为final看似没什么作用，数组里的值同样能改变
	 */
	public static final String[] EXCEPT={"password","photo","last_time",};
	private JTable table=null;
	private DefaultTableModel model=null;
	private List<Record> list=null;
	private Button[] button=null;
	private JLabel[] title=null;
	private Object[][] data=null;
	private String[] head=null;
	private int width,height;
	private int col=0,row;
	private int max;
	private int cellWidth,cellHeight;
	private int page;
	private int currentPage=1;
	private Theme theme=null;
	private final String[] TEXT={"首页","上一页","下一页","末页"};
	@SuppressWarnings("unused")
	private TableModule(){
		
	}
	public TableModule(List<Record> list){
		this(list,DEFAULT_MAX_LIST);
	}
	public TableModule(List<Record> list,int max){
		this.list=list;
		this.max=max;
		this.theme=Theme.getTheme();
		load();
	}
	private void init(){
		if(list==null){
			return;
		}
		row=list.size();
		if(row==0){
			/**
			 * @param row 记录总数，如果为0则为空，退出
			 */
			return;
		}
		cellWidth=DEFAULT_CELL_WIDTH;
		cellHeight=DEFAULT_CELL_HEIGHT;
		Field[] field=list.get(0).getFields();
		col=0;
		for(int i=0,n=field.length;i<n;i++){
			/**
			 * 计算有效的字段数量
			 */
			if(except(field[i].getFieldName())){
				col++;
			}
		}
		width=cellWidth*col>MIN_WIDTH?cellWidth*col:MIN_WIDTH;
		cellWidth=width/col;
		height=row>max?(max+1)*cellHeight:row*cellHeight;
		this.setSize(width,height+cellHeight*4);
		this.setBackground(Color.white);
		countPage();
		loadTitle();
		loadData();
		loadTable(1);
		loadButton();
	}
	private void countPage(){
		page=row<max?1:(row%max==0?row/max:row/max+1);
	}
	private void loadTitle(){
		/**
		 * 加载列名
		 * 此处未使用table的列标题，是为了保留对标题各种属性的的控制权
		 */
		Record record=list.get(0);
		title=new JLabel[col];
		head=new String[col];
		Font font=new Font(theme.getFontType(),Font.BOLD,22);
		int xtemp=0;
		String key=null;
		Field[] field=record.getFields();
		for(int i=0,j=0;i<field.length;i++){
			key=field[i].getFieldName();
			if(!except(key)){
				/**
				 * 判断是否为需要屏蔽的字段
				 */
				continue;
			}
			title[j]=new JLabel();
			title[j].setBounds(xtemp,0,cellWidth,cellHeight);
			title[j].setForeground(theme.getBgColor());
			title[j].setFont(font);
			title[j].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			title[j].setHorizontalAlignment(JLabel.CENTER);
			title[j].setText(field[i].getMean());
			this.add(title[j]);
			xtemp+=cellWidth;
			j++;
		}
	}
	private void loadData(){
		/**
		 * 获取数据
		 */
		Field[] field=null;
		data=new Object[row][col];
		String value=null;
		String fieldName=null;
		for(int i=0;i<row;i++){
			field=list.get(i).getFields();
			for(int j=0,n=0;j<field.length;j++){
				if(!except(field[j].getFieldName())){
					continue;
				}
				fieldName=field[j].getFieldName();
				if(fieldName.split("_").length==2&&fieldName.split("_")[1].equals("id")){
					value=field[j].getValue();
					value=QueryName.getQuery().query(fieldName.split("_")[0], value);
					data[i][n++]=value;
				}else{
					data[i][n++]=field[j].getValue();
				}
				if(data[i][n-1]==null){
					data[i][n-1]="";
				}
			}
		}
	}
	private void loadTable(int page){
		if(page<=0){
			/**
			 * @param page 当前要加载的页码，小于0无效，退出
			 */
			page=1;
		}
		if(table!=null){
			/**
			 * 如果已经存在表格，则只刷新数据
			 */
			for(int i=model.getRowCount()-1;i>=0;i--){
				model.removeRow(i);
			}
			Object[][] temp=getData(page);
			for(int i=0;i<max;i++){
				model.addRow(temp[i]);
			}
			table.repaint();
		}else{
			/**
			 * 初始化表格
			 */
			currentPage=page;
			model=new DefaultTableModel(getData(page),head);
			
			table=new JTable(model);
			table.setFont(new Font(theme.getFontType(),Font.PLAIN,20));
			table.setBackground(Color.WHITE);
			/**
			 * 设置行高
			 */
			table.setRowHeight(cellHeight);
			table.setBorder(BorderFactory.createLineBorder(Color.black,1));
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(JLabel.CENTER);
			/**
			 * 设置默认单元格渲染器
			 */
			table.setDefaultRenderer(Object.class, renderer);
			
			/**
			 * 添加鼠标监听，但鼠标点击对应行时，弹出对应数据的编辑窗口
			 */
			table.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					Point p=e.getPoint();
					int clickRow=table.rowAtPoint(p);
					loadEdit(clickRow);
				}
			});
			/**
			 * 禁止编辑
			 */
			table.setEnabled(false);
			table.setBounds(0,cellHeight,this.getWidth(),height);
			this.add(table);
		}
		this.repaint();
	}
	private Object[][] getData(int page){
		if(page>this.page){
			return null;
		}
		/**
		 * 根据页面返回对应索引的数据
		 */
		currentPage=page;
		Object[][] value=new Object[max][col];
		/**
		 * @param　start 返回数据的起始索引号
		 * @param end 返回数据的数量
		 */
		int start=(page-1)*max;
		int end=data.length-start>0?(data.length-start>max?max:data.length-start):0;
		for(int i=0;i<end;i++){
			for(int j=0;j<col;j++){
				value[i][j]=data[start][j];
			}
			start++;
		}
		return value;
	}
	private void loadButton(){
		/**
		 * 自定义按钮
		 */
		int n=4;
		button=new Button[n];
		
		int w=100,h=40;
		int xtemp=this.getWidth()-(w+10)*n-10>>1;
		int ytemp=this.getHeight()-h-20;
		for(int i=0;i<n;i++){
			button[i]=new Button(TEXT[i]);
			button[i].setBounds(xtemp,ytemp,w,h);
			button[i].setType(String.valueOf(i));
			button[i].addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					Button view=(Button)e.getSource();
					int index=Integer.parseInt(view.getType());
					if(button[index].getText().equals(TEXT[0])){
						loadTable(1);
					}
					if(button[index].getText().equals(TEXT[1])){
						if(currentPage>1){
							loadTable(currentPage-1);
						}
					}
					if(button[index].getText().equals(TEXT[2])){
						if(currentPage<page){
							loadTable(currentPage+1);
						}
					}
					if(button[index].getText().equals(TEXT[3])){
						loadTable(page);
					}
				}
			});
			this.add(button[i]);
			xtemp+=w+10;
		}
	}
	public void setMax(int max){
		/**
		 * 设置单页显示数量，重置page和table
		 */
		this.max=max;
		countPage();
		loadTable(currentPage);
	}
	private void loadEdit(int clickRow){
		if(clickRow==-1){
			return;
		}
		
		new EditModule(list.get((currentPage-1)*max+clickRow));
	}
	public void load(){
		init();
	}
	public void setData(List<Record> list){
		this.list=list;
		init();
	}
	private boolean except(String name){
		/**
		 * @param name　字段名，判断改字段是否为需要显示的字段
		 */
		for(int i=0,n=EXCEPT.length;i<n;i++){
			if(EXCEPT[i].equals(name)){
				return false;
			}
		}
		return true;
	}
	public void TableRepaint(){
		for(int i=model.getRowCount()-1;i>=0;i--){
			model.removeRow(i);
		}
		Object[][] temp=getData(currentPage);
		for(int i=0;i<max;i++){
			model.addRow(temp[i]);
		}
		table.repaint();
	}
}
