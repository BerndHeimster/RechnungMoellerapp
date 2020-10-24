package de.janoroid.rechnungmoeller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
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

public class CreatePdf extends AppCompatActivity {

    private String Steuernummer, NameUnternehm, AdresseUnternehm, PLZStadtUnternehm, DatumUnternehm;

    private String NamePrivat, AdressePrivat, PLZPrivat, PersonalAusweis, DatumPrivat;

    private Document document = new Document();

    private   Paragraph InfoPrivatPerson,InfoUnternehm;

    private TextView tvFileName;


    private singleTon singleTonClass = singleTon.getInstance();




    private Font SchriftgrößeHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.BOLD);
    private  Font SchriftgrößeTitel = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16,Font.BOLD);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pdf);

        tvFileName = findViewById(R.id.tvFileName);

        try {
            getCustomInformation();

        }
        catch (DocumentException | IOException | InterruptedException e) {
            Toast.makeText(getApplicationContext(),"Beim Übertragen der Daten ist ein Fehler entstanden:  " + e,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void getCustomInformation() throws DocumentException, IOException, InterruptedException {

        SharedPreferences pref = getSharedPreferences("CustomInformation", MODE_PRIVATE);
        NameUnternehm = pref.getString("Name der Firma", "");
        AdresseUnternehm = pref.getString("adresse der Firma", "");
        PLZStadtUnternehm = pref.getString("PLZ und die Stadt", "");
        Steuernummer = pref.getString("Steuernummer", "");
        DatumUnternehm = pref.getString("Datum", "");

        NamePrivat = pref.getString("Name der PrivatPerson", "");
        AdressePrivat = pref.getString("Adresse der PrivatPerson", "");
        PLZPrivat = pref.getString("PLZ und die Stadt", "");
        PersonalAusweis = pref.getString("PersonalAusweis", "");
        DatumPrivat = pref.getString("Datum", "");


        CreateDocument();


    }

    private void CreateDocument() throws DocumentException, IOException, InterruptedException {
        File file = new File(Environment.getExternalStorageDirectory(), "/PDF-Moeller/Abrechnung/" + NameUnternehm + NamePrivat+"-Abrechnung.pdf");
        //Das Dokument soll eine DIN-A4-Format bekommen
        document.setPageSize(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();

        CreateHeader();

    }

    private void CreateHeader() throws DocumentException, IOException, InterruptedException {

        //Das Logo wird hinzugefügt

        Drawable d = getResources().getDrawable(R.drawable.logopapa);
        BitmapDrawable bitDw = ((BitmapDrawable) d);
        Bitmap bmp = bitDw.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image image = Image.getInstance(stream.toByteArray());
        image.scaleToFit(200,78);

        image.setAlignment(Paragraph.ALIGN_RIGHT);

        document.add(image);


        // Die Überschrift
        Chunk underline = new Chunk("Abrechnung",SchriftgrößeTitel);
        underline.setUnderline(0.1f, -2f);
        document.add(underline);



        InfoUnternehm = new Paragraph("Unternehmer  "  ,SchriftgrößeHeader);
        Paragraph Unternehmsdaten = new Paragraph("Name: " + NameUnternehm + "\n" +
                "Anschrift: " + AdresseUnternehm + "\n" +
                "Postleitzahl:" + PLZStadtUnternehm + "\n"+
                "Steuernummer: " + Steuernummer ,SchriftgrößeHeader);


        Paragraph NewlineSteuernummer = new Paragraph(Chunk.NEWLINE);

        InfoUnternehm.setAlignment(Element.ALIGN_TOP);

        Paragraph Newlinetableheader = new Paragraph(Chunk.NEWLINE);

        PdfPTable tableheader = new PdfPTable(2);
        tableheader.setWidthPercentage(100);
        tableheader.addCell(getCell("Der Rechnungsbetrag enthält keine \n Umsatzsteuer! Die Steuer wird \n gemäß § 12b Abs2. Nr7. UStG vom \n Leitungsempfänger geschuldet!", PdfPCell.ALIGN_LEFT));

        tableheader.addCell(getCell("Schaumburger Weg 1b \n" +
                "32423 Minden \n" +
                "Tel:0571/3983607 \n" +
                "Mobil:017678103103\n" +
                "UST-NR.335/5151/1209", PdfPCell.ALIGN_RIGHT));


        InfoPrivatPerson = new Paragraph("Privatperson  " ,SchriftgrößeHeader);
        Paragraph PrivatPersonAdresse = new Paragraph("Name " + NamePrivat +   "\n" +
                "Anschrift: " + AdressePrivat + "\n" +
                 "Postleitzahl:" + PLZPrivat + "\n" +
                "Pers.-Ausweiß Nr: " + PersonalAusweis ,SchriftgrößeHeader);


        PdfPTable tablePrivatperson = new PdfPTable(2);
        tableheader.setWidthPercentage(100);
        tableheader.setSpacingAfter(10f);

        tableheader.addCell(getCell(" ", PdfPCell.ALIGN_LEFT));
        tableheader.addCell(getCell("\n"  + DatumPrivat  , PdfPCell.ALIGN_RIGHT));


        document.add(InfoUnternehm);
        document.add(Unternehmsdaten);
        document.add(NewlineSteuernummer);
        document.add(Newlinetableheader);
        document.add(tableheader);
        document.add(tablePrivatperson);
        document.add(InfoPrivatPerson);
        document.add(PrivatPersonAdresse);



        CreateTable();




    }



    private void CreateTable() throws DocumentException, InterruptedException {


        //Erzeugt eine Trennlinie zwischen der Tabelle
        Paragraph newline = new Paragraph(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4); // 4 columns.
        table.setSpacingBefore(5f);
        table.setSpacingAfter(10f);
        table.setWidthPercentage(100);


        PdfPCell cell1 = new PdfPCell(new Paragraph("Gewicht Kg"));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Bezeichnung"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Preis/kg"));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Betrag €"));



        PdfPCell cell5 = new PdfPCell(new Paragraph(singleTonClass.getSchrottGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell6 = new PdfPCell(new Paragraph("Schrott,Stanzabfälle"));
        PdfPCell cell7 = new PdfPCell(new Paragraph((singleTonClass.getSchrottPreisKg().toString().substring(1).replaceFirst("]", ""))));
        PdfPCell cell8 = new PdfPCell(new Paragraph(singleTonClass.getSchrottBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell9 = new PdfPCell(new Paragraph(singleTonClass.getEMotorGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell10 = new PdfPCell(new Paragraph("E-Motore"));
        PdfPCell cell11 = new PdfPCell(new Paragraph(singleTonClass.getEMotorPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell12 = new PdfPCell(new Paragraph(singleTonClass.getEMotorBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell13 = new PdfPCell(new Paragraph(singleTonClass.getSperrGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell14 = new PdfPCell(new Paragraph("Sperrschrott"));
        PdfPCell cell15 = new PdfPCell(new Paragraph(singleTonClass.getSperrPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell16 = new PdfPCell(new Paragraph(singleTonClass.getSperrBetrag().toString().substring(1).replaceFirst("]", "")));;

        PdfPCell cell17 = new PdfPCell(new Paragraph(singleTonClass.getSchredderGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell18 = new PdfPCell(new Paragraph("Schredder- Vormaterial"));
        PdfPCell cell19 = new PdfPCell(new Paragraph(singleTonClass.getSchredderPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell20 = new PdfPCell(new Paragraph(singleTonClass.getSchredderBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell21 = new PdfPCell(new Paragraph(singleTonClass.getGussGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell22 = new PdfPCell(new Paragraph("Guss"));
        PdfPCell cell23 = new PdfPCell(new Paragraph(singleTonClass.getGussPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell24 = new PdfPCell(new Paragraph(singleTonClass.getGussBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell25 = new PdfPCell(new Paragraph(singleTonClass.getSpaeneGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell26 = new PdfPCell(new Paragraph("Späne Aluminium kehrreste (Verschmutz)"));
        PdfPCell cell27 = new PdfPCell(new Paragraph(singleTonClass.getSpaenePreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell28 = new PdfPCell(new Paragraph(singleTonClass.getSpaeneBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell29 = new PdfPCell(new Paragraph(singleTonClass.getKabelGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell30 = new PdfPCell(new Paragraph("Kabel"));
        PdfPCell cell31 = new PdfPCell(new Paragraph(singleTonClass.getKabelPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell32 = new PdfPCell(new Paragraph(singleTonClass.getKabelBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell33 = new PdfPCell(new Paragraph(singleTonClass.getKupferLeichtGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell34 = new PdfPCell(new Paragraph("Kupfer,leicht,Draht,schwer"));
        PdfPCell cell35 = new PdfPCell(new Paragraph(singleTonClass.getKupferLeichtPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell36 = new PdfPCell(new Paragraph(singleTonClass.getKupferLeichtBetrag().toString().substring(1).replaceFirst("]", "")));


        PdfPCell cell37 = new PdfPCell(new Paragraph(singleTonClass.getMessingGewicht().toString().substring(1).replaceFirst("]", "")));; //Gewicht in Kg
        PdfPCell cell38 = new PdfPCell(new Paragraph("Messing,schwer,leicht,Spähne"));
        PdfPCell cell39 = new PdfPCell(new Paragraph(singleTonClass.getMessingPreisKg().toString().substring(1).replaceFirst("]", "")));// Preis/kg
        PdfPCell cell40 = new PdfPCell(new Paragraph(singleTonClass.getMessingBetrag().toString().substring(1).replaceFirst("]", ""))); // betrag

        PdfPCell cell41 = new PdfPCell(new Paragraph(singleTonClass.getAluGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell42 = new PdfPCell(new Paragraph("Aluminium Guß"));
        PdfPCell cell43 = new PdfPCell(new Paragraph(singleTonClass.getAluPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell44 = new PdfPCell(new Paragraph(singleTonClass.getAluBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell45 = new PdfPCell(new Paragraph(singleTonClass.getAluNeuGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell46 = new PdfPCell(new Paragraph("Aluminium,neu,Profile"));
        PdfPCell cell47 = new PdfPCell(new Paragraph(singleTonClass.getAluNeuPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell48 = new PdfPCell(new Paragraph(singleTonClass.getAluNeuBetrag().toString().substring(1).replaceFirst("]", "")));


        PdfPCell cell49 = new PdfPCell(new Paragraph(singleTonClass.getAluSchredderGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell50 = new PdfPCell(new Paragraph("Aluminium-Schredder"));
        PdfPCell cell51 = new PdfPCell(new Paragraph(singleTonClass.getALuSchrederPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell52 = new PdfPCell(new Paragraph(singleTonClass.getAluSchredderBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell53 = new PdfPCell(new Paragraph(singleTonClass.getZinkGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell54 = new PdfPCell(new Paragraph("Zink"));
        PdfPCell cell55 = new PdfPCell(new Paragraph(singleTonClass.getZinkPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell56 = new PdfPCell(new Paragraph(singleTonClass.getZinkBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell57 = new PdfPCell(new Paragraph(singleTonClass.getBleiGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell58 = new PdfPCell(new Paragraph("Blei"));
        PdfPCell cell59 = new PdfPCell(new Paragraph(singleTonClass.getBleiPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell60 = new PdfPCell(new Paragraph(singleTonClass.getBleiBetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell61 = new PdfPCell(new Paragraph(singleTonClass.getVAGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell62 = new PdfPCell(new Paragraph("VA"));
        PdfPCell cell63 = new PdfPCell(new Paragraph(singleTonClass.getVAPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell64 = new PdfPCell(new Paragraph(singleTonClass.getVABetrag().toString().substring(1).replaceFirst("]", "")));

        PdfPCell cell65 = new PdfPCell(new Paragraph(singleTonClass.getKupferGewicht().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell66 = new PdfPCell(new Paragraph("Kupfer"));
        PdfPCell cell67 = new PdfPCell(new Paragraph(singleTonClass.getKupferPreisKg().toString().substring(1).replaceFirst("]", "")));
        PdfPCell cell68 = new PdfPCell(new Paragraph(singleTonClass.getKupferBetrag().toString().substring(1).replaceFirst("]", "")));





        PdfPCell cell69 = new PdfPCell(new Phrase("Gesamtbetrag:                                                                                                                                " + GesamtSumme()+ "€",SchriftgrößeHeader));
        cell69.setColspan(4);
        // Defiles the relative width of the columns
        float[] columnWidths = new float[]{10f, 30f, 10f, 10f};
        table.setWidths(columnWidths);


        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        table.addCell(cell7);
        table.addCell(cell8);
        table.addCell(cell9);
        table.addCell(cell10);
        table.addCell(cell11);
        table.addCell(cell12);
        table.addCell(cell13);
        table.addCell(cell14);
        table.addCell(cell15);
        table.addCell(cell16);
        table.addCell(cell17);
        table.addCell(cell18);
        table.addCell(cell19);
        table.addCell(cell20);
        table.addCell(cell21);
        table.addCell(cell22);
        table.addCell(cell23);
        table.addCell(cell24);
        table.addCell(cell25);
        table.addCell(cell26);
        table.addCell(cell27);
        table.addCell(cell28);
        table.addCell(cell29);
        table.addCell(cell30);
        table.addCell(cell31);
        table.addCell(cell32);
        table.addCell(cell33);
        table.addCell(cell34);
        table.addCell(cell35);
        table.addCell(cell36);
        table.addCell(cell37);
        table.addCell(cell38);
        table.addCell(cell39);
        table.addCell(cell40);
        table.addCell(cell41);
        table.addCell(cell42);
        table.addCell(cell43);
        table.addCell(cell44);
        table.addCell(cell45);
        table.addCell(cell46);
        table.addCell(cell47);
        table.addCell(cell48);
        table.addCell(cell49);
        table.addCell(cell50);
        table.addCell(cell51);
        table.addCell(cell52);
        table.addCell(cell53);
        table.addCell(cell54);
        table.addCell(cell55);
        table.addCell(cell56);
        table.addCell(cell57);
        table.addCell(cell58);
        table.addCell(cell59);
        table.addCell(cell60);
        table.addCell(cell61);
        table.addCell(cell62);
        table.addCell(cell63);
        table.addCell(cell64);
        table.addCell(cell65);
        table.addCell(cell66);
        table.addCell(cell67);
        table.addCell(cell68);
        table.addCell(cell69);

        document.add(newline);
        document.add(table);

        CreateFooter();


    }




    private void CreateFooter() throws DocumentException, InterruptedException {


        PdfPTable tableUnterschrift = new PdfPTable(1);
        tableUnterschrift.setWidthPercentage(100);
        tableUnterschrift.setSpacingAfter(10f);

        //Die Unterschrift wird hinzugefügt

        String photoPath = Environment.getExternalStorageDirectory() + "/PDF-Moeller/signature.png";
        BitmapFactory.Options options = new BitmapFactory.Options();
        final Bitmap b = BitmapFactory.decodeFile(photoPath, options);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap.createScaledBitmap(b, 10, 10, false);
        b.compress(Bitmap.CompressFormat.PNG, 30, stream);
        Image UnterschriftFoto = null;
        byte[] byteArray = stream.toByteArray();
        try {
            UnterschriftFoto = Image.getInstance(byteArray);
            UnterschriftFoto.setRotationDegrees(-90);
            UnterschriftFoto.scaleToFit(350,78);
            UnterschriftFoto.setAbsolutePosition(420,0);


        } catch (BadElementException | IOException e) {
            e.printStackTrace();
        }

        tableUnterschrift.addCell(getCell("Ich versichere,dass die von  mir abgelieferte Ware mein Eigentum ist.\nWir weisen sie drauf hin,das jede nachaltige  Tätigkeit zur Erzielung von Einnahmen zur Umsatzsteuer- und Einkommenssteuerpflicht führen kann.\nKlären sie  dies mit  ihren Finanzamt oder Steuerberater.", PdfPCell.ALIGN_LEFT));
        document.add(tableUnterschrift);
        document.add(UnterschriftFoto);

        document.close();

        tvFileName.setText("Der Name der Datei: " + "/PDF-Moeller/Abrechnung/" + NameUnternehm + NamePrivat+"-Abrechnung.pdf");
        tvFileName.setTextSize(16);


    }





    private PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }


    private String GesamtSumme(){

        String PreisGesamt = singleTonClass.getAluBetrag().toString() + singleTonClass.getKupferBetrag().toString() + singleTonClass.getVABetrag().toString() + singleTonClass.getBleiBetrag().toString()
                + singleTonClass.getZinkBetrag().toString() + singleTonClass.getAluSchredderBetrag().toString() + singleTonClass.getAluNeuBetrag().toString() + singleTonClass.getAluBetrag().toString()
                + singleTonClass.getAluBetrag().toString() + singleTonClass.getMessingBetrag().toString() + singleTonClass.getKupferLeichtBetrag().toString() + singleTonClass.getKabelBetrag().toString()
                + singleTonClass.getSpaeneBetrag().toString() + singleTonClass.getGussBetrag().toString() + singleTonClass.getSchredderBetrag().toString() + singleTonClass.getSperrBetrag().toString()
                + singleTonClass.getEMotorBetrag().toString() + singleTonClass.getSchrottBetrag().toString();



        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(PreisGesamt);
        int summe = 0;
        while (m.find()) {
            summe += Integer.parseInt(m.group());

        }
        return String.valueOf(summe);
    }


    private void BackToMainPage() {


        // Der User muss drei Sekunden warten,dann wird er zurück in die MainActivity geleitet
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}