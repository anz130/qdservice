package com.paopao.roblistapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.main.indent.IndentItemActivity;
import com.paopao.roblistapp.model.IndentInfo;
import com.paopao.roblistapp.model.IndentResult;
import com.paopao.roblistapp.main.adapter.IndentAdapter;
import com.paopao.roblistapp.network.UICallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/17.
 */

public class GrabSingleFragment extends Fragment {
    List<IndentInfo> list = new ArrayList<>();
    private String pageType = "";
    private TextView mTvLoadError;
    private PtrClassicFrameLayout mRefreshLayout;
    private IndentAdapter indentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grab_single, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = (ListView) getView().findViewById(R.id.listview);
        mRefreshLayout = (PtrClassicFrameLayout) getView().findViewById(R.id.refreshLayout);
        mTvLoadError = (TextView) getView().findViewById(R.id.tv_load_error);
        for (int i = 0; i < 30; i++) {
            IndentInfo indentInfo = new IndentInfo("小宁家" + i, "小李家" + i, i * 4 + "", "2017-6-20  16:30:23", "悬赏", "距你" + i + "千米","实时");
            list.add(indentInfo);

        }
        // TODO: 2017/6/17 网络数据请求，添加到list集合，上拉加载，下拉刷新
        indentAdapter = new IndentAdapter(getActivity(), list);
        listView.setAdapter(indentAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IndentItemActivity.startIndentItem(getActivity(),"");
            }
        });
       /* mRefreshLayout.setLastUpdateTimeRelateObject(this);
        mRefreshLayout.setBackgroundResource(R.color.recycler_bg);
        mRefreshLayout.setDurationToCloseHeader(1500);
        mRefreshLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                loadData(pageType);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshData(pageType);
            }
        });


        mTvLoadError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshLayout.autoRefresh();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 2017/6/20
            }
        });*/
    }


   /* private Call mCall;
    private int pageInt = 1;
    public void loadData(String type) {
        // TODO: 2017/6/21  mCall = ShenLanPaoPaoClient.getInstance().getGoods(pageInt, type);
        mCall.enqueue(new UICallback() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                showMessage(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                showRefresh();
                IndentResult mIndentResult = new Gson().fromJson(body, IndentResult.class);
                if (mIndentResult.getCode() == 1) {
                    if (mIndentResult.getDatas().size() == 0) {
                        showLoadMoreEnd();
                    }else {
                        addMoreData(mIndentResult.getDatas());
                        showLoadMoreEnd();
                    }
                    pageInt++;
                } else {
                    showRefreshError(mIndentResult.getMessage());
                }

            }
        });
    }*/



    /*public void refreshData(String type) {
        showRefresh();
        // TODO: 2017/6/21  mCall = ShenLanPaoPaoClient.getInstance().getGoods(1, type);

        mCall.enqueue(new UICallback() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                showMessage(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                IndentResult mIndentResult = new Gson().fromJson(body, IndentResult.class);
                if (mIndentResult.getCode() == 1) {
                    if (mIndentResult.getDatas().size() == 0) {
                        showRefreshEnd();
                    } else {
                        addRefreshData(mIndentResult.getDatas());
                        showRefreshEnd();
                    }
                    pageInt = 2;
                } else {
                    showLoadMoreError(mIndentResult.getMessage());
                }
            }
        });
    }*/




    public void showRefresh() {
        mTvLoadError.setVisibility(View.GONE);
    }


    public void showRefreshError(String msg) {
        mRefreshLayout.refreshComplete();
        if (indentAdapter.getItemCount() > 0) {
            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            return;
        }
        mTvLoadError.setVisibility(View.VISIBLE);
    }


    public void showRefreshEnd() {
        Toast.makeText(getActivity(),getResources().getString(R.string.refresh_more_end),Toast.LENGTH_SHORT).show();
        mRefreshLayout.refreshComplete();
    }


    public void hideRefresh() {
        mRefreshLayout.refreshComplete();
    }

    public void addRefreshData(List<IndentInfo> data) {
        indentAdapter.clear();
        if (data != null) {
            indentAdapter.addData(data);
        }
    }


    public void showLoadMoreLoading() {
        mTvLoadError.setVisibility(View.GONE);
    }


    public void showLoadMoreError(String msg) {
        mRefreshLayout.refreshComplete();
        if (indentAdapter.getItemCount() > 0) {
            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            return;
        }
        mTvLoadError.setVisibility(View.VISIBLE);
    }


    public void showLoadMoreEnd() {
        mRefreshLayout.refreshComplete();
    }

    public void hideLoadMore() {
        mRefreshLayout.refreshComplete();
    }

    public void addMoreData(List<IndentInfo> data) {
        indentAdapter.addData(data);
    }


    public void showMessage(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }




}
