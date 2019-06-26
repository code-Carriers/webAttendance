package com.cmhit.service.paibanguangli.bancishezhi;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmhit.core.base.BaseService;
import com.cmhit.po.Positionclasses;
import com.cmhit.po.StaffPosition;
import com.cmhit.vo.Number;

public interface IsetClassesSv extends BaseService<Positionclasses, String>{
	/**查询班次**/
	public List<Positionclasses> selectPositionclasses();
   /** 查询班次和班段信息**/
	public List<Positionclasses> selectClass();
	/**查询该班次是否正在使用中**/
    int selectClassUseNo(int positionClassesId);
    
}
