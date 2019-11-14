package com.ceng319.mpandroidchart_demo;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Pie extends AppCompatActivity {
    PieChart piechart;
    int N = 10;  // generate N demo/fake data.
    // the labels that should be drawn on the XAxis
    String[] XLabels = new String[] {"Jan", "Feb", "Mar", "Apr", "May",
                                     "Jun", "Jul", "Aug", "Sep", "Oct",
                                     "Nov", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        setupTitleandHomeButton();


        // TODO 4: Find the linechart view from layout.
        piechart = findViewById(R.id.pie_chart);

        // TODO 5: Find the "demo" data, this must be the last function to call.
        List<PieEntry> dataGenerated = getStaticData();

        // TODO: Draw graph on pictures.
        drawGraph(dataGenerated);
    }



    @Override
    public void onBackPressed() {
            finish();
    }


    private void drawGraph(List dataGenerated) {

        // TODO: Set description of the xAxis
        Description desc = new Description();
        desc.setText(getString(R.string.pie));
        desc.setTextSize(34);
        piechart.setDescription(desc);
         piechart.animateXY(1000,1000);
      //  piechart.animateY(1000);
        // TODO: Set PieDataSet
        PieDataSet dataset = new PieDataSet(dataGenerated, getString(R.string.temperature));
        // TODO: Set Table Colors.
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData piedata = new PieData(dataset);

        // TODO: Set the X-axis labels
        setAndValidateLabels();
        piechart.setData(piedata);

        piechart.invalidate(); // refresh
    }

    private void setAndValidateLabels() {
// customize legends
        Legend l = piechart.getLegend();
        l.setXOffset(0);
        //l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

    }

    private List getStaticData() {
        // TODO : Get DataStructure
        DataStructure data = new DataStructure();
        // TODO : Get List of Entries for the Chart.
        List<PieEntry> dataArray = new ArrayList ();
        // TODO : Add N sample data into the arraylist to feed the chart.
        for (int i=0; i< N; i++) {
            // generate a random number between 21 and 35.
            data.setTemperature(String.valueOf(randomWithinRange(21, 35)));

            // TODO : Get the pieentry data
            PieEntry e = new PieEntry (Float.parseFloat(data.getTemperature()), XLabels[i]);
            dataArray.add(e);
        }

        return dataArray;

    }

    int randomWithinRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    private void setupTitleandHomeButton() {
        getSupportActionBar().setSubtitle(R.string.local_pie);
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
