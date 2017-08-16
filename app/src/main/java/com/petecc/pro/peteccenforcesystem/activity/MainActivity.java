package com.petecc.pro.peteccenforcesystem.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.petecc.pro.peteccenforcesystem.R;
import com.petecc.pro.peteccenforcesystem.adapter.MyRecycleViewAdapter;
import com.petecc.pro.peteccenforcesystem.base.BaseActivity;
import com.petecc.pro.peteccenforcesystem.model.UserInfoResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;


    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ButterKnife.bind(this);
        setView();
    }


    @Override
    public void creatPresenter() {

    }

    private void setView() {
        setSupportActionBar(toolbar);//设置toolebar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.item1);
        navigationView.setNavigationItemSelectedListener(this);
        //drawer.openDrawer(GravityCompat.START);
        final List<String> strs = new ArrayList<>();
       for(int i = 0; i < 10; i++) {
         strs.add(i+"项");
       }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new MyRecycleViewAdapter(strs,R.layout.recyclerview_item,mContext,false) {
            @Override
            protected void setPositionClick(MyViewHolder holder, int position) {
                toInfoActivity();
            }

            @Override
            protected void initData(MyViewHolder holder, int position) {
                holder.setText(R.id.item_text,strs.get(position));
            }
        });
    }

    /**
     * 去往详情页面
     */
    private void toInfoActivity() {
        Intent intent = new Intent(mContext,InfoActivity.class);
        startActivity(intent);
    }

//    @OnClick({R.id.main_btn})
//    void OnClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_btn :
//                break;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exit :
                Toast.makeText(MainActivity.this, "退出", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_setting:
                Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.item1) {
            Toast.makeText(MainActivity.this, R.string.one_stall, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item2) {
            Toast.makeText(MainActivity.this, R.string.integral_management, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item3) {
            Toast.makeText(MainActivity.this, R.string.online_patrol, Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 获取数据成功的方法
     * @param model
     */
    @Override
    public void getDataSuccess(Object model) {
        UserInfoResult userInfoResult = (UserInfoResult) model;
        Toast.makeText(MainActivity.this, "getDataSuccess"+userInfoResult.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取数据失败方法
     * @param msg
     */
    @Override
    public void getDataFail(String msg) {
        Toast.makeText(MainActivity.this,"getDataSuccess"+msg, Toast.LENGTH_SHORT).show();
    }
}
