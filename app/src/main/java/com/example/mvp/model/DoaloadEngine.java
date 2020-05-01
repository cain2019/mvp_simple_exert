package com.example.mvp.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mvp.Constant;
import com.example.mvp.DoaloadContract;
import com.example.mvp.presenter.DoaloadPrenseter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DoaloadEngine implements DoaloadContract.M {

    private DoaloadPrenseter prenseter;

    public DoaloadEngine(DoaloadPrenseter prenseter) {
        this.prenseter = prenseter;
    }

    @Override
    public void requestDoaload(ImageBean bean) {


        new Thread(new DownLoader(bean)).start();

    }



    final class DownLoader implements Runnable {

        private final ImageBean imageBean;

        public DownLoader(ImageBean imageBean) {
            this.imageBean = imageBean;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageBean.getRequestPath());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    showUi(Constant.SUCCESS, bitmap);
                } else {
                    showUi(Constant.ERROR, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showUi(Constant.ERROR, null);
            }
        }

        private void showUi(int resultCode, Bitmap bitmap) {
            imageBean.setBitmap(bitmap);
            prenseter.doaloadResult(resultCode == Constant.SUCCESS, imageBean);
        }
    }

}
