package com.paopao.roblistapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseActivity;
import com.paopao.roblistapp.main.mine.PersonActivity;
import com.paopao.roblistapp.main.mine.SetActivity;
import com.paopao.roblistapp.main.mine.wallet.WalletActivity;

/**
 * Created by Administrator on 2017/6/26.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RelativeLayout person = (RelativeLayout) getView().findViewById(R.id.rl_person);
        RelativeLayout wallet = (RelativeLayout) getView().findViewById(R.id.rl_wallet);
        RelativeLayout set = (RelativeLayout) getView().findViewById(R.id.rl_set);
        person.setOnClickListener(this);
        wallet.setOnClickListener(this);
        set.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_person:
                ((BaseActivity) getActivity()).startActivity(getActivity(), PersonActivity.class);
                break;
            case R.id.rl_wallet:
                ((BaseActivity) getActivity()).startActivity(getActivity(), WalletActivity.class);
                break;
            case R.id.rl_set:
                ((BaseActivity) getActivity()).startActivity(getActivity(), SetActivity.class);
                break;
        }
    }
}
