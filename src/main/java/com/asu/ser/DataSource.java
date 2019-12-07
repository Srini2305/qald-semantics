package com.asu.ser;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.FileManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static String defaultNameSpace = "http://ser.asu.com/semantics/qald/#";

    static List<String> executeQuery(String queryRequest, List<String> resultArgs, Model model){
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append(SparQLQuery.RDF_PREFIX);
        queryStringBuilder.append(SparQLQuery.RDFS_PREFIX);
        queryStringBuilder.append(queryRequest);
        Query query = QueryFactory.create(queryStringBuilder.toString());
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
        ResultSet resultSet = queryExecution.execSelect();
        List<String> result = new ArrayList<String>();
        while( resultSet.hasNext())
        {
            QuerySolution querySolution = resultSet.nextSolution();
            for (String str: resultArgs) {
                RDFNode rdfNode = querySolution.get(str);
                if(rdfNode != null){
                    result.add(rdfNode.toString());
                }
            }
        }
        queryExecution.close();
        return result;
    }

    private Model loadRDFData(String filename) {
        Model model = ModelFactory.createOntologyModel();
        try {
            InputStream inputStream = FileManager.get().open(filename);
            model.read(inputStream,defaultNameSpace);
            inputStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return model;
    }

    public static String constructCountryQuery(List<String> resultArgs, String keyword){
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SparQLQuery.SELECT_DISTINCT_QUERY);
        String resultParams = constructResultArgumentString(resultArgs);
        queryBuilder.append(resultParams);
        queryBuilder.append(SparQLQuery.SERVICE_START);
        queryBuilder.append(SparQLQuery.COUNTRY_QUERY_SERVICE_NAME);
        queryBuilder.append(SparQLQuery.SERVICE_END);
        queryBuilder.append(SparQLQuery.COUNTRY_QUERY_COUNTRY_NAME_COL);
        queryBuilder.append(SparQLQuery.COUNTRY_QUERY_CAPITAL_COL);
        queryBuilder.append(SparQLQuery.COUNTRY_QUERY_CONTINENT_COL);
        queryBuilder.append(SparQLQuery.COUNTRY_QUERY_ISO2_COL);
        queryBuilder.append(SparQLQuery.COUNTRY_QUERY_CURRENCY_COL);
        String filterQuery = constructFilters(resultArgs, keyword, true);
        queryBuilder.append(filterQuery);
        queryBuilder.append(SparQLQuery.QUERY_END);
        return queryBuilder.toString();
    }

    public static String constructRestaurantQuery(List<String> resultArgs, String keyword){
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SparQLQuery.SELECT_DISTINCT_QUERY);
        String resultParams = constructResultArgumentString(resultArgs);
        queryBuilder.append(resultParams);
        queryBuilder.append(SparQLQuery.SERVICE_START);
        queryBuilder.append(SparQLQuery.COUNTRY_QUERY_SERVICE_NAME);
        queryBuilder.append(SparQLQuery.SERVICE_END);
        queryBuilder.append(SparQLQuery.RESTAURANT_NAME_COL);
        queryBuilder.append(SparQLQuery.RESTAURANT_ADDRESS_COL);
        queryBuilder.append(SparQLQuery.RESTAURANT_LOCALITY_COL);
        queryBuilder.append(SparQLQuery.RESTAURANT_CUISINE_COL);
        queryBuilder.append(SparQLQuery.RESTAURANT_PRICE_COL);
        queryBuilder.append(SparQLQuery.RESTAURANT_TABLE_BOOKING_COL);
        queryBuilder.append(SparQLQuery.RESTAURANT_ONLINE_DELIVERY_COL);
        queryBuilder.append(SparQLQuery.RESTAURANT_RATING_COL);
        String filterQuery = constructFilters(resultArgs, keyword, false);
        queryBuilder.append(filterQuery);
        queryBuilder.append(SparQLQuery.QUERY_END);
        return queryBuilder.toString();
    }

    public static String constructFootballPlayerQuery(List<String> resultArgs, String keyword){
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SparQLQuery.SELECT_DISTINCT_QUERY);
        String resultParams = constructResultArgumentString(resultArgs);
        queryBuilder.append(resultParams);
        queryBuilder.append(SparQLQuery.SERVICE_START);
        queryBuilder.append(SparQLQuery.COUNTRY_QUERY_SERVICE_NAME);
        queryBuilder.append(SparQLQuery.SERVICE_END);
        queryBuilder.append(SparQLQuery.PLAYER_NAME_COL);
        queryBuilder.append(SparQLQuery.PLAYER_COUNTRY_COL);
        queryBuilder.append(SparQLQuery.PLAYER_CLUB_COL);
        queryBuilder.append(SparQLQuery.PLAYER_CLUB_POSITION_COL);
        queryBuilder.append(SparQLQuery.PLAYER_RATING_COL);
        queryBuilder.append(SparQLQuery.PLAYER_HEIGHT_COL);
        queryBuilder.append(SparQLQuery.PLAYER_WEIGHT_COL);
        queryBuilder.append(SparQLQuery.PLAYER_AGE_COL);
        String filterQuery = constructFilters(resultArgs, keyword, true);
        queryBuilder.append(filterQuery);
        queryBuilder.append(SparQLQuery.QUERY_END);
        return queryBuilder.toString();
    }

    private static String constructResultArgumentString(List<String> resultArgs) {
        StringBuilder resultArgString = new StringBuilder();
        for (String str: resultArgs) {
            resultArgString.append(str).append(" ");
        }
        return resultArgString.toString();
    }

    private static String constructFilters(List<String> resultArgs, String keyword, boolean flag) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SparQLQuery.FILTER_START);
        if(flag)
            queryBuilder.append("?Country");
        else
            queryBuilder.append("?Address");
        queryBuilder.append(SparQLQuery.FILTER_PART1);
        queryBuilder.append("\"").append(keyword).append("\"");
        for (int i = 0; i < resultArgs.size(); i++) {
            queryBuilder.append(SparQLQuery.FILTER_PART2);
            queryBuilder.append(resultArgs.get(i));
            queryBuilder.append(SparQLQuery.FILTER_PART1);
            queryBuilder.append("\"").append(keyword).append("\"");
        }
        queryBuilder.append(SparQLQuery.FILTER_END);
        return queryBuilder.toString();
    }

    public static void main(String[] args){
        DataSource dataSource = new DataSource();
        Model model = ModelFactory.createOntologyModel();
        List<String> resultArg = new ArrayList<String>();
        String keyword = "\"india\"";
        String query = constructCountryQuery(resultArg, keyword);
        dataSource.executeQuery(query, resultArg, model);
        System.out.println("Restaurant!!!");
        //resultArg = "?RestaurantName ?Address ?Locality ?Cuisine ?Price ?TableBooking ?OnlineDelivery ?Rating".split(" ");
        query = constructRestaurantQuery(resultArg, keyword);
        dataSource.executeQuery(query, resultArg, model);
        System.out.println("Football Player!!!");
        //resultArg = "?PlayerName ?PlayerCountry ?Club ?ClubPosition ?PlayerRating ?PlayerHeight ?PlayerWeight ?PlayerAge".split(" ");
        query = constructFootballPlayerQuery(resultArg, keyword);
        dataSource.executeQuery(query, resultArg, model);
    }

}
