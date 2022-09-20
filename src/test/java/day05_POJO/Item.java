package day05_POJO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
@Getter
@Setter
@ToString
public class Item{
    private int region_id;
    private String region_name;
    private ArrayList<Link> links;
}
