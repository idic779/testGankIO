package com.example.administrator.testgankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.testgankio.adpter.ReadAdapter;
import com.example.administrator.testgankio.model.DataResults;
import com.example.administrator.testgankio.model.Results;
import com.example.administrator.testgankio.retrofit.CoderfunSingle;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/18.
 */
public class ReadFragment extends Fragment {
    private SwipyRefreshLayout swipyRefreshLayout;
    private RecyclerView recyclerview;
    private ReadAdapter readAdapter;

    private static final String ARG_TITLE = "title";
    private String mTitle;
    private static int read_num = CoderfunKey.READ_NUM;
    private static int NOW_PAGE_READ = 1;

    public static ReadFragment getInstance(String title) {
        ReadFragment readFragment=new ReadFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_TITLE,title);
        readFragment.setArguments(bundle);
        return  readFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        mTitle=bundle.getString(ARG_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list, container, false);
        initRecyclerView(view);
        initSwipyRefreshLaout(view);
        return view;
    }

    private void initSwipyRefreshLaout(View view) {
        swipyRefreshLayout= (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                Observable.timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                swipyRefreshLayout.setRefreshing(false);
                            }
                        });
                loadData(direction == SwipyRefreshLayoutDirection.TOP ? true : false);
            }
        });
    }

    private void initRecyclerView(View view) {
            recyclerview= (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerview.setLayoutManager(new LinearLayoutManager(recyclerview.getContext()));
            readAdapter=new ReadAdapter(getActivity(),null);
            recyclerview.setAdapter(readAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(true);
    }

    private void loadData(boolean isTop) {
        if (isTop) {
            NOW_PAGE_READ=1;
        }
        getDataResults(mTitle, read_num, NOW_PAGE_READ, isTop);
    }

    private void getDataResults(final  String type,int number,int page,final boolean isTop) {
        CoderfunSingle.getInstance().getDataResults(type,number,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataResults>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(recyclerview, "网络不顺畅嘞,更新不了数据啦", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(DataResults dataResults) {
                        if (dataResults.isError()) {
                            Snackbar.make(recyclerview, "啊擦，服务器出问题啦", Snackbar.LENGTH_SHORT).show();
                        } else {
                            if (isTop) {
                                clearAdapterResults();
                            }
                            dealWithDataInRecyclerView(dataResults.getResults());
                        }
                    }
                }) ;
    }

    private void dealWithDataInRecyclerView(List<Results> results) {
        readAdapter.getRead_list().addAll(results);
        readAdapter.notifyDataSetChanged();
        NOW_PAGE_READ++;
    }

    private void clearAdapterResults() {
        readAdapter.getRead_list().clear();
    }

}
