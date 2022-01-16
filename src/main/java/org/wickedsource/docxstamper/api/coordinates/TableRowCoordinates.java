package org.wickedsource.docxstamper.api.coordinates;

import org.docx4j.wml.Tr;

public class TableRowCoordinates extends AbstractCoordinates {

    private final Tr row;

    private final int index;

    private final TableCoordinates parentTableCoordinates;


    public TableRowCoordinates(Tr row, int index, TableCoordinates parentTableCoordinates) {
        this.row = row;
        this.index = index;
        this.parentTableCoordinates = parentTableCoordinates;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        String toString = String.format("paragraph at index %d", index);
        return parentTableCoordinates.toString() + "\n" + toString;
    }

    public Tr getRow() {
        return row;
    }

    public TableCoordinates getParentTableCoordinates() {
        return parentTableCoordinates;
    }

}
