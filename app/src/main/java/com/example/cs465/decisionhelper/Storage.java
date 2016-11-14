package com.example.cs465.decisionhelper;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class Storage extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DecisionHelper.db";

    // ex: "Choosing a Job"
    public static final String DECISIONS_TABLE_NAME = "decisions";
    public static final String DECISIONS_COLUMN_ID = "id";
    public static final String DECISIONS_COLUMN_NAME = "name";
    public static final String DECISIONS_COLUMN_OWNER = "owner";

    // Each Choice is associated with a Decision
    // ex: "Microsoft, Amazon, Google"
    public static final String CHOICES_TABLE_NAME = "choices";
    public static final String CHOICES_COLUMN_ID = "id";
    public static final String CHOICES_COLUMN_NAME = "name";
    public static final String CHOICES_COLUMN_DECISION_ID = "decision_id";

    // Each Factor is associated with a Decision
    // ex: Salary, Location
    public static final String FACTORS_TABLE_NAME = "factors";
    public static final String FACTORS_COLUMN_ID = "id";
    public static final String FACTORS_COLUMN_NAME = "name";
    public static final String FACTORS_COLUMN_DECISION_ID = "decision_id";

    // Each Value is EITHER associated with a Factor (when it's first created)
    // OR associated with a Choice AND Factor(after it's assigned)
    // ex: $100,000, San Francisco, Large
    public static final String VALUE_TABLE_NAME = "values";
    public static final String VALUE_COLUMN_ID = "id";
    public static final String VALUE_COLUMN_NAME = "name";

    // Maps a Factor to its possible Values
    //eg: (Salary: [$100,000, $20,000])
    public static final String FACTOR_TO_VALUE_TABLE_NAME = "factor_to_value";
    public static final String FACTOR_TO_VALUE_COLUMN_ID = "id";
    public static final String FACTOR_TO_VALUE_COLUMN_FACTOR_ID = "factor_id";
    public static final String FACTOR_TO_VALUE_COLUMN_VALUE_ID = "value_id";

    // Maps each of a Choice's Factors to its assigned Value
    // Essentially, each Choice will have many FACTOR_TO_VALUE, which represents
    // The Value for each one of the Factors
    //eg: (Microsoft: (Salary: $100,000))
    public static final String CHOICE_TO_FACTOR_TO_VALUE_TABLE_NAME = "choice_to_factor_to_value";
    public static final String CHOICE_TO_FACTOR_TO_VALUE_COLUMN_ID = "id";
    public static final String CHOICE_TO_FACTOR_TO_VALUE_COLUMN_CHOICE_ID = "choice_id";
    public static final String CHOICE_TO_FACTOR_TO_VALUE_COLUMN_FACTOR_TO_VALUE_ID = "factor_to_value_id";

    // Each Score is associated with a Factor OR a Value.
    // Each Score has an owner to differentiate between the score the user gave
    //     vs the score the person s/he shared it with gave
    // eg: (Salary:50), ($100,000:80)
    public static final String SCORES_TABLE_NAME = "scores";
    public static final String SCORES_COLUMN_ID = "id";
    public static final String SCORES_COLUMN_OWNER = "owner";
    public static final String SCORES_COLUMN_SCORE = "score";
    public static final String SCORES_COLUMN_FACTOR_ID = "factor_id";
    public static final String SCORES_COLUMN_VALUE_ID = "value_id";

    public Storage(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO create all the tables using db.execSQL()
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO drop all tables IF EXISTS
        // TODO onCreate(db);
    }

    // TODO: write methods to write and read from db
    // SQLiteDatabase db = this.getWritableDatabase();
    // SQLiteDatabase db = this.getReadableDatabase();
    // db.rawQuery
}
