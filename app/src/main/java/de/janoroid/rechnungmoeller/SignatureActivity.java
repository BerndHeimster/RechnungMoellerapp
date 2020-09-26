package de.janoroid.rechnungmoeller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.byox.drawview.views.DrawView;

public class SignatureActivity extends AppCompatActivity {

    private static final int MENU_CLEAR = Menu.FIRST;
    private static final int MENU_SAVE = Menu.FIRST+1;
    private static final int MENU_LOAD = Menu.FIRST+2;

    private de.janoroid.rechnungmoeller.DrawView drawView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);


        drawView = new de.janoroid.rechnungmoeller.DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
        drawView.requestFocus();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_CLEAR, 0, "Neu");
        menu.add(0, MENU_SAVE, 0, "Abrechnung erstellen");
        menu.add(0, MENU_LOAD, 0, "Unterschrift anzeigen lassen");
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_CLEAR:
                drawView.clearBitmap();
                return true;
            case MENU_SAVE:
                drawView.saveBitmap();
                Intent intent = new Intent(SignatureActivity.this, CreatePdf.class);
                startActivity(intent);
                return true;
            case MENU_LOAD:
                drawView.loadBitmap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}