package de.janoroid.rechnungmoeller;

import java.util.ArrayList;

class singleTon {


    private static final singleTon ourInstance = new singleTon();

    ArrayList<String> schrottPreisKg;
    ArrayList<String> SchrottGewicht;
    ArrayList<String> SchrottBetrag;

    ArrayList<String> SperrPreisKg;
    ArrayList<String> SperrGewicht;
    ArrayList<String> SperrBetrag;

    ArrayList<String> EMotorPreisKg;
    ArrayList<String> EMotorGewicht;
    ArrayList<String> EMotorBetrag;

    ArrayList<String> SchredderPreisKg;
    ArrayList<String> SchredderGewicht;
    ArrayList<String> SchredderBetrag;

    ArrayList<String> SpaenePreisKg;
    ArrayList<String> SpaeneGewicht;
    ArrayList<String> SpaeneBetrag;

    ArrayList<String> KabelPreisKg;
    ArrayList<String> KabelGewicht;
    ArrayList<String> KabelBetrag;

    ArrayList<String> KupferLeichtPreisKg;
    ArrayList<String> KupferLeichtGewicht;
    ArrayList<String> KupferLeichtBetrag;

    ArrayList<String> MessingPreisKg;
    ArrayList<String> MessingGewicht;
    ArrayList<String> MessingBetrag;

    ArrayList<String> AluPreisKg;
    ArrayList<String> AluGewicht;
    ArrayList<String> AluBetrag;

    ArrayList<String> AluNeuPreisKg;
    ArrayList<String> AluNeuGewicht;
    ArrayList<String> AluNeuBetrag;

    ArrayList<String> ALuSchrederPreisKg;
    ArrayList<String> AluSchredderGewicht;
    ArrayList<String> AluSchredderBetrag;

    ArrayList<String> ZinkPreisKg;
    ArrayList<String> ZinkGewicht;
    ArrayList<String> ZinkBetrag;

    ArrayList<String> BleiPreisKg;
    ArrayList<String> BleiGewicht;
    ArrayList<String> BleiBetrag;

    ArrayList<String> VAPreisKg;
    ArrayList<String> VAGewicht;
    ArrayList<String> VABetrag;

    ArrayList<String> KupferPreisKg;
    ArrayList<String> KupferGewicht;
    ArrayList<String> KupferBetrag;

    ArrayList<String> GussPreisKg;
    ArrayList<String> GussGewicht;
    ArrayList<String> GussBetrag;


    static singleTon getInstance() {
        return ourInstance;
    }


    private singleTon() {

        schrottPreisKg = new ArrayList<String>();
        SchrottGewicht = new ArrayList<String>();
        SchrottBetrag = new ArrayList<String>();

        SperrPreisKg = new ArrayList<String>();
        SperrGewicht = new ArrayList<String>();
        SperrBetrag = new ArrayList<String>();

        EMotorPreisKg = new ArrayList<String>();
        EMotorGewicht = new ArrayList<String>();
        EMotorBetrag = new ArrayList<String>();

        SchredderPreisKg = new ArrayList<String>();
        SchredderGewicht = new ArrayList<String>();
        SchredderBetrag = new ArrayList<String>();

        SpaenePreisKg = new ArrayList<String>();
        SpaeneBetrag = new ArrayList<String>();
        SpaeneGewicht = new ArrayList<String>();

        KabelPreisKg = new ArrayList<String>();
        KabelGewicht = new ArrayList<String>();
        KabelBetrag = new ArrayList<String>();

        KupferLeichtPreisKg = new ArrayList<String>();
        KupferLeichtGewicht = new ArrayList<String>();
        KupferLeichtBetrag = new ArrayList<String>();

        MessingPreisKg = new ArrayList<String>();
        MessingGewicht = new ArrayList<String>();
        MessingBetrag = new ArrayList<String>();

        AluPreisKg = new ArrayList<String>();
        AluGewicht = new ArrayList<String>();
        AluBetrag = new ArrayList<String>();

        AluNeuPreisKg = new ArrayList<String>();
        AluNeuGewicht = new ArrayList<String>();
        AluNeuBetrag = new ArrayList<String>();

        ALuSchrederPreisKg = new ArrayList<String>();
        AluSchredderGewicht = new ArrayList<String>();
        AluSchredderBetrag = new ArrayList<String>();

        ZinkPreisKg = new ArrayList<String>();
        ZinkGewicht = new ArrayList<String>();
        ZinkBetrag = new ArrayList<String>();

        BleiPreisKg = new ArrayList<String>();
        BleiGewicht = new ArrayList<String>();
        BleiBetrag = new ArrayList<String>();

        VAPreisKg = new ArrayList<String>();
        VAGewicht = new ArrayList<String>();
        VABetrag = new ArrayList<String>();

        KupferPreisKg = new ArrayList<String>();
        KupferGewicht = new ArrayList<String>();
        KupferBetrag = new ArrayList<String>();

        GussPreisKg = new ArrayList<String>();
        GussGewicht = new ArrayList<String>();
        GussBetrag = new ArrayList<String>();

    }

