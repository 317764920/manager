package com.lltech.manager.activity.me;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.utils.DateUtils;
import com.lcx.mysdk.utils.DensityUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.biz.impl.BaseBiz;
import com.lltech.manager.entity.rc.Rc;
import com.lltech.manager.view.RcListView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class RcListAty extends BaseActivity implements RcListView {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private PullToRefreshListView pullToRefreshListView;
    private View detail_view;
    private AlertDialog dialog;
    private QuickAdapter<HashMap<String, LinkedList<Rc>>> adapter;
    private static final HashMap<String, Integer> COLORMAP = new HashMap<String, Integer>();
    private static final HashMap<String, Integer> ICONMAP = new HashMap<String, Integer>();
    private BaseBiz meBiz = new BaseBiz(this);

    static {
        COLORMAP.put("1", R.drawable.selecter_rc_text1);
        COLORMAP.put("2", R.drawable.selecter_rc_text2);
        COLORMAP.put("3", R.drawable.selecter_rc_text3);
        ICONMAP.put("1", R.drawable.rc_warn1);
        ICONMAP.put("2", R.drawable.rc_warn2);
        ICONMAP.put("3", R.drawable.rc_warn3);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.baselist);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        pullToRefreshListView = $(R.id.pullToRefreshListView);
    }

    @Override
    public void initListeners() {
        topBar.getLeftBtn().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                meBiz.getRcList();
            }
        });
    }

    @Override
    public void initData() {
        startProgressDialog();
        meBiz.getRcList();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initConfig() {
        topBar.setTopText("我的日程");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        adapter = new QuickAdapter<HashMap<String, LinkedList<Rc>>>(this, R.layout.item_rc, read(new LinkedList<Rc>())) {

            @Override
            protected void convert(BaseAdapterHelper helper, HashMap<String, LinkedList<Rc>> item) {
                String key = item.keySet().iterator().next();
                LinkedList<Rc> contentList = item.get(key);
                Date date = DateUtils.string2Date(key);
                int compareHourAndMinute = DateUtils.getBetweenDays(date, DateUtils.currentDate()).intValue();
                if (compareHourAndMinute == 1) {
                    helper.setText(R.id.month, "");
                    helper.setVisible(R.id.month, false);
                    helper.setText(R.id.day, "今天");
                } else {
                    helper.setText(R.id.month, DateUtils.getChMonth(date));
                    helper.setVisible(R.id.month, true);
                    helper.setText(R.id.day, DateUtils.getDay(date));
                }
                LinearLayout content = helper.getView(R.id.content);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                content.removeAllViewsInLayout();
                for (int i = 0; i < contentList.size(); i++) {
                    Rc rc = contentList.get(i);
                    TextView t = new TextView(application);
                    t.setText(rc.getScheduleDesc());
                    t.setTextColor(Color.WHITE);
                    t.setTextSize(DensityUtils.sp2px(application, 7.5f));
                    t.setBackgroundResource(COLORMAP.get(rc.getLeve()));
                    t.setClickable(true);
                    t.setOnClickListener(new TvOnClick(rc));
                    int dp2px = DensityUtils.dp2px(application, 5);
                    t.setPadding(dp2px, dp2px, dp2px, dp2px);
                    if (i > 0) {
                        lp.setMargins(0, 20, 0, 0);
                        t.setLayoutParams(lp);
                    }
                    content.addView(t);
                }
            }
        };
        pullToRefreshListView.setAdapter(adapter);
        pullToRefreshListView.setMode(Mode.PULL_DOWN_TO_REFRESH);
        pullToRefreshListView.getRefreshableView().setSelector(R.color.white);
        pullToRefreshListView.getRefreshableView().setDividerHeight(0);
        pullToRefreshListView.getRefreshableView().setVerticalScrollBarEnabled(false);
    }

    /**
     * @param list
     * @return LinkedList<Rc>
     * @throws :
     * @Description(功能描述) : 解析返回的list
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2016年3月17日 下午3:26:19
     */
    private LinkedList<HashMap<String, LinkedList<Rc>>> read(LinkedList<Rc> list) {
        // 定义一个集合用于分组
        LinkedList<HashMap<String, LinkedList<Rc>>> result = new LinkedList<HashMap<String, LinkedList<Rc>>>();
        LinkedList<String> zuList = new LinkedList<String>();
        for (Rc rc : list) {
            String time = DateUtils.date2StringByDay(DateUtils.string2Date(rc.getStartTime(), "yyyy/M/d hh:mm:ss"));
            if (!zuList.contains(time)) {
                zuList.add(time);
            }
        }
        for (String zuTime : zuList) {
            HashMap<String, LinkedList<Rc>> one = new HashMap<String, LinkedList<Rc>>();
            LinkedList<Rc> dayList = new LinkedList<Rc>();
            for (Rc rc : list) {
                String time = DateUtils.date2StringByDay(DateUtils.string2Date(rc.getStartTime(), "yyyy/M/d hh:mm:ss"));
                if (zuTime.equals(time)) {
                    dayList.add(rc);
                }
            }
            one.put(zuTime, dayList);
            result.add(one);
        }
        Collections.sort(result, new Comparator<HashMap<String, LinkedList<Rc>>>() {

            @Override
            public int compare(HashMap<String, LinkedList<Rc>> lhs, HashMap<String, LinkedList<Rc>> rhs) {
                String time1 = lhs.keySet().iterator().next();
                String time2 = rhs.keySet().iterator().next();
                return DateUtils.compareIgnoreSecond(DateUtils.string2Date(time2), DateUtils.string2Date(time1));
            }
        });
        return result;
    }

    @Override
    public void onSuccess(LinkedList<Rc> list) {
        stopProgressDialog();
        adapter.replaceAll(read(list));
        pullToRefreshListView.onRefreshComplete(true);
    }

    @Override
    public void onFail(Data data) {
        stopProgressDialog();
        Msg.showError(application, VolleyHttp.errorInfo(data));
        pullToRefreshListView.onRefreshComplete();
    }

    @Override
    public void onError(VolleyError volleyError) {
        stopProgressDialog();
        Msg.showError(application, getString(R.string.net_error));
        pullToRefreshListView.onRefreshComplete();
    }

    @Override
    public void onTokenError() {
        new LoginManager().goToLogin(RcListAty.this, LoginAty.class, true);
    }

    /**
     * @ClassName(类名) : TvOnClick
     * @Description(描述) : item中每一个textview的点击事件
     * @author(作者) ：liuchunxu
     * @date (开发日期) ：2016年3月18日 上午9:51:05
     */
    class TvOnClick implements OnClickListener {

        private Rc rc;

        public TvOnClick(Rc rc) {
            this.rc = rc;
        }

        @Override
        public void onClick(View v) {
            // 弹出详情框
            TipDetail(rc);
        }

    }

    /**
     * @throws : void
     * @Description(功能描述) : 详情
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2016年2月25日 下午4:52:02
     */
    private void TipDetail(Rc rc) {
        if (detail_view == null)
            detail_view = LayoutInflater.from(application).inflate(R.layout.detail_me_rc, null);
        TextView desc = $(detail_view, R.id.desc);
        TextView person = $(detail_view, R.id.person);
        TextView begin = $(detail_view, R.id.begin);
        TextView end = $(detail_view, R.id.end);
        ImageView leve = $(detail_view, R.id.leve);
        desc.setText(rc.getScheduleDesc());
        person.setText("执行人：" + rc.getExecutUser());
        begin.setText(rc.getStartTime());
        end.setText(rc.getEndTime());
        leve.setImageResource(ICONMAP.get(rc.getLeve()));
        if (dialog == null)
            dialog = new AlertDialog.Builder(this).setView(detail_view).create();
        dialog.show();
    }
}
