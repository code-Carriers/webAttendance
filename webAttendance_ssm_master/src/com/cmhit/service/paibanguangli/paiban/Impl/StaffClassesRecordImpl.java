package com.cmhit.service.paibanguangli.paiban.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmhit.dao.StaffClassesRecordMapper;
import com.cmhit.po.StaffClassesRecord;
import com.cmhit.service.paibanguangli.paiban.IstaffClassesRecordSv;
import com.cmhit.vo.GengGaiPaiPanXinXi;
import com.cmhit.vo.YuanGongFenBanJiLu;
import com.cmhit.vo.paibanchaxunVo;

import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Var;
/*import com.sun.org.apache.regexp.internal.recompile;*/
@Transactional
@Service("istaffClassesRecord")
public class StaffClassesRecordImpl implements IstaffClassesRecordSv {
@Autowired
StaffClassesRecordMapper staffClassesRecordDao;
	@Override
	public StaffClassesRecord selectById(int id) {
		return null;
	}

	@Override
	public int insert(StaffClassesRecord t) {
	 
		return 0;
	}

	@Override
	public int update(StaffClassesRecord t) {
	 
		return 0;
	}

	@Override
	public int delete(int id) {
	
		return 0;
	}
      /**查询班组信息**/
	public List<paibanchaxunVo> selectClassGroup() {
	 List<paibanchaxunVo>banzuList= staffClassesRecordDao.selectClassGroup();
		return banzuList;
	}
	  /** 查询班组员工分班记录 **/
   public List<YuanGongFenBanJiLu> selectGroupRecord(int goupid,String bginDate, String endDate) {
	   List<YuanGongFenBanJiLu> list= staffClassesRecordDao.selectGroupRecord(goupid, bginDate, endDate);
	   return list;
		}

	public int updatePaiBanXinXi(List<GengGaiPaiPanXinXi> listInsert,int[] deleteIds,List<GengGaiPaiPanXinXi> listUpdate,int groupid) {
	int i=	staffClassesRecordDao.updatePaiBanXinXi(listInsert, deleteIds, listUpdate,groupid);
	return i;
	}

	   /* 按日期添加排班*/
	public int riqiPaiban(List<GengGaiPaiPanXinXi>listInsert, int groupid){
		int i=	staffClassesRecordDao.riqiPaiban(listInsert, groupid);
		return i;
	}
	 
}
