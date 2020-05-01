package com.example.mvp;

import com.example.mvp.model.ImageBean;

public interface DoaloadContract {


    interface M {
       // P层告诉M层，需要做什么事情
        void requestDoaload(ImageBean bean);
    }

    interface PV {
        // V层告诉P层，需要做什么事情
        void requestDoaloadP(ImageBean bean);
        // P层得到M层的结果返回，再通知V层
        void doaloadResult(boolean isSuccess,ImageBean bean);
    }



}
