package com.lltech.manager.fragment.info;

import android.widget.TextView;

import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.entity.eq.Eq;

/**
 * 
 * @ClassName(类名) : CwFragment
 * @Description(描述) : 财务信息
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:04
 *
 */
public class CwFragment extends BaseFragment {

	private TextView PurchaseDate, CapitalAssetsNo, CapitalAssetsSubject, CostAccounting, CostPresentValue, DepreciationYears, UsedYears, IsUsed;

	@Override
	public int setContentView() {
		return R.layout.detail_eq_cw;
	}

	@Override
	public void initView() {
		PurchaseDate = $(R.id.PurchaseDate);
		CapitalAssetsNo = $(R.id.CapitalAssetsNo);
		CapitalAssetsSubject = $(R.id.CapitalAssetsSubject);
		CostAccounting = $(R.id.CostAccounting);
		CostPresentValue = $(R.id.CostPresentValue);
		DepreciationYears = $(R.id.DepreciationYears);
		UsedYears = $(R.id.UsedYears);
		IsUsed = $(R.id.IsUsed);
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
		PurchaseDate.setText(eq.getPurchaseDate());
		CapitalAssetsNo.setText(eq.getCapitalAssetsNo());
		CapitalAssetsSubject.setText(eq.getCapitalAssetsSubject());
		CostAccounting.setText(eq.getCostAccounting());
		CostPresentValue.setText(eq.getCostPresentValue());
		DepreciationYears.setText(eq.getDepreciationYears());
		UsedYears.setText(eq.getUsedYears());
		IsUsed.setText(eq.getIsUsed());
	}
}
