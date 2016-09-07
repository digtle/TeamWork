package com.phone1000.groupproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.phone1000.groupproject.fragment.FindFragment;
import com.phone1000.groupproject.fragment.HomepageFragment;
import com.phone1000.groupproject.fragment.TeamFragment;

public class MainActivity extends AppCompatActivity {

    private HomepageFragment mHomepageFragment;
    private TeamFragment mTeamFragment;
    private FindFragment mFindFragment;
    private RadioGroup mRadioGroup;
    private Fragment mCurrentShowFragment;
    private FragmentManager mSupportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSupportFragmentManager = getSupportFragmentManager();
         //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        initFragment();
        initView();
    }
    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_bottom_radio_group);
        //默认选中
        mRadioGroup.check(R.id.main_rb);
        //设置监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.main_rb:
                        controlFragment(mHomepageFragment);
                        break;
                    case R.id.team_rb:
                        controlFragment(mTeamFragment);
                        break;
                    case R.id.find_rb:
                        controlFragment(mFindFragment);
                }
            }
        });
    }

    private void controlFragment(Fragment fragment){
        //开启事务
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        //判断当前显示的Fragment是否为空，不为空并且当前显示的Fragment已经存在，就将当前显示的Fragment隐藏
        if (mCurrentShowFragment != null && mCurrentShowFragment.isAdded()){
            fragmentTransaction.hide(mCurrentShowFragment);
        }
        //判断fragment是否添加到supportFragmentManager（视图）中来
        if (!fragment.isAdded()){
            //如果没有添加，就添加到布局中来
            fragmentTransaction.add(R.id.main_frame_layout,fragment);
        } else {
            //如果已经添加了，就show出来
            fragmentTransaction.show(fragment);
        }

        //别忘记提交
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }

    //初始化Fragment
    private void initFragment() {
        mHomepageFragment = HomepageFragment.newInstance();
        mTeamFragment = TeamFragment.newInstance();
        mFindFragment = FindFragment.newInstance();
        //默认添加第一个Fragment
        controlFragment(mHomepageFragment);
    }
}
