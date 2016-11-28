package com.example.cs465.decisionhelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;
import org.hamcrest.CoreMatchers;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private static final String test_owner = "test_owner$123";
    private static final String test_user = "test_user$123";
    private static final int test_decision_id = 0;
    private static final int test_factor_id = 0;
    private static final int test_value_id = 0;
    private static final int test_choice_id = 0;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.cs465.decisionhelper", appContext.getPackageName());
    }

    @Test
    public void testDatabaseCreation() throws Exception {
        Storage db = createdb();
        assertEquals(db.getDatabaseName(), db.DATABASE_NAME);
    }

    @Test
    public void testDecisionCreationAndDeletion() throws Exception {
        Storage db = createdb();
        db.removeAllDecisionsForOwner(test_owner);
        db.createDecision("decision1", test_owner);
        List<Storage.Decision> decisions = db.getAllDecisionsForOwner(test_owner);

        assertEquals(decisions.size(), 1);

        db.removeAllDecisionsForOwner(test_owner);
        decisions = db.getAllDecisionsForOwner(test_owner);

        assertEquals(decisions.size(), 0);
    }

    @Test
    public void testGetDecisionByID() throws Exception {
        Storage db = createdb();
        String name = "decision1";
        long id = db.createDecision(name, test_owner);
        Storage.Decision d = db.getDecisionByID((int)id);
        db.removeAllDecisionsForOwner(test_owner);

        assertNotNull(d);
        assertEquals(d.id, id);
        assertEquals(d.name, name);
        assertEquals(d.owner, test_owner);
    }

    @Test
    public void testChoiceCreationAndDeletion() throws Exception {
        Storage db = createdb();
        db.removeAllChoicesForDecision(test_decision_id);
        db.createChoice("choice1", test_decision_id);
        List<Storage.Choice> choices = db.getAllChoicesForDecision(test_decision_id);

        assertEquals(choices.size(), 1);

        db.removeAllChoicesForDecision(test_decision_id);
        choices = db.getAllChoicesForDecision(test_decision_id);

        assertEquals(choices.size(), 0);
    }

    @Test
    public void testGetChoiceByID() throws Exception {
        Storage db = createdb();
        String name = "choice1";
        long id = db.createChoice(name, test_decision_id);
        Storage.Choice c = db.getChoiceByID((int)id);
        db.removeAllChoicesForDecision(test_decision_id);

        assertNotNull(c);
        assertEquals(c.id, id);
        assertEquals(c.name, name);
        assertEquals(c.decision_id, test_decision_id);
    }

    @Test
    public void testFactorCreationAndDeletion() throws Exception {
        Storage db = createdb();
        db.removeAllFactorsForDecision(test_decision_id);
        db.createFactor("factor1", test_decision_id);
        List<Storage.Factor> factors = db.getAllFactorsForDecision(test_decision_id);

        assertEquals(factors.size(), 1);

        db.removeAllFactorsForDecision(test_decision_id);
        factors = db.getAllFactorsForDecision(test_decision_id);

        assertEquals(factors.size(), 0);
    }

    @Test
    public void testGetFactorByID() throws Exception {
        Storage db = createdb();
        String name = "factor1";
        long id = db.createFactor(name, test_decision_id);
        Storage.Factor f = db.getFactorByID((int)id);
        db.removeAllFactorsForDecision(test_decision_id);


        assertEquals(f.id, id);
        assertEquals(f.name, name);
        assertEquals(f.decision_id, test_decision_id);
    }

    @Test
    public void testValueCreationAndDeletion() throws Exception {
        Storage db = createdb();
        String name = "value1";
        db.removeAllValuesForFactor(test_factor_id);
        db.createValueForFactor(name, test_decision_id);
        List<Storage.Value> values = db.getAllValuesForFactor(test_factor_id);

        assertEquals(values.size(), 1);

        db.removeAllValuesForFactor(test_factor_id);
        values = db.getAllValuesForFactor(test_factor_id);

        assertEquals(values.size(), 0);
    }

    @Test
    public void testGetValueByID() throws Exception {
        Storage db = createdb();
        String name = "value1";
        long id = db.createValueForFactor(name, test_factor_id);
        Storage.Value v = db.getValueByID((int)id);
        db.removeAllValuesForFactor(test_factor_id);


        assertEquals(v.id, id);
        assertEquals(v.name, name);
    }

    @Test
    public void testAssignScoreToValue() throws Exception {
        Storage db = createdb();
        long id = db.assignScoreToValue(test_owner, 10, test_value_id);
        db.removeAllScoresForOwner(test_owner);

        assert(id > 0);
    }

    @Test
    public void testGetScoreForValue() throws Exception {
        Storage db = createdb();
        int test_score = 10;
        long id = db.assignScoreToValue(test_owner, test_score, test_value_id);
        int score = db.getScoreForValue(test_owner, test_value_id);
        db.removeAllScoresForOwner(test_owner);

        assertEquals(score, test_score);
    }

    @Test
    public void testAssignScoreToFactor() throws Exception {
        Storage db = createdb();
        long id = db.assignScoreToFactor(test_owner, 10, test_factor_id);
        db.removeAllScoresForOwner(test_owner);

        assert(id > 0);
    }

    @Test
    public void testGetScoreForFactor() throws Exception {
        Storage db = createdb();
        int test_score = 10;
        long id = db.assignScoreToFactor(test_owner, test_score, test_factor_id);
        int score = db.getScoreForFactor(test_owner, test_factor_id);
        db.removeAllScoresForOwner(test_owner);

        assertEquals(score, test_score);
    }

    @Test
    public void testAssignAndRemoveValueToFactorForChoice() throws Exception {
        Storage db = createdb();
        db.removeFactorToValueForChoiceAndFactor(test_choice_id, test_factor_id);
        db.removeAllValuesForFactor(test_factor_id);

        long value_id = db.createValueForFactor("hello", test_factor_id);
        long id = db.assignValueToFactorForChoice(test_choice_id, test_factor_id, (int)value_id);
        int one = db.getAllFactorToValuesForChoice(test_choice_id).size();
        db.removeFactorToValueForChoiceAndFactor(test_choice_id, test_factor_id);
        db.removeAllValuesForFactor(test_factor_id);
        int zero = db.getAllFactorToValuesForChoice(test_choice_id).size();

        assert(id > 0);
        assertEquals(one, 1);
        assertEquals(zero, 0);
    }

    @Test
    public void testGetValueToFactorForChoice() throws Exception {
        Storage db = createdb();
        String name = "value1";
        long value_id = db.createValueForFactor(name, test_factor_id);
        long id = db.assignValueToFactorForChoice(test_choice_id, test_factor_id, (int)value_id);
        Storage.Value v = db.getValueForFactorAndChoice(test_choice_id, test_factor_id);
        db.removeFactorToValueForChoiceAndFactor(test_choice_id, test_factor_id);
        db.removeAllValuesForFactor(test_factor_id);

        assertEquals(v.id, value_id);
        assertEquals(v.name, name);
    }

    @Test
    public void testShareAndUnshareDecisionWithUser() throws Exception {
        Storage db = createdb();
        long decision_id = db.createDecision("decision1", test_owner);
        long shared_id = db.shareDecisionWithUser((int)decision_id, test_user);
        int one = db.getAllDecisionsSharedWithUser(test_user).size();
        db.unshareDecisionWithUser((int)decision_id, test_user);
        db.removeAllDecisionsForOwner(test_owner);
        int zero = db.getAllDecisionsSharedWithUser(test_user).size();

        assert(shared_id > 0);
        assertEquals(one, 1);
        assertEquals(zero, 0);
    }

    @Test
    public void testCompleteSharedDecision() throws Exception {
        Storage db = createdb();
        String message = "test message";
        long decision_id = db.createDecision("decision1", test_owner);
        long shared_id = db.shareDecisionWithUser((int)decision_id, test_user);
        db.completeSharedDecision((int)decision_id, test_user, message);
        String shared_message = db.getResponseMessageForSharedDecision((int)decision_id, test_user);
        db.unshareDecisionWithUser((int)decision_id, test_user);
        db.removeAllDecisionsForOwner(test_owner);

        assertEquals(shared_message, message);
    }



    private Storage createdb() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Storage db = new Storage(appContext);
        return db;
    }
}