package com.betamall.service;

import com.betamall.dao.ManagerDao;
import com.betamall.dto.ManagerDto;

public class ManagerService {
	public int modifyMgrInfo(ManagerDto managerDto) {
		
		ManagerDao managerDao = ManagerDao.getInstance();
		
		managerDto.setMgrNo(0);
		managerDto.setMgrPwd(null);
		managerDto.setMgrName(null);
		managerDto.setMgrTel(null);
		managerDto.setMgrEmail(null);
		managerDto.setMgrImg(null);
		managerDao.update(managerDto);
		
		return 0;
	}
}
