package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.mvp.model.ImageBean;
import com.example.mvp.presenter.DoaloadPrenseter;

/**
 * 简单 mvp 模式
 */
public class MainActivity extends AppCompatActivity implements DoaloadContract.PV{

    private DoaloadPrenseter prenseter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prenseter = new DoaloadPrenseter(this);
    }


    public void btnclick(View view) {

        Log.e("ss","btnkk");

        ImageBean b = new ImageBean();

        b.setRequestPath(Constant.IMAGE_PATH);

        requestDoaloadP(b);
    }


    @Override
    public void requestDoaloadP(ImageBean bean) {

        prenseter.requestDoaloadP(bean);

    }

    @Override
    public void doaloadResult(boolean isSuccess, ImageBean bean) {

        if (isSuccess){

            ImageView viewById = findViewById(R.id.imageView);

            viewById.setImageBitmap(bean.getBitmap());
        }
    }
}
