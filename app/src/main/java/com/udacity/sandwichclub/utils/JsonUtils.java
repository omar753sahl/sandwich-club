package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject root = new JSONObject(json);
        Log.d(TAG, "parseSandwichJson: JSON Object: " + root);

        JSONObject name = root.getJSONObject("name");

        String mainName = name.getString("mainName");

        List<String> alsoKnownAs = new ArrayList<>();
        JSONArray alsoKnownAsJson = name.getJSONArray("alsoKnownAs");
        for (int i = 0; i < alsoKnownAsJson.length(); i++) {
            alsoKnownAs.add(alsoKnownAsJson.getString(i));
        }

        String placeOfOrigin = root.getString("placeOfOrigin");
        String description = root.getString("description");
        String image = root.getString("image");

        JSONArray ingredientsJson = root.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsJson.length(); i++) {
            ingredients.add(ingredientsJson.getString(i));
        }

        return new Sandwich(
                mainName,
                alsoKnownAs,
                placeOfOrigin,
                description,
                image,
                ingredients
        );
    }
}
