package com.ceng319.mpandroidchart_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_quit) {
            finishAndRemoveTask();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;
        if (id == R.id.nav_line) {
            intent = new Intent(getApplicationContext(), Line.class);
        } else if (id == R.id.nav_pie) {
            intent = new Intent(getApplicationContext(), Pie.class);
        } else if (id == R.id.nav_bar) {
            intent = new Intent(getApplicationContext(), Bar.class);
        }
        else if (id == R.id.nav_firebasebar) {
            intent = new Intent(getApplicationContext(), Firebase_Bar.class);
        }
        else if (id == R.id.nav_firebaseline)
        {
            intent = new Intent(getApplicationContext(), Firebase_Line.class);
        }
        else if (id == R.id.nav_alert){
            buildAlertDialogBox();
        }

        if (intent != null)
          startActivity(intent);  // start activity only when the intent is not null.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void buildAlertDialogBox() {
        // Build an AlertDialog as example
        // TODO: AlertDialog.Builder is the builder for the AlertDialogBox.
        // TODO: Builder in Java is a design pattern to set up complex objects. One general usage is to build a
        // TODO: Refer to here: https://dzone.com/articles/design-patterns-the-builder-pattern
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                                         .setMessage(getString(R.string.message))
                                        .setCancelable(false)
                                        .setTitle("Reminder");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click positive button
                buildSpinnerDialogBox();
                Toast.makeText(getApplicationContext(),
                        "Yes Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click the negative button
                Toast.makeText(getApplicationContext(),
                        "No Button Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }

    private void buildSpinnerDialogBox() {
        final NumberPicker numberPicker = new NumberPicker(getApplicationContext());
        numberPicker.setMaxValue(30);
        numberPicker.setMinValue(0);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setMessage("How many hours you will register?")
                .setView(numberPicker)
                .setTitle("Please make a choice");
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "You have selected " + numberPicker.getValue()+ " hours", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "You have cancelled your selection", Toast.LENGTH_LONG).show();
            }
        });


        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }
}
