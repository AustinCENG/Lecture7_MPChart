package com.ceng319.mpandroidchart_demo;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Line extends AppCompatActivity {
    LineChart linechart;
    int N = 10;  // generate N demo/fake data.
    // the labels that should be drawn on the XAxis
    String[] XLabels = new String[N];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        setupTitleandHomeButton();

        // TODO 4: Find the linechart view from layout.
        linechart = findViewById(R.id.line_chart);

        // TODO 5: Find the "demo" Data List, this List should be a List of Entries.
       List<Entry> dataGenerated = getStaticData();

        // TODO 6: Draw graph on pictures.
        drawGraph(dataGenerated);
    }

    private void drawGraph(List dataGenerated) {
        // TODO: Set text description of the xAxis
        Description desc = new Description();
        desc.setText("LineChart");
        desc.setTextSize(15);
        linechart.setDescription(desc);
        linechart.animateXY(1000, 1000);
        // TODO: Set the X-axis labels
        setAndValidateLabels();

                // TODO: Set LineData
        // TODO: find the dataset of the ArrayList.
        LineDataSet dataset = new LineDataSet(dataGenerated, "Temperature");
        dataset.setColor(R.color.colorAccent);  // set the color of this chart.
        // TODO: Get the LineData Object from dataset.
        LineData linedata = new LineData(dataset);
        linechart.setData(linedata);
        // TODO: This is a must to refresh the chart.
        linechart.invalidate(); // refresh
    }

    private void setAndValidateLabels() {
        // TODO: Set the labels to be displayed.
        XAxis xAxis = linechart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(-30f);  // rotate the xAxis label for 30 degrees.
        xAxis.setValueFormatter(new IAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return XLabels[(int) value];
            }
        });
        xAxis.setGranularity(3f); // minimum axis-step (interval) is 3
    }

    private List getStaticData() {
        // TODO : Define the DataStructure to hold your data.
        DataStructure data = new DataStructure();

        // TODO : Define an ArrayList for Entries of the Chart.
        List<Entry> dataArray = new ArrayList ();

        // TODO : Add N sample data into the arraylist to feed the chart.
        for (int i=0; i< N; i++) {
            // generate a random number between 21 and 35.
            data.setTemperature(String.valueOf(randomWithinRange(21, 35)));
            // TODO: Get the timestamp for the data
            Long time = System.currentTimeMillis()/1000 + i*60; // just simulate the data is generated every one minute.
            String timestamp = time.toString();
            data.setTimestamp(timestamp);
            // TODO: Define the XLabels of the chart.
            XLabels[i] = convertTimestamp(timestamp);

            // TODO: Entry is the element of the data input to the chart. All the data should be organized as Entries' ArrayList
            Entry e = new Entry (i, Float.parseFloat(data.getTemperature()));

            // TODO: This is the final ArrayList generated, it should be a list collection of Entries.
            dataArray.add(e);
        }

        return dataArray;  // Now dataArray should be the data feed to the chart.

    }

    int randomWithinRange(int min, int max)
    {
        // method to generate a random number between min and max.
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    private String convertTimestamp(String timestamp){

        // Convert timestamp to text.
        long yourSeconds = Long.valueOf(timestamp);
        Date mDate = new Date(yourSeconds*1000);
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        // df.setTimeZone(TimeZone.getTimeZone("Etc/GMT-5"));
        DateFormat df1 = new SimpleDateFormat("hh:mm:ss");
            Log.d("MapleLeaf", df.format(mDate) +System.lineSeparator() + df1.format(mDate));
        return df.format(mDate) +System.lineSeparator() + df1.format(mDate);
    }

    private void setupTitleandHomeButton() {
        getSupportActionBar().setSubtitle(R.string.local_line);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     *
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
