package com.example.mvp.presenter;

import com.example.mvp.DoaloadContract;
import com.example.mvp.MainActivity;
import com.example.mvp.model.DoaloadEngine;
import com.example.mvp.model.ImageBean;

public class DoaloadPrenseter implements DoaloadContract.PV {


    private MainActivity view;

    private DoaloadEngine model;


    public DoaloadPrenseter(MainActivity view) {
        this.view = view;

        model = new DoaloadEngine(this);
    }

    @Override
    public void requestDoaloadP(ImageBean bean) {

        if (model != null){

            model.requestDoaload(bean);
        }
    }

    @Override
    public void doaloadResult(final boolean isSuccess, final ImageBean bean) {

        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.doaloadResult(isSuccess,bean);
            }
        });


    }


}
