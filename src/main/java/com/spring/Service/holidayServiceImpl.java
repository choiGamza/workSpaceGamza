package com.spring.Service;

import java.util.List;

import com.spring.DAO.holidayDaoImpl;
import com.spring.DTO.holidayDto;

public class holidayServiceImpl implements holidayServiceInterface{

	private holidayDaoImpl holidaydao;
	public holidayDaoImpl getHolidaydao() {
		return holidaydao;
	}
	public void setHolidaydao(holidayDaoImpl holidaydao) {
		this.holidaydao = holidaydao;
	}
	@Override
	public int applyHoliday(holidayDto holidaydto)
	{
		return holidaydao.applyHoliday(holidaydto);
	}
	@Override
	public List<holidayDto> holidayList()
	{
		return holidaydao.holidayList();
	}
	@Override
	public int processHoliday(String id,String process,String holidaytype)
	{
		return holidaydao.processHoliday(id, process,holidaytype);
	}
	@Override
	public holidayDto showDetail(String id,String holidaytype)
	{
		return holidaydao.showDetail(id,holidaytype);
	}
	@Override
	public List<holidayDto> checkMyHoliday(String id)
	{
		return holidaydao.checkMyHoliday(id);
	}
	@Override
	public int deleteHoliday(String id,String holidaytype)
	{
		return holidaydao.deleteHoliday(id, holidaytype);
	}
}
