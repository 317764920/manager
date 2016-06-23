package com.lltech.manager.fragment.info;

import android.widget.TextView;

import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.entity.eq.Eq;

/**
 * 
 * @ClassName(类名) : ByFragment
 * @Description(描述) : 保养信息
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:04
 *
 */
public class ByFragment extends BaseFragment {

	private TextView IsMaintenance, MaintenanceStandardsID, MaintenanceCycle, RemindUsers, MaintenanceBeginTime, MaintenanceLastTime;

	@Override
	public int setContentView() {
		return R.layout.detail_eq_by;
	}

	@Override
	public void initView() {
		IsMaintenance = $(R.id.IsMaintenance);
		MaintenanceStandardsID = $(R.id.MaintenanceStandardsID);
		MaintenanceCycle = $(R.id.MaintenanceCycle);
		RemindUsers = $(R.id.RemindUsers);
		MaintenanceBeginTime = $(R.id.MaintenanceBeginTime);
		MaintenanceLastTime = $(R.id.MaintenanceLastTime);
	}

	@Override
	public void initListener() {

	}

	@Override
	public void initConfig() {

	}

	@Override
	public void initData() {
		Eq eq = (Eq) this.getArguments().getSerializable("eq");
		IsMaintenance.setText(eq.getIsMaintenance());
		MaintenanceStandardsID.setText(eq.getMaintenanceStandardsID());
		MaintenanceCycle.setText(eq.getMaintenanceCycle());
		RemindUsers.setText(eq.getRemindUsers());
		MaintenanceBeginTime.setText(eq.getMaintenanceBeginTime());
		MaintenanceLastTime.setText(eq.getMaintenanceLastTime());
	}
}
