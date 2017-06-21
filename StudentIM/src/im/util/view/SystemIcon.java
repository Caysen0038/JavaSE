package im.util.view;
import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.imageio.ImageIO;
import java.io.*;
public class SystemIcon {
	/**
	 * ϵͳ����ͼ��������
	 */
	private TrayIcon trayIcon=null;
	private SystemTray sysTray=null;
	private File file=null;
	@SuppressWarnings("unused")
	private SystemIcon(){
		//��ֹʹ�ã�ʵ�������봫��ͼ��·��
	}
	public SystemIcon(String path){
		/**
		 * @pram path ͼ���ļ�·��
		 */
		this(new File(path));
	}
	public SystemIcon(File file){
		if(file!=null){
			this.file=file;
		}else{
			throw new NullPointerException("ͼ��·���쳣");
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
		 * ͨ���ⲿ��������ͼ�������¼���ʵ�ֹ������ⲿ����
		 */
		trayIcon.addMouseListener(mouseEvent);
	}
}
