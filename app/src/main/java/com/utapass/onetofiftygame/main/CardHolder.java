package com.utapass.onetofiftygame.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.utapass.onetofiftygame.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CardHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.bt_number) Button mButton;

    private final ArrayList<Integer> subData;
    private callback callback;
    private static int nowNumber ;

    public interface callback{
        void onItemClickListener(int num);
    }

    public CardHolder(View itemView, callback callback, ArrayList<Integer> subData) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.callback = callback;
        this.subData = subData;
        nowNumber = 1;
    }

    public void initContent(int num){
        mButton.setText(String.valueOf(num));
        mButton.setTag(String.valueOf(num));
    }

    private void updateContent(int num){
        if (num > 25) {
            mButton.setVisibility(View.INVISIBLE);
        } else {
            mButton.setText(subData.get(num - 1).toString());
            mButton.setTag(subData.get(num - 1).toString());
            mButton.setBackgroundResource(R.color.colorPrimaryDark);
        }
    }

    @OnClick(R.id.bt_number)
    public void onNumberClicked(){
        if (Integer.valueOf(mButton.getTag().toString()) == nowNumber) {
            updateContent(nowNumber);
            if(callback==null)
                return;
            callback.onItemClickListener(nowNumber);
            nowNumber++;
        }
    }
}
