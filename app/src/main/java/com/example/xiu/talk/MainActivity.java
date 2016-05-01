package com.example.xiu.talk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toggle = (Button)findViewById(R.id.toggle);
        final Slide_Menu slide_menu = (Slide_Menu)findViewById(R.id.slideMenu);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slide_menu.toggle();
            }
        });
    }
}
