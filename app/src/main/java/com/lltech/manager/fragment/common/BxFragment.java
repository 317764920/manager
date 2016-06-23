package com.lltech.manager.fragment.common;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.kyleduo.switchbutton.SwitchButton;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.Recorder;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.mutiImageSelector.MultiImageSelectorActivity;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.DateUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.MediaManager;
import com.lcx.mysdk.utils.ResultCode;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lcx.mysdk.view.AudioRecordButton;
import com.lcx.mysdk.view.DateTimePicker;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.dialog.EquipmentAty;
import com.lltech.manager.activity.dialog.StoreyAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.BxState;
import com.lltech.manager.common.DiaLogCode;
import com.lltech.manager.common.Mark;
import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.MyData;
import com.lltech.manager.view.BxView;
import com.lltech.manager.widget.BottomWindow;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;
import com.lltech.manager.widget.UploadFileView;
import com.lltech.manager.widget.UploadVoiceView;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName(类名) : BxFragment
 * @Description(描述) : 编辑报修单
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月17日 14:04
 */
public class BxFragment extends BaseFragment implements View.OnClickListener, BxView {
    private BaseApplication application = BaseApplication.getApplication();
    private EditText edit_theme, edit_fault, edit_repair_person, edit_address, edit_phone;
    private TextView txt_area, txt_repair_time, eq, txt_state, txt_state_time;
    private TopBar topBar;
    private SwitchButton open;
    private LinearLayout linear_add;
    private AudioRecordButton button;
    private RelativeLayout rela_progress;
    private BottomWindow bottomWindow;
    private Button keep, delete, cancel;
    private UploadFileView uploadFileView;
    private UploadVoiceView uploadVoiceView;
    private Bx bx;
    private HashMap<String, String> map = new HashMap<String, String>();
    private BxBiz bxBiz = new BxBiz(this);

    @Override
    public int setContentView() {
        return R.layout.detail_bx;
    }

    @Override
    public void initView() {
        edit_theme = $(R.id.edit_theme);
        edit_fault = $(R.id.edit_fault);
        edit_repair_person = $(R.id.edit_repair_person);
        edit_address = $(R.id.edit_address);
        edit_phone = $(R.id.edit_phone);
        txt_area = $(R.id.txt_area);
        txt_repair_time = $(R.id.txt_repair_time);
        topBar = $(R.id.top);
        uploadFileView = $(R.id.uploadFileView);
        uploadVoiceView = $(R.id.uploadVoiceView);
        open = $(R.id.open);
        linear_add = $(R.id.linear_add);
        button = $(R.id.recordButton);
        eq = $(R.id.eq);
        txt_state = $(R.id.txt_state);
        txt_state_time = $(R.id.txt_state_time);
        rela_progress = $(R.id.rela_progress);
        bottomWindow = new BottomWindow(getActivity(), R.layout.bottom_window_bx);
        keep = bottomWindow.getView(R.id.keep);
        delete = bottomWindow.getView(R.id.delete);
        cancel = bottomWindow.getView(R.id.cancel);
    }

