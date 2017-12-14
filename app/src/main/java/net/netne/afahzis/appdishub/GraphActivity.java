package net.netne.afahzis.appdishub;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.netne.afahzis.appdishub.Server.AppVar;
import net.netne.afahzis.appdishub.Server.JSONParser;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GraphActivity extends AppCompatActivity {

    Toolbar toolbar;
    private GraphicalView mChart;
    String bulan[] = {"Jan","Feb", "Mar","Apr","Mei","Jun","Jul","Agts","Sepr","Okt","Nov","Des"};
    String level="";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    LinearLayout chart_container;
    int first=0,done=0;

    //grafik
    List<int[]> dataMenu = new ArrayList<int[]>();
    List<XYSeries> xySeriesList =new ArrayList<XYSeries>();
    List<XYSeriesRenderer> rendering = new ArrayList<XYSeriesRenderer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        chart_container=(LinearLayout)findViewById(R.id.layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        sharedpreferences = getSharedPreferences(AppVar.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        level = getIntent().getExtras().getString("level");

        dataMenu.clear();
        xySeriesList.clear();
        rendering.clear();
        dataMenu.clear();

        if(level.equals("5")){
            getParkir();
        }else{
            getData();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    /*private void OpenChart()
    {



        //Definisikan nilai-nilai yang ingin
        //divisualisasikan ke dalam chart
        //int x[]=data;

        // Create a Dataset to hold the XSeries.

        XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();

        // Add X series to the Dataset.

        for(int i=0;i<dataMenu.size();i++){
            int setData[]=dataMenu.get(i);
            for (int j=0;j<setData.length;j++){
                xySeriesList.get(i).add(j,setData[j]);
            }
        }

        for (int i=0;i<xySeriesList.size();i++){
            dataset.addSeries(xySeriesList.get(i));
        }

        // Create XYSeriesRenderer to customize XSeries

        for (int i=0;i<rendering.size();i++){
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            rendering.get(i).setColor(color);
            rendering.get(i).setPointStyle(PointStyle.DIAMOND);
            rendering.get(i).setDisplayChartValues(true);
            rendering.get(i).setLineWidth(2);
            rendering.get(i).setFillPoints(true);
        }


        // Create XYMultipleSeriesRenderer to customize the whole chart
        final XYMultipleSeriesRenderer mRenderer=new XYMultipleSeriesRenderer();

        mRenderer.setChartTitle("Statistik");
        mRenderer.setXTitle("Bulan");
        mRenderer.setYTitle("Value");

        //Tambahkan tombol Zoom
        mRenderer.setZoomButtonsVisible(false);

        //Kita set 0 agar kita bisa membuat label kita sendiri
        mRenderer.setXLabels(0);

        //Agar chart bisa kita geser, kita aktifkan pan
        mRenderer.setPanEnabled(true);

        //Jika kita ingin membatasi user menggeser dari koodinat mana sampai mana,
        //kita set panlimit(xMinPanCoor, xMaxPanCoor, yMinPanCoor, yMaxPanCoor)
        //mRenderer.setPanLimits(new double[] {0,1000000000,-1000000000,1000000000});

        //Agar chart bisa kita zoom, kita aktifkan zoom
        //mRenderer.setZoomEnabled(true);

        //Kita set biar ada grid yang tampak
        mRenderer.setShowGrid(true);

        //Set warna chart, label, axis
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.DKGRAY);
        mRenderer.setMarginsColor(Color.DKGRAY);
        mRenderer.setAxesColor(Color.WHITE);
        mRenderer.setLabelsColor(Color.WHITE);
        mRenderer.setXLabelsColor(Color.WHITE);
        mRenderer.setYLabelsColor(0, Color.WHITE);

        mRenderer.setXLabelsAlign(Paint.Align.CENTER);

        mRenderer.setClickEnabled(true);

        //Jika kita ingin label hanya ada pada range/nilai tertentu
        //Disini saya set label hanya ada setiap tiga nilai value dalam chart
        for(int i=0;i<bulan.length;i++)
        {
            mRenderer.addXTextLabel(i, bulan[i].toString());  //label bisa angka, bisa text juga
        }

        // Adding the XSeriesRenderer to the MultipleRenderer.
        for (int i =0;i<rendering.size();i++){
            mRenderer.addSeriesRenderer(rendering.get(i));
        }




        // Creating an intent to plot line chart using dataset and multipleRenderer

        mChart=(GraphicalView) ChartFactory.getLineChartView(getBaseContext(), dataset, mRenderer);
        mChart.setBackgroundColor(Color.DKGRAY);

        //Di sini kita buat jika user mengklik suatu nilai values dalam chart,
        //akan nampil Toast nilai yang diklik user

        mChart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                SeriesSelection series_selection=mChart.getCurrentSeriesAndPoint();

                if(series_selection!=null)
                {
                    int series_index=series_selection.getSeriesIndex();

                    String select_series="X Series";
                    if(series_index==0)
                    {
                        select_series="X Series";
                    }else
                    {
                        select_series="Y Series";
                    }

                    //String month=mMonth[(int)series_selection.getXValue()];

                    int amount=(int)series_selection.getValue();

                    Toast.makeText(getBaseContext(), select_series+"in: "+amount, Toast.LENGTH_LONG).show();
                }
            }
        });


        chart_container.addView(mChart); // Masukkan chart ke dalam linear layout yang sudah dibuat

    }*/

    private void ChartParkir(int target[], int tercapai[])
    {
        //Definisikan nilai-nilai yang ingin
        //divisualisasikan ke dalam chart
        int x[]=target;
        int y[]=tercapai;


        // Create XY Series for X Series.
        XYSeries xSeries=new XYSeries("Target");
        XYSeries xxSeries=new XYSeries("Realisasi");


        //  Adding data to the X Series.
        for(int i=0;i<x.length;i++)
        {
            xSeries.add(i,x[i]);
            xxSeries.add(i,y[i]);
        }

        // Create a Dataset to hold the XSeries.

        XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();

        // Add X series to the Dataset.
        dataset.addSeries(xSeries);
        dataset.addSeries(xxSeries);


        // Create XYSeriesRenderer to customize XSeries

        XYSeriesRenderer Xrenderer=new XYSeriesRenderer();
        Xrenderer.setColor(Color.GREEN);
        Xrenderer.setPointStyle(PointStyle.DIAMOND);
        Xrenderer.setDisplayChartValues(true);
        Xrenderer.setLineWidth(2);
        Xrenderer.setFillPoints(true);

        XYSeriesRenderer XXrender=new XYSeriesRenderer();
        XXrender.setColor(Color.RED);
        XXrender.setPointStyle(PointStyle.DIAMOND);
        XXrender.setDisplayChartValues(true);
        XXrender.setLineWidth(2);
        XXrender.setFillPoints(true);


        // Create XYMultipleSeriesRenderer to customize the whole chart

        final XYMultipleSeriesRenderer mRenderer=new XYMultipleSeriesRenderer();

        mRenderer.setChartTitle("Statistik");
        mRenderer.setXTitle("Bulan");
        mRenderer.setYTitle("Value");

        //Tambahkan tombol Zoom
        mRenderer.setZoomButtonsVisible(false);

        //Kita set 0 agar kita bisa membuat label kita sendiri
        mRenderer.setXLabels(0);
        mRenderer.setLabelsTextSize((float) 18);

        //Agar chart bisa kita geser, kita aktifkan pan
        //mRenderer.setPanEnabled(true);

        //Jika kita ingin membatasi user menggeser dari koodinat mana sampai mana,
        //kita set panlimit(xMinPanCoor, xMaxPanCoor, yMinPanCoor, yMaxPanCoor)
        //mRenderer.setPanLimits(new double[] {0,1000000000,-1000000000,1000000000});

        //Agar chart bisa kita zoom, kita aktifkan zoom
        //mRenderer.setZoomEnabled(true);

        //Kita set biar ada grid yang tampak
        mRenderer.setShowGrid(true);

        //Set warna chart, label, axis
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.DKGRAY);
        mRenderer.setMarginsColor(Color.DKGRAY);
        mRenderer.setAxesColor(Color.WHITE);
        mRenderer.setLabelsColor(Color.WHITE);
        mRenderer.setXLabelsColor(Color.WHITE);
        mRenderer.setYLabelsColor(0, Color.WHITE);

        mRenderer.setXLabelsAlign(Paint.Align.CENTER);

        mRenderer.setClickEnabled(true);

        //Jika kita ingin label hanya ada pada range/nilai tertentu
        //Disini saya set label hanya ada setiap tiga nilai value dalam chart
        for(int i=0;i<bulan.length;i++)
        {
            mRenderer.addXTextLabel(i, bulan[i].toString());  //label bisa angka, bisa text juga
        }

        // Adding the XSeriesRenderer to the MultipleRenderer.
        mRenderer.addSeriesRenderer(Xrenderer);
        mRenderer.addSeriesRenderer(XXrender);



        // Creating an intent to plot line chart using dataset and multipleRenderer

        mChart=(GraphicalView) ChartFactory.getLineChartView(getBaseContext(), dataset, mRenderer);
        mChart.setBackgroundColor(Color.DKGRAY);

        //Di sini kita buat jika user mengklik suatu nilai values dalam chart,
        //akan nampil Toast nilai yang diklik user

        mChart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                SeriesSelection series_selection=mChart.getCurrentSeriesAndPoint();

                if(series_selection!=null)
                {
                    int series_index=series_selection.getSeriesIndex();

                    String select_series="X Series";
                    if(series_index==0)
                    {
                        select_series="X Series";
                    }else
                    {
                        select_series="Y Series";
                    }

                    //String month=mMonth[(int)series_selection.getXValue()];

                    int amount=(int)series_selection.getValue();

                    Toast.makeText(getBaseContext(), select_series+"in: "+amount, Toast.LENGTH_LONG).show();
                }
            }
        });


        chart_container.addView(mChart); // Masukkan chart ke dalam linear layout yang sudah dibuat


    }

    private void ChartData(int target[], int tercapai[])
    {
        //Definisikan nilai-nilai yang ingin
        //divisualisasikan ke dalam chart
        int x[]=target;
        int y[]=tercapai;


        // Create XY Series for X Series.
        XYSeries xSeries=new XYSeries("Target");
        XYSeries xxSeries=new XYSeries("Realisasi");


        //  Adding data to the X Series.
        for(int i=0;i<x.length;i++)
        {
            xSeries.add(i,x[i]);
            xxSeries.add(i,y[i]);
        }

        // Create a Dataset to hold the XSeries.

        XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();

        // Add X series to the Dataset.
        dataset.addSeries(xSeries);
        dataset.addSeries(xxSeries);


        // Create XYSeriesRenderer to customize XSeries

        XYSeriesRenderer Xrenderer=new XYSeriesRenderer();
        Xrenderer.setColor(Color.GREEN);
        Xrenderer.setPointStyle(PointStyle.DIAMOND);
        Xrenderer.setDisplayChartValues(true);
        Xrenderer.setLineWidth(2);
        Xrenderer.setFillPoints(true);

        XYSeriesRenderer XXrender=new XYSeriesRenderer();
        XXrender.setColor(Color.RED);
        XXrender.setPointStyle(PointStyle.DIAMOND);
        XXrender.setDisplayChartValues(true);
        XXrender.setLineWidth(2);
        XXrender.setFillPoints(true);


        // Create XYMultipleSeriesRenderer to customize the whole chart

        final XYMultipleSeriesRenderer mRenderer=new XYMultipleSeriesRenderer();

        mRenderer.setChartTitle("Statistik");
        mRenderer.setXTitle("Bulan");
        mRenderer.setYTitle("Value");

        //Tambahkan tombol Zoom
        mRenderer.setZoomButtonsVisible(false);

        //Kita set 0 agar kita bisa membuat label kita sendiri
        mRenderer.setXLabels(0);
        mRenderer.setLabelsTextSize((float) 18);

        //Agar chart bisa kita geser, kita aktifkan pan
        //mRenderer.setPanEnabled(true);

        //Jika kita ingin membatasi user menggeser dari koodinat mana sampai mana,
        //kita set panlimit(xMinPanCoor, xMaxPanCoor, yMinPanCoor, yMaxPanCoor)
        //mRenderer.setPanLimits(new double[] {0,1000000000,-1000000000,1000000000});

        //Agar chart bisa kita zoom, kita aktifkan zoom
        //mRenderer.setZoomEnabled(true);

        //Kita set biar ada grid yang tampak
        mRenderer.setShowGrid(true);

        //Set warna chart, label, axis
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.DKGRAY);
        mRenderer.setMarginsColor(Color.DKGRAY);
        mRenderer.setAxesColor(Color.WHITE);
        mRenderer.setLabelsColor(Color.WHITE);
        mRenderer.setXLabelsColor(Color.WHITE);
        mRenderer.setYLabelsColor(0, Color.WHITE);

        mRenderer.setXLabelsAlign(Paint.Align.CENTER);

        mRenderer.setClickEnabled(true);

        //Jika kita ingin label hanya ada pada range/nilai tertentu
        //Disini saya set label hanya ada setiap tiga nilai value dalam chart
        for(int i=0;i<bulan.length;i++)
        {
            mRenderer.addXTextLabel(i, bulan[i].toString());  //label bisa angka, bisa text juga
        }

        // Adding the XSeriesRenderer to the MultipleRenderer.
        mRenderer.addSeriesRenderer(Xrenderer);
        mRenderer.addSeriesRenderer(XXrender);



        // Creating an intent to plot line chart using dataset and multipleRenderer

        mChart=(GraphicalView) ChartFactory.getLineChartView(getBaseContext(), dataset, mRenderer);
        mChart.setBackgroundColor(Color.DKGRAY);

        //Di sini kita buat jika user mengklik suatu nilai values dalam chart,
        //akan nampil Toast nilai yang diklik user

        mChart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                SeriesSelection series_selection=mChart.getCurrentSeriesAndPoint();

                if(series_selection!=null)
                {
                    int series_index=series_selection.getSeriesIndex();

                    String select_series="X Series";
                    if(series_index==0)
                    {
                        select_series="X Series";
                    }else
                    {
                        select_series="Y Series";
                    }

                    //String month=mMonth[(int)series_selection.getXValue()];

                    int amount=(int)series_selection.getValue();

                    Toast.makeText(getBaseContext(), select_series+"in: "+amount, Toast.LENGTH_LONG).show();
                }
            }
        });


        chart_container.addView(mChart); // Masukkan chart ke dalam linear layout yang sudah dibuat


    }
