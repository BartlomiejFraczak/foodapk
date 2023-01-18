package bartlomiejfraczak.foodapk.activity;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Locale;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
import bartlomiejfraczak.foodapk.util.Jezyk;

public class CustomAppCompatActivity extends AppCompatActivity {
    private Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_settings:
//                // User chose the "Settings" item, show the app settings UI...
//                return true;

            case R.id.action_jezyk:
                zmienJezyk();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        zmienIkone();
        return super.onPrepareOptionsMenu(menu);
    }

    public void zmienIkone() {
        if (menu == null) {
            return;
        }
        MenuItem item = menu.findItem(R.id.action_jezyk);
        String region = GlobalneInfo.getInstancja().getRegion();
        switch (region) {
            case "en":
                item.setIcon(ContextCompat.getDrawable(this, R.mipmap.flag_gb_foreground));
                break;
            case "pl":
                item.setIcon(ContextCompat.getDrawable(this, R.mipmap.flag_pl_foreground));
                break;
            default:
                System.out.println("error CustomAppCompatActivity onPrepareOptionsMenu() switch");
        }
    }

    private void zmienJezyk() {
        String region = GlobalneInfo.getInstancja().getRegion();
        if (region.equals("en")) {
            region = "pl";
        } else {
            region = "en";
        }
        GlobalneInfo.getInstancja().setRegion(region);
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(new Locale(region));
        resources.updateConfiguration(configuration, metrics);
        onConfigurationChanged(configuration);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Jezyk.getInstancja().updateJezyka();
//        setTitle(R.string.title_activity_szukaj);
        invalidateOptionsMenu();
    }

    public void updateJezyka() {
        zmienIkone();
    }

    public void dodajBackButton() {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
