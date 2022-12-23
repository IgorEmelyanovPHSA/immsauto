package Constansts;

public class Header {
    public static final String SUPPLY_LOCATION_NAME = "Supply Location Name";
    public static final String SUPPLY_CONTAINER_NAME = "Supply Container Name";
    public static final String SUPPLY_DISTRIBUTION_DESCRIPTION = "Supply Distribution Description";
    private static final String SORT_BY = "Sort by:\n";
    private static final String SORT = "Sort\n";
    private static final String SORT_NONE = "\nSorted: None";
    public static final String SUPPLY_CONTAINER_NAME_ = SORT_BY + SUPPLY_CONTAINER_NAME + SORT_NONE;
    public static final String REMAINING_DOSES = SORT_BY + "Remaining Doses" + SORT_NONE;
    public static final String REMAINING_QUANTITY = SORT_BY + "Remaining Quantity" + SORT_NONE;
    public static final String SUPPLY_DISTRIBUTION_DESCRIPTION_ = SORT_BY + SUPPLY_DISTRIBUTION_DESCRIPTION + SORT_NONE;
    private static final String SHOW_ACTION = "\nShow actions";
    public static final String SUPPLY_ITEM_NAME = SORT_BY + "Supply Item Name" + SORT_NONE + SHOW_ACTION;
    private static final String COLUMN_ACTION = "\nColumn Actions";
    public static final String SUPPLY_LOCATION_NAME_ = SORT + SUPPLY_LOCATION_NAME + COLUMN_ACTION;


}
