package bartlomiejfraczak.foodapk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.modele.PrzepisSzczegolowyModel;

public class InfoActivity extends CustomAppCompatActivity {
    private TextView tvReadyInMinutesLabel;
    private TextView tvReadyInMinutes;
    private View sepReadyInMinutes;
    private TextView tvServingsLabel;
    private TextView tvServings;
    private View sepServings;
    private TextView tvCuisinesLabel;
    private TextView tvCuisines;
    private View sepCuisines;
    private TextView tvDishTypesLabel;
    private TextView tvDishTypes;
    private View sepDishTypes;
    private TextView tvPairedWinesLabel;
    private TextView tvPairedWinesText;
    private TextView tvPairedWines;
    private View sepPairedWines;
    private TextView tvDietsLabel;
    private TextView tvDiets;
    private View sepDiets;
    private TextView tvHealthScoreLabel;
    private TextView tvHealthScore;
    private View sepHealthScore;


    private PrzepisSzczegolowy przepisSzczegolowy;


    private static InfoActivity instancja;

    public static InfoActivity getInstancja() {
        return instancja;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle(R.string.title_activity_info);
        instancja = this;
        init();
    }

    private void init() {
        dodajBackButton();

        tvReadyInMinutesLabel = findViewById(R.id.tvReadyInMinutesLabel);
        tvReadyInMinutes = findViewById(R.id.tvReadyInMinutes);
        sepReadyInMinutes = findViewById(R.id.sepReadyInMinutes);

        tvServingsLabel = findViewById(R.id.tvServingsLabel);
        tvServings = findViewById(R.id.tvServings);
        sepServings = findViewById(R.id.sepServings);

        tvCuisinesLabel = findViewById(R.id.tvCuisinesLabel);
        tvCuisines = findViewById(R.id.tvCuisines);
        sepCuisines = findViewById(R.id.sepCuisines);

        tvDishTypesLabel = findViewById(R.id.tvDishTypesLabel);
        tvDishTypes = findViewById(R.id.tvDishTypes);
        sepDishTypes = findViewById(R.id.sepDishTypes);

        tvPairedWinesLabel = findViewById(R.id.tvPairedWinesLabel);
        tvPairedWinesText = findViewById(R.id.tvPairedWinesText);
        tvPairedWines = findViewById(R.id.tvPairedWines);
        sepPairedWines = findViewById(R.id.sepPairedWines);

        tvDietsLabel = findViewById(R.id.tvDietsLabel);
        tvDiets = findViewById(R.id.tvDiets);
        sepDiets = findViewById(R.id.sepDiets);

        tvHealthScoreLabel = findViewById(R.id.tvHealthScoreLabel);
        tvHealthScore = findViewById(R.id.tvHealthScore);
        sepHealthScore = findViewById(R.id.sepHealthScore);

        przepisSzczegolowy = PrzepisSzczegolowyModel.getInstancja().getPrzepisSzczegolowy();
        if (przepisSzczegolowy == null) {
            return;
        }
        if (przepisSzczegolowy.getReadyInMinutes() > 0) {
            tvReadyInMinutes.setText(String.valueOf(przepisSzczegolowy.getReadyInMinutes()) + " min");
        } else {
            tvReadyInMinutesLabel.setVisibility(View.GONE);
            tvReadyInMinutes.setVisibility(View.GONE);
            sepReadyInMinutes.setVisibility(View.GONE);
        }
        if (przepisSzczegolowy.getServings() > 0) {
            tvServings.setText(String.valueOf(przepisSzczegolowy.getServings()));
        } else {
            tvServingsLabel.setVisibility(View.GONE);
            tvServings.setVisibility(View.GONE);
            sepServings.setVisibility(View.GONE);
        }
        if (przepisSzczegolowy.getCuisines() != null && przepisSzczegolowy.getCuisines().size() > 0) {
            tvCuisines.setText(String.join("\n", przepisSzczegolowy.getCuisines()));
        } else {
            tvCuisinesLabel.setVisibility(View.GONE);
            tvCuisines.setVisibility(View.GONE);
            sepCuisines.setVisibility(View.GONE);
        }
        if (przepisSzczegolowy.getDishTypes() != null && przepisSzczegolowy.getDishTypes().size() > 0) {

            tvDishTypes.setText(String.join("\n", przepisSzczegolowy.getDishTypes()));
        } else {
            tvDishTypesLabel.setVisibility(View.GONE);
            tvDishTypes.setVisibility(View.GONE);
            sepDishTypes.setVisibility(View.GONE);
        }
        if (przepisSzczegolowy.getPairedWines() != null && przepisSzczegolowy.getPairedWines().size() > 0
                || przepisSzczegolowy.getPairingText() != null && !przepisSzczegolowy.getPairingText().equals("")
        ) {
            if (przepisSzczegolowy.getPairedWines() != null && przepisSzczegolowy.getPairedWines().size() > 0) {
                tvPairedWines.setText(String.join("\n", przepisSzczegolowy.getPairedWines()));
            } else {
                tvPairedWines.setVisibility(View.GONE);
            }
            if (przepisSzczegolowy.getPairingText() != null && !przepisSzczegolowy.getPairingText().equals("")) {
                tvPairedWinesText.setText(przepisSzczegolowy.getPairingText());
            } else {
                tvPairedWinesText.setVisibility(View.GONE);
            }
        } else {
            tvPairedWinesLabel.setVisibility(View.GONE);
            tvPairedWines.setVisibility(View.GONE);
            tvPairedWinesText.setVisibility(View.GONE);
            sepPairedWines.setVisibility(View.GONE);
        }
        if (przepisSzczegolowy.getDiets() != null && przepisSzczegolowy.getDiets().size() > 0) {
            tvDiets.setText(String.join("\n", przepisSzczegolowy.getDiets()));
        } else {
            tvDietsLabel.setVisibility(View.GONE);
            tvDiets.setVisibility(View.GONE);
            sepDiets.setVisibility(View.GONE);
        }
        if (przepisSzczegolowy.getHealthScore() > 0f) {
            tvHealthScore.setText(String.valueOf(przepisSzczegolowy.getHealthScore()));
        } else {
            tvHealthScoreLabel.setVisibility(View.GONE);
            tvHealthScore.setVisibility(View.GONE);
            sepHealthScore.setVisibility(View.GONE);
        }
    }


    public void updateJezyka() {
        setTitle(R.string.title_activity_info);

        tvReadyInMinutesLabel.setText(R.string.gotowew);
        tvServingsLabel.setText(R.string.porcje);
        tvCuisinesLabel.setText(R.string.kuchnie);
        tvDishTypesLabel.setText(R.string.typypotrawy);
        tvPairedWinesLabel.setText(R.string.winadopary);
        tvDietsLabel.setText(R.string.diety);
        tvHealthScoreLabel.setText(R.string.wynikzdrowotny);

        super.updateJezyka();
    }
}