    public ArrayList<String> getSchrottPreisKg() {
        return schrottPreisKg;
    }

    public String getSchrottPreisKg(int pos) {
        return schrottPreisKg.get(pos);
    }

    public void setSchrottPreisKg(String st) {
        schrottPreisKg.add(st);

    }

    public void setSchrottPreisKgDell(){
        schrottPreisKg.clear();

    }


    public ArrayList<String> getSchrottGewicht(){
        return SchrottGewicht;
    }
    public String getSchrottGewicht(int pos) {
        return SchrottGewicht.get(pos);
    }
    public void setSchrottGewicht(String st) {
        SchrottGewicht.add(st);
    }

    public void setSchrottGewichtKgDell(){
        SchrottGewicht.clear();

    }

    public ArrayList<String> getSchrottBetrag(){
        return SchrottBetrag;
    }
    public String getSchrottBetrag(int pos) {
        return SchrottBetrag.get(pos);
    }
    public void setSchrottBetrag(String st) {
        SchrottBetrag.add(st);
    }

    public void setSchrottBetragKgDell(){
        SchrottBetrag.clear();

    }

    public ArrayList<String> getSperrGewicht(){
        return SperrGewicht;
    }

    public void setSperrGewicht(String st) {
        SperrGewicht.add(st);
    }



    public ArrayList<String> getSperrPreisKg(){
        return SperrPreisKg;
    }
    public String getSperrPreisKg(int pos) {
        return SperrPreisKg.get(pos);
    }
    public void setSperrPreisKg(String st) {
        SperrPreisKg.add(st);
    }

    public void setSperrPreisKgDell(){
        SperrPreisKg.clear();

    }

    public ArrayList<String> getSperrBetrag(){
        return SperrBetrag;
    }
    public String getSperrBetrag(int pos) {
        return SperrBetrag.get(pos);
    }
    public void setSperrBetrag(String st) {
        SperrBetrag.add(st);
    }

    public void setSperrBetragDell(){
        SperrBetrag.clear();

    }

    public ArrayList<String> getEMotorPreisKg(){
        return EMotorPreisKg;
    }
    public String getEMotorPreisKg(int pos) {
        return EMotorPreisKg.get(pos);
    }
    public void setEMotorPreisKg(String st) {
        EMotorPreisKg.add(st);
    }
    public void setEMotorPreisKgDell(){
        EMotorPreisKg.clear();

    }

    public ArrayList<String> getEMotorGewicht(){
        return EMotorGewicht;
    }
    public String getEMotorGewicht(int pos) {
        return EMotorGewicht.get(pos);
    }
    public void setEMotorGewicht(String st) {
        EMotorGewicht.add(st);
    }
    public void  setEMotorGewichtDell(){
        EMotorGewicht.clear();

    }

    public ArrayList<String> getEMotorBetrag(){
        return EMotorBetrag;
    }
    public String getEMotorBetrag(int pos) {
        return EMotorBetrag.get(pos);
    }
    public void setEMotorBetrag(String st) {
        EMotorBetrag.add(st);
    }
    public void  setEMotorBetragDell(){
        EMotorBetrag.clear();

    }

    public ArrayList<String> getSchredderPreisKg(){
        return SchredderPreisKg;
    }
    public String getSchredderPreisKg(int pos) {
        return SchredderPreisKg.get(pos);
    }
    public void setSchredderPreisKg(String st) {
        SchredderPreisKg.add(st);
    }
    public void setSchredderPreisKgDell(){
        SchredderPreisKg.clear();

    }

    public ArrayList<String> getSchredderGewicht(){
        return SchredderGewicht;
    }
    public String getSchredderGewicht(int pos) {
        return SchredderGewicht.get(pos);
    }
    public void setSchredderGewicht(String st) {
        SchredderGewicht.add(st);
    }
    public void setSchredderGewichtDell(){
        SchredderGewicht.clear();

    }

