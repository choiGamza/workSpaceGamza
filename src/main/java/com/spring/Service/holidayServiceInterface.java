package com.spring.Service;

import java.util.List;

import com.spring.DTO.holidayDto;

public interface holidayServiceInterface {

	public int applyHoliday(holidayDto holidaydto);
	public List<holidayDto> holidayList();
	public int processHoliday(String id,String process,String holidaytype);
	public holidayDto showDetail(String id,String holidaytype);
	public List<holidayDto> checkMyHoliday(String id);
	public int deleteHoliday(String id,String holidaytype);
}
