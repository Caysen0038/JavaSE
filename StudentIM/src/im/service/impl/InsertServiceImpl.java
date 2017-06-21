package im.service.impl;
import im.service.*;
import im.dao.factory.DAOFactory;
import im.entity.*;
public class InsertServiceImpl implements InsertService{
	public InsertServiceImpl(){
		
	}
	@Override
	public int insert(Record[] record){
		/**
		 * 因为需要执行的记录可能会有多条，所以采取数组形式传递，可更改接口重载为单条数据插入
		 */
		int n=0;
		for(int i=0,j=record.length;i<j;i++){
			try{
				DAOFactory.getDAO("record").doInsert(record[i]);
			}catch(Exception e){
				continue;
			}
			n++;
		}
		return n;
	}
}
