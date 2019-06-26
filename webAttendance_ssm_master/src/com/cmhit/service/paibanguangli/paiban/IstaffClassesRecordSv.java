package com.cmhit.service.paibanguangli.paiban;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmhit.core.base.BaseService;
import com.cmhit.po.StaffClassesRecord;
import com.cmhit.vo.GengGaiPaiPanXinXi;
import com.cmhit.vo.YuanGongFenBanJiLu;
import com.cmhit.vo.paibanchaxunVo;

public interface IstaffClassesRecordSv extends BaseService<StaffClassesRecord, String>{
	 List<paibanchaxunVo> selectClassGroup();
	  /** 查询班组员工分班记录 **/
	  List<YuanGongFenBanJiLu>  selectGroupRecord(int goupid,String bginDate,String endDate);
	  /**更改班组排班信息**/
      public int updatePaiBanXinXi(List<GengGaiPaiPanXinXi>listInsert,int[] deleteIds,List<GengGaiPaiPanXinXi>listUpdate,int groupid);
		 
      /* 按日期添加排班*/
      public int riqiPaiban(List<GengGaiPaiPanXinXi>listInsert,int groupid);
    	
}
