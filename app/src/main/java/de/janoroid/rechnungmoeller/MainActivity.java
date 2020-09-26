package de.janoroid.rechnungmoeller;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    final AbrechnungFragment abrechnungFragment = new AbrechnungFragment();
    final RechnungFragment rechnungFragment = new RechnungFragment();
    final DruckenFragment druckenFragment = new DruckenFragment();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        BottomNavigationView bottomNav = findViewById(R.id.bottomnavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    new RechnungFragment()).commit();




    }


        //Create Folder
        File rechnung = new File(Environment.getExternalStorageDirectory().toString()+"/PDF-Moeller/Rechnung/");
        rechnung.mkdirs();
        File abrechnung = new File(Environment.getExternalStorageDirectory().toString()+"/PDF-Moeller/Abrechnung/");
        abrechnung.mkdirs();

        //Save the path as a string value
       // String extStorageDirectory = folder.toString();


    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    int id = menuItem.getItemId();

                    if (id == R.id.rechnung)
                    {
                        setFragment(rechnungFragment);
                        return true;

                    }
                    else if (id == R.id.abrechnung)
                    {
                        setFragment(abrechnungFragment);
                        return true;
                    }
                    else if (id == R.id.drucken)
                    {
                        setFragment(druckenFragment);
                        return true;
                    }
                    return false;

                }


                private void setFragment(Fragment fragment) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                }


            };





}