    @Override
    public void initListener() {
        keep.setOnClickListener(this);
        delete.setOnClickListener(this);
        cancel.setOnClickListener(this);
        txt_repair_time.setOnClickListener(this);
        eq.setOnClickListener(this);
        txt_area.setOnClickListener(this);
        uploadFileView.setOnSelectPicListener(new UploadFileView.OnSelectPicListener() {
            @Override
            public void onSelectClick(View v) {
                checkPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
            }
        });
        open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linear_add.setVisibility(View.VISIBLE);
                } else {
                    linear_add.setVisibility(View.GONE);
                }

            }
        });
        button.setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {

            @Override
            public void onFinished(float seconds, String filePath) {
                Recorder recorder = new Recorder(seconds, filePath);
                uploadVoiceView.uploadVoice(recorder);
            }
        });
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (application.getOperMark()) {
                    case Mark.OperMark.ADD:
                        save();
                        break;
                    case Mark.OperMark.EDIT:
                        bottomWindow.show(topBar);
                        break;
                }

            }

            @Override
            public void onLeftClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void initConfig() {
        topBar.setTopText("报修信息");
        setWidget();
    }

    private void setWidget() {
        switch (application.getOperMark()) {
            case Mark.OperMark.ADD:
                topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
                topBar.getRightBtn2().setText(R.string.submit);
                topBar.getRightBtn2().setBackground(null);
                rela_progress.setVisibility(View.GONE);
                break;
            case Mark.OperMark.EDIT:
                switch (bx.getState()) {
                    case BxState.N:
                        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
                        topBar.setBtnType(TopBar.RIGHT2, TopBar.MORE);
                        rela_progress.setVisibility(View.GONE);
                        button.setVisibility(View.VISIBLE);
                        uploadFileView.setEnable(true);
                        if (User.YY.equals(MyData.getUser(User.class).getUserType())) {
                            topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
                            topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
                            button.setVisibility(View.GONE);
                            uploadFileView.setEnable(false);
                            uploadVoiceView.setEnabled(false);
                            setTableChildFalse();
                        }
                        break;
                    default:
                        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
                        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
                        rela_progress.setVisibility(View.VISIBLE);
                        button.setVisibility(View.GONE);
                        uploadFileView.setEnable(false);
                        uploadVoiceView.setEnabled(false);
                        setTableChildFalse();
                        break;
                }
                break;
        }
    }

    @Override
    public void initData() {
        switch (application.getOperMark()) {
            case Mark.OperMark.ADD:
                edit_repair_person.setText(MyData.getUser(User.class).getUserName());
                if (CommonUtil.isNotEmpty(getArguments())) {
                    Eq seq = (Eq) getArguments().getSerializable("eq");
                    StringBuffer sb = new StringBuffer();
                    sb.append(seq.getEquipmentName());
                    sb.append("\n");
                    sb.append("编号：");
                    sb.append(seq.getEquipmentNo());
                    sb.append("\n");
                    sb.append("品牌：");
                    sb.append(seq.getBrand());
                    sb.append("\n");
                    sb.append("规格：");
                    sb.append(seq.getModel());
                    eq.setText(sb.toString());
                    linear_add.setVisibility(View.VISIBLE);
                    open.setChecked(true);
                }
                break;
            case Mark.OperMark.EDIT:
                bx = (Bx) getArguments().getSerializable("item");
                startProgressDialog();
                bxBiz.getBxEntity(bx.getFaultReportID());
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MediaManager.release();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //预计维修时间
            case R.id.txt_repair_time:
                DateTimePicker.getInstance().setTime(getActivity(), txt_repair_time);
                break;
            case R.id.keep:
                startProgressDialog();
                save();
                break;
            case R.id.delete:
                startProgressDialog();
                bxBiz.delBx(bx);
                break;
            case R.id.cancel:
                //取消底部菜单
                bottomWindow.dismiss();
                break;
            //选择区域
            case R.id.txt_area:
                Intent intent1 = new Intent(getActivity(), StoreyAty.class);
                startActivityForResult(intent1, DiaLogCode.STOREY);
                break;
            //添加设备
            case R.id.eq:
                Intent intent = new Intent(getActivity(), EquipmentAty.class);
                startActivityForResult(intent, DiaLogCode.EQ);
                break;
        }
    }

    private void save() {
        if (CommonUtil.isEmpty(bx)) {
            bx = new Bx();
        }
        bx.setFaultReportTitle(edit_theme.getText().toString());
        bx.setReportUser(MyData.getUser(User.class).getEmployeeID());
        bx.setFaultDescription(edit_fault.getText().toString());
        bx.setExpectedDate(txt_repair_time.getText().toString());
        bx.setFaultStoreyId(map.get("FaultStoreyId"));
        bx.setContactPeople(edit_repair_person.getText().toString());
        bx.setContactPhone(edit_phone.getText().toString());
        bx.setFaultAdress(edit_address.getText().toString());
        bx.setRemark("");
        bx.setState("1");
        if (open.isChecked()) {
            //精確報修
            bx.setFaultReportType("1");
            bx.setEquipmentID(map.get("EquipmentID"));
        } else {
            //模糊報修
            bx.setFaultReportType("2");
            bx.setEquipmentID("0");
        }
        bx.setCompanyID(MyData.getUser(User.class).getCompanyID());
        bx.setDepartID(MyData.getUser(User.class).getDepartmentId());
        bx.setProjectID(MyData.getProject().getProjectID());
        bx.setReportTime(DateUtils.date2StringBySecond(DateUtils.currentDateTime()));
        bx.setAttachmentList(uploadFileView.getAttachmentList());
        bx.setVoiceList(uploadVoiceView.getVoiceList());
        bxBiz.saveBx(bx);
    }

    private void setData() {
        if (!"0".equals(bx.getEquipmentID())) {
            StringBuffer sb = new StringBuffer();
            sb.append(bx.getEquipmentName());
            sb.append("\n");
            sb.append("编号：");
            sb.append(bx.getEquipmentNo());
            sb.append("\n");
            sb.append("品牌：");
            sb.append(bx.getBrand());
            sb.append("\n");
            sb.append("规格：");
            sb.append(bx.getModel());
            eq.setText(sb.toString());
            open.setChecked(true);
        }
        txt_state.setText("报修单" + BxState.getString(bx.getState()));
        txt_state_time.setText(bx.getReportTime());
        edit_theme.setText(bx.getFaultReportTitle());
        edit_fault.setText(bx.getFaultDescription());
        txt_area.setText(bx.getStoreyName());
        edit_repair_person.setText(bx.getReportUserName());
        edit_address.setText(bx.getFaultAdress());
        edit_phone.setText(bx.getContactPhone());
        txt_repair_time.setText(bx.getExpectedDate());
        uploadVoiceView.updateDataSource(bx.getVoiceList());
        uploadFileView.updateDataSource(bx.getAttachmentList());
        map.put("EquipmentID", bx.getEquipmentID());
        map.put("FaultStoreyId", bx.getFaultStoreyId());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case MultiImageSelectorActivity.REQUEST_IMAGE:
                uploadFileView.upload(data);
                break;
            case DiaLogCode.STOREY: {
                map.put("FaultStoreyId", data.getStringExtra("id"));
                txt_area.setText(data.getStringExtra("text"));
                break;
            }
            case DiaLogCode.EQ: {
                map.put("EquipmentID", data.getStringExtra("id"));
                eq.setText(data.getStringExtra("text"));
                break;
            }
        }
    }


    @Override
    public void doNext(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ResultCode.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    Intent intent = new Intent(application, MultiImageSelectorActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    // Permission Denied
                    Toast.makeText(application, "访问存储设备被禁止，请打开权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void doNext() {
        Intent intent = new Intent();
        intent.setClass(application, MultiImageSelectorActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onGetEntitySuccess(Bx bx) {
        this.bx = bx;
        setData();
        stopProgressDialog();
    }

    @Override
    public void onSaveSuccess() {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                bottomWindow.dismiss();
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onDelSuccess() {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                bottomWindow.dismiss();
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onFail(Data data) {
        Msg.showError(application, VolleyHttp.errorInfo(data));
        stopProgressDialog();
    }

    @Override
    public void onError(VolleyError volleyError) {
        Msg.showError(application, getString(R.string.net_error));
        stopProgressDialog();
    }

    @Override
    public void onTokenError() {
        new LoginManager().goToLogin(getActivity(), LoginAty.class, true);
    }
}
