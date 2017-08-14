package kevinstar1.edu.cn.effectingandroidthreading.asynctask;

/**
 * Created by admin on 2017/8/14.
 */

public interface AsyncListener <Progress,Result>{
    void onPreExecute();
    void onProgressUpdate(Progress...progresses);
    void onPostExecute(Result result);
    void onCancelled(Result result);
}
