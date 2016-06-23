package com.lltech.manager.activity.dialog;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.common.DiaLogCode;
import com.lltech.manager.entity.GroupMemberBean;
import com.lltech.manager.entity.me.Employee;
import com.lltech.manager.entity.me.EmployeeReq;
import com.lltech.manager.entity.me.EmployeeRes;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.CharacterParser;
import com.lltech.manager.util.PinyinComparator;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.widget.ClearEditText;
import com.lltech.manager.widget.ListCheckBox;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.SideBar;
import com.lltech.manager.widget.TopBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EmployeesAty extends BaseActivity implements SectionIndexer {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private QuickAdapter<GroupMemberBean> adapter;
    private ClearEditText mClearEditText;

    private LinearLayout titleLayout;
    private TextView title;
    private TextView tvNofriends;
    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<GroupMemberBean> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private List<GroupMemberBean> newList = new ArrayList<GroupMemberBean>();
    private List<GroupMemberBean> oldList = new ArrayList<GroupMemberBean>();

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_employees);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        titleLayout = $(R.id.title_layout);
        title = $(R.id.title_layout_catalog);
        tvNofriends = $(R.id.title_layout_no_friends);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sideBar = $(R.id.sidrbar);
        dialog = $(R.id.dialog);
        sideBar.setTextView(dialog);
        sortListView = $(R.id.country_lvcountry);
        mClearEditText = $(R.id.filter_edit);
    }

    @Override
    public void initListeners() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getCheckBox(i)) {
                        newList.add(adapter.getItem(i));
                    }
                }
                if (CommonUtil.isEmpty(newList)) {
                    Toast.makeText(application, "请至少选择一条", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = getIntent();
                intent.putExtra("list", (Serializable) newList);
                setResult(DiaLogCode.EMPLOYEE, intent);
                finish();
            }

            @Override
            public void onLeftClick(View view) {
                finish();
            }
        });
        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListCheckBox listCheckBox = $(view, R.id.checkBox);
                boolean isCheck = !listCheckBox.isChecked();
                adapter.setCheckBox((int) id, isCheck);
                listCheckBox.setChecked(isCheck);
            }
        });
        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (CommonUtil.isEmpty(SourceDateList)) {
                    return;
                }
                int section = getSectionForPosition(firstVisibleItem);
                int nextSection = getSectionForPosition(firstVisibleItem + 1);
                int nextSecPosition = getPositionForSection(+nextSection);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                            .getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(getPositionForSection(section) != -1 ? SourceDateList.get(
                            getPositionForSection(section)).getSortLetters() : "");
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 这个时候不需要挤压效果 就把他隐藏掉
                titleLayout.setVisibility(View.GONE);
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void initData() {
        oldList = (List<GroupMemberBean>) getIntent().getExtras().getSerializable("list");
        SourceDateList = (List<GroupMemberBean>) application.getCommonData().get("SourceDateList");
        if (CommonUtil.isEmpty(SourceDateList)) {
            SourceDateList = new LinkedList<GroupMemberBean>();
            refresh();
        }
    }

    @Override
    public void initConfig() {
        topBar.setTopText("选择人员");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.getRightBtn2().setBackground(null);
        topBar.getRightBtn2().setText(getString(R.string.confirm));
        adapter = new QuickAdapter<GroupMemberBean>(application, R.layout.item_dialog_employees, SourceDateList) {
            @Override
            protected void convert(BaseAdapterHelper helper, GroupMemberBean item) {
                int index = helper.getPosition();
                String id = item.getId();
                for (GroupMemberBean bean : oldList) {
                    if (id.equals(bean.getId())) {
                        adapter.setCheckBox(index, true);
                    }
                }
                ListCheckBox listCheckBox = helper.getView(R.id.checkBox);
                listCheckBox.show();
                helper.setChecked(R.id.checkBox, adapter.getCheckBox(index));
                // 根据position获取分类的首字母的Char ascii值
                int section = getSectionForPosition(index);
                // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
                if (index == getPositionForSection(section)) {
                    helper.setVisible(R.id.catalog, true);
                    helper.setText(R.id.catalog, item.getSortLetters());
                } else {
                    helper.setVisible(R.id.catalog, false);
                }
                helper.setText(R.id.title, item.getName());
            }
        };
        sortListView.setAdapter(adapter);
    }

    /**
     * 为ListView填充数据
     *
     * @param data
     * @return
     */
    private List<GroupMemberBean> filledData(List<Employee> data) {
        List<GroupMemberBean> mSortList = new ArrayList<GroupMemberBean>();
        for (int i = 0; i < data.size(); i++) {
            Employee e = data.get(i);
            GroupMemberBean sortModel = new GroupMemberBean();
            sortModel.setId(e.getEmployeeID());
            sortModel.setName(e.getEmployeeName());
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(e.getEmployeeName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<GroupMemberBean> filterDateList = new ArrayList<GroupMemberBean>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
            tvNofriends.setVisibility(View.GONE);
        } else {
            filterDateList.clear();
            for (GroupMemberBean sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.replaceAll(filterDateList);
        if (filterDateList.size() == 0) {
            tvNofriends.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return SourceDateList.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < SourceDateList.size(); i++) {
            String sortStr = SourceDateList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    private void refresh() {
        startProgressDialog();
        String url = UrlCons.url(UrlCons.EmployeeService.GET_LIST);
        EmployeeReq req = new EmployeeReq();
        req.setPageIndex(0);
        req.setPageSize(10000);
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                EmployeeRes res = JsonUtils.jsonToEntity(response, EmployeeRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    LinkedList<Employee> list = res.getDataList();
                    SourceDateList = filledData(list);
                    // 根据a-z进行排序
                    Collections.sort(SourceDateList, pinyinComparator);
                    adapter.replaceAll(SourceDateList);
                    if (SourceDateList.size() == 0) {
                        tvNofriends.setVisibility(View.VISIBLE);
                    }
                    application.getCommonData().put("SourceDateList", SourceDateList);
                }
                stopProgressDialog();
            }

            @Override
            public void onFail(Data data) {
                stopProgressDialog();
                Msg.showError(application, VolleyHttp.errorInfo(data));
            }

            @Override
            public void onError(VolleyError volleyError) {
                stopProgressDialog();
                Msg.showError(application, getString(R.string.net_error));
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                new LoginManager().goToLogin(EmployeesAty.this, LoginAty.class, true);
            }
        });
    }
}
