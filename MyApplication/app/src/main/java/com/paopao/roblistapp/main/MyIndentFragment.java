package com.paopao.roblistapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paopao.roblistapp.R;
import com.paopao.roblistapp.main.adapter.IndentAdapter;
import com.paopao.roblistapp.main.indent.IndentItemActivity;
import com.paopao.roblistapp.model.CachePreferences;
import com.paopao.roblistapp.model.IndentInfo;
import com.paopao.roblistapp.model.IndentItem;
import com.paopao.roblistapp.model.IndentResult;
import com.paopao.roblistapp.network.ShenLanPaoPaoClient;
import com.paopao.roblistapp.network.UICallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import okhttp3.Call;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/6/17.
 */

public class MyIndentFragment extends Fragment implements View.OnClickListener {
    private TextView[] textViews;
    private List<IndentInfo> list = new ArrayList<>();
    private IndentAdapter indentAdapter;
    private ListView listView;
    private PtrClassicFrameLayout refreshLayout;
    private TextView loadError;
    private TextView loadEmpty;
    private int pageNo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_indent, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView total = (TextView) getView().findViewById(R.id.total);
        TextView toTake = (TextView) getView().findViewById(R.id.to_take);
        TextView toSign = (TextView) getView().findViewById(R.id.to_sign);
        TextView signed = (TextView) getView().findViewById(R.id.signed);
        textViews = new TextView[]{total, toTake, toSign, signed};
        listView = (ListView) getView().findViewById(R.id.listview);
        refreshLayout = ((PtrClassicFrameLayout) getView().findViewById(R.id.refreshLayout));
        loadError = ((TextView) getView().findViewById(R.id.tv_load_error));
        loadEmpty = ((TextView) getView().findViewById(R.id.tv_load_empty));

