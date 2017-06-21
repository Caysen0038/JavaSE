package im.service;

import im.entity.Record;

public interface UpdateService extends Service{
	int update(Record[] record);
}
