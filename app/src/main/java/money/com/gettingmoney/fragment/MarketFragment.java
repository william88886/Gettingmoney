package money.com.gettingmoney.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.app.MoneyApplication;

public class MarketFragment extends Fragment implements View.OnClickListener {

    /**
     * 行情界面
     * @param savedInstanceState
     */
    private View view;
    private Context con;
    private RelativeLayout market_selfchosebtn,market_indexbtn,market_shenzhenbtn,market_platebtn,market_gangmeibtn;
    private TextView market_selfchosetext,market_indextext,market_shenzhentext,market_platetext,market_gangmeitext;
    private View market_view1,market_view2,market_view3,market_view4,market_view5;
    private ArrayList<TextView> texts;
    private ArrayList<View> views;
    private int position;
    private FragmentManager fm;
    private SelfchoseFragment selfchoseFragment;
    private IndexFragment indexFragment;
    private PlateFragment plateFragment;
    private ShenzhengFragment shenzhengFragment;
    private GangmeiFragment gangmeiFragment;
    private ViewPager viewpager;
    private ArrayList<Fragment> fragmentArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_market,null);
        con = MoneyApplication.getContext();
        initview();
        return view;
    }

    private void initview() {
        texts = new ArrayList<>();
        views = new ArrayList<>();
        fragmentArrayList = new ArrayList<>();

        market_selfchosebtn = (RelativeLayout) view.findViewById(R.id.market_selfchosebtn);
        market_indexbtn = (RelativeLayout) view.findViewById(R.id.market_indexbtn);
        market_shenzhenbtn = (RelativeLayout) view.findViewById(R.id.market_shenzhenbtn);
        market_platebtn = (RelativeLayout) view.findViewById(R.id.market_platebtn);
        market_gangmeibtn = (RelativeLayout) view.findViewById(R.id.market_gangmeibtn);

        market_selfchosetext = (TextView) view.findViewById(R.id.market_selfchosetext);
        market_indextext = (TextView) view.findViewById(R.id.market_indextext);
        market_shenzhentext = (TextView) view.findViewById(R.id.market_shenzhentext);
        market_platetext = (TextView) view.findViewById(R.id.market_platetext);
        market_gangmeitext = (TextView) view.findViewById(R.id.market_gangmeitext);
        texts.add(market_selfchosetext);
        texts.add(market_indextext);
        texts.add(market_shenzhentext);
        texts.add(market_platetext);
        texts.add(market_gangmeitext);

        market_view1 = view.findViewById(R.id.market_view1);
        market_view2 = view.findViewById(R.id.market_view2);
        market_view3 = view.findViewById(R.id.market_view3);
        market_view4 = view.findViewById(R.id.market_view4);
        market_view5 = view.findViewById(R.id.market_view5);
        views.add(market_view1);
        views.add(market_view2);
        views.add(market_view3);
        views.add(market_view4);
        views.add(market_view5);

        viewpager = (ViewPager) view.findViewById(R.id.viewpager);

        selfchoseFragment = new SelfchoseFragment();
        indexFragment = new IndexFragment();
        plateFragment = new PlateFragment();
        shenzhengFragment = new ShenzhengFragment();
        gangmeiFragment = new GangmeiFragment();

        fragmentArrayList.add(selfchoseFragment);
        fragmentArrayList.add(indexFragment);
        fragmentArrayList.add(shenzhengFragment);
        fragmentArrayList.add(plateFragment);
        fragmentArrayList.add(gangmeiFragment);

        market_selfchosebtn.setOnClickListener(this);
        market_indexbtn.setOnClickListener(this);
        market_shenzhenbtn.setOnClickListener(this);
        market_platebtn.setOnClickListener(this);
        market_gangmeibtn.setOnClickListener(this);

        position = 0;
        fm = getChildFragmentManager();
        viewpager.setAdapter(new MyFrageStatePagerAdapter(fm));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.market_selfchosebtn:
                if(position==0){
                    return;
                }
                chose(0);
                break;

            case R.id.market_indexbtn:
                if(position==1){
                    return;
                }
                chose(1);
                break;

            case R.id.market_shenzhenbtn:
                if(position==2){
                    return;
                }
                chose(2);
                break;

            case R.id.market_platebtn:
                if(position==3){
                    return;
                }
                chose(3);
                break;

            case R.id.market_gangmeibtn:
                if(position==4){
                    return;
                }
                chose(4);

                break;
        }
    }

    private void chose(int po){
        for (int i = 0; i < views.size(); i++) {
            if (i == po) {
                texts.get(i).setTextColor(getResources().getColor(R.color.home_color));
                views.get(i).setVisibility(View.VISIBLE);
            } else {
                texts.get(i).setTextColor(getResources().getColor(R.color.text_black));
                views.get(i).setVisibility(View.GONE);
            }

        }
        viewpager.setCurrentItem(po, true);
        position=po;
    }

    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter
    {

        public MyFrageStatePagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container)
        {
            super.finishUpdate(container);//这句话要放在最前面，否则会报错
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem=viewpager.getCurrentItem();
            if (currentItem==position)
            {
                return ;
            }
            chose(viewpager.getCurrentItem());
            position=viewpager.getCurrentItem();
        }

    }
}
