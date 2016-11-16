package com.example.cs465.decisionhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
    public static final String VALUES_TABLE_NAME = "values";
    public static final String VALUES_COLUMN_ID = "id";
    public static final String VALUES_COLUMN_NAME = "name";

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

    // Decisions can be shared with others. This maps a Decision's id with a string representing
    // A user's name. IRL we'd have a User table, but not necessary, I don't think
    // eg: {12345: "bob"}
    public static final String SHARED_WITH_TABLE_NAME = "shared_with";
    public static final String SHARED_WITH_COLUMN_ID = "id";
    public static final String SHARED_WITH_COLUMN_USER = "user";
    public static final String SHARED_WITH_COLUMN_DECISION_ID = "decision_id";
    public static final String SHARED_WITH_COLUMN_RESPONSE_MESSAGE = "response_message";
    public static final String SHARED_WITH_COLUMN_COMPLETED = "completed";

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

    public List<Decision> getAllDecisionsForOwner(String owner) {
        ArrayList<Decision> decisions = new ArrayList<Decision>();
        return decisions;
    }

    public List<Decision> getAllDecisionsSharedWithOwner(String owner) {
        ArrayList<Decision> decisions = new ArrayList<Decision>();
        return decisions;
    }

    public Decision getDecisionByID(int id) {
        Decision decision = new Decision();
        return decision;
    }

    public Decision createDecision(String name, String owner) {
        Decision decision = new Decision();
        return decision;
    }

    public int getCurrentStep(int decision_id) {
        return 4;
    }

    public List<Choice> getAllChoicesForDecision(int decision_id) {
        ArrayList<Choice> choices = new ArrayList<Choice>();
        return choices;
    }

    public Choice getChoiceByID(int id) {
        Choice choice = new Choice();
        return choice;
    }

    public Choice createChoice(String name, int decision_id) {
        Choice choice = new Choice();
        return choice;
    }

    public List<Factor> getAllFactorsForDecision(int decision_id) {
        ArrayList<Factor> factors = new ArrayList<Factor>();
        return factors;
    }

    public Factor getFactorByID(int id) {
        Factor factor= new Factor();
        return factor;
    }

    public Factor createFactor(String name, int decision_id) {
        Factor factor = new Factor();
        return factor;
    }

    public List<Value> getAllValuesForFactor(int factor_id) {
        ArrayList<Value> values = new ArrayList<Value>();
        return values;
    }

    public Value getValueByID(int id) {
        Value value = new Value();
        return value;
    }

    public Value createValueForFactor(String name, int factor_id) {
        Value value = new Value();
        return value;
    }

    public void assignScoreToValue(String owner, int score, int value_id) {

    }

    public int getScoreForValue(String owner, int value_id) {
        return 0;
    }

    public void assignScoreToFactor(String owner, int score, int factor_id) {

    }

    public int getScoreForFactor(String owner, int factor_id) {
        return 0;
    }

    public void assignValueToFactorForChoice(int choice_id, int factor_id, int value_id) {

    }

    public Value getValueForFactorAndChoice(int choice_id, int factor_id) {
        Value value = new Value();
        return value;
    }

    public void shareDecisionWithUser(int decision_id, String user) {

    }

    public void completeSharedDecision(int decision_id, String user, String message) {

    }

    public Choice getTopChoiceForDecision(int decision_id) {
        Choice choice = new Choice();
        return choice;
    }

    /*
     *  CLASSES TO MAKE WORKING WITH DATA RECEIVED FROM SQL EASIER
     */
    public class Decision {
        public int id;
        public String name;
        public String owner;

        public Decision(Cursor row) {
            this.id = row.getInt(row.getColumnIndex(DECISIONS_COLUMN_ID));
            this.name = row.getString(row.getColumnIndex(DECISIONS_COLUMN_NAME));
            this.owner = row.getString(row.getColumnIndex(DECISIONS_COLUMN_OWNER));
        }
        private Decision() {}
    }

    public class Choice {
        public int id;
        public String name;
        public int decision_id;

        public Choice(Cursor row) {
            this.id = row.getInt(row.getColumnIndex(CHOICES_COLUMN_ID));
            this.name = row.getString(row.getColumnIndex(CHOICES_COLUMN_NAME));
            this.decision_id = row.getInt(row.getColumnIndex(CHOICES_COLUMN_DECISION_ID));
        }
        private Choice() {}
    }

    public class Factor {
        public int id;
        public String name;
        public int decision_id;

        public Factor(Cursor row) {
            this.id = row.getInt(row.getColumnIndex(FACTORS_COLUMN_ID));
            this.name = row.getString(row.getColumnIndex(FACTORS_COLUMN_NAME));
            this.decision_id = row.getInt(row.getColumnIndex(FACTORS_COLUMN_DECISION_ID));
        }
        private Factor() {}
    }

    public class Value {
        public int id;
        public String name;

        public Value(Cursor row) {
            this.id = row.getInt(row.getColumnIndex(VALUES_COLUMN_ID));
            this.name = row.getString(row.getColumnIndex(VALUES_COLUMN_NAME));
        }

        private Value() {}
    }

    public class Score {
        public int id;
        public String owner;
        public int score;
        public int factor_id;
        public int value_id;

        public Score(Cursor row) {
            this.id = row.getInt(row.getColumnIndex(SCORES_COLUMN_ID));
            this.owner = row.getString(row.getColumnIndex(SCORES_COLUMN_OWNER));
            this.score = row.getInt(row.getColumnIndex(SCORES_COLUMN_SCORE));
            this.value_id = row.getInt(row.getColumnIndex(SCORES_COLUMN_VALUE_ID));
            this.factor_id = row.getInt(row.getColumnIndex(SCORES_COLUMN_FACTOR_ID));
        }

        private Score() {}
    }

}