    public ArrayList<String> getSchredderBetrag(){
        return SchredderBetrag;
    }
    public String getSchredderBetrag(int pos) {
        return SchredderBetrag.get(pos);
    }
    public void setSchredderBetrag(String st) {
        SchredderBetrag.add(st);
    }
    public void setSchredderBetragDell(){
        SchredderBetrag.clear();

    }

    public ArrayList<String> getGussPreisKg(){
        return GussPreisKg;
    }
    public String getGussPreisKg(int pos) {
        return GussPreisKg.get(pos);
    }
    public void setGussPreisKg(String st) {
        GussPreisKg.add(st);
    }
    public void setGussPreisKgDell(){
        GussPreisKg.clear();

    }

    public ArrayList<String> getGussGewicht(){
        return GussGewicht;
    }
    public String getGussGewicht(int pos) {
        return GussGewicht.get(pos);
    }
    public void setGussGewicht(String st) {
        GussGewicht.add(st);
    }
    public void setGussGewichtDell(){
        GussGewicht.clear();

    }

    public ArrayList<String> getGussBetrag(){
        return GussBetrag;
    }
    public String getGussBetrag(int pos) {
        return GussBetrag.get(pos);
    }
    public void setGussBetrag(String st) {
        GussBetrag.add(st);
    }
    public void setGussBetragDell(){
        GussBetrag.clear();

    }

    public ArrayList<String> getSpaenePreisKg(){
        return SpaenePreisKg;
    }
    public String getSpaenePreisKg(int pos) {
        return SperrPreisKg.get(pos);
    }
    public void setSpaenePreisKg(String st) {
        SpaenePreisKg.add(st);
    }
    public void setSpaenePreisKgDell(){
        SpaenePreisKg.clear();

    }

    public ArrayList<String> getSpaeneGewicht(){
        return SpaenePreisKg;
    }
    public String getSpaeneGewicht(int pos) {
        return SperrPreisKg.get(pos);
    }
    public void setSpaeneGewicht(String st) {
        SpaenePreisKg.add(st);
    }
    public void setSpaeneGewichtDell(){
        SpaenePreisKg.clear();

    }

    public ArrayList<String> getSpaeneBetrag(){
        return SpaeneBetrag;
    }
    public String getSpaeneBetrag(int pos) {
        return SpaeneBetrag.get(pos);
    }
    public void setSpaeneBetrag(String st) {
        SpaeneBetrag.add(st);
    }
    public void setSpaeneBetragDell(){
        SpaeneBetrag.clear();

    }

    public ArrayList<String> getKabelPreisKg(){
        return KabelPreisKg;
    }
    public String getKabelPreisKg(int pos) {
        return KabelPreisKg.get(pos);
    }
    public void setKabelPreisKg(String st) {
        KabelPreisKg.add(st);
    }
    public void setKabelPreisKg(){
        KabelPreisKg.clear();

    }

    public ArrayList<String> getKabelGewicht(){
        return KabelGewicht;
    }
    public String getKabelGewicht(int pos) {
        return KabelGewicht.get(pos);
    }
    public void setKabelGewicht(String st) {
        KabelGewicht.add(st);
    }
    public void getKabelGewichtDell(){
        KabelGewicht.clear();

    }

    public ArrayList<String> getKabelBetrag(){
        return KabelBetrag;
    }
    public String getKabelBetrag(int pos) {
        return KabelBetrag.get(pos);
    }
    public void setKabelBetrag(String st) {
        KabelBetrag.add(st);
    }
    public void setKabelBetragDell(){
        KabelBetrag.clear();

    }

    public ArrayList<String> getKupferLeichtPreisKg(){
        return KupferLeichtPreisKg;
    }
    public String setKupferLeichtPreisKg(int pos) {
        return KupferLeichtPreisKg.get(pos);
    }
    public void setKupferLeichtPreisKg(String st) {
        KupferLeichtPreisKg.add(st);
    }
    public void setKupferLeichtPreisKgDell(){
        KupferLeichtPreisKg.clear();

    }

    public ArrayList<String> getKupferLeichtGewicht(){
        return KupferLeichtGewicht;
    }
    public String getKupferLeichtGewicht(int pos) {
        return KupferLeichtGewicht.get(pos);
    }
    public void setKupferLeichtGewicht(String st) {
        KabelGewicht.add(st);
    }
    public void setKupferLeichtGewichtDell(){
        KabelGewicht.clear();

    }

