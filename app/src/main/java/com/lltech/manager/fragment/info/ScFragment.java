package com.lltech.manager.fragment.info;

import android.widget.TextView;

import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.entity.eq.Eq;

/**
 * @ClassName(类名) : ScFragment
 * @Description(描述) : 生产信息
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:04
 */
public class ScFragment extends BaseFragment {

    private TextView PurchaseUserID, Supplier, SupplierUser, SupplierTel, SupplierAddress, ProductionDate;

    @Override
    public int setContentView() {
        return R.layout.detail_eq_sc;
    }

    @Override
    public void initView() {
        PurchaseUserID = $(R.id.PurchaseUserID);
        Supplier = $(R.id.Supplier);
        SupplierUser = $(R.id.SupplierUser);
        SupplierTel = $(R.id.SupplierTel);
        SupplierAddress = $(R.id.SupplierAddress);
        ProductionDate = $(R.id.ProductionDate);
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
        PurchaseUserID.setText(eq.getPurchaseUserID());
        Supplier.setText(eq.getSupplier());
        SupplierUser.setText(eq.getSupplierUser());
        SupplierTel.setText(eq.getSupplierTel());
        SupplierAddress.setText(eq.getSupplierAddress());
        ProductionDate.setText(eq.getProductionDate());
    }
}
