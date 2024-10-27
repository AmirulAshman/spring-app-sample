package com.ashman.sample.api.switchgames;

import java.util.List;
import java.util.Map;

import lombok.Data;

/* Sample Data
 * {
        "id": 827,
        "name": "Hotel Transylvania 3: Monsters Overboard",
        "genre": [
            "Action"
        ],
        "developers": [
            "Torus Games"
        ],
        "publishers": [
            "NA: Outright Games",
            "PAL: Bandai Namco Entertainment"
        ],
        "releaseDates": {
            "Japan": "Unreleased",
            "NorthAmerica": "July 10, 2018",
            "Europe": "July 13, 2018",
            "Australia": "July 13, 2018"
        }
    }
 */

@Data
public class Response extends Object {
    private Integer id;
    private String name;
    private List<String> genre;
    private List<String> developers;
    private List<String> publishers;
    private Map<String,String> releaseDates;
}
