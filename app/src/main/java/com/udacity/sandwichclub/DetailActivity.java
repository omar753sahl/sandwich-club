package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.utils.StringUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Toolbar toolbar;
    private CollapsingToolbarLayout toolbarLayout;
    private TextView akaTextView;
    private TextView originTextView;
    private TextView descriptionTextView;
    private TextView ingredientsTextView;
    private ImageView ingredientsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        toolbarLayout = findViewById(R.id.toolbarLayout);
        akaTextView = findViewById(R.id.also_known_tv);
        originTextView = findViewById(R.id.origin_tv);
        descriptionTextView = findViewById(R.id.description_tv);
        ingredientsTextView = findViewById(R.id.ingredients_tv);
        ingredientsImageView = findViewById(R.id.image_iv);

        toolbar.setTitle(sandwich.getMainName());
        toolbarLayout.setTitle(sandwich.getMainName());
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsImageView);
        akaTextView.setText(sandwich.getAlsoKnownAs().size() > 0 ? StringUtils.join(sandwich.getAlsoKnownAs(), ", ") : getString(R.string.no_other_names));
        originTextView.setText(sandwich.getPlaceOfOrigin().equals("") ? getString(R.string.unknown) : sandwich.getPlaceOfOrigin());
        descriptionTextView.setText(sandwich.getDescription());
        ingredientsTextView.setText(StringUtils.join(sandwich.getIngredients(), ", "));
    }

}
