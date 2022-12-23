package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;


public class GenericTable {
    private final WebElement webElement;

    public GenericTable(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElement getWrappedElement() {
        return webElement;
    }

    /**
     * Prints all rows with headings.
     */
    public void readRowsWithHeading() {
        for (Map<String, WebElement> row : getRowsMappedToHeadings()) {
            row.forEach((k, v) -> System.out.println("Heading : " + k + System.lineSeparator() + "Value : " + v.getText()));
            System.out.println("next item");
        }
    }
    /**
     * Returns a single cell WebElement
     *
     * @return WebElement
     */
    public WebElement getCellElement(Map<String, String> searchCriteria) {
        WebElement element = null;
        for (Map<String, WebElement> row : getRowsMappedToHeadings()) {
            for (String key : searchCriteria.keySet()) {
                if (row.containsKey(key) && row.get(key).getText().contains(searchCriteria.get(key))) {
                    element = row.get(key);
                }
            }
        }
        return element;
    }

    /**
     * Returns a single row with heading elements.
     *
     * @return List with table heading elements.
     */
    public Map<String, WebElement> getMappedRow(Map<String, String> searchCriteria) {
        Map<String, WebElement> map1 = new HashMap<>();
        for (Map<String, WebElement> row : getRowsMappedToHeadings()) {
            //ToDo need to find a better way to validate map if its contains multiple entries, currently only capable of working with map of 2 entries
            if (searchCriteria.keySet().size() > 1) {
                if (validateRowContainsMapIndexes(row, searchCriteria, 0) && validateRowContainsMapIndexes(row, searchCriteria, 1)) {
                    map1.putAll(row);
                }
            } else {
                if (validateRowContainsMapIndexes(row, searchCriteria, 0)) {
                    map1.putAll(row);
                }
            }
        }
        if (map1.isEmpty()) {
            throw new AssertionError("Item with that name not found");
        }
        return map1;
    }

    private boolean validateRowContainsMapIndexes(Map<String, WebElement> row, Map<String, String> searchCriteria, int index) {
        List<String> stringsList = new ArrayList<>(searchCriteria.keySet());
        return row.containsKey(stringsList.get(index)) && row.get(stringsList.get(index)).getText().contains(searchCriteria.get(stringsList.get(index)));
    }

    /**
     * Returns a list of table heading elements.
     * Multiple rows of heading elements, rows  are flattened
     * i.e. the second row, will follow the first
     *
     * @return List with table heading elements.
     */
    public List<WebElement> getHeadings() {
        return getWrappedElement().findElements(By.xpath(".//thead//th"));
    }

    /**
     * Returns text values of table heading elements
     *
     * @return List with text values of table heading elements.
     */
    public List<String> getHeadingsAsString() {
        List<String> list = new ArrayList<>();
        for (WebElement el : getHeadings()) {
            if (el.getText().isEmpty()) {
                list.add(el.getAttribute("title"));
            } else {
                list.add(el.getText());
            }

        }
        return list;
    }

    /**
     * Returns table cell elements ({@code <td>}), grouped by rows.
     *
     * @return List where each item is a table row.
     */
    public List<List<WebElement>> getRows() {
        return getWrappedElement()
                .findElements(By.xpath(".//tr"))
                .stream()
                .map(rowElement -> rowElement.findElements(By.xpath(".//td | .//th")))
                .filter(row -> row.size() > 0) // ignore rows with no <td> tags
                .collect(toList());
    }

    /**
     * Returns text values of table cell elements ({@code <td>}), grouped by rows.
     *
     * @return List where each item is text values of a table row.
     */
    public List<List<String>> getRowsAsString() {
        return getRows().stream()
                .map(row -> row.stream()
                        .map(WebElement::getText)
                        .collect(toList()))
                .collect(toList());
    }

    /**
     * Returns table cell elements ({@code <td>}), grouped by columns.
     *
     * @return List where each item is a table column.
     */
    public List<List<WebElement>> getColumns() {
        List<List<WebElement>> columns = new ArrayList<>();
        List<List<WebElement>> rows = getRows();

        if (rows.isEmpty()) {
            return columns;
        }

        int columnCount = rows.get(0).size();
        for (int i = 0; i < columnCount; i++) {
            List<WebElement> column = new ArrayList<>();
            for (List<WebElement> row : rows) {
                column.add(row.get(i));
            }
            columns.add(column);
        }

        return columns;
    }

    /**
     * Returns table cell elements ({@code <td>}), of a particular column.
     *
     * @param index the 1-based index of the desired column
     * @return List where each item is a cell of a particular column.
     */
    public List<WebElement> getColumnByIndex(int index) {
        return getWrappedElement().findElements(
                By.cssSelector(String.format("tr > td:nth-of-type(%d)", index)));
    }

    /**
     * Returns text values of table cell elements ({@code <td>}), grouped by columns.
     *
     * @return List where each item is text values of a table column.
     */
    public List<List<String>> getColumnsAsString() {
        return getColumns().stream()
                .map(row -> row.stream()
                        .map(WebElement::getText)
                        .collect(toList()))
                .collect(toList());
    }

    /**
     * Returns table cell element ({@code <td>}), at i-th row and j-th column.
     *
     * @param i Row number
     * @param j Column number
     * @return Cell element at i-th row and j-th column.
     */
    public WebElement getCellAt(int i, int j) {
        return getRows().get(i).get(j);
    }

    /**
     * Returns list of maps where keys are table headings and values are table row elements ({@code <td>}).
     */
    public List<Map<String, WebElement>> getRowsMappedToHeadings() {
        List<String> headingsAsString = getHeadingsAsString();
        return getRows().stream()
                .map(row -> row.stream()
                        .collect(toMap(e -> headingsAsString.get(row.indexOf(e)), identity(), (v1, v2) -> v2)))//, (v1, v2)-> v2
                .collect(toList());
    }

    /**
     * Returns list of maps where keys are passed headings and values are table row elements ({@code <td>}),.
     *
     * @param headings List containing strings to be used as table headings.
     */
    public List<Map<String, WebElement>> getRowsMappedToHeadings(List<String> headings) {
        return getRowsMappedToHeadings().stream()
                .map(e -> e.entrySet().stream().filter(m -> headings.contains(m.getKey()))
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .collect(toList());
    }

    /**
     * Same as {@link #getRowsMappedToHeadings()} but retrieves text from row elements ({@code <td>}).
     */
    public List<Map<String, String>> getRowsAsStringMappedToHeadings() {
        return getRowsMappedToHeadings().stream()
                .map(m -> m.entrySet().stream()
                        .collect(toMap(Map.Entry::getKey, e -> e.getValue().getText())))
                .collect(toList());

    }

    /**
     * Same as {@link #getRowsMappedToHeadings(java.util.List)} but retrieves text from row elements ({@code <td>}).
     */
    public List<Map<String, String>> getRowsAsStringMappedToHeadings(List<String> headings) {
        return getRowsMappedToHeadings(headings).stream()
                .map(m -> m.entrySet().stream()
                        .collect(toMap(Map.Entry::getKey, e -> e.getValue().getText())))
                .collect(toList());
    }
}
