package com.asu.ser;

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Conversion()
public class QuestionAnsweringAction {

    private String input;
    private String message;
    private List<String> queryResponse;

    public String query() {
        message = "";
        parseInput();
        return Action.SUCCESS;
    }

    private void parseInput() {
        String keyword = "";
        String dataset = "country";
        try {
            List<String> blacklistWords = getBlackListWords();
            if (input != null) {
                input = input.toLowerCase();
                if (input.contains("restaurant")) {
                    dataset = "restaurant";
                } else if (input.contains("football")) {
                    dataset = "football";
                }
                String[] wordArray = input.split(" ");
                List<String> parsedWords = new ArrayList<String>();
                int pos = 0;
                List<String> wordList = new ArrayList<String>();
                for (String word : wordArray) {
                    if (!blacklistWords.contains(word)) {
                        String parsedWord = mapWords(word, dataset);
                        if (parsedWord != null) {
                            parsedWords.add(parsedWord);
                            wordList.add(word);
                            pos++;
                        }
                    }
                }
                keyword = wordArray[wordArray.length - 1];
                String query = "";
                if (dataset.equalsIgnoreCase("restaurant")) {
                    query = DataSource.constructRestaurantQuery(parsedWords, keyword);
                } else if (dataset.equalsIgnoreCase("football")) {
                    query = DataSource.constructFootballPlayerQuery(parsedWords, keyword);
                } else {
                    query = DataSource.constructCountryQuery(parsedWords, keyword);
                }
                String query1 = query.replace("regex(", "");
                query1 = query1.replace(")), ", "))=");
                query1 = query1.replace(")) )", ") )");
                query1 = query1.replace(")) ||", ") ||");
                List<String> result = DataSource.executeQuery(query1, parsedWords, ModelFactory.createOntologyModel());
                if (result.isEmpty()) {
                    result = DataSource.executeQuery(query, parsedWords, ModelFactory.createOntologyModel());
                }
                queryResponse = result;
                for (int i = 0; i < result.size(); i++) {
                    message += result.get(i) + "  ";
                }
            }
        } catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }

    private String mapWords(String word, String dataset) {
        if(StringUtils.equalsIgnoreCase(dataset, "restaurant")){
            if(StringUtils.equalsIgnoreCase(word, "address")){
                return "?Address";
            } else if(StringUtils.equalsIgnoreCase(word, "name") || StringUtils.equalsIgnoreCase(word, "restaurantname")){
                return "?RestaurantName";
            } else if(StringUtils.equalsIgnoreCase(word, "locality")){
                return "?Locality";
            } else if(StringUtils.equalsIgnoreCase(word, "cuisine")){
                return "?Cuisine";
            } else if(StringUtils.equalsIgnoreCase(word, "price")){
                return "?Price";
            } else if(StringUtils.equalsIgnoreCase(word, "booking") || StringUtils.equalsIgnoreCase(word, "tablebooking")){
                return "?TableBooking";
            } else if(StringUtils.equalsIgnoreCase(word, "delivery") || StringUtils.equalsIgnoreCase(word, "onlinedelivery")){
                return "?OnlineDelivery";
            } else if(StringUtils.equalsIgnoreCase(word, "rating")){
                return "?Rating";
            } else if(StringUtils.equalsIgnoreCase(word, "country")){
                return "?Address";
            }
        } else if(StringUtils.equalsIgnoreCase(dataset, "football")){
            if(StringUtils.equalsIgnoreCase(word, "name") || StringUtils.equalsIgnoreCase(word, "player")){
                return "?PlayerName";
            } else if(StringUtils.equalsIgnoreCase(word, "club")){
                return "?Club";
            } else if(StringUtils.equalsIgnoreCase(word, "position")){
                return "?ClubPosition";
            } else if(StringUtils.equalsIgnoreCase(word, "rating")){
                return "?PlayerRating";
            } else if(StringUtils.equalsIgnoreCase(word, "height")){
                return "?PlayerHeight";
            } else if(StringUtils.equalsIgnoreCase(word, "weight")){
                return "?PlayerWeight";
            } else if(StringUtils.equalsIgnoreCase(word, "age")){
                return "?PlayerAge";
            } else if(StringUtils.equalsIgnoreCase(word, "country")){
                return "?PlayerCountry";
            }
        } else{
            if(StringUtils.equalsIgnoreCase(word, "capital")){
                return "?Capital";
            } else if(StringUtils.equalsIgnoreCase(word, "continent")){
                return "?Continent";
            } else if(StringUtils.equalsIgnoreCase(word, "currency")){
                return "?Currency";
            } else if(StringUtils.equalsIgnoreCase(word, "iso") || StringUtils.equalsIgnoreCase(word, "iso2")){
                return "?ISO2";
            } else if(StringUtils.equalsIgnoreCase(word, "country")){
                return "?CountryName";
            }
        }
        return null;
    }

    private List<String> getBlackListWords() {
        List<String> blacklistWords  =new ArrayList<String>();
        blacklistWords.add("what");
        blacklistWords.add("is");
        blacklistWords.add("a");
        blacklistWords.add("the");
        blacklistWords.add("are");
        blacklistWords.add("was");
        blacklistWords.add("were");
        blacklistWords.add("that");
        blacklistWords.add("this");
        blacklistWords.add("and");
        blacklistWords.add("about");
        blacklistWords.add("to");
        blacklistWords.add("as");
        blacklistWords.add("of");
        return blacklistWords;
    }

    private String constructResultArgument(String s) {
        return null;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getQueryResponse() {
        return queryResponse;
    }

    public void setQueryResponse(List<String> queryResponse) {
        this.queryResponse = queryResponse;
    }
}
