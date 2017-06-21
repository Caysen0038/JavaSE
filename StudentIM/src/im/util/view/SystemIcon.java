package im.util.view;
import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.imageio.ImageIO;
import java.io.*;
public class SystemIcon {
	/**
	 * 系统托盘图标设置类
	 */
	private TrayIcon trayIcon=null;
	private SystemTray sysTray=null;
	private File file=null;
	@SuppressWarnings("unused")
	private SystemIcon(){
		//禁止使用，实例化必须传入图标路径
	}
	public SystemIcon(String path){
		/**
		 * @pram path 图标文件路径
		 */
		this(new File(path));
	}
	public SystemIcon(File file){
		if(file!=null){
			this.file=file;
		}else{
			throw new NullPointerException("图标路径异常");
		}
		try{
			init();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void init() throws Exception{
		Image img=ImageIO.read(file);
		trayIcon=new TrayIcon(img);
		sysTray=SystemTray.getSystemTray();
		sysTray.add(trayIcon);
	}
	public void addMouseListener(MouseAdapter mouseEvent){
		/**
		 * 通过外部设置托盘图标的鼠标事件，实现功能又外部决定
		 */
		trayIcon.addMouseListener(mouseEvent);
	}
}
