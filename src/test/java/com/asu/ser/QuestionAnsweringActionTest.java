package com.asu.ser;

import com.opensymphony.xwork2.Action;
import junit.framework.TestCase;

public class QuestionAnsweringActionTest extends TestCase {

    public void testCountryQuery(){
        QuestionAnsweringAction questionAnsweringAction = new QuestionAnsweringAction();
        questionAnsweringAction.setInput("what is capital of India");
        assertEquals(Action.SUCCESS, questionAnsweringAction.query());
        assertEquals("New Delhi", questionAnsweringAction.getMessage().trim());
    }

    public void testFootballQuery(){
        QuestionAnsweringAction questionAnsweringAction = new QuestionAnsweringAction();
        questionAnsweringAction.setInput("what is the height of football Player Jimmy Medranda");
        assertEquals(Action.SUCCESS, questionAnsweringAction.query());
        String[] result = questionAnsweringAction.getMessage().split(" ");
        assertEquals("172", result[0]);
    }

    public void testRestaurantQuery(){
        QuestionAnsweringAction questionAnsweringAction = new QuestionAnsweringAction();
        questionAnsweringAction.setInput("what is the cuisine of restaurant name Izakaya Kikufuji");
        assertEquals(Action.SUCCESS, questionAnsweringAction.query());
        String[] result = questionAnsweringAction.getMessage().split(" ");
        assertEquals("Japanese", result[0]);
    }
}
