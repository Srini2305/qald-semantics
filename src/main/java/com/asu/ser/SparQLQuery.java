package com.asu.ser;

public class SparQLQuery {

    static final String RDF_PREFIX = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
    static final String RDFS_PREFIX = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> ";
    static final String SAMPLE_QUERY = "SELECT ?s ?p ?a { " +
                    "SERVICE <http://ec2-18-218-38-8.us-east-2.compute.amazonaws.com:3030/semantics/sparql> " +
                    "     { ?s ?p ?a }" +
                    "}";
    static final String COUNTRY_QUERY = "SELECT DISTINCT" +
            "?CountryName ?Capital ?Continent\n" +
            " { \n" +
            "    SERVICE <"+
            "http://ec2-18-218-38-8.us-east-2.compute.amazonaws.com:3030/semantics/sparql" +
            "> {\n" +
            "          ?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-10#name>  ?CountryName.\n" +
            "          ?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-10#capital>  ?Capital.\n" +
            "          ?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-10#continent_name>  ?Continent.\n" +
            "          FILTER ( (regex(lcase(str(?CountryName)), \"india\"))\n" +
            "                    || (regex(lcase(str(?Capital)), \"india\"))\n" +
            "          )\n" +
            "    }\n" +
            "}";

    static final String SELECT_DISTINCT_QUERY = "SELECT DISTINCT ";
    static final String SERVICE_START = "{ SERVICE <";
    static final String SERVICE_END = "> {";
    static final String COUNTRY_QUERY_RESULT = "?CountryName ?Capital ?Continent ";
    static final String COUNTRY_QUERY_SERVICE_NAME = "http://ec2-18-218-38-8.us-east-2.compute.amazonaws.com:3030/semantics/sparql";
    static final String COUNTRY_QUERY_COUNTRY_NAME_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-10#name>  ?Country. ";
    static final String COUNTRY_QUERY_CAPITAL_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-10#capital>  ?Capital. ";
    static final String COUNTRY_QUERY_CONTINENT_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-10#continent_name>  ?Continent. ";
    static final String COUNTRY_QUERY_ISO2_COL = "?subject <http://127.0.0.1:3333/code>  ?ISO2. ";
    static final String COUNTRY_QUERY_CURRENCY_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-10#currency>  ?Currency. ";
    static final String FILTER_START = "FILTER ( (regex(lcase(str(";
    static final String FILTER_PART1 = ")), ";
    static final String FILTER_PART2 = ")) || (regex(lcase(str(";
    static final String FILTER_END = ")) ) ";
    static final String QUERY_END = " } } ";

    static final String RESTAURANT_NAME_COL = "?subject <http://www.semanticweb.org/srini/ontologies/2019/10/untitled-ontology-9#name>  ?RestaurantName. ";
    static final String RESTAURANT_ADDRESS_COL = "?subject <http://www.semanticweb.org/srini/ontologies/2019/10/untitled-ontology-9#address>  ?Address. ";
    static final String RESTAURANT_LOCALITY_COL = "?subject <http://www.semanticweb.org/srini/ontologies/2019/10/untitled-ontology-9#locality>  ?Locality. ";
    static final String RESTAURANT_CUISINE_COL = "?subject <http://www.semanticweb.org/srini/ontologies/2019/10/untitled-ontology-9#cuisine>  ?Cuisine. ";
    static final String RESTAURANT_PRICE_COL = "?subject <http://www.semanticweb.org/srini/ontologies/2019/10/untitled-ontology-9#price>  ?Price. ";
    static final String RESTAURANT_TABLE_BOOKING_COL = "?subject <http://www.semanticweb.org/srini/ontologies/2019/10/untitled-ontology-9#tableBooking>  ?TableBooking. ";
    static final String RESTAURANT_ONLINE_DELIVERY_COL = "?subject <http://www.semanticweb.org/srini/ontologies/2019/10/untitled-ontology-9#onlineDelivery>  ?OnlineDelivery. ";
    static final String RESTAURANT_RATING_COL = "?subject <http://www.semanticweb.org/srini/ontologies/2019/10/untitled-ontology-9#rating>  ?Rating. ";

    static final String PLAYER_NAME_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-18#player_name>  ?PlayerName. ";
    static final String PLAYER_COUNTRY_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-18#player_country>  ?Country. ";
    static final String PLAYER_CLUB_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-18#player_club>  ?Club. ";
    static final String PLAYER_CLUB_POSITION_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-18#player_club_position>  ?ClubPosition. ";
    static final String PLAYER_RATING_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-18#player_rating>  ?PlayerRating. ";
    static final String PLAYER_HEIGHT_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-18#player_height>  ?PlayerHeight.";
    static final String PLAYER_WEIGHT_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-18#player_weight>  ?PlayerWeight.";
    static final String PLAYER_AGE_COL = "?subject <http://www.semanticweb.org/14802/ontologies/2019/10/untitled-ontology-18#player_age>  ?PlayerAge. ";

}
