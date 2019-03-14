package com.example.testapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.databinding.ActivityRefreshBinding;
import com.example.testapp.widget.ScrollImageViewBehavior;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: zqz
 * Data: 2019/3/13 14:37
 * Description:
 */
public class RefreshActivity extends BaseActivity<ActivityRefreshBinding> {


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_refresh;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i + "");
        }
        mBinding.recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(getLayoutInflater().inflate(R.layout.item_activity_layout, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh = (ViewHolder) holder;
                vh.text.setText("第" + position + "条");
            }

            @Override
            public int getItemCount() {
                return 50;
            }

            class ViewHolder extends RecyclerView.ViewHolder {

                TextView text;
                TextView text2;

                public ViewHolder(View itemView) {
                    super(itemView);

                    text = (TextView) itemView.findViewById(R.id.text);
                }

            }
        });


        mBinding.refreshLayout.setEnableRefresh(false);
        ScrollImageViewBehavior.setIsRefrshListener(new ScrollImageViewBehavior.IsRefrshListener() {
            @Override
            public void isResh(boolean isRefresh) {
                mBinding.refreshLayout.setEnableRefresh(isRefresh);
            }
        });
    }




}
