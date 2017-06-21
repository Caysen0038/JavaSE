package im.view.service;

import im.entity.Record;
import im.service.proxy.InsertProxy;
import im.service.proxy.QueryProxy;
import im.service.proxy.RemoveProxy;
import im.service.proxy.UpdateProxy;

public class Save{
	
	private InsertProxy insert=null;
	private static Save save=new Save();
	private UpdateProxy update=null;
	private RemoveProxy remove=null;
	private QueryProxy query=null;
	private Save(){
		insert=new InsertProxy();
		update=new UpdateProxy();
		remove=new RemoveProxy();
		query=new QueryProxy();
	}
	public static Save getSave(){
		return save;
	}
	public boolean save(Record record){
		if(query.query(record.getTable(), record.getField(0))!=null){
			return create(record);
		}
		int n=update.update(new Record[]{record});
		if(n==1){
			return true;
		}
		return false;
	}
	public boolean save(Record[] record){
		int n=record.length;
		int m=0;
		for(int i=0;i<n;i++){
			if(save(record[i])){
				m++;
			}
		}
		if(n==m){
			return true;
		}
		return false;
	}
	public boolean remove(Record record){
		remove=new RemoveProxy();
		boolean success=remove.removce(record.getTable(), record.getFields());
		remove=null;
		return success;
	}
	private boolean create(Record record){
		int n=insert.insert(new Record[]{record});
		if(n==1){
			return true;
		}
		return false;
	}
}
