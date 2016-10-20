package org.loader.shortcutsdynamic;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Adapter mAdapter;
    private ShortcutManager mShortcutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);
        setupAdapter();
        mListView.setAdapter(mAdapter);
        setupShortcuts();

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAdapter.remove(i);
                removeItem(i);
                return true;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAdapter.set("新的名字", i);
                updItem(i);
            }
        });
    }

    private void updItem(int index) {
        Intent intent = new Intent(this, MessageActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.putExtra("msg", "我和" + mAdapter.getItem(index) + "的对话");

        ShortcutInfo info = new ShortcutInfo.Builder(this, "id" + index)
                .setShortLabel(mAdapter.getItem(index))
                .setLongLabel("联系人:" + mAdapter.getItem(index))
                .setIcon(Icon.createWithResource(this, R.drawable.icon))
                .setIntent(intent)
                .build();

        mShortcutManager.updateShortcuts(Arrays.asList(info));
    }

    private void removeItem(int index) {
        List<ShortcutInfo> infos = mShortcutManager.getPinnedShortcuts();
        for (ShortcutInfo info : infos) {
            if (info.getId().equals("id" + index)) {
                mShortcutManager.disableShortcuts(Arrays.asList(info.getId()), "暂无该联系人");
            }
        }
        mShortcutManager.removeDynamicShortcuts(Arrays.asList("id" + index));
    }

    private void setupShortcuts() {
        mShortcutManager = getSystemService(ShortcutManager.class);

        List<ShortcutInfo> infos = new ArrayList<>();
        for (int i = 0; i < mShortcutManager.getMaxShortcutCountPerActivity(); i++) {
            Intent intent = new Intent(this, MessageActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            intent.putExtra("msg", "我和" + mAdapter.getItem(i) + "的对话");

            ShortcutInfo info = new ShortcutInfo.Builder(this, "id" + i)
                    .setShortLabel(mAdapter.getItem(i))
                    .setLongLabel("联系人:" + mAdapter.getItem(i))
                    .setIcon(Icon.createWithResource(this, R.drawable.icon))
                    .setIntent(intent)
                    .build();
            infos.add(info);
//            manager.addDynamicShortcuts(Arrays.asList(info));
        }

        mShortcutManager.setDynamicShortcuts(infos);
    }

    private void setupAdapter() {
        List<String> list = new ArrayList<>(10);
        list.add("亓斌");
        list.add("齐彬");
        list.add("乔杉");
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("狗蛋");
        list.add("二狗子");
        mAdapter = new Adapter();
        mAdapter.setDatas(list);
    }
}
