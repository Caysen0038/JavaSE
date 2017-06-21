package im.service.proxy;
import im.service.impl.*;
import im.service.*;
import im.entity.*;
public class InsertProxy implements InsertService{
	private InsertServiceImpl service=null;
	public InsertProxy(){
		this.service=new InsertServiceImpl();
	}
	@Override
	public int insert(Record[] record){
		int i=service.insert(record);
		service=null;
		return i;
	}
}
