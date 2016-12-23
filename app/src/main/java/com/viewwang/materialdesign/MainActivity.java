package com.viewwang.materialdesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.allenliu.floatview.FloatView;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

public class MainActivity extends Activity implements AppBarLayout.OnOffsetChangedListener,DefaultHardwareBackBtnHandler{
    public RecyclerView mRecyclerView;
    private RecycleViewAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private AppBarLayout appBarLayout;
    private FloatView floatView;
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mReactRootView = new ReactRootView(this);
//        mReactInstanceManager = ReactInstanceManager.builder()
//                .setApplication(getApplication())
//                .setBundleAssetName("index.android.bundle")
//                .setJSMainModuleName("index.android")
//                .addPackage(new MainReactPackage())
//                .setUseDeveloperSupport(BuildConfig.DEBUG)
//                .setInitialLifecycleState(LifecycleState.RESUMED)
//                .build();
        initView();

    }

    private void initView() {
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        collapsingToolbarLayout =(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("文章");
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new RecycleViewAdapter(DataSource.generateData(20));
//        collapsingToolbarLayout.setTitle("Title");
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        swipeRefreshLayout.setColorSchemeResources(R.color.green,R.color.orange,R.color.blue);
            initBottomNavigationView();

        initListener();
    }

    private void initListener() {
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    System.out.println("MOVE");
                    LayoutInflater factorys = LayoutInflater.from(MainActivity.this);
                    final View item = factorys.inflate(R.layout.cardview, null);
                    item.setTranslationZ(0);
                }
                return false;
            }
        });
        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(MainActivity.this, "data:" + data, Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setTranslationZ(5);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setTranslationZ(0);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                floatView.addToWindow();
                Intent intent =new Intent(MainActivity.this,PlayActivity.class);
                startActivity(intent);
            }
        });

        floatView=new FloatView(this,0,0,R.layout.floatview_layotu);
        floatView.setFloatViewClickListener(new FloatView.IFloatViewClick() {
            @Override
            public void onFloatViewClick() {
                Toast.makeText(MainActivity.this,"floatview is clicked",Toast.LENGTH_SHORT).show();
            }
        });
        floatView.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                final int keyStowevent = event.getAction();

                if ((keyStowevent == MotionEvent.ACTION_HOVER_EXIT)) {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                        }
//                    }).start();
                    floatView.setVisibility(View.GONE);
                }
                return false;
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }

        });

        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        loadData();
    }

    private void loadData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                    DataSource.generateData(20);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerView.setAdapter(mAdapter);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Record", getResources().getColor(R.color.green), R.drawable.ic_account_circle_red_400_24dp);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("Like", getResources().getColor(R.color.blue), R.drawable.ic_polymer_cyan_a700_24dp);

        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);

        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                Toast.makeText(MainActivity.this, "Item " +index +" clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshContent() {
        mAdapter = new RecycleViewAdapter(DataSource.generateData(20));
        mRecyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(verticalOffset==0){
            swipeRefreshLayout.setEnabled(true);
        }else {
            swipeRefreshLayout.setEnabled(false);
        }
    }

    @Override
    protected void onStart() {
        floatView.addToWindow();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    protected void onStop() {
        floatView.removeFromWindow();
        super.onStop();
    }

    @Override
    public void invokeDefaultOnBackPressed() {

    }
}


