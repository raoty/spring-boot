package hello.service.db.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.service.db.bean.AppMessage;
import hello.service.db.mapper.AppMessageMapper;

@Service
public class AppMessageService {

	@Autowired
	private AppMessageMapper mapper;
	
	public List<AppMessage> getMessage(String id){
        List<AppMessage> list = new ArrayList<AppMessage>();
        list.add(mapper.selectByPrimaryKey(id));
        return list;
   }
}