        total.setOnClickListener(this);
        toTake.setOnClickListener(this);
        toSign.setOnClickListener(this);
        signed.setOnClickListener(this);
        // TODO: 2017/6/28  网络请求
        for (int i = 0; i < 30; i++) {
            IndentInfo indentInfo = new IndentInfo("小宁家" + i, "小李家" + i, i * 4 + "", "2017-6-20  16:30:23", "正常", "距你" + i + "千米","送达");
            list.add(indentInfo);
        }
        refreshLayout.setLastUpdateTimeRelateObject(this);
        refreshLayout.setBackgroundResource(R.color.recycler_bg);
        refreshLayout.setDurationToCloseHeader(1500);
        /*refreshLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                loadData("");
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshData("");
            }
        });*/
        indentAdapter = new IndentAdapter(getActivity(), list);
        listView.setAdapter(indentAdapter);
        textViews[0].setBackgroundColor(getResources().getColor(R.color.orange));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IndentItem indentItem = new IndentItem();
                indentItem.setCompleteness("notake");
                IndentItemActivity.startIndentItem(getActivity(), indentItem.getCompleteness());
            }
        });
    }

    /*@Override
    public void onStart() {
        if (indentAdapter.getItemCount()==0){
           refreshLayout.autoRefresh();
        }
        super.onStart();
    }*/
    @Override
    public void onClick(View v) {
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setBackgroundColor(getResources().getColor(R.color.purple));
        }
        list.clear();
        switch (v.getId()) {
            case R.id.total:
              /*  refreshLayout.setPtrHandler(new PtrDefaultHandler2() {
                    @Override
                    public void onLoadMoreBegin(PtrFrameLayout frame) {
                        loadData("");
                    }

                    @Override
                    public void onRefreshBegin(PtrFrameLayout frame) {
                        refreshData("");
                    }
                });*/
                for (int i = 0; i < 30; i++) {
                    IndentInfo indentInfo = new IndentInfo("小宁家" + i, "小李家" + i, i * 4 + "", "2017-6-20  16:30:23", "悬赏", "距你" + i + "千米", "送达");
                    list.add(indentInfo);
                    listView.setAdapter(indentAdapter);
                }
                textViews[0].setBackgroundColor(getResources().getColor(R.color.orange));
                break;
            case R.id.to_take:
               /* refreshLayout.setPtrHandler(new PtrDefaultHandler2() {
                    @Override
                    public void onLoadMoreBegin(PtrFrameLayout frame) {
                        loadData("notake");
                    }

                    @Override
                    public void onRefreshBegin(PtrFrameLayout frame) {
                        refreshData("notake");
                    }
                });*/
                for (int i = 0; i < 30; i++) {
                    IndentInfo indentInfo = new IndentInfo("小宁家" + i, "小李家" + i, i * 1 + "", "2017-6-20  16:30:23", "正常", "距你" + i + "千米", "送达");
                    list.add(indentInfo);
                }
                listView.setAdapter(indentAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        IndentItem indentItem = new IndentItem();
                        indentItem.setCompleteness("notake");
                        IndentItemActivity.startIndentItem(getActivity(), indentItem.getCompleteness());
                    }
                });
                textViews[1].setBackgroundColor(getResources().getColor(R.color.orange));
                break;
            case R.id.to_sign:
                /*refreshLayout.setPtrHandler(new PtrDefaultHandler2() {
                    @Override
                    public void onLoadMoreBegin(PtrFrameLayout frame) {
                        loadData("undone");
                    }

                    @Override
                    public void onRefreshBegin(PtrFrameLayout frame) {
                        refreshData("undone");
                    }
                });*/
                for (int i = 0; i < 30; i++) {
                    IndentInfo indentInfo = new IndentInfo("小宁家" + i, "小李家" + i, i * 2 + "", "2017-6-20  16:30:23", "悬赏", "距你" + i + "千米", "送达");
                    list.add(indentInfo);
                }
                listView.setAdapter(indentAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        IndentItem indentItem = new IndentItem();
                        indentItem.setCompleteness("undone");
                        IndentItemActivity.startIndentItem(getActivity(), indentItem.getCompleteness());
                    }
                });
                textViews[2].setBackgroundColor(getResources().getColor(R.color.orange));
                break;
            case R.id.signed:
                /*refreshLayout.setPtrHandler(new PtrDefaultHandler2() {
                    @Override
                    public void onLoadMoreBegin(PtrFrameLayout frame) {
                        loadData("finish");
                    }

                    @Override
                    public void onRefreshBegin(PtrFrameLayout frame) {
                        refreshData("finish");
                    }
                });*/
                for (int i = 0; i < 30; i++) {
                    IndentInfo indentInfo = new IndentInfo("小宁家" + i, "小李家" + i, i * 3 + "", "2017-6-20  16:30:23", "正常", "距你" + i + "千米", "送达");
                    list.add(indentInfo);
                }
                listView.setAdapter(indentAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        IndentItem indentItem = new IndentItem();
                        indentItem.setCompleteness("finish");
                        IndentItemActivity.startIndentItem(getActivity(), indentItem.getCompleteness());
                    }
                });
                textViews[3].setBackgroundColor(getResources().getColor(R.color.orange));
                break;
        }
    }


    public void refreshData(String type){
        showRefresh();
        Call call = ShenLanPaoPaoClient.getInstance().getPersonIndent(1, CachePreferences.getUser().getName(), "", type);
        call.enqueue(new UICallback() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                showRefreshError(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                IndentResult indentResult = new Gson().fromJson(body, IndentResult.class);
                if (indentResult.getCode()==1){

                    if (indentResult.getDatas().size()!=0){
                        addRefreshData(indentResult.getDatas());
                        hideRefresh();
                    }else {
                        showRefreshEnd();
                    }
                    pageNo = 2;
                }else {
                    showRefreshError(indentResult.getMessage());
                }
            }
        });
    }

    public void loadData(String type){
        showLoadMoreLoading();
        Call call = ShenLanPaoPaoClient.getInstance().getPersonIndent(1, CachePreferences.getUser().getName(), "", type);
        call.enqueue(new UICallback() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                showLoadMoreError(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                IndentResult indentResult = new Gson().fromJson(body, IndentResult.class);
                if (indentResult.getCode()==1){
                    if (indentResult.getDatas().size()!=0){
                        addMoreData(indentResult.getDatas());
                        hideLoadMore();
                    }else {
                        showLoadMoreEnd();
                    }
                    pageNo++;
                }else {
                    showLoadMoreError(indentResult.getMessage());
                }
            }
        });
    }

    public void showRefresh() {
        loadEmpty.setVisibility(View.GONE);
        loadError.setVisibility(View.GONE);
    }


    public void showRefreshError(String msg) {
        refreshLayout.refreshComplete();
        if (indentAdapter.getItemCount()>0){
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            return;
        }loadError.setVisibility(View.VISIBLE);
    }

    public void showRefreshEnd() {
        refreshLayout.refreshComplete();
        loadEmpty.setVisibility(View.VISIBLE);
    }


    public void hideRefresh() {
        refreshLayout.refreshComplete();
    }


    public void addRefreshData(List<IndentInfo> data) {
        indentAdapter.clear();
        if (data!=null)indentAdapter.addData(data);
    }


    public void showLoadMoreLoading() {
        loadError.setVisibility(View.GONE);
    }


    public void showLoadMoreError(String msg) {
        refreshLayout.refreshComplete();
        if (indentAdapter.getItemCount()>0){
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            return;
        }
        loadError.setVisibility(View.VISIBLE);
    }


    public void showLoadMoreEnd() {
        refreshLayout.refreshComplete();
        Toast.makeText(getActivity(), R.string.load_more_end, Toast.LENGTH_SHORT).show();
    }

    public void hideLoadMore() {
        refreshLayout.refreshComplete();
    }


    public void addMoreData(List<IndentInfo> data) {
        indentAdapter.addData(data);
    }

    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}