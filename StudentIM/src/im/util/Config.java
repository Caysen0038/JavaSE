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
	 * DOM解析XML
	 * 可泛用，此处定制修改为解析config初始化软件配置
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
		 * 禁止外部类使用，必须传入文件路径
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
		 * 初始化，得到DOM模型
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
		 * 根据结点名称取出内容
		 */
		NodeList list=doc.getElementsByTagName(name);
		return list.item(0).getFirstChild().getNodeValue().toString();
	}
	public Map<String,String> getDB(){
		/*
		 * 得到DB配置信息
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
		 * 得到得到主题名称
		 */
		setPath(Config.CONFIG_PATH);
		List<String> id=null;
		/**
		 * 得到theme结点下所有节点
		 */
		NodeList list=doc.getElementsByTagName("theme").item(0).getChildNodes();
		id=new ArrayList<>();
		/**
		 * 得到第一个子节点，即默认主题
		 */
		id.add(list.item(1).getFirstChild().getNodeValue());
		Node node=null;
		NamedNodeMap temp=null;
		for(int i=3,n=list.getLength();i<n;i+=2){
			/**
			 * 获取各个主题配置的名称，名称为主题的ID属性，并非theme-name
			 */
			node=list.item(i);
			temp=node.getAttributes();
			id.add(temp.item(0).getFirstChild().getNodeValue());
		}
		return id;
	}
	public boolean changeTheme(String id){
		/**
		 * 更新默认主题
		 */
		setPath(Config.CONFIG_PATH);
		Node node=doc.getElementsByTagName("default-theme").item(0);
		node.getFirstChild().setNodeValue(id);
		return save();
	}
	public Map<String,String> getTheme(){
		/**
		 * 得到默认主题样式信息
		 */
		setPath(Config.CONFIG_PATH);
		Map<String,String> theme=new HashMap<>();
		String name=null;
		/**
		 * @param name 默认主题名称
		 */
		NodeList list=doc.getElementsByTagName("default-theme");
		name=list.item(0).getFirstChild().getNodeValue();
		list=doc.getElementsByTagName("theme-set");
		Node node=null;
		NamedNodeMap att=null;
		int i,n;
		for(i=0,n=list.getLength();i<n;i++){
			/*
			 * 查找<theme-set>节点中id属性与name相同的节点
			 */
			node=list.item(i);
			att=node.getAttributes();
			if(att.item(0).getNodeName().equals("id")&&att.item(0).getNodeValue().equals(name)){
				break;
			}
		}
		if(i==n){
			/**
			 * 防止xml主题设置错误，如果没查询到指定主题则直接使用第一个主题
			 */
			node=list.item(0);
		}
		/**
		 * @param list 获得主题节点下的所有子节点
		 */
		list=node.getChildNodes();
		Node temp=null;
		NodeList nl=null;
		StringBuilder color;
		for(i=1,n=list.getLength();i<n;i+=2){
			/**
			 * 因为子节点中相隔包含了text_node和element_node
			 * 这里只需要element_Node下的信息，所以i+=2
			 */
			temp=list.item(i);
			nl=temp.getChildNodes();
			/**
			 * 通过子节点数判断当前节点是文本节点还是颜色节点
			 * 此处指的文本节点是指节点中包含了一个文本信息
			 * 此处指的颜色节点是指节点中包含了三个子节点，分别包含了RGB三种颜色
			 */
			if(nl.getLength()==1){
				theme.put(temp.getNodeName(),temp.getFirstChild().getNodeValue());
			}else{
				color=new StringBuilder();
				/**
				 * 迭代访问颜色节点中三个节点的RGB颜色值
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
		 * 得到默认主题样式信息
		 */
		setPath(Config.CONFIG_PATH);
		Map<String,String> theme=new HashMap<>();
		/**
		 * @param name 默认主题名称
		 */
		NodeList list=doc.getElementsByTagName("theme-set");
		Node node=null;
		NamedNodeMap att=null;
		int i,n;
		for(i=0,n=list.getLength();i<n;i++){
			/*
			 * 查找<theme-set>节点中id属性与name相同的节点
			 */
			node=list.item(i);
			att=node.getAttributes();
			if(att.item(0).getFirstChild().getNodeValue().equals(id)){
				break;
			}
		}
		if(i==n){
			/**
			 * 防止xml主题设置错误，如果没查询到指定主题则直接使用第一个主题
			 */
			node=list.item(0);
		}
		/**
		 * @param list 获得主题节点下的所有子节点
		 */
		list=node.getChildNodes();
		Node temp=null;
		NodeList nl=null;
		StringBuilder color;
		for(i=1,n=list.getLength();i<n;i+=2){
			/**
			 * 因为子节点中相隔包含了text_node和element_node
			 * 这里只需要element_Node下的信息，所以i+=2
			 */
			temp=list.item(i);
			nl=temp.getChildNodes();
			/**
			 * 通过子节点数判断当前节点是文本节点还是颜色节点
			 * 此处指的文本节点是指节点中包含了一个文本信息
			 * 此处指的颜色节点是指节点中包含了三个子节点，分别包含了RGB三种颜色
			 */
			if(nl.getLength()==1){
				theme.put(temp.getNodeName(),temp.getFirstChild().getNodeValue());
			}else{
				color=new StringBuilder();
				/**
				 * 迭代访问颜色节点中三个节点的RGB颜色值
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
		 * 根据表明得到对应的字段名称信息，key=字段名，value=字段中文含义
		 */
		setPath(Config.FIELD_PATH);
		Map<String,String> info=null;
		/**
		 * 得到指定表下的所有子节点
		 */
		NodeList list=doc.getElementsByTagName(table).item(0).getChildNodes();
		Node node=null;
		info=new HashMap<>();
		for(int i=1,n=list.getLength();i<n;i+=2){
			/**
			 * 存入结点名称和结点值
			 */
			node=list.item(i);
			info.put(node.getNodeName(), node.getFirstChild().getNodeValue());
		}
		return info;
	}
	public List<String> getField(String table){
		/**
		 * 根据表名得到对应的字段名称
		 */
		setPath(Config.FIELD_PATH);
		List<String> info=null;
		NodeList list=doc.getElementsByTagName(table).item(0).getChildNodes();
		Node node=null;
		info=new ArrayList<>();
		for(int i=1,n=list.getLength();i<n;i+=2){
			/**
			 * 存入结点名称
			 */
			node=list.item(i);
			info.add(node.getNodeName());
		}
		return info;
	}
	public boolean insertTheme(Map<String,String> theme){
		/**
		 * 插入新主题，为后期扩展自定义主题预留
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
		 * 更新主题配置
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
		 * 删除指定主题，但不能删除自带的三个主题
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
		 * 更新数据库配置信息
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
		 * 重新保存doc打开的xml文件
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
//		info.put(t[i++].getName(), "黑体");
//		info.put(DBInfo.PORT.getName(), "1");
//		c.updateDB(info);
//		c.changeTheme("blue");
//	}
}
