package com.lltech.manager.fragment.info;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.Cons;
import com.lltech.manager.R;
import com.lltech.manager.entity.Attachment;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.util.FileType;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : FjFragment
 * @Description(描述) : 附件信息
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:04
 */
public class FjFragment extends BaseFragment {

    private GridView fujians;
    private QuickAdapter<Attachment> f_adapter;
    private List<Attachment> attachmentList = new LinkedList<Attachment>();

    @Override
    public int setContentView() {
        return R.layout.detail_eq_fj;
    }

    @Override
    public void initView() {
        fujians = $(R.id.fujians);
    }

    @Override
    public void initListener() {
        fujians.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter2 = parent.getAdapter();
                Attachment attachment = (Attachment) adapter2.getItem(position);
                String name = attachment.getRealName();
//                if (FileType.isImg(name)) {
//                    Intent intent = new Intent(getActivity().getApplicationContext(), ZoomImgAty.class);
//                    intent.putExtra("position", position);
//                    intent.putExtra("attachmentList", (Serializable) attachmentList);
//                    startActivity(intent);
//                } else if (FileType.isPdf(name)) {
//                    Intent intent = new Intent(getActivity().getApplicationContext(), PdfActivity.class);
//                    intent.putExtra("attachment", attachment);
//                    startActivity(intent);
//                } else if (FileType.isOfficeDoc(name)) {
//                    Intent intent = new Intent(getActivity().getApplicationContext(), OfficeActivity.class);
//                    intent.putExtra("attachment", attachment);
//                    startActivity(intent);
//                }
            }
        });
    }

    @Override
    public void initConfig() {
        f_adapter = new QuickAdapter<Attachment>(getActivity(), R.layout.common_item_fujian, attachmentList) {

            @Override
            protected void convert(BaseAdapterHelper helper, Attachment item) {
                helper.setText(R.id.title, item.getAttachmentID());
                String name = item.getRealName();
                if (FileType.isImg(name)) {
                    if (CommonUtil.isEmpty(item.getAttachmentUrl())) {
                        helper.setImageUrl(R.id.fujian_image, item.getLocalFile(), Cons.ImageLoadType.PICASSO);
                    } else {
                        helper.setImageUrl(R.id.fujian_image, item.getAttachmentUrl(), Cons.ImageLoadType.PICASSO);
                    }
                } else if (FileType.isPdf(name)) {
                    helper.setImageResource(R.id.fujian_image, R.drawable.pdf);
                } else if (FileType.isWord(name)) {
                    helper.setImageResource(R.id.fujian_image, R.drawable.word);
                } else if (FileType.isExcel(name)) {
                    helper.setImageResource(R.id.fujian_image, R.drawable.excel);
                }
            }
        };
        fujians.setAdapter(f_adapter);
    }

    @Override
    public void initData() {
        Eq eq = (Eq) this.getArguments().getSerializable("eq");
        attachmentList = eq.getAttachmentList();
    }
}
