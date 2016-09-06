/*
 * 鍔ㄦ�佹柟寮忓拰闈欐�佹姌绾垮浘鏂瑰紡瀹屽叏绫讳技锛屽彧鏄湪鏈�鍒濆苟娌℃湁涓� XYSeries 瀵硅薄add鍊硷紝
 * 鑰屾槸鍚庨潰鍔ㄦ�佹坊鍔犲�硷紝骞朵笖娣诲姞鏂板�煎悗锛屽埛鏂板嵆鍙��
 * 姝ゅ浣跨敤 Timer 鍜孴imerTask杩涜瀹氭椂娣诲姞鏂版暟鎹�
 * 
 * 涓ょ鎶樼嚎鍥惧疄鐜板嚑涔庡畬鍏ㄤ竴鏍凤紝鍙槸娣诲姞鐐规椂鐨勫鐞嗕笉鍚岋紝鍗虫柟娉� initLine(XYSeries series) 涓疄鐜颁笉鍚� 
 * 
 */

package com.example.line_chart;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint.Align;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.util.Yaohui;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DynamicChartLineActivity2 extends Activity {
    private RelativeLayout dynamic_chart_line_layout2;
    private String title = "鍔ㄦ�佹姌绾垮浘鏂瑰紡2";

    // 鐢ㄤ簬瀛樻斁姣忔潯鎶樼嚎鐨勭偣鏁版嵁
    private XYSeries line1, line2;
    // 鐢ㄤ簬瀛樻斁鎵�鏈夐渶瑕佺粯鍒剁殑XYSeries
    private XYMultipleSeriesDataset mDataset;
    // 鐢ㄤ簬瀛樻斁姣忔潯鎶樼嚎鐨勯鏍�
    private XYSeriesRenderer renderer1, renderer2;
    // 鐢ㄤ簬瀛樻斁鎵�鏈夐渶瑕佺粯鍒剁殑鎶樼嚎鐨勯鏍�
    private XYMultipleSeriesRenderer mXYMultipleSeriesRenderer;
    private GraphicalView chart;

    // 浠ヤ笅灞炴�х敤浜巌nitLine(XYSeries series)鏂规硶涓洿鏂版暟鎹�
    private int count;
    private double xTemp, yTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_chart_line_activity2);

        dynamic_chart_line_layout2 = (RelativeLayout) findViewById(R.id.dynamic_chart_line_layout2);

        initChart();
        refreshChart();

    }

    private void initChart() {
        // 鍒濆鍖栵紝蹇呴』淇濊瘉XYMultipleSeriesDataset瀵硅薄涓殑XYSeries鏁伴噺鍜�
        // XYMultipleSeriesRenderer瀵硅薄涓殑XYSeriesRenderer鏁伴噺涓�鏍峰
        line1 = new XYSeries("鎶樼嚎1");
        line2 = new XYSeries("鎶樼嚎2");
        renderer1 = new XYSeriesRenderer();
        renderer2 = new XYSeriesRenderer();
        mDataset = new XYMultipleSeriesDataset();
        mXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer();

        // 瀵筙YSeries鍜孹YSeriesRenderer鐨勫璞＄殑鍙傛暟璧嬪��
        // initLine(line1);
        // initLine(line2);
        initRenderer(renderer1, Color.GREEN, PointStyle.CIRCLE, true);
        initRenderer(renderer2, Color.CYAN, PointStyle.TRIANGLE, true);

        // 灏哫YSeries瀵硅薄鍜孹YSeriesRenderer瀵硅薄鍒嗗埆娣诲姞鍒癤YMultipleSeriesDataset瀵硅薄鍜孹YMultipleSeriesRenderer瀵硅薄涓��
        mDataset.addSeries(line1);
        mDataset.addSeries(line2);
        mXYMultipleSeriesRenderer.addSeriesRenderer(renderer1);
        mXYMultipleSeriesRenderer.addSeriesRenderer(renderer2);

        // 閰嶇疆chart鍙傛暟
        setChartSettings(mXYMultipleSeriesRenderer, "X", "Y", 0, 10, 0, 100, Color.RED,
                Color.WHITE);

        // 閫氳繃璇ュ嚱鏁拌幏鍙栧埌涓�涓猇iew 瀵硅薄
        chart = ChartFactory.getLineChartView(this, mDataset, mXYMultipleSeriesRenderer);

        // 灏嗚View 瀵硅薄娣诲姞鍒發ayout涓��
        dynamic_chart_line_layout2.addView(chart, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

    }

    private XYSeriesRenderer initRenderer(XYSeriesRenderer renderer, int color,
            PointStyle style, boolean fill) {
        // 璁剧疆鍥捐〃涓洸绾挎湰韬殑鏍峰紡锛屽寘鎷鑹层�佺偣鐨勫ぇ灏忎互鍙婄嚎鐨勭矖缁嗙瓑
        renderer.setColor(color);
        renderer.setPointStyle(style);
        renderer.setFillPoints(fill);
        renderer.setLineWidth(1);
        renderer.setFillBelowLine(true);
        Yaohui.setYaohui(true);
        Yaohui.setLg(new LinearGradient(130, 100, 130, 250, Color.GREEN, 0, TileMode.CLAMP));
        return renderer;
    }

    protected void setChartSettings(XYMultipleSeriesRenderer mXYMultipleSeriesRenderer,
            String xTitle, String yTitle, double xMin, double xMax,
            double yMin, double yMax, int axesColor, int labelsColor) {
        // 鏈夊叧瀵瑰浘琛ㄧ殑娓叉煋鍙弬鐪媋pi鏂囨。
        mXYMultipleSeriesRenderer.setChartTitle(title);
        mXYMultipleSeriesRenderer.setXTitle(xTitle);
        mXYMultipleSeriesRenderer.setYTitle(yTitle);
        mXYMultipleSeriesRenderer.setXAxisMin(xMin);
        mXYMultipleSeriesRenderer.setAxisTitleTextSize(30);
        mXYMultipleSeriesRenderer.setChartTitleTextSize(50);
        mXYMultipleSeriesRenderer.setLabelsTextSize(15);
        mXYMultipleSeriesRenderer.setXAxisMax(xMax);
        mXYMultipleSeriesRenderer.setYAxisMin(yMin);
        mXYMultipleSeriesRenderer.setYAxisMax(yMax);
        mXYMultipleSeriesRenderer.setAxesColor(axesColor);
        mXYMultipleSeriesRenderer.setLabelsColor(labelsColor);
        mXYMultipleSeriesRenderer.setShowGrid(true);
        mXYMultipleSeriesRenderer.setGridColor(Color.GRAY);
        mXYMultipleSeriesRenderer.setXLabels(20);
        mXYMultipleSeriesRenderer.setYLabels(10);
        mXYMultipleSeriesRenderer.setXTitle("time");
        mXYMultipleSeriesRenderer.setYLabelsAlign(Align.RIGHT);
        mXYMultipleSeriesRenderer.setPointSize((float) 5);
        mXYMultipleSeriesRenderer.setShowLegend(true);
        mXYMultipleSeriesRenderer.setLegendTextSize(20);
    }

    class RefreshSeriesTask extends TimerTask {
        public void run() {
            initLine(line1);
            initLine(line2);
            chart.postInvalidate();
        }
    }

    private void initLine(XYSeries series) {

        count = series.getItemCount();

        Random r = new Random();
        if (count != 0) {
            xTemp = series.getX(count - 1) + 1;
        }
        else {
            xTemp = 0;
        }
        yTemp = r.nextInt(100);
        series.add(xTemp, yTemp);

    }

    private void refreshChart() {
        Timer timer = new Timer();
        timer.schedule(new RefreshSeriesTask(), 0, 1 * 1000);
    }
}
