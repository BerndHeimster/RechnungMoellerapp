package de.janoroid.rechnungmoeller;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import java.util.ArrayList;


public class AbrechnungFragment extends Fragment {

    private Font SchriftgrößeHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.BOLD);
    private  Font SchriftgrößeTitel = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16,Font.BOLD);
    private View view;
    private LinearLayout linearLayout;

    private Spinner spinner;
    private EditText etBetrag,etPreisKg,etGewicht;


    private singleTon singleTonClass = singleTon.getInstance();


    private ArrayList<String> Metalle= new ArrayList<>();

    private int Selecteditem;

    private Button KundenInfobt;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_abrechnung, container, false);

        // Der Name von der Activity
        getActivity().setTitle("Abrechnung schreiben");


        Metalle.add("Wähle eine Sorte aus!");
        Metalle.add("Schrott,Stanzabfälle");
        Metalle.add("E-Motore");
        Metalle.add("Sperrschrott");
        Metalle.add("Schredder-Vormaterial");
        Metalle.add("Guß");
        Metalle.add("Späne Aluminium kehrreste (Verschmutzt");
        Metalle.add("Kabel");
        Metalle.add("Kupfer,leicht,Drahr,schwer");
        Metalle.add("Messing,schwer,leicht,Spähne");
        Metalle.add("Aluminium Guß");
        Metalle.add("Aluminium,neu,Profile");
        Metalle.add("Aluminium-Schredder");
        Metalle.add("Zink");
        Metalle.add("Blei");
        Metalle.add("VA");
        Metalle.add("Kupfer");


        //Es wird ein AlertDialog erstellt, wer dann den User fragt wie viele Spalten er bearbeiten möchte

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Wie viele Spalten?");


        final EditText AnswerEditext = new EditText(getActivity());
        AnswerEditext.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(AnswerEditext);

        builder.setPositiveButton("Erstellen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Die Zahl wird in den String gespeichert
                String HowManyColumns = AnswerEditext.getText().toString();


                    //Der String wird in ein Integer umgewandelt
                    int NumberOfColumns = Integer.parseInt(HowManyColumns);

                    //Hier werden die EditText-Felder und Spinners generiert

                        for (int i = 1; i <= NumberOfColumns; i++) {

                            linearLayout = view.findViewById(R.id.linear);

                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                            layoutParams.setMargins(0, 70, 0, 0);


                            etPreisKg = new EditText(getActivity());
                            etGewicht = new EditText(getActivity());
                            etBetrag = new EditText(getActivity());
                            spinner = new Spinner(getActivity());
                            KundenInfobt = new Button(getActivity());


                            spinner.setId(i);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Metalle);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);


                            linearLayout.addView(spinner, layoutParams);

                            etPreisKg.setId(i);
                            etPreisKg.setHint(R.string.Preiskg);
                            etPreisKg.setBackgroundResource(R.drawable.textviewborder);
                            linearLayout.addView(etPreisKg, layoutParams);

                            etGewicht.setId(i);
                            etGewicht.setBackgroundResource(R.drawable.textviewborder);
                            etGewicht.setHint(R.string.Gewicht);
                            linearLayout.addView(etGewicht, layoutParams);

                            etBetrag.setId(i);
                            etBetrag.setBackgroundResource(R.drawable.textviewborder);
                            etBetrag.setHint(R.string.Betrag);
                            linearLayout.addView(etBetrag, layoutParams);
                        }



                KundenInfobt.setText("Kundeninformationen");
                linearLayout.addView(KundenInfobt);

                  // Das ausgewählte Item wird in die Variable Selecteditem gespeichert

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                        Selecteditem = position;




                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });


                // Beim drücken des Buttons werden die Werte in die Klasse SingleTon weitergeleitet,damit die Daten weiter verarbeitet werden können.

                KundenInfobt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (spinner.getItemAtPosition(Selecteditem).toString()) {

                            case "Schrott,Stanzabfälle":


                                singleTonClass.setSchrottPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setSchrottGewicht(etGewicht.getText().toString());
                                singleTonClass.setSchrottBetrag(etBetrag.getText().toString());

                                break;

                            case "E-Motore":

                                singleTonClass.setEMotorPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setEMotorGewicht(etGewicht.getText().toString());
                                singleTonClass.setEMotorBetrag(etBetrag.getText().toString());

                                break;

                            case "Sperrschrott":

                                singleTonClass.setSperrPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setSperrGewicht(etGewicht.getText().toString());
                                singleTonClass.setSperrBetrag(etBetrag.getText().toString());


                                break;

                            case "Schredder-Vormaterial":

                                singleTonClass.setSchredderPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setSchredderGewicht(etGewicht.getText().toString());
                                singleTonClass.setSchredderBetrag(etBetrag.getText().toString());


                                break;

                            case "Guß":

                                singleTonClass.setGussPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setGussGewicht(etGewicht.getText().toString());
                                singleTonClass.setGussBetrag(etBetrag.getText().toString());

                                break;


                            case "Späne Aluminium kehrreste (Verschmutzt":

                                singleTonClass.setSpaenePreisKg(etPreisKg.getText().toString());
                                singleTonClass.setSpaeneGewicht(etGewicht.getText().toString());
                                singleTonClass.setSpaeneBetrag(etBetrag.getText().toString());


                                break;

                            case "Kabel":

                                singleTonClass.setKabelPreisKg(etPreisKg.getText().toString());
                                singleTonClass.getKabelGewicht(etGewicht.getText().toString());
                                singleTonClass.setKabelBetrag(etBetrag.getText().toString());

                                break;

                            case "Kupfer,leicht,Drahr,schwer":

                                singleTonClass.setKupferLeichtPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setKupferLeichtGewicht(etGewicht.getText().toString());
                                singleTonClass.setKupferLeichtBetrag(etBetrag.getText().toString());

                                break;

                            case "Messing,schwer,leicht,Spähne":


                                singleTonClass.setMessingPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setMessingGewicht(etGewicht.getText().toString());
                                singleTonClass.setMessingBetrag(etBetrag.getText().toString());

                                break;

                            case "Aluminium Guß":

                                singleTonClass.setAluPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setAluGewicht(etGewicht.getText().toString());
                                singleTonClass.setAluBetrag(etBetrag.getText().toString());

                                break;

                            case "Aluminium,neu,Profile":

                                singleTonClass.setAluNeuPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setAluNeuGewicht(etGewicht.getText().toString());
                                singleTonClass.setAluNeuBetrag(etBetrag.getText().toString());


                                break;

                            case "Aluminium-Schredder":

                                singleTonClass.setALuSchrederPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setAluSchredderGewicht(etGewicht.getText().toString());
                                singleTonClass.setAluSchredderBetrag(etBetrag.getText().toString());


                                break;

                            case "Zink":

                                singleTonClass.setZinkPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setZinkGewicht(etGewicht.getText().toString());
                                singleTonClass.setZinkBetrag(etBetrag.getText().toString());


                                break;

                            case "Blei":

                                singleTonClass.setBleiPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setBleiGewicht(etGewicht.getText().toString());
                                singleTonClass.setBleiBetrag(etBetrag.getText().toString());


                                break;

                            case "VA":

                                singleTonClass.setVAPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setVAGewicht(etGewicht.getText().toString());
                                singleTonClass.setVAGewicht(etBetrag.getText().toString());


                                break;

                            case "Kupfer":

                                singleTonClass.setKupferPreisKg(etPreisKg.getText().toString());
                                singleTonClass.setKupferGewicht(etGewicht.getText().toString());
                                singleTonClass.setKupferBetrag(etBetrag.getText().toString());


                                break;


                            default:


                        }

                        // Der User wird weitergeleitet zur der nächste Activity

                        Intent intent = new Intent(getActivity(), CustomActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                    }
                });

            }

        });


        // Beim Abbrechen von den AlertDialog, wird ein Toast-Message ausgegeben

        builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getContext(), "Abgebrochen", Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();


        return view;


    }



}





