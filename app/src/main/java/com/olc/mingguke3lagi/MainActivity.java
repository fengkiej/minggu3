package com.olc.mingguke3lagi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.olc.mingguke3lagi.R;

public class MainActivity extends AppCompatActivity {



    TextView hargaTotalTV, diskonTV ;
    Spinner jenisLaundrySpinner;
    RadioButton dinginRB, panasRB;
    EditText beratET;
    CheckBox kilatCBox, anjemCBox;
    SeekBar diskonSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hargaTotalTV = (TextView)findViewById(R.id.harga_total_textview);
        diskonTV = (TextView)findViewById(R.id.diskon_textview);

        jenisLaundrySpinner = (Spinner)findViewById(R.id.jenis_laundry_spinner);

        dinginRB = (RadioButton)findViewById(R.id.dingin_radiobutton);
        panasRB = (RadioButton)findViewById(R.id.panas_radiobutton);

        beratET = (EditText)findViewById(R.id.berat_edittext);

        kilatCBox = (CheckBox)findViewById(R.id.kilat_checkbox);
        anjemCBox = (CheckBox)findViewById(R.id.anjem_checkbox);

        diskonSeekbar = (SeekBar)findViewById(R.id.diskon_seekbar);

        jenisLaundrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hitungDanTampilkan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        beratET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hitungDanTampilkan();
            }
        });

        kilatCBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hitungDanTampilkan();
            }
        });

        anjemCBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hitungDanTampilkan();
            }
        });

        diskonSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hitungDanTampilkan();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void hitungDanTampilkan(){

        int nomerTerpilih = jenisLaundrySpinner.getSelectedItemPosition();

        double hargaPerKilo = 0;

        if(nomerTerpilih==0){
            hargaPerKilo = 3000;
        }
        else if(nomerTerpilih==1){
            hargaPerKilo = 4000;
        }
        else if(nomerTerpilih==2){
            hargaPerKilo = 5000;
        }

        double berat=0;

        String beratString = beratET.getText().toString();

        if(beratString.equals(""))
            berat = 0;
        else
            berat = Double.parseDouble(beratString);

        if(kilatCBox.isChecked()){
            hargaPerKilo = hargaPerKilo*2;
        }

        if(anjemCBox.isChecked()){
            hargaPerKilo = hargaPerKilo+500;
        }

        int diskon = diskonSeekbar.getProgress();

        double totalHarga = (hargaPerKilo*berat) - (hargaPerKilo * berat) * diskon/100;

        hargaTotalTV.setText( "Rp" + String.valueOf(totalHarga) );
    }

}
