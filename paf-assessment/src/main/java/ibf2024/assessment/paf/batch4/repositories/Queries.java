package ibf2024.assessment.paf.batch4.repositories;

public interface Queries {
    public static final String SQL_GET_BEER_STYLES_AND_COUNT="""
        SELECT 
            s.id,
            s.style_name,
            COUNT(*) AS beer_count
        FROM 
            brewery.styles s JOIN brewery.beers b
        ON
            s.id = b.style_id
        GROUP BY
            s.id,
            s.style_name
        ORDER BY
            beer_count DESC, s.style_name ASC
            """;
    
    public static final String SQL_GET_BEER_STYLE_LIST="""
        SELECT 
            s.id,
            s.style_name,
            be.id,
            be.brewery_id,
            be.name AS beer_name,
            be.descript AS description,
            br.name AS brewery_name
        FROM 
            brewery.styles s JOIN brewery.beers be JOIN brewery.breweries br
        ON
            s.id = be.style_id 
        AND
            be.brewery_id = br.id
        WHERE
            s.id = ?
        ORDER BY
            beer_name ASC
        """;

    public static final String SQL_GET_BEERS_FROM_BREWERY="""
        SELECT
            be.brewery_id,
            br.name,
            br.address1,
            br.address2,
            br.city,
            br.phone,
            br.website,
            br.descript AS description,
            be.name AS beer_name,
            be.descript AS beer_description
        FROM
            brewery.beers be JOIN brewery.breweries br
        ON
            be.brewery_id = br.id
        WHERE
            br.name = ?
        ORDER BY 
            beer_name ASC;
        """;
} 