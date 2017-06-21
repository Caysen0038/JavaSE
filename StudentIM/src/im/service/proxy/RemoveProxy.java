package im.service.proxy;
import im.entity.Field;
import im.service.*;
import im.service.impl.*;
public class RemoveProxy implements RemoveService{
	private RemoveServiceImpl service=null;
	public RemoveProxy(){
		service=new RemoveServiceImpl();
	}
	@Override
	public boolean removce(String table, Field[] field) {
		if(table==null||"".equals(table)){
			return false;
		}
		if(field.length<1){
			return false;
		}
		boolean success=service.removce(table, field);
		service=null;
		return success;
	}
	
	
}
