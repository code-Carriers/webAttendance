package com.cmhit.service.paibanguangli.gudingbancishezhi;

import java.util.List;

import com.cmhit.core.base.BaseService;
import com.cmhit.po.ShiftChild;

public interface IsetShiftChildSv extends BaseService<ShiftChild, String> {
	 List<ShiftChild> selectbyShiftId(int id);
	int deleteByShiftId(int shiftid);
}
