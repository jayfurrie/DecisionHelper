package com.example.cs465.decisionhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
    public static final String VALUES_TABLE_NAME = "myvalues";
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
        String queries[] = ("" +
            "create table decisions" +
                "(" +
                    "id integer primary key," +
                    "name varchar(255)," +
                    "owner varchar(255)" +
                ");" +

            "create table choices (" +
                "id integer primary key," +
                "name varchar(255)," +
                "decision_id integer" +
            ");" +

            "create table factors (" +
                "id integer primary key," +
                "name varchar(255)," +
                "decision_id integer" +
            ");" +

            "create table myvalues (" +
                "id integer primary key," +
                "name varchar(255)" +
            ");" +

            "create table factor_to_value (" +
                "id integer primary key," +
                "factor_id integer," +
                "value_id integer" +
            ");" +

            "create table choice_to_factor_to_value (" +
                "id integer primary key," +
                "choice_id integer," +
                "factor_to_value_id integer" +
            ");" +

            "create table scores (" +
                "id integer primary key," +
                "owner varchar(255)," +
                "score integer," +
                "factor_id integer," +
                "value_id integer" +
            ");" +

            "create table shared_with (" +
                "id integer primary key," +
                "user varchar(255)," +
                "decision_id integer," +
                "response_message varchar(1000)," +
                "completed boolean" +
            ");").split(";");

        for(String query : queries){
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        clear();
    }

    public void clear() {
        String[] queries = ("DROP TABLE IF EXISTS decisions;" +
                "DROP TABLE IF EXISTS choices;" +
                "DROP TABLE IF EXISTS factors;" +
                "DROP TABLE IF EXISTS myvalues;" +
                "DROP TABLE IF EXISTS factor_to_value;" +
                "DROP TABLE IF EXISTS choice_to_factor_to_value;" +
                "DROP TABLE IF EXISTS scores;" +
                "DROP TABLE IF EXISTS shared_with;").split(";");

        SQLiteDatabase db = this.getWritableDatabase();
        for(String query : queries){
            db.execSQL(query);
        }
        onCreate(db);
    }

    public List<Decision> getAllDecisionsForOwner(String owner) {
        List<Decision> decisions = new ArrayList<Decision>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + DECISIONS_TABLE_NAME + "" +
                " where " + DECISIONS_COLUMN_OWNER + "=\"" + owner + "\"", null );


        results.moveToFirst();

        while(results.isAfterLast() == false){
            decisions.add(new Decision(results));
            results.moveToNext();
        }
        return decisions;
    }

    public void removeAllDecisionsForOwner(String owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + DECISIONS_TABLE_NAME +
                " where " + DECISIONS_COLUMN_OWNER + "=\"" + owner + "\"");
    }

    public List<Decision> getAllDecisionsSharedWithUser(String user) {
        List<Decision> decisions = new ArrayList<Decision>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + SHARED_WITH_TABLE_NAME +
                " where " + SHARED_WITH_COLUMN_USER + "=\"" + user + "\"", null );

        results.moveToFirst();

        while(results.isAfterLast() == false){
            int decision_id = results.getInt(results.getColumnIndex(SHARED_WITH_COLUMN_DECISION_ID));
            decisions.add(getDecisionByID(decision_id));
            results.moveToNext();
        }
        return decisions;
    }

    public Decision getDecisionByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + DECISIONS_TABLE_NAME +
                " where " + DECISIONS_COLUMN_ID + "=" + id, null );
        results.moveToFirst();
        Decision decision = new Decision(results);
        return decision;
    }

    public long createDecision(String name, String owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DECISIONS_COLUMN_NAME, name);
        contentValues.put(DECISIONS_COLUMN_OWNER, owner);
        return db.insert(DECISIONS_TABLE_NAME, null, contentValues);
    }

    public int getCurrentStepForDecision(int decision_id) {
        // TODO
        return 4;
    }

    public List<Choice> getAllChoicesForDecision(int decision_id) {
        ArrayList<Choice> choices = new ArrayList<Choice>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + CHOICES_TABLE_NAME +
                " where " + CHOICES_COLUMN_DECISION_ID + "=" + decision_id, null );

        results.moveToFirst();

        while(results.isAfterLast() == false){
            choices.add(new Choice(results));
            results.moveToNext();
        }
        return choices;
    }

    public void removeAllChoicesForDecision(int decision_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + CHOICES_TABLE_NAME +
                " where " + CHOICES_COLUMN_DECISION_ID + "=\"" + decision_id + "\"");
    }

    public Choice getChoiceByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + CHOICES_TABLE_NAME +
                " where " + CHOICES_COLUMN_ID + "=" + id, null );
        results.moveToFirst();
        Choice choice = new Choice(results);
        return choice;
    }

    public long createChoice(String name, int decision_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHOICES_COLUMN_NAME, name);
        contentValues.put(CHOICES_COLUMN_DECISION_ID, decision_id);
        return db.insert(CHOICES_TABLE_NAME, null, contentValues);
    }

    public List<Factor> getAllFactorsForDecision(int decision_id) {
        ArrayList<Factor> factors = new ArrayList<Factor>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + FACTORS_TABLE_NAME +
                " where " + FACTORS_COLUMN_DECISION_ID + "=" + decision_id, null );

        results.moveToFirst();

        while(results.isAfterLast() == false){
            factors.add(new Factor(results));
            results.moveToNext();
        }
        return factors;
    }

    public void removeAllFactorsForDecision(int decision_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + FACTORS_TABLE_NAME +
                " where " + FACTORS_COLUMN_DECISION_ID + "=\"" + decision_id + "\"");
    }

    public Factor getFactorByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + FACTORS_TABLE_NAME +
                " where " + FACTORS_COLUMN_ID + "=" + id, null );
        results.moveToFirst();
        Factor factor = new Factor(results);
        return factor;
    }

    public long createFactor(String name, int decision_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FACTORS_COLUMN_NAME, name);
        contentValues.put(FACTORS_COLUMN_DECISION_ID, decision_id);
        return db.insert(FACTORS_TABLE_NAME, null, contentValues);
    }

    public List<Value> getAllValuesForFactor(int factor_id) {
        ArrayList<Value> values = new ArrayList<Value>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " +
                VALUES_TABLE_NAME + ", " + FACTOR_TO_VALUE_TABLE_NAME +
                " where " + VALUES_TABLE_NAME + "." + VALUES_COLUMN_ID + "=" +
                FACTOR_TO_VALUE_TABLE_NAME + "." + FACTOR_TO_VALUE_COLUMN_VALUE_ID +
                " and " + FACTOR_TO_VALUE_TABLE_NAME + "." + FACTOR_TO_VALUE_COLUMN_FACTOR_ID + "=" + factor_id, null );

        results.moveToFirst();

        while(results.isAfterLast() == false){
            values.add(new Value(results));
            results.moveToNext();
        }

        return values;
    }

    public void removeAllValuesForFactor(int factor_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Value> values = this.getAllValuesForFactor(factor_id);
        for (Value v : values) {
            db.execSQL("delete from " + VALUES_TABLE_NAME +
                    " where " + VALUES_COLUMN_ID + "=\"" + v.id + "\"");
            db.execSQL("delete from " + FACTOR_TO_VALUE_TABLE_NAME +
                    " where " + FACTOR_TO_VALUE_COLUMN_VALUE_ID + "=\"" + v.id + "\"");
        }
    }

    public Value getValueByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + VALUES_TABLE_NAME +
                " where " + VALUES_COLUMN_ID + "=" + id, null );
        results.moveToFirst();
        Value value = new Value(results);
        return value;
    }

    public long createValueForFactor(String name, int factor_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VALUES_COLUMN_NAME, name);
        Long value_id = db.insert(VALUES_TABLE_NAME, null, contentValues);
        if (value_id != -1) {
            contentValues = new ContentValues();
            contentValues.put(FACTOR_TO_VALUE_COLUMN_FACTOR_ID, factor_id);
            contentValues.put(FACTOR_TO_VALUE_COLUMN_VALUE_ID, value_id);
            db.insert(FACTOR_TO_VALUE_TABLE_NAME, null, contentValues);
        }
        return value_id;
    }

    public long assignScoreToValue(String owner, int score, int value_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCORES_COLUMN_OWNER, owner);
        contentValues.put(SCORES_COLUMN_SCORE, score);
        contentValues.put(SCORES_COLUMN_VALUE_ID, value_id);
        return db.insert(SCORES_TABLE_NAME, null, contentValues);
    }

    public void removeAllScoresForOwner(String owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + SCORES_TABLE_NAME +
                " where " + SCORES_COLUMN_OWNER + "=\"" + owner + "\"");
    }

    public int getScoreForValue(String owner, int value_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + SCORES_TABLE_NAME +
                " where " + SCORES_COLUMN_OWNER + "=\"" + owner + "\"" +
                " and " + SCORES_COLUMN_VALUE_ID + "=" + value_id, null );
        results.moveToFirst();
        return results.getInt(results.getColumnIndex(SCORES_COLUMN_SCORE));
    }

    public long assignScoreToFactor(String owner, int score, int factor_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCORES_COLUMN_OWNER, owner);
        contentValues.put(SCORES_COLUMN_SCORE, score);
        contentValues.put(SCORES_COLUMN_FACTOR_ID, factor_id);
        return db.insert(SCORES_TABLE_NAME, null, contentValues);
    }

    public int getScoreForFactor(String owner, int factor_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + SCORES_TABLE_NAME +
                " where " + SCORES_COLUMN_OWNER + "=\"" + owner + "\"" +
                " and " + SCORES_COLUMN_FACTOR_ID + "=" + factor_id, null );
        results.moveToFirst();
        return results.getInt(results.getColumnIndex(SCORES_COLUMN_SCORE));
    }

    public long assignValueToFactorForChoice(int choice_id, int factor_id, int value_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " + FACTOR_TO_VALUE_TABLE_NAME +
                " where " + FACTOR_TO_VALUE_COLUMN_FACTOR_ID + "=" + factor_id +
                " and " + FACTOR_TO_VALUE_COLUMN_VALUE_ID + "=" + value_id, null );
        results.moveToFirst();
        int factor_to_value_id = results.getInt(results.getColumnIndex(FACTOR_TO_VALUE_COLUMN_ID));
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHOICE_TO_FACTOR_TO_VALUE_COLUMN_FACTOR_TO_VALUE_ID, factor_to_value_id);
        contentValues.put(CHOICE_TO_FACTOR_TO_VALUE_COLUMN_CHOICE_ID, choice_id);
        return db.insert(CHOICE_TO_FACTOR_TO_VALUE_TABLE_NAME, null, contentValues);
    }

    public List<Integer> getAllFactorToValuesForChoice(int choice_id) {
        ArrayList<Integer> factor_to_values = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " +
                FACTOR_TO_VALUE_TABLE_NAME + ", " + CHOICE_TO_FACTOR_TO_VALUE_TABLE_NAME +
                " where " + FACTOR_TO_VALUE_TABLE_NAME + "." + FACTOR_TO_VALUE_COLUMN_ID + "=" +
                CHOICE_TO_FACTOR_TO_VALUE_TABLE_NAME + "." + CHOICE_TO_FACTOR_TO_VALUE_COLUMN_FACTOR_TO_VALUE_ID +
                " and " + CHOICE_TO_FACTOR_TO_VALUE_TABLE_NAME + "." + CHOICE_TO_FACTOR_TO_VALUE_COLUMN_CHOICE_ID +
                "=" + choice_id, null );

        results.moveToFirst();

        while(results.isAfterLast() == false){
            factor_to_values.add(results.getInt(results.getColumnIndex(CHOICE_TO_FACTOR_TO_VALUE_COLUMN_FACTOR_TO_VALUE_ID)));
            results.moveToNext();
        }

        return factor_to_values;
    }

    public void removeFactorToValueForChoiceAndFactor(int choice_id, int factor_id) {
        Cursor factor_to_value_cursor = getFactorToValueCursorForChoiceAndFactor(choice_id, factor_id);
        if (factor_to_value_cursor.getCount() == 0) {
            return;
        }
        int factor_to_value_id = factor_to_value_cursor.getInt(
                factor_to_value_cursor.getColumnIndex(
                        CHOICE_TO_FACTOR_TO_VALUE_COLUMN_FACTOR_TO_VALUE_ID
                )
        );
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + CHOICE_TO_FACTOR_TO_VALUE_TABLE_NAME +
                " where " + CHOICE_TO_FACTOR_TO_VALUE_COLUMN_CHOICE_ID + "=" + choice_id +
                " and " + CHOICE_TO_FACTOR_TO_VALUE_COLUMN_FACTOR_TO_VALUE_ID + "=" + factor_to_value_id);
    }

    private Cursor getFactorToValueCursorForChoiceAndFactor(int choice_id, int factor_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery( "select * from " +
                FACTOR_TO_VALUE_TABLE_NAME + ", " + CHOICE_TO_FACTOR_TO_VALUE_TABLE_NAME +
                " where " + FACTOR_TO_VALUE_TABLE_NAME + "." + FACTOR_TO_VALUE_COLUMN_ID + "=" +
                CHOICE_TO_FACTOR_TO_VALUE_TABLE_NAME + "." + CHOICE_TO_FACTOR_TO_VALUE_COLUMN_FACTOR_TO_VALUE_ID +
                " and " + CHOICE_TO_FACTOR_TO_VALUE_TABLE_NAME + "." + CHOICE_TO_FACTOR_TO_VALUE_COLUMN_CHOICE_ID +
                "=" + choice_id +
                " and " + FACTOR_TO_VALUE_TABLE_NAME + "." + FACTOR_TO_VALUE_COLUMN_FACTOR_ID + "=" + factor_id, null );
        results.moveToFirst();
        return results;
    }

    public Value getValueForFactorAndChoice(int choice_id, int factor_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor factor_to_value_cursor = getFactorToValueCursorForChoiceAndFactor(choice_id, factor_id);
        if (!factor_to_value_cursor.moveToFirst()) {
            return null;
        }
        int value_id = factor_to_value_cursor.getInt(
                factor_to_value_cursor.getColumnIndex(
                        FACTOR_TO_VALUE_COLUMN_VALUE_ID
                )
        );
        Cursor results = db.rawQuery("select * from " + VALUES_TABLE_NAME +
                " where " + VALUES_COLUMN_ID + "=" + value_id, null);
        if (results.moveToLast()) {
            Value value = new Value(results);
            return value;
        }
        return null;
    }

    public long shareDecisionWithUser(int decision_id, String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SHARED_WITH_COLUMN_USER, user);
        contentValues.put(SHARED_WITH_COLUMN_DECISION_ID, decision_id);
        contentValues.put(SHARED_WITH_COLUMN_RESPONSE_MESSAGE, "");
        contentValues.put(SHARED_WITH_COLUMN_COMPLETED, false);
        return db.insert(SHARED_WITH_TABLE_NAME, null, contentValues);
    }

    public void unshareDecisionWithUser(int decision_id, String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + SHARED_WITH_TABLE_NAME +
                " where " + SHARED_WITH_COLUMN_DECISION_ID + "=\"" + decision_id + "\"" +
                " and " + SHARED_WITH_COLUMN_USER + "=\"" + user + "\"");
    }

    public void completeSharedDecision(int decision_id, String user, String message) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + SHARED_WITH_TABLE_NAME +
                " set " + SHARED_WITH_COLUMN_RESPONSE_MESSAGE + "=\"" + message + "\" , " +
                SHARED_WITH_COLUMN_COMPLETED + "=" + 1 +
                " where " + SHARED_WITH_COLUMN_DECISION_ID + "=\"" + decision_id + "\"" +
                " and " + SHARED_WITH_COLUMN_USER + "=\"" + user + "\"");
    }

    public String getResponseMessageForSharedDecision(int decision_id, String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.rawQuery("select * from " + SHARED_WITH_TABLE_NAME +
                " where " + SHARED_WITH_COLUMN_DECISION_ID + "=\"" + decision_id + "\"" +
                " and " + SHARED_WITH_COLUMN_USER + "=\"" + user + "\"", null);
        results.moveToFirst();
        return results.getString(results.getColumnIndex(SHARED_WITH_COLUMN_RESPONSE_MESSAGE));
    }

    public Choice getTopChoiceForDecision(int decision_id) {
        Decision decision = this.getDecisionByID(decision_id);
        List<Choice> choices = this.getAllChoicesForDecision(decision_id);
        List<Factor> factors = this.getAllFactorsForDecision(decision_id);
        int factor_score_sum = 0;
        for (Factor f : factors) {
            f.score = this.getScoreForFactor(decision.owner, f.id);
            factor_score_sum += f.score;
        }

        for(Choice c : choices) {
            c.score = 0;
            for (Factor f : factors) {
                Value v = this.getValueForFactorAndChoice(c.id, f.id);
                double v_score = this.getScoreForValue(decision.owner, v.id);
                c.score += v_score * f.score / factor_score_sum;
            }
        }

        Choice top_choice = null;
        for (Choice c : choices) {
            if (top_choice == null || c.score > top_choice.score) {
                top_choice = c;
            }
        }
        return top_choice;
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
    }

    public class Choice {
        public int id;
        public String name;
        public int decision_id;
        public double score;

        public Choice(Cursor row) {
            this.id = row.getInt(row.getColumnIndex(CHOICES_COLUMN_ID));
            this.name = row.getString(row.getColumnIndex(CHOICES_COLUMN_NAME));
            this.decision_id = row.getInt(row.getColumnIndex(CHOICES_COLUMN_DECISION_ID));
        }
    }

    public class Factor {
        public int id;
        public String name;
        public int decision_id;
        public double score;

        public Factor(Cursor row) {
            this.id = row.getInt(row.getColumnIndex(FACTORS_COLUMN_ID));
            this.name = row.getString(row.getColumnIndex(FACTORS_COLUMN_NAME));
            this.decision_id = row.getInt(row.getColumnIndex(FACTORS_COLUMN_DECISION_ID));
        }
    }

    public class Value {
        public int id;
        public String name;

        public Value(Cursor row) {
            this.id = row.getInt(row.getColumnIndex(VALUES_COLUMN_ID));
            this.name = row.getString(row.getColumnIndex(VALUES_COLUMN_NAME));
        }
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
    }

}