/*
    public void getChart(String idData) {
        new DataChart().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_CHART,
                idData

        );
    }

    private class DataChart extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        private final String LOGIN_URL = AppVar.URL_SERVER;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            try {

                HashMap<String, String> params = new HashMap<>();
                params.put(AppVar.KEY_API, args[0]);
                params.put(AppVar.KEY_FUNCTION, args[1]);
                params.put(AppVar.FUNC_ID, args[2]);
                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            if (json != null) {
                try {
                    JSONArray jsonArray = json.getJSONArray("hasil");
                    if (jsonArray.length() == 0) {
                        Toast.makeText(GraphActivity.this,"Hasil tidak ditemukan",Toast.LENGTH_LONG).show();
                    } else {
                        int getTercapai[]=new int[jsonArray.length()];
                        String kegiatan="";
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject isiArray = jsonArray.getJSONObject(i);
                            kegiatan = isiArray.getString("var_kegiatan");
                            String dana = isiArray.getString("var_dana");
                            getTercapai[i]=Integer.valueOf(dana);
                        }
                        dataMenu.add(getTercapai);
                        if(xySeriesList.size()==dataMenu.size()){
                            OpenChart();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(GraphActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

 */

    public void getParkir() {
        new DataParkir().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_CHART_PARKIR
        );
    }

    private class DataParkir extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        private final String LOGIN_URL = AppVar.URL_SERVER;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            try {

                HashMap<String, String> params = new HashMap<>();
                params.put(AppVar.KEY_API, args[0]);
                params.put(AppVar.KEY_FUNCTION, args[1]);
                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            if (json != null) {
                try {
                    JSONArray jsonArray = json.getJSONArray("hasil");
                    if (jsonArray.length() == 0) {
                        Toast.makeText(GraphActivity.this,"Hasil tidak ditemukan",Toast.LENGTH_LONG).show();
                    } else {
                        int getData[]=new int[jsonArray.length()];
                        int getTercapai[]=new int[jsonArray.length()];
                        String kegiatan="";
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject isiArray = jsonArray.getJSONObject(i);
                            kegiatan = isiArray.getString("var_tercapai");
                            String dana = isiArray.getString("var_target");
                            getData[i]=Integer.valueOf(dana);
                            getTercapai[i]=Integer.valueOf(kegiatan);
                        }
                        ChartParkir(getData,getTercapai);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(GraphActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void getData() {
        new DataItem().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_ALL_DATA,
                level
        );
    }

    private class DataItem extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        private final String LOGIN_URL = AppVar.URL_SERVER;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            try {

                HashMap<String, String> params = new HashMap<>();
                params.put(AppVar.KEY_API, args[0]);
                params.put(AppVar.KEY_FUNCTION, args[1]);
                params.put(AppVar.FUNC_ID, args[2]);
                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            if (json != null) {
                try {
                    JSONArray jsonArray = json.getJSONArray("hasil");
                    if (jsonArray.length() == 0) {
                        Toast.makeText(GraphActivity.this,"Hasil tidak ditemukan",Toast.LENGTH_LONG).show();
                    } else {
                        done=jsonArray.length();
                        int getData[]=new int[jsonArray.length()];
                        int getTercapai[]=new int[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject isiArray = jsonArray.getJSONObject(i);
                            String id = isiArray.getString("var_id");
                            String kegiatan = isiArray.getString("var_kegiatan");
                            String bulan = isiArray.getString("var_bulan");
                            String dana = isiArray.getString("var_dana");
                            getData[i]=Integer.valueOf(kegiatan);
                            getTercapai[i]=Integer.valueOf(dana);
                            /*
                            XYSeries setSeri = new XYSeries(kegiatan);
                            GraphActivity.this.xySeriesList.add(setSeri);
                            XYSeriesRenderer neRender = new XYSeriesRenderer();
                            GraphActivity.this.rendering.add(neRender);
                            getChart(id);
                            first++;
                            */
                        }
                        ChartData(getData,getTercapai);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(GraphActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
