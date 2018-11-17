package com.example.nitza.dialogwlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    Intent r;
    TextView t1, t2, t3, t4;
    ListView lv;
    int n;
    double x1, d1,sum;
    String[] list = new String[20];
    AlertDialog.Builder ad;
    EditText ed1, ed2;
    LinearLayout dialog;
    Switch sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv2);
        t1 = (TextView) findViewById(R.id.tv1);
        t2 = (TextView) findViewById(R.id.tv2);
        t3 = (TextView) findViewById(R.id.tv3);
        t4 = (TextView) findViewById(R.id.tv4);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuc) {
            Intent t = new Intent(this, Main2Activity.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }


    public void go2 (View view) {
        dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialogx, null);
        ed1 = (EditText) dialog.findViewById(R.id.ed1);
        ed2 = (EditText) dialog.findViewById(R.id.ed2);
        sw = (Switch) dialog.findViewById(R.id.sw);
        ad = new AlertDialog.Builder(this);
        ad.setCancelable(false);
        ad.setTitle("enter data");
        ad.setView(dialog);
        ad.setNegativeButton("reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                x1 = 0;
                d1 = 0;
                t1.setText(" ");
                t2.setText(" ");
                t3.setText(" ");
                t4.setText(" ");
                lv.setAdapter(null);
                dialogInterface.dismiss();
            }
        });
        ad.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        ad.setPositiveButton("calculate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if ((ed1.getText().toString().equals("")) || (ed1.getText().toString().equals(".-")) || ((ed1.getText().toString().equals(".")) || (ed1.getText().toString().equals("-")) || (ed1.getText().toString().equals("-."))) ||
                        ((ed2.getText().toString().equals("")) || (ed2.getText().toString().equals(".-")) || ((ed2.getText().toString().equals(".")) || (ed2.getText().toString().equals("-")) || (ed2.getText().toString().equals("-."))))) {
                    dialogInterface.cancel();
                    Toast.makeText(MainActivity.this, "Input is unavailable", Toast.LENGTH_SHORT).show();
                } else {
                    x1 = Double.parseDouble(ed1.getText().toString());
                    d1 = Double.parseDouble(ed2.getText().toString());
                    t1.setText(Double.toString(x1));
                    t2.setText(Double.toString(d1));
                    list[0] = Double.toString(x1);
                    if (sw.isChecked())

                        for (int j = 1; j < 20; j++) {
                            list[j] = Double.toString(Double.parseDouble(list[j - 1]) * d1);
                        }
                    else {

                        for (int j = 1; j < 20; j++) {
                            list[j] = Double.toString(Double.parseDouble(list[j- 1]) + d1);
                        }
                    }



                    lv.setOnItemClickListener(MainActivity.this);
                    lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, list);
                    lv.setAdapter(adp);
                }

            }
        });

        AlertDialog adb = ad.create();
        adb.show();}




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                n = i + 1;
                t3.setText(Integer.toString(n));

                if (!sw.isChecked())
                    sum = ((n * ((2 * x1) + d1 * (n - 1))) / 2);

                else{
                    if ((x1 != 0) || (d1 != 0) || (d1 != 1))
                    sum = (x1 * (Math.pow(d1, n) - 1)) / (d1 - 1);
                else{
                    if ((x1 == 0) || (d1 == 0))
                    sum = 0;
                else
                    sum = Double.parseDouble(list[n]);
                     }
                     }
                    t4.setText(Double.toString(sum));
           }

}









