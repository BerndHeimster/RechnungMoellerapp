package de.janoroid.rechnungmoeller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CustomActivity extends AppCompatActivity {

    private EditText etNameUnternehm,etStadtUnternehm,etAdresseUnternehm,etSteuernummer,etDatumUnternehm;

    private EditText etNamenPrivat,etStadtPrivat,etAdressePrivat,etPersonalausweis,etDatumPrivat;

    private Button WeiterBtn;

    private RadioButton rbPrivat,rbUnternehm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);


        // Die Edittext-Felder für den Unternehmer
        etNameUnternehm = findViewById(R.id.etName);
        etStadtUnternehm = findViewById(R.id.etPLZUndStadt);
        etAdresseUnternehm = findViewById(R.id.etAdresseUnternehm);
        etSteuernummer = findViewById(R.id.etSteuernummer);
        etDatumUnternehm = findViewById(R.id.etDatum);
        rbUnternehm = findViewById(R.id.radioButtonUnternehmer);





        // Die Edittext-Felder für den Unternehmer

        etNamenPrivat = findViewById(R.id.etNamePrivat);
        etAdressePrivat = findViewById(R.id.etAdressePrivat);
        etStadtPrivat = findViewById(R.id.etPLZUndStadtPrivat);
        etPersonalausweis = findViewById(R.id.etPersonalAusweis);
        etDatumPrivat = findViewById(R.id.etDatumPrivat);
        rbPrivat = findViewById(R.id.radioButtonPrivatPerson);


        WeiterBtn = findViewById(R.id.button);


        //Falls der Radiobutton Unternehm angeklickt wurde,dann werden die Textfelder von der PrivatPerson ausgeblendet

        rbUnternehm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rbPrivat.setVisibility(View.INVISIBLE);
                    etNamenPrivat.setVisibility(View.INVISIBLE);
                    etAdressePrivat.setVisibility(View.INVISIBLE);
                    etStadtPrivat.setVisibility(View.INVISIBLE);
                    etPersonalausweis.setVisibility(View.INVISIBLE);
                    etDatumPrivat.setVisibility(View.INVISIBLE);
                    rbPrivat.setChecked(false);
                }
            }
        });


        rbPrivat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rbUnternehm.setVisibility(View.VISIBLE);
                    etNameUnternehm.setVisibility(View.INVISIBLE);
                    etAdresseUnternehm.setVisibility(View.INVISIBLE);
                    etStadtUnternehm.setVisibility(View.INVISIBLE);
                    etSteuernummer.setVisibility(View.INVISIBLE);
                    etDatumUnternehm.setVisibility(View.INVISIBLE);
                    rbUnternehm.setChecked(false);
                }
            }
        });


                    WeiterBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                    SharedPreferences pref = getSharedPreferences("CustomInformation", 0);
                                    SharedPreferences.Editor ed = pref.edit();

                                    //Die Preferences für das Unternehmen
                                    ed.putString("Name der Firma", etNameUnternehm.getText().toString());
                                    ed.putString("adresse der Firma", etAdresseUnternehm.getText().toString());
                                    ed.putString("PLZ und die Stadt", etStadtUnternehm.getText().toString());
                                    ed.putString("Steuernummer", etSteuernummer.getText().toString());
                                    ed.putString("Datum", etDatumUnternehm.getText().toString());

                                    //Die Preferences für die PrivatPerson

                                    ed.putString("Name der PrivatPerson", etNamenPrivat.getText().toString());
                                    ed.putString("Adresse der PrivatPerson", etAdressePrivat.getText().toString());
                                    ed.putString("PLZ und die Stadt", etStadtPrivat.getText().toString());
                                    ed.putString("PersonalAusweis", etPersonalausweis.getText().toString());
                                    ed.putString("Datum", etDatumPrivat.getText().toString());

                                    ed.commit();
                                    Intent intent = new Intent(CustomActivity.this, SignatureActivity.class);
                                    startActivity(intent);
                                    finish();

                        }
                    });






    }

}
