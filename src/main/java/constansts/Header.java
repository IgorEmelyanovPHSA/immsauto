package constansts;

public class Header {
    public static final String SUPPLY_LOCATION_NAME = "Supply Location Name";
    public static final String SUPPLY_CONTAINER_NAME = "Supply Container Name";
    public static final String SUPPLY_TRANSACTION_NAME = "Supply Transaction Name";
    public static final String LOT_NUMBER = "Lot Number";
    public static final String SUPPLY_DISTRIBUTION_DESCRIPTION = "Supply Distribution Description";
    private static final String SORT_BY = "Sort by:\n";
    private static final String SORT = "Sort\n";
    private static final String SORT_NONE = "\nSorted: None";
    public static final String SUPPLY_CONTAINER_NAME_FULL = SORT_BY + SUPPLY_CONTAINER_NAME + SORT_NONE;
    public static final String REMAINING_DOSES_FULL = SORT_BY + "Remaining Doses" + SORT_NONE;
    public static final String REMAINING_QUANTITY_FULL = SORT_BY + "Remaining Quantity" + SORT_NONE;
    public static final String REMAINING_DOSES = "Remaining Doses";
    public static final String REMAINING_QUANTITY = "Remaining Quantity";
    public static final String SUPPLY_DISTRIBUTION_DESCRIPTION_FULL = SORT_BY + SUPPLY_DISTRIBUTION_DESCRIPTION + SORT_NONE;
    private static final String SHOW_ACTION = "\nShow actions";
    public static final String SUPPLY_ITEM_NAME = SORT_BY + "Supply Item Name" + SORT_NONE + SHOW_ACTION;
    private static final String COLUMN_ACTION = "\nColumn Actions";
    public static final String SUPPLY_LOCATION_NAME_FULL = SORT + SUPPLY_LOCATION_NAME + COLUMN_ACTION;
    public static final String SUPPLY_TRANSACTION_NAME_FULL = SORT_BY + SUPPLY_TRANSACTION_NAME + SORT_NONE + SHOW_ACTION;
    public static final String LOT_NUMBER_FULL = SORT_BY + "Lot Number" + SORT_NONE;
}
