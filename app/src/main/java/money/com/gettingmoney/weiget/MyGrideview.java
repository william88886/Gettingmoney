package money.com.gettingmoney.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/12/10.
 */
public class MyGrideview extends GridView {


    public MyGrideview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