    public ArrayList<String> getKupferLeichtBetrag(){
        return KupferLeichtBetrag;
    }
    public String getKupferLeichtBetrag(int pos) {
        return KupferLeichtBetrag.get(pos);
    }
    public void setKupferLeichtBetrag(String st) {
        KupferLeichtBetrag.add(st);
    }
    public void setKupferLeichtBetragDell(){
        KupferLeichtBetrag.clear();

    }

    public ArrayList<String> getMessingPreisKg(){
        return MessingPreisKg;
    }
    public String getKupferLeichtPreisKg(int pos) {
        return MessingPreisKg.get(pos);
    }
    public void setMessingPreisKg(String st) {
        MessingPreisKg.add(st);
    }
    public void setMessingPreisKgDell(){
        MessingPreisKg.clear();

    }

    public ArrayList<String> getMessingGewicht(){
        return MessingGewicht;
    }
    public String getMessingGewicht(int pos) {
        return MessingGewicht.get(pos);
    }
    public void setMessingGewicht(String st) {
        MessingGewicht.add(st);
    }
    public void setMessingGewichtDell(){
        MessingGewicht.clear();

    }

    public ArrayList<String> getMessingBetrag(){
        return MessingBetrag;
    }
    public String getMessingBetrag (int pos) {
        return MessingBetrag.get(pos);
    }
    public void setMessingBetrag(String st) {
        MessingBetrag.add(st);
    }
    public void setMessingBetragDell(){
        MessingBetrag.clear();

    }

    public ArrayList<String> getAluPreisKg(){
        return AluPreisKg;
    }
    public String getAluPreisKg(int pos) {
        return AluPreisKg.get(pos);
    }
    public void setAluPreisKg(String st) {
        AluPreisKg.add(st);
    }
    public void setAluPreisKgDell(){
        AluPreisKg.clear();

    }

    public ArrayList<String> getAluGewicht(){
        return AluGewicht;
    }
    public String getAluGewicht(int pos) {
        return AluGewicht.get(pos);
    }
    public void setAluGewicht(String st) {
        AluGewicht.add(st);
    }
    public void setAluGewichtDell(){
        AluGewicht.clear();

    }

    public ArrayList<String> getAluBetrag(){
        return AluBetrag;
    }
    public String getAluBetrag (int pos) {
        return AluBetrag.get(pos);
    }
    public void setAluBetrag(String st) {
        AluBetrag.add(st);
    }
    public void setAluBetragDell(){
        AluBetrag.clear();

    }

    public ArrayList<String> getAluNeuPreisKg(){
        return AluNeuPreisKg;
    }
    public String getAluNeuPreisKg(int pos) {
        return AluNeuPreisKg.get(pos);
    }
    public void setAluNeuPreisKg(String st) {
        AluNeuPreisKg.add(st);
    }
    public void setAluNeuPreisKgDell(){
        AluNeuPreisKg.clear();

    }

    public ArrayList<String> getAluNeuGewicht(){
        return AluNeuGewicht;
    }
    public String getAluNeuGewicht(int pos) {
        return AluNeuGewicht.get(pos);
    }
    public void setAluNeuGewicht(String st) {
        AluNeuGewicht.add(st);
    }
    public void setAluNeuGewichtDell(){
        AluNeuGewicht.clear();

    }

    public ArrayList<String> getAluNeuBetrag(){
        return AluNeuBetrag;
    }
    public String getAluNeuBetrag (int pos) {
        return AluNeuBetrag.get(pos);
    }
    public void setAluNeuBetrag(String st) {
        AluNeuBetrag.add(st);
    }
    public void setAluNeuBetragDell(){
        AluNeuBetrag.clear();

    }

    public ArrayList<String> getALuSchrederPreisKg(){
        return ALuSchrederPreisKg;
    }
    public String getALuSchrederPreisKg(int pos) {
        return ALuSchrederPreisKg.get(pos);
    }
    public void setALuSchrederPreisKg(String st) {
        ALuSchrederPreisKg.add(st);
    }
    public void setALuSchrederPreisKgDell(){
        ALuSchrederPreisKg.clear();

    }

    public ArrayList<String> getAluSchredderGewicht(){
        return AluSchredderGewicht;
    }
    public String getAluSchredderGewicht(int pos) {
        return AluSchredderGewicht.get(pos);
    }
    public void setAluSchredderGewicht(String st) {
        AluSchredderGewicht.add(st);
    }
    public void setAluSchredderGewichtDell(){
        AluSchredderGewicht.clear();

    }

