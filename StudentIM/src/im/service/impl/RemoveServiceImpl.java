package im.service.impl;
import im.dao.factory.DAOFactory;
import im.entity.Field;
import im.service.*;
public class RemoveServiceImpl implements RemoveService{

	@Override
	public boolean removce(String table, Field[] field) {
		boolean success=false;
		try{
			success=DAOFactory.getDAO("record").doRemove(table, field);
		}catch(Exception e){
			e.printStackTrace();
		}
		return success;
	}

}
