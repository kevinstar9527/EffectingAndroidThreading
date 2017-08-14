package kevinstar1.edu.cn.effectingandroidthreading.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.math.BigInteger;

/**
 * Created by admin on 2017/8/14.
 */

public class PrimesFragment extends android.support.v4.app.Fragment {
    private  AsyncListener<Integer,BigInteger> asyncListener;
    private PrimesTask task;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task = new PrimesTask();
        task.execute(2000);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        asyncListener = (AsyncListener<Integer, BigInteger>) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        asyncListener = null;
    }
    class PrimesTask extends AsyncTask<Integer,Integer,BigInteger>{
        @Override
        protected BigInteger doInBackground(Integer... params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (asyncListener!=null){
                asyncListener.onPreExecute();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (asyncListener!=null){
                asyncListener.onProgressUpdate(values);
            }
        }

        @Override
        protected void onPostExecute(BigInteger bigInteger) {
            super.onPostExecute(bigInteger);
            if (asyncListener!=null) {
                asyncListener.onPostExecute(bigInteger);
            }
        }

        @Override
        protected void onCancelled(BigInteger bigInteger) {
            super.onCancelled(bigInteger);
            if (asyncListener!=null){
                asyncListener.onCancelled(bigInteger);
            }
        }
    }
}