    public ArrayList<String> getAluSchredderBetrag(){
        return AluSchredderBetrag;
    }
    public String getAluSchredderBetrag (int pos) {
        return AluSchredderBetrag.get(pos);
    }
    public void setAluSchredderBetrag(String st) {
        AluSchredderBetrag.add(st);
    }
    public void setAluSchredderBetragDell(){
        AluSchredderBetrag.clear();

    }

    public ArrayList<String> getZinkPreisKg(){
        return ZinkPreisKg;
    }
    public String getZinkPreisKg(int pos) {
        return ZinkPreisKg.get(pos);
    }
    public void setZinkPreisKg(String st) {
        ZinkPreisKg.add(st);
    }
    public void setZinkPreisKg(){
        ZinkPreisKg.clear();

    }

    public ArrayList<String> getZinkGewicht(){
        return ZinkGewicht;
    }
    public String getZinkGewicht(int pos) {
        return ZinkGewicht.get(pos);
    }
    public void setZinkGewicht(String st) {
        ZinkGewicht.add(st);
    }
    public void setZinkGewicht(){
        ZinkGewicht.clear();

    }

    public ArrayList<String> getZinkBetrag(){
        return ZinkBetrag;
    }
    public String getZinkBetrag (int pos) {
        return ZinkBetrag.get(pos);
    }
    public void setZinkBetrag(String st) {
        ZinkBetrag.add(st);
    }
    public void setZinkBetrag(){
        ZinkBetrag.clear();

    }

    public ArrayList<String> getBleiPreisKg(){
        return BleiPreisKg;
    }
    public String getBleiPreisKg(int pos) {
        return BleiPreisKg.get(pos);
    }
    public void setBleiPreisKg(String st) {
        BleiPreisKg.add(st);
    }
    public void setBleiPreisKg(){
        BleiPreisKg.clear();

    }

    public ArrayList<String> getBleiGewicht(){
        return BleiGewicht;
    }
    public String getBleiGewicht (int pos) {
        return BleiGewicht.get(pos);
    }
    public void setBleiGewicht(String st) {
        BleiGewicht.add(st);
    }
    public void setBleiGewicht(){
        BleiGewicht.clear();

    }

    public ArrayList<String> getBleiBetrag(){
        return BleiBetrag;
    }
    public String getBleiBetrag (int pos) {
        return BleiBetrag.get(pos);
    }
    public void setBleiBetrag(String st) {
        BleiBetrag.add(st);
    }
    public void setBleiBetrag(){
        BleiBetrag.clear();

    }

    public ArrayList<String> getVAPreisKg(){
        return VAPreisKg;
    }
    public String getVAPreisKg(int pos) {
        return VAPreisKg.get(pos);
    }
    public void setVAPreisKg(String st) {
        VAPreisKg.add(st);
    }
    public void setVAPreisKg(){
        VAPreisKg.clear();

    }

    public ArrayList<String> getVAGewicht(){
        return VAGewicht;
    }
    public String getVAGewicht (int pos) {
        return VAGewicht.get(pos);
    }
    public void setVAGewicht(String st) {
        VAGewicht.add(st);
    }
    public void setVAGewicht(){
        VAGewicht.clear();

    }

    public ArrayList<String> getVABetrag(){
        return VABetrag;
    }
    public String getVABetrag (int pos) {
        return VABetrag.get(pos);
    }
    public void setVABetrag(String st) {
        VABetrag.add(st);
    }
    public void setVABetrag(){
        VABetrag.clear();

    }

    public ArrayList<String> getKupferPreisKg(){
        return KupferPreisKg;
    }
    public String getKupferPreisKg(int pos) {
        return KupferPreisKg.get(pos);
    }
    public void setKupferPreisKg(String st) {
        KupferPreisKg.add(st);
    }
    public void setKupferPreisKg(){
        KupferPreisKg.clear();

    }

    public ArrayList<String> getKupferGewicht(){
        return KupferGewicht;
    }
    public String getKupferGewicht (int pos) {
        return KupferGewicht.get(pos);
    }
    public void setKupferGewicht(String st) {
        KupferGewicht.add(st);
    }
    public void setKupferGewicht(){
        KupferGewicht.clear();

    }

    public ArrayList<String> getKupferBetrag(){
        return KupferBetrag;
    }
    public String getKupferBetrag (int pos) {
        return KupferBetrag.get(pos);
    }
    public void setKupferBetrag(String st) {
        KupferBetrag.add(st);
    }
    public void setKupferBetrag(){
        KupferBetrag.clear();

    }




}
