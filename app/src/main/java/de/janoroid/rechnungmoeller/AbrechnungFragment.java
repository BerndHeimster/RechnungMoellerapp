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
import java.util.ArrayList;
import java.util.List;


public class AbrechnungFragment extends Fragment {

    private View view;
    private LinearLayout linearLayout;

    private singleTon singleTonClass = singleTon.getInstance();

    private ArrayList<String> Metalle = new ArrayList<>();


    private Button KundenInfobt;


    //Die Arrays für die EditText-Felder
    final EditText[] etBetrag = new EditText[16];
    final EditText[] etPreisKg = new EditText[16];
    final EditText[] etGewicht = new EditText[16];
    final Spinner[] spinner = new Spinner[16];

    //Die Listen

   private List<Integer> lastValue = new ArrayList<Integer>();

    private List<Integer> lastValueFromI = new ArrayList<Integer>();


    private int i;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_abrechnung, container, false);

        // Der Name von der Activity
        getActivity().setTitle("Abrechnung schreiben");


        Metalle.add("Bitte Auswählen");
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

                for (i = 1; i <= NumberOfColumns; i++) {

                    linearLayout = view.findViewById(R.id.linear);

                    final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    layoutParams.setMargins(0, 70, 0, 0);

                    etBetrag[i] = new EditText(getActivity());
                    etGewicht[i] = new EditText(getActivity());
                    etPreisKg[i] = new EditText(getActivity());
                    spinner[i] = new Spinner(getActivity());

                    KundenInfobt = new Button(getActivity());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Metalle);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner[i].setId((getId()+ i));
                    spinner[i].setAdapter(adapter);
                    linearLayout.addView(spinner[i], layoutParams);

                    etGewicht[i].setHint(R.string.Preiskg);
                    etGewicht[i].setId((getId()+ i));
                    etGewicht[i].setSaveEnabled(false);
                    etGewicht[i].setBackgroundResource(R.drawable.textviewborder);
                    linearLayout.addView(etGewicht[i], layoutParams);

                    etPreisKg[i].setBackgroundResource(R.drawable.textviewborder);
                    etPreisKg[i].setHint(R.string.Gewicht);
                    etPreisKg[i].setId((getId()+ i));
                    etPreisKg[i].setSaveEnabled(false);
                    linearLayout.addView(etPreisKg[i], layoutParams);

                    etBetrag[i].setBackgroundResource(R.drawable.textviewborder);
                    etBetrag[i].setHint(R.string.Betrag);
                    etBetrag[i].setId((getId()+ i));
                    etBetrag[i].setSaveEnabled(false);
                    linearLayout.addView(etBetrag[i], layoutParams);


                        spinner[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                                //Hier werden die ausgewählten Item gepeichert
                                lastValue.add(position);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }

                        });


                        //Hier wird die Zählervariable gespeichert

                    lastValueFromI.add(i);




                    KundenInfobt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                         //Hier wird die Zählervariable ausgegeben,damit mehrere Listener erstellt werden können

                            for (int h = 1; h < lastValueFromI.size(); h++) {

                                System.out.println("H-for" + h);



                                //Hier werden die ausgewähltem Item ausgegeben

                                for (int k = 0; k < lastValue.size(); k++) {
                                    System.out.println("for-K " + k);



                                    switch (spinner[lastValueFromI.get(h)].getItemAtPosition(lastValue.get(k)).toString()) {


                                        case "Schrott,Stanzabfälle":


                                            singleTonClass.setSchrottPreisKg(etPreisKg[h].getText().toString());
                                            singleTonClass.setSchrottGewicht(etGewicht[h].getText().toString());
                                            singleTonClass.setSchrottBetrag(etBetrag[h].getText().toString());


                                            break;


                                        case "E-Motore":


                                            singleTonClass.setEMotorPreisKg(etPreisKg[h].getText().toString());
                                            singleTonClass.setEMotorGewicht(etGewicht[h].getText().toString());
                                            singleTonClass.setEMotorBetrag(etBetrag[h].getText().toString());


                                            break;

                                        case "Sperrschrott":

                                            singleTonClass.setSperrPreisKg(etPreisKg[h].getText().toString());
                                            singleTonClass.setSperrGewicht(etGewicht[h].getText().toString());
                                            singleTonClass.setSperrBetrag(etBetrag[h].getText().toString());


                                            break;

                                        case "Schredder-Vormaterial":

                                            singleTonClass.setSchredderPreisKg(etPreisKg[h].getText().toString());
                                            singleTonClass.setSchredderGewicht(etGewicht[h].getText().toString());
                                            singleTonClass.setSchredderBetrag(etBetrag[h].getText().toString());


                                            break;

                                        case "Guß":

                                            singleTonClass.setGussPreisKg(etPreisKg[h].getText().toString());
                                            singleTonClass.setGussGewicht(etGewicht[h].getText().toString());
                                            singleTonClass.setGussBetrag(etBetrag[h].getText().toString());

                                            break;


                            /*            case "Späne Aluminium kehrreste (Verschmutzt":

                                            singleTonClass.setSpaenePreisKg(etPreisKg.getText().toString());
                                            singleTonClass.setSpaeneGewicht(etGewicht.getText().toString());
                                            singleTonClass.setSpaeneBetrag(etBetrag.getText().toString());


                                            break;

                                        case "Kabel":

                                            singleTonClass.setKabelPreisKg(etPreisKg.getText().toString());
                                            singleTonClass.setKabelGewicht(etGewicht.getText().toString());
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
*/


                                        default:

                                    }

                                    }

                                }
                                // Der User wird weitergeleitet zur der nächste Activity

                                Intent intent = new Intent(getActivity(), CustomActivity.class);
                                startActivity(intent);
                                getActivity().finish();

                            }
                    });


                }


                KundenInfobt.setText("Zu den Kundeninformationen");
                linearLayout.addView(KundenInfobt);


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

