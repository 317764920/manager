package com.lltech.manager.fragment.info;

import android.widget.TextView;

import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.entity.eq.Eq;

/**
 * @ClassName(类名) : JbFragment
 * @Description(描述) : 基本信息
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:04
 */
public class JbFragment extends BaseFragment {

    private TextView EquipmentNo, EquipmentName, Brand, Model, MeasurementUnit, SystemCode, InstallPlace, UseOrganization, ResponsibilityDep,
            ResponsibilityUser, TypeCode, PutIntoUseDate, IsControl, Status;

    @Override
    public int setContentView() {
        return R.layout.detail_eq_jb;
    }

    @Override
    public void initView() {
        EquipmentNo = $(R.id.EquipmentNo);
        EquipmentName = $(R.id.EquipmentName);
        Brand = $(R.id.Brand);
        Model = $(R.id.Model);
        MeasurementUnit = $(R.id.MeasurementUnit);
        SystemCode = $(R.id.SystemCode);
        InstallPlace = $(R.id.InstallPlace);
        UseOrganization = $(R.id.UseOrganization);
        ResponsibilityDep = $(R.id.ResponsibilityDep);
        ResponsibilityUser = $(R.id.ResponsibilityUser);
        TypeCode = $(R.id.TypeCode);
        PutIntoUseDate = $(R.id.PutIntoUseDate);
        IsControl = $(R.id.IsControl);
        Status = $(R.id.Status);
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
        EquipmentNo.setText(eq.getEquipmentNo());
        EquipmentName.setText(eq.getEquipmentName());
        Brand.setText(eq.getBrand());
        Model.setText(eq.getModel());
        MeasurementUnit.setText(eq.getMeasurementUnit());
        SystemCode.setText(eq.getSystemCode());
        InstallPlace.setText(eq.getInstallPlace());
        UseOrganization.setText(eq.getUseOrganization());
        ResponsibilityDep.setText(eq.getResponsibilityDep());
        ResponsibilityUser.setText(eq.getResponsibilityUser());
        TypeCode.setText(eq.getTypeCode());
        PutIntoUseDate.setText(eq.getPutIntoUseDate());
        IsControl.setText(eq.getIsControl());
        Status.setText(eq.getStatus());
    }
}
