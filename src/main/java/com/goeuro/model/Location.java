package com.goeuro.model;

/**
 * Created by sujay on 23/01/16.
 */
public class Location {
    private static final String COMMA = ",";

    private final long id;

    private final String key;

    private final String name;

    private final String fullName;

    private final String iataAirportCode;

    private final String type;

    private final String country;

    private final String countryCode;

    private final GeoPosition geoPosition;

    private final long locationId;

    private final boolean inEurope;

    private final boolean coreCountry;

    private final int distance;

    public long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getIataAirportCode() {
        return iataAirportCode;
    }

    public String getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public long getLocationId() {
        return locationId;
    }

    public boolean isInEurope() {
        return inEurope;
    }

    public boolean isCoreCountry() {
        return coreCountry;
    }

    public int getDistance() {
        return distance;
    }

    private static class GeoPosition {
        double latitude;

        double longitude;

        GeoPosition(final double latitude, final double longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    public static class Builder {

        private final long id;

        private String key;

        private String name;

        private String fullName;

        private String iataAirportCode;

        private String type;

        private String country;

        private String countryCode;

        private GeoPosition geoPosition;

        private long locationId;

        private boolean inEurope;

        private boolean coreCountry;

        private int distance;

        public Builder(long id){
            this.id = id;
            this.locationId = id;

        }

        public Builder key(final String val){
            key = val;
            return this;
        }

        public Builder name(final String val){
            name = val;
            return  this;
        }

        public Builder fullName(final String val){
            fullName = val;
            return this;
        }

        public Builder iataAirportCode(final String val){
            iataAirportCode = val;
            return this;
        }

        public Builder type(final String val){
            type = val;
            return this;
        }

        public Builder country(final String val){
            country = val;
            return this;
        }

        public Builder countryCode(final String val){
            countryCode = val;
            return this;
        }

        public Builder inEurope(final boolean val){
            inEurope = val;
            return this;
        }

        public Builder coreCountry(final boolean val){
            coreCountry = val;
            return this;
        }

        public Builder distance(final int val){
            distance = val;
            return this;
        }

        public Builder geoPosition(final double lat, final double lon){
            geoPosition = new GeoPosition(lat, lon);
            return this;
        }

        public Location build(){
            return new Location(this);
        }
    }

    private Location(Builder builder){
        id = builder.id;
        locationId = builder.locationId;
        key = builder.key;
        name = builder.name;
        fullName = builder.fullName;
        iataAirportCode = builder.iataAirportCode;
        type = builder.type;
        country = builder.country;
        coreCountry = builder.coreCountry;
        countryCode = builder.countryCode;
        geoPosition = builder.geoPosition;
        inEurope = builder.inEurope;
        distance = builder.distance;
    }

    public String getCommaSeparatedProperties(){
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append(COMMA)
                .append(getLocationId()).append(COMMA)
                .append(applyQuote(getName())).append(COMMA)
                .append(applyQuote(getFullName())).append(COMMA)
                .append(applyQuote(getCountry())).append(COMMA)
                .append(applyQuote(getCountryCode())).append(COMMA)
                .append(applyQuote(getIataAirportCode())).append(COMMA)
                .append(applyQuote(getKey())).append(COMMA)
                .append(applyQuote(getType())).append(COMMA)
                .append(getDistance()).append(COMMA)
                .append(getGeoPosition().latitude).append(COMMA)
                .append(getGeoPosition().longitude).append(COMMA)
                .append(isInEurope()).append(COMMA)
                .append(isCoreCountry());
        return sb.toString();
    }

    public String applyQuote(final String property){
        if(property.contains(",")){
            return "\"" + property + "\"";
        }else {
            return property;
        }
    }
}
