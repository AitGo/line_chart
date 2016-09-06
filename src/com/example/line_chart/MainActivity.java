
package com.example.line_chart;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button static_button, dynamic_button, dynamic_button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        static_button = (Button) findViewById(R.id.static_line_chart);
        dynamic_button = (Button) findViewById(R.id.dynamic_line_chart);
        dynamic_button2 = (Button) findViewById(R.id.dynamic_line_chart2);

        static_button.setOnClickListener(new ButtonListener());
        dynamic_button.setOnClickListener(new ButtonListener());
        dynamic_button2.setOnClickListener(new ButtonListener());
    }

    class ButtonListener implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();

            switch (arg0.getId())
            {
                case R.id.static_line_chart:
                    intent.setClass(MainActivity.this, StaticChartLineActivity.class);
                    break;
                case R.id.dynamic_line_chart:
                    intent.setClass(MainActivity.this, DynamicChartLineActivity1.class);
                    break;
                case R.id.dynamic_line_chart2:
                    intent.setClass(MainActivity.this, DynamicChartLineActivity2.class);
                    break;
            }

            MainActivity.this.startActivity(intent);

        }

    }
}
