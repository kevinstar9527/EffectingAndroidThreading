package kevinstar1.edu.cn.effectingandroidthreading.asynctask;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by admin on 2017/8/14.
 */

public class AsyncTaskActivity extends FragmentActivity implements AsyncListener{

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        PrimesFragment primesFragment = (PrimesFragment) fragmentManager.findFragmentByTag("primes");
        if (primesFragment == null) {
            primesFragment = new PrimesFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(primesFragment,"primes").commit();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()){//用于判断当前Activty是被 回收 还是存在回退栈中

        }
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onProgressUpdate(Object[] objects) {
        prepareProgressDialog((Integer) objects[0]);
    }

    @Override
    public void onPostExecute(Object o) {
        cleanUp();
    }

    @Override
    public void onCancelled(Object o) {
        cleanUp();
    }

    private void prepareProgressDialog(Integer integer){
        if (progressBar == null) {
            progressBar = new ProgressDialog(this);
        }
    }

    /**
     * 清除资源占用
     */
    private void cleanUp(){
        progressBar.dismiss();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("primes");
        fragmentManager.beginTransaction().remove(fragment).commit();

    };
}
