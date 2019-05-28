package com.example.proyectocine.Activities;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectocine.Fragments.Fragment_horario;
import com.example.proyectocine.Helpers.ObjetoFuncion;
import com.example.proyectocine.R;
import com.example.proyectocine.Fragments.Fragment_sinopsis;
import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.Controllers.claseBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;

public class ActivityInfoPelicula extends claseBase implements Response.Listener<JSONArray>, Response.ErrorListener {

    Fragment_sinopsis tab2 = null;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pelicula);


        VariablesGlobales vg = VariablesGlobales.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        ImageView midib = (ImageView) findViewById(R.id.imageViewPelicula);
        midib.setImageResource(DeterminarImagen(vg.getIdPelicula()));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_info_pelicula, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            VariablesGlobales vg = VariablesGlobales.getInstance();
            switch (position) {
                case 0:
                    Fragment_horario tab1 = new Fragment_horario();
                    //tab1.setIdPelicula(vg.getIdPelicula());

                    return tab1;
                case 1:

                    tab2 = new Fragment_sinopsis();

                    //DesplegarInfoPelicula(vg.getIdPelicula());

                    return tab2;

                default:
                    return null;
            }
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {

        try {
            for (int i = 0; i < response.length(); i++) {

                String temp = response.get(i).toString();
                JSONObject jsonObject = new JSONObject(temp);
                String sinopsis = jsonObject.getString("Sinopsis");
                String publico  = jsonObject.getString("Publico");
                String tipo = jsonObject.getString("Tipo");
                String msg= "Publico: "+publico+"      Tipo: "+tipo+" \n\n\n\n Sinopsis: "+sinopsis+ "\n";
                tab2.setSinopsis(msg);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void DesplegarInfoPelicula(String idPelicula) {
        String url = "http://192.168.150.1/Android/v1/consultarSinopsis.php?id_pelicula="+idPelicula;

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

    }


}
