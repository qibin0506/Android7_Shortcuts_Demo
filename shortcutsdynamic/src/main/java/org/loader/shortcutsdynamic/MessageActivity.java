package org.loader.shortcutsdynamic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by qibin on 16-10-20.
 */

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        TextView msg = (TextView) findViewById(R.id.msg);
        msg.setText(getIntent().getStringExtra("msg"));
    }
}
