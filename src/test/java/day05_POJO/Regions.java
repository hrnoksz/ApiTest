package day05_POJO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Regions {
    private ArrayList<Item> items;
    private boolean hasMore;
    private int limit;
    private int offset;
    private int count;
    private ArrayList<Link> links;
}
