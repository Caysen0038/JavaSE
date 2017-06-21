package im.util;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import im.util.enums.DBInfo;
import im.util.enums.ThemeEnum;

import java.io.File;
import java.io.IOException;
import java.util.*;
public class Config {
	/*
	 * DOM����XML
	 * �ɷ��ã��˴������޸�Ϊ����config��ʼ���������
	 */
	public static final String CONFIG_PATH="config/config.xml";
	public static final String FIELD_PATH="config/fieldName.xml";
	private DocumentBuilderFactory factory=null;
	private DocumentBuilder builder=null;
	private Document doc=null;
	private String path=null;
	private static Config dom=new Config(CONFIG_PATH);
	@Deprecated
	@SuppressWarnings("unused")
	private Config(){
		/*
		 * ��ֹ�ⲿ��ʹ�ã����봫���ļ�·��
		 */
	}
	public Config(String path){
		this.path=path;
		init();
	}
	public static Config getDOM(){
		return dom;
	}
	public void init(){
		/*
		 * ��ʼ�����õ�DOMģ��
		 */
		factory=DocumentBuilderFactory.newInstance();
		try{
			builder=factory.newDocumentBuilder();
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}
		try{
			doc=builder.parse(path);
		}catch(SAXException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void setPath(String path){
		this.path=path;
		try{
			doc=builder.parse(path);
		}catch(SAXException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public String findByTagName(String name){
		/*
		 * ���ݽ������ȡ������
		 */
		NodeList list=doc.getElementsByTagName(name);
		return list.item(0).getFirstChild().getNodeValue().toString();
	}
	public Map<String,String> getDB(){
		/*
		 * �õ�DB������Ϣ
		 */
		setPath(Config.CONFIG_PATH);
		DBInfo[] db=DBInfo.values();
		Map<String,String> info=new HashMap<>();
		String value=null;
		for(int i=0,n=db.length;i<n;i++){
			//System.out.println(db[i].getName());
			value=findByTagName(db[i].getName());
			info.put(db[i].getName(), value);
		}
		return info;
	}
	public List<String> getThemeID(){
		/**
		 * �õ��õ���������
		 */
		setPath(Config.CONFIG_PATH);
		List<String> id=null;
		/**
		 * �õ�theme��������нڵ�
		 */
		NodeList list=doc.getElementsByTagName("theme").item(0).getChildNodes();
		id=new ArrayList<>();
		/**
		 * �õ���һ���ӽڵ㣬��Ĭ������
		 */
		id.add(list.item(1).getFirstChild().getNodeValue());
		Node node=null;
		NamedNodeMap temp=null;
		for(int i=3,n=list.getLength();i<n;i+=2){
			/**
			 * ��ȡ�����������õ����ƣ�����Ϊ�����ID���ԣ�����theme-name
			 */
			node=list.item(i);
			temp=node.getAttributes();
			id.add(temp.item(0).getFirstChild().getNodeValue());
		}
		return id;
	}
	public boolean changeTheme(String id){
		/**
		 * ����Ĭ������
		 */
		setPath(Config.CONFIG_PATH);
		Node node=doc.getElementsByTagName("default-theme").item(0);
		node.getFirstChild().setNodeValue(id);
		return save();
	}
	public Map<String,String> getTheme(){
		/**
		 * �õ�Ĭ��������ʽ��Ϣ
		 */
		setPath(Config.CONFIG_PATH);
		Map<String,String> theme=new HashMap<>();
		String name=null;
		/**
		 * @param name Ĭ����������
		 */
		NodeList list=doc.getElementsByTagName("default-theme");
		name=list.item(0).getFirstChild().getNodeValue();
		list=doc.getElementsByTagName("theme-set");
		Node node=null;
		NamedNodeMap att=null;
		int i,n;
		for(i=0,n=list.getLength();i<n;i++){
			/*
			 * ����<theme-set>�ڵ���id������name��ͬ�Ľڵ�
			 */
			node=list.item(i);
			att=node.getAttributes();
			if(att.item(0).getNodeName().equals("id")&&att.item(0).getNodeValue().equals(name)){
				break;
			}
		}
		if(i==n){
			/**
			 * ��ֹxml�������ô������û��ѯ��ָ��������ֱ��ʹ�õ�һ������
			 */
			node=list.item(0);
		}
		/**
		 * @param list �������ڵ��µ������ӽڵ�
		 */
		list=node.getChildNodes();
		Node temp=null;
		NodeList nl=null;
		StringBuilder color;
		for(i=1,n=list.getLength();i<n;i+=2){
			/**
			 * ��Ϊ�ӽڵ������������text_node��element_node
			 * ����ֻ��Ҫelement_Node�µ���Ϣ������i+=2
			 */
			temp=list.item(i);
			nl=temp.getChildNodes();
			/**
			 * ͨ���ӽڵ����жϵ�ǰ�ڵ����ı��ڵ㻹����ɫ�ڵ�
			 * �˴�ָ���ı��ڵ���ָ�ڵ��а�����һ���ı���Ϣ
			 * �˴�ָ����ɫ�ڵ���ָ�ڵ��а����������ӽڵ㣬�ֱ������RGB������ɫ
			 */
			if(nl.getLength()==1){
				theme.put(temp.getNodeName(),temp.getFirstChild().getNodeValue());
			}else{
				color=new StringBuilder();
				/**
				 * ����������ɫ�ڵ��������ڵ��RGB��ɫֵ
				 */
				for(int j=1,m=nl.getLength();j<m;j+=2){
					color.append(nl.item(j).getFirstChild().getNodeValue());
					if(j<m-2){
						color.append(",");
					}
				}
				theme.put(temp.getNodeName(), color.toString());
			}
		}
		return theme;
	}
	public Map<String,String> getTheme(String id){
		/**
		 * �õ�Ĭ��������ʽ��Ϣ
		 */
		setPath(Config.CONFIG_PATH);
		Map<String,String> theme=new HashMap<>();
		/**
		 * @param name Ĭ����������
		 */
		NodeList list=doc.getElementsByTagName("theme-set");
		Node node=null;
		NamedNodeMap att=null;
		int i,n;
		for(i=0,n=list.getLength();i<n;i++){
			/*
			 * ����<theme-set>�ڵ���id������name��ͬ�Ľڵ�
			 */
			node=list.item(i);
			att=node.getAttributes();
			if(att.item(0).getFirstChild().getNodeValue().equals(id)){
				break;
			}
		}
		if(i==n){
			/**
			 * ��ֹxml�������ô������û��ѯ��ָ��������ֱ��ʹ�õ�һ������
			 */
			node=list.item(0);
		}
		/**
		 * @param list �������ڵ��µ������ӽڵ�
		 */
		list=node.getChildNodes();
		Node temp=null;
		NodeList nl=null;
		StringBuilder color;
		for(i=1,n=list.getLength();i<n;i+=2){
			/**
			 * ��Ϊ�ӽڵ������������text_node��element_node
			 * ����ֻ��Ҫelement_Node�µ���Ϣ������i+=2
			 */
			temp=list.item(i);
			nl=temp.getChildNodes();
			/**
			 * ͨ���ӽڵ����жϵ�ǰ�ڵ����ı��ڵ㻹����ɫ�ڵ�
			 * �˴�ָ���ı��ڵ���ָ�ڵ��а�����һ���ı���Ϣ
			 * �˴�ָ����ɫ�ڵ���ָ�ڵ��а����������ӽڵ㣬�ֱ������RGB������ɫ
			 */
			if(nl.getLength()==1){
				theme.put(temp.getNodeName(),temp.getFirstChild().getNodeValue());
			}else{
				color=new StringBuilder();
				/**
				 * ����������ɫ�ڵ��������ڵ��RGB��ɫֵ
				 */
				for(int j=1,m=nl.getLength();j<m;j+=2){
					color.append(nl.item(j).getFirstChild().getNodeValue());
					if(j<m-2){
						color.append(",");
					}
				}
				theme.put(temp.getNodeName(), color.toString());
			}
		}
		return theme;
	}
	public Map<String,String> getFieldName(String table){
		/**
		 * ���ݱ����õ���Ӧ���ֶ�������Ϣ��key=�ֶ�����value=�ֶ����ĺ���
		 */
		setPath(Config.FIELD_PATH);
		Map<String,String> info=null;
		/**
		 * �õ�ָ�����µ������ӽڵ�
		 */
		NodeList list=doc.getElementsByTagName(table).item(0).getChildNodes();
		Node node=null;
		info=new HashMap<>();
		for(int i=1,n=list.getLength();i<n;i+=2){
			/**
			 * ���������ƺͽ��ֵ
			 */
			node=list.item(i);
			info.put(node.getNodeName(), node.getFirstChild().getNodeValue());
		}
		return info;
	}
	public List<String> getField(String table){
		/**
		 * ���ݱ����õ���Ӧ���ֶ�����
		 */
		setPath(Config.FIELD_PATH);
		List<String> info=null;
		NodeList list=doc.getElementsByTagName(table).item(0).getChildNodes();
		Node node=null;
		info=new ArrayList<>();
		for(int i=1,n=list.getLength();i<n;i+=2){
			/**
			 * ����������
			 */
			node=list.item(i);
			info.add(node.getNodeName());
		}
		return info;
	}
	public boolean insertTheme(Map<String,String> theme){
		/**
		 * ���������⣬Ϊ������չ�Զ�������Ԥ��
		 */
		setPath(Config.CONFIG_PATH);
		ThemeEnum[] info=ThemeEnum.values();
		Element element=(Element)doc.getElementsByTagName("theme").item(0);
		Element[] e=new Element[theme.size()];
		Element set=doc.createElement("theme-set");
		Element r=null;
		Element g=null;
		Element b=null;
		set.setAttribute("id", theme.get(info[0].getName()));
		for(int i=0,n=e.length;i<n;i++){
			e[i]=doc.createElement(info[i].getName());
			if(i==0||i==n-1){
				e[i].appendChild(doc.createTextNode(theme.get(info[i].getName())));
			}else{
				r=doc.createElement("red");
				g=doc.createElement("green");
				b=doc.createElement("blue");
				String[] color=theme.get(info[i].getName()).split(",");
				r.appendChild(doc.createTextNode(color[0]));
				g.appendChild(doc.createTextNode(color[1]));
				b.appendChild(doc.createTextNode(color[2]));
				e[i].appendChild(r);
				e[i].appendChild(g);
				e[i].appendChild(b);
			}
			set.appendChild(e[i]);
		}
		element.appendChild(set);
		save();
		return true;
	}
	public boolean updateTheme(Map<String,String> theme){
		/**
		 * ������������
		 */
		setPath(Config.CONFIG_PATH);
		ThemeEnum[] info=ThemeEnum.values();
		Element element=(Element)doc.getElementsByTagName("theme").item(0);
		NodeList list=element.getChildNodes();
		Node node=null;
		String id=null;
		boolean find=false;
		for(int i=3,n=list.getLength();i<n;i+=2){
			node=list.item(i);
			id=node.getAttributes().item(0).getNodeValue();
			if(!id.equals(theme.get(info[0].getName()))){
				continue;
			}
			find=true;
			break;
		}
		if(!find){
			return false;
		}
		list=node.getChildNodes();
		NodeList temp=null;
		for(int i=3,n=list.getLength(),m=1;i<n;i+=2){
			node=list.item(i);
			if(i==n-2){
				node.getFirstChild().setNodeValue(theme.get(info[m++].getName()));
			}else{
				String[] color=theme.get(info[m++].getName()).split(",");
				temp=node.getChildNodes();
				System.out.println();
				temp.item(1).getFirstChild().setNodeValue(color[0]);
				temp.item(3).getFirstChild().setNodeValue(color[1]);
				temp.item(5).getFirstChild().setNodeValue(color[2]);
			}
		}
		save();
		return true;
	}
	public boolean removeTheme(String id){
		/**
		 * ɾ��ָ�����⣬������ɾ���Դ�����������
		 */
		if(id.equals("blue")||id.equals("green")||id.equals("dark")){
			return false;
		}
		setPath(Config.CONFIG_PATH);
		Element element=(Element)doc.getElementsByTagName("theme").item(0);
		NodeList list=element.getChildNodes();
		String defaultName=list.item(1).getFirstChild().getNodeValue();
		Node node=null;
		String name=null;
		boolean find=false;
		for(int i=3,n=list.getLength();i<n;i+=2){
			node=list.item(i);
			name=node.getAttributes().item(0).getNodeValue();
			if(name.equals(id)){
				find=true;
				break;
			}
		}
		if(find){
			if(name.equals(defaultName)){
				list.item(1).getFirstChild().setNodeValue("dark");
			}
			element.removeChild(node);
			save();
			return true;
		}
		return false;
	}
	public boolean updateDB(Map<String,String> info){
		/**
		 * �������ݿ�������Ϣ
		 */
		setPath(Config.CONFIG_PATH);
		DBInfo[] name=DBInfo.values();
		NodeList list=doc.getElementsByTagName("database").item(0).getChildNodes();
		Node node=null;
		String value;
		for(int i=1,n=list.getLength(),m=0;i<n;i+=2){
			node=list.item(i);
			value=info.get(name[m++].getName());
			if(value!=null){
				node.getFirstChild().setNodeValue(value);
			}
		}
		return save();
	}
	private boolean save(){
		/**
		 * ���±���doc�򿪵�xml�ļ�
		 */
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		try {
			transformer.transform(source, result);
			return true;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
//	@SuppressWarnings("unused")
//	public static void main(String[] args){
//		Config c=Config.getDOM();
//		c.setPath(Config.FIELD_PATH);
//		Map<String,String> info=new HashMap<>();
//		ThemeEnum[] t=ThemeEnum.values();
//		DBInfo[] name=DBInfo.values();
//		int i=0;
//		info.put(t[i++].getName(), "user");
//		info.put(t[i++].getName(), "0,0,0");
//		info.put(t[i++].getName(), "0,0,0");
//		info.put(t[i++].getName(), "0,0,0");
//		info.put(t[i++].getName(), "0,0,0");
//		info.put(t[i++].getName(), "0,0,0");
//		info.put(t[i++].getName(), "����");
//		info.put(DBInfo.PORT.getName(), "1");
//		c.updateDB(info);
//		c.changeTheme("blue");
//	}
}
