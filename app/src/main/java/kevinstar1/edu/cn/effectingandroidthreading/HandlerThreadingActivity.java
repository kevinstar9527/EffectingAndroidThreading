package kevinstar1.edu.cn.effectingandroidthreading;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HandlerThreadingActivity extends AppCompatActivity {
    private ProgressDialog mDialog;
    private Handler mUiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    mDialog.setMessage("正在加载");
                    mDialog.show();
                    break;
                case 2:
                    mDialog.setMessage("加载完毕");
                    SystemClock.sleep(1000);
                    mDialog.dismiss();
                    break;
            }
        }
    };
    private class CustomHandlerThread extends HandlerThread{
        private Handler mHandler;
        public CustomHandlerThread() {
            super("CustomHandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            mHandler = new Handler(getLooper()){
                @Override
                public void handleMessage(Message msg) {
                   super.handleMessage(msg);
                   switch (msg.what){
                       case 1:
                           mUiHandler.sendEmptyMessage(1);
                           SystemClock.sleep(3000);//模拟加载数据操作
                           publishedMethod2();
                           break;
                       case 2:
                           mUiHandler.sendEmptyMessage(2);
                           break;
                   }
                }
            };
        }
        public void publishedMethod1() {
            mHandler.sendEmptyMessage(1);
        };
        public void publishedMethod2() {
            mHandler.sendEmptyMessage(2);
        };

        /**
         * 恢复出厂设置
         */
        public void resetHandlerThread(){
            mHandler.removeCallbacksAndMessages(null);
        }

        /**
         * 退出并停止当前执行的任务
         * @param handlerThread
         */
        public void stopHandlerThread(HandlerThread handlerThread) {
            handlerThread.quit();
            handlerThread.interrupt();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CustomHandlerThread customHandlerThread = new CustomHandlerThread();
        customHandlerThread.start();
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customHandlerThread.publishedMethod1();
            }
        });
        mDialog = new ProgressDialog(this);

    }
}
