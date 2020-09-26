package de.janoroid.rechnungmoeller;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RechnungFragment extends Fragment {

    private EditText etAdresse, etPLZUndStadt, etDatum, etDienstleistung, etMenge, etSumme, etMwSt, etName;
    private Button btSenden, btLöschen;
    private String GesamtSumme;

    private Document document = new Document();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rechnung, container, false);
        getActivity().setTitle("Rechnung schreiben");


        etAdresse = view.findViewById(R.id.etAdresse);
        etDatum = view.findViewById(R.id.etDatum);
        etPLZUndStadt = view.findViewById(R.id.etPLZUndStadt);
        etDienstleistung = view.findViewById(R.id.etDienstleistung);
        etMenge = view.findViewById(R.id.etMenge);
        etSumme = view.findViewById(R.id.etSumme);
        etMwSt = view.findViewById(R.id.etMwSt);
        etName = view.findViewById(R.id.etName);

        btSenden = view.findViewById(R.id.btSenden);
        btLöschen = view.findViewById(R.id.btLöschen);


        btLöschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Alle Textview werden geleert,falls der User es möchte

                etAdresse.setText("");
                etName.setText("");
                etDatum.setText("");
                etPLZUndStadt.setText("");
                etMenge.setText("");
                etSumme.setText("");
                etMwSt.setText("");
                etDienstleistung.setText("");
                Toast.makeText(getActivity(), "Alle Felder gelöscht!", Toast.LENGTH_SHORT).show();


            }
        });


        btSenden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (etName.getText().toString().matches("")) {
                        Toast.makeText(getActivity(),"Bitte Name eingeben!",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    else
                    {
                        CreateHeader();
                    }

                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }

    private void CreateHeader() throws IOException, DocumentException {


        //Das Dokument wird erstellt,geöffnet und benannt mit den Namen der Firma oder Privatperson

        File file = new File(Environment.getExternalStorageDirectory(), "/PDF-Moeller/Rechnung/"  + etName.getText().toString() +"-Rechnung.pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        //Das Dokument soll eine DIN-A4-Format bekommen
        document.setPageSize(PageSize.A4);
        document.open();


        Drawable d = getResources().getDrawable(R.drawable.logo);
        BitmapDrawable bitDw = ((BitmapDrawable) d);
        Bitmap bmp = bitDw.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image image = Image.getInstance(stream.toByteArray());
        image.scaleToFit(200,78);

        image.setAlignment(Paragraph.ALIGN_RIGHT);

        document.add(image);



        //Die Daten von den Kunden werden hier eingetragen

        document.add(new Paragraph("Fa Möller – Schaumburger Weg 1b 32423 Minden"));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph(etName.getText().toString() + " \n" +
                etAdresse.getText().toString() + "\n" +
                etPLZUndStadt.getText().toString() + " \n"));

        GesamtSumme = etSumme.getText().toString();



        Paragraph Newline = new Paragraph(Chunk.NEWLINE);
        document.add(Newline);



        Chunk EntsorgerAnschrift = new Chunk(
                "Fa Möller \n" +
                        "Schaumburger Weg 1b\n" +
                        "32423 Minden \n" +
                        "Tel.: 0571/3983607\n" +
                        "Mobil:0176/78103103\n" +
                        "E-Mail:Moeller-schrott@web.de\n" +
                        etDatum.getText().toString());


        Paragraph adresse = new Paragraph(EntsorgerAnschrift);
        // you changed the alignment AFTER adding p1 to the document
        adresse.setAlignment(Paragraph.ALIGN_TOP | Paragraph.ALIGN_RIGHT);
        document.add(adresse);


        // Die Überschrift wird dick gemacht und bekommt Unterstrich zugewiesen
        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.BLACK);
        // you created a font, but you never used it:
        Chunk c = new Chunk("Rechnung", f);
        Paragraph p1 = new Paragraph(c);
        // you changed the alignment AFTER adding p1 to the document
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p1);


        //Die nächste Methode wird aufgerufen, um den Haupteil der PDF-Datei zu erstellen

        CreateBody();


    }

    private void CreateBody() throws DocumentException {


        Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 11.0f, Font.BOLD, BaseColor.BLACK);

        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Sehr geehrte Damen und Herren,"));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("vielen Dank für Ihren Auftrag und das damit verbundene Vertrauen!"));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("Für meine Leistungen am " + etDatum.getText().toString() + " erlaube ich mir folgenden Leistungen in Rechnung zu stellen:"));


        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(90);
        table.setSpacingBefore(10);

        table.setSpacingAfter(15);
        table.addCell(getCell("Dienstleistungen", PdfPCell.ALIGN_LEFT));
        table.addCell(getCell("Menge", PdfPCell.ALIGN_CENTER));
        table.addCell(getCell("Summe", PdfPCell.ALIGN_RIGHT));
        table.setSpacingAfter(30);

        table.addCell((getCell(etDienstleistung.getText().toString(), PdfPCell.ALIGN_LEFT)));
        table.addCell((getCell(etMenge.getText().toString(), PdfPCell.ALIGN_CENTER)));
        table.addCell((getCell(etSumme.getText().toString(), PdfPCell.ALIGN_RIGHT)));


        document.add(table);



        document.add(new Paragraph(Chunk.NEWLINE));


        PdfPTable tablegesamt = new PdfPTable(2);
        tablegesamt.setWidthPercentage(90);

        tablegesamt.addCell(getCell("Gesamt: " , PdfPCell.ALIGN_LEFT));

        tablegesamt.addCell(getCell(GesamtSumme() + "€", PdfPCell.ALIGN_RIGHT));
        document.add(tablegesamt);

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph("Bitte begleichen Sie den Gesamtbetrag von " + GesamtSumme() + "€ bei Erhalt der Rechnung in bar."));

        document.add(new Paragraph(Chunk.NEWLINE));

        CreateFooter();


    }

    private void CreateFooter() throws DocumentException {
        document.add(new Paragraph("Mit den besten Grüßen"));
        document.add(new Paragraph("Mario Möller"));

        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));

        PdfPTable tablefooter = new PdfPTable(3);
        tablefooter.setWidthPercentage(100);
        tablefooter.addCell(getCell(" Fa Möller \n Schaumburger Weg 1b \n 32423 Minden", PdfPCell.ALIGN_LEFT));

        tablefooter.addCell(getCell("Tel.: 0571/3983607\n" +
                "Moeller-schrott@web.de", PdfPCell.ALIGN_CENTER));
        tablefooter.addCell(getCell("Steuer-Nr.335/5151/1209 \n    Finanzamt  Minden", PdfPCell.ALIGN_RIGHT));
        document.add(tablefooter);


        //Das Dokument wird geschlossen
        document.close();


        Toast.makeText(getActivity(),"Die Rechnung wurde erstellt",Toast.LENGTH_SHORT).show();


    }


    //Design für die Tabelle
    public PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

private String GesamtSumme(){

    Pattern p = Pattern.compile("[0-9]+");
    Matcher m = p.matcher(GesamtSumme);
    int summe = 0;
    while (m.find()) {
        summe += Integer.parseInt(m.group());

}


return String.valueOf(summe);

 }

}