package im.service.impl;

import im.service.*;
import java.util.*;
import im.dao.factory.DAOFactory;
import im.entity.*;

public class QueryServiceImpl implements QueryService{
	
	public QueryServiceImpl(){
		
	}
	@Override
	public List<Record> query(String table,Field... field) {
		List<Record> list=null;
		try{
			list=DAOFactory.getDAO("record").findByKeyWord(table,field);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
