package com.example.administrator.testgankio;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.testgankio.utils.SaveImageUtils;
import com.example.administrator.testgankio.utils.ShareUtils;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2016/6/19.
 */
public class ImageAcitivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SimpleDraweeView simpleDraweeView;
    private String url,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        CoderfunCache.isBackFromWebOrImage=true;
        url=getIntent().getStringExtra("url");
        desc=getIntent().getStringExtra("desc");
        Log.d("测试2", "2222"+url+desc);
        initToolbar();
        initSimpleDraweeView();
    }

    private void initSimpleDraweeView() {
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.draweeview);
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        hierarchy.setProgressBarImage(new ProgressBarDrawable());
        Uri uri = Uri.parse(url);

        simpleDraweeView.setImageURI(uri);
        Log.d("1", "2222"+url+desc);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle("beautiful girls");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                ShareUtils.shareImage(this, SaveImageUtils.saveImage(url, desc, this));
                break;
            case R.id.action_save:
                SaveImageUtils.saveImage(url, desc, this);
                Toast.makeText(this, "已经保存图片啦", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}
