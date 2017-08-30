package com.bwie.aizhonghui.xutilshttp_github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.aizhonghui.xutilshttp_github.Adapter.MyAdapter;
import com.bwie.aizhonghui.xutilshttp_github.Bean.Mybean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private String url="http://v.juhe.cn/toutiao/index";
    private List<Mybean> mblist=new ArrayList<>();
    private MyAdapter ma;
    @ViewInject(R.id.lv_xlv)
    XListView lvv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);
        initutils();
    }

    private void initutils() {
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("key","22a108244dbb8d1f49967cd74a0c144d");
        params.addBodyParameter("type","top");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("——————"+result);
                JSONjie(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void JSONjie(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            JSONObject result1 = obj.getJSONObject("result");
            JSONArray data = result1.getJSONArray("data");
            if(data!=null&&data.length()>0){
                for (int i = 0; i <data.length(); i++) {
                    JSONObject js = data.getJSONObject(i);
                    Mybean mb=new Mybean();
                    mb.title=js.optString("title");
                    mb.author_name=js.optString("author_name");
                    mb.pics=js.optString("thumbnail_pic_s");
                    mblist.add(mb);
                }
            }
            if(mblist!=null){
              setDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDate() {
     ma=new MyAdapter(MainActivity.this,mblist);
     lvv.setAdapter(ma);
    }
}
