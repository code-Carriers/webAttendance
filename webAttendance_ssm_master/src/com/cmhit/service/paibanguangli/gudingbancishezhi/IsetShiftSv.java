package com.cmhit.service.paibanguangli.gudingbancishezhi;

import java.util.List;

import com.cmhit.core.base.BaseService;
import com.cmhit.po.Shift;
import com.cmhit.po.ShiftChild;

public interface IsetShiftSv extends BaseService<Shift, String>{
	 List<Shift>selectShiftByPositionid(int id);
	 int insertAll(Shift t,List<ShiftChild> listChild);
}
