package money.com.gettingmoney.bai.main.base;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;

import java.lang.Thread.UncaughtExceptionHandler;

import money.com.gettingmoney.activity.MainActivity;
import money.com.gettingmoney.bai.main.utils.ToastUtils;
import money.com.gettingmoney.util.MyAppApiConfig;


//import android.util.Log;

public class UnCeHandler implements UncaughtExceptionHandler {

    private UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = "CatchExcep";
    MyAppApiConfig application;

    public UnCeHandler(MyAppApiConfig application) {
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.application = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            // mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
//            //TODO 错误机制开启
//            Intent intent = new Intent(,
//                    MainActivity.class);
//            PendingIntent restartIntent = PendingIntent.getActivity(
//                    application.getApplicationContext(), 0, intent,
//                    Intent.FLAG_ACTIVITY_NEW_TASK);
//            // 退出程序
//            AlarmManager mgr = (AlarmManager) application
//                    .getSystemService(Context.ALARM_SERVICE);
//            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
//                    restartIntent); // 1秒钟后重启应用
//            application.closeApplication();
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
//                ToastUtils.MyToast(application.getApplicationContext(), "很抱歉,程序出现异常,即将重启");
//                Toast.makeText(application.getApplicationContext(),
//                        "很抱歉,程序出现异常,即将重启", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        return true;
    }
}
