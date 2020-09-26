package de.janoroid.rechnungmoeller;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;


public class DruckenFragment extends Fragment {


    private Intent FileIntent;

    private Button btFileChooser, btSendMail, btPrinter;

    private Uri uri;



    private com.github.barteksc.pdfviewer.PDFView pdfView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_drucken, container, false);
        getActivity().setTitle("Drucken und Mail versenden");


        pdfView = view.findViewById(R.id.pdfView);


        btFileChooser = view.findViewById(R.id.filechooser);
        btSendMail = view.findViewById(R.id.btsendMail);
        btPrinter = view.findViewById(R.id.btDrucken);



        btFileChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                FileIntent.setType("application/pdf");
                startActivityForResult(FileIntent, 10);
            }
        });

        btPrinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PDFPrint();

            }
        });

        btSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMail();
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {

            case 10:

                if (resultCode == RESULT_OK) {


                    uri = data.getData();

                    try {
                        InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);

                        System.out.println("Uri"+ uri);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }



                    pdfView.fromUri(uri)
                            .load();


                }

                break;

        }



    }


    private void PDFPrint() {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //Uri uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", sharedFile);

        intent.setDataAndType(uri, "application/pdf");

        PackageManager pm = getActivity().getPackageManager();
        if (intent.resolveActivity(pm) != null) {
            getActivity().startActivity(intent);

        }
    }

    private void SendMail(){


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("E-Mail-Adresse eingeben:");

        final EditText AnswerEditext = new EditText(getActivity());
        AnswerEditext.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(AnswerEditext);

        builder.setPositiveButton("O.k", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Die E-Mail-Adresse wird in den String gespeichert
                String eMailAdresse = AnswerEditext.getText().toString();

                if (AnswerEditext.getText().toString().matches("")) {
                    Toast.makeText(getActivity(), "Bitte eine E-Mail-Adresse eingeben!", Toast.LENGTH_LONG).show();
                }

                else

                {



                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);


                    /* Fill it with Data */
                    emailIntent.setType("application/pdf");

                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{eMailAdresse});
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Schrotthandel Möller");
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Sehr geehrte Damen und Herren," + "\n" + "\n" + "hiermit schicke ich ihnen die Rechung/Abrechnung." + "\n" + "\n" + "Mit freundlichen Grüßen," + "\n" + "Mario Möller");
                    emailIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

                    startActivity(emailIntent);

                    /* Send it off to the Activity-Chooser */
                    getActivity().startActivity(Intent.createChooser(emailIntent, "gmail:"));


                }
            }

        });



        builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getActivity(),"Die E-Mail wurde abgebrochen!",Toast.LENGTH_LONG).show();


            }
        });
        builder.show();

    }

}