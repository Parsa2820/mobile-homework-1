package ir.parsa2820.terminator.storage;

public class AgendaContract {

    private AgendaContract() {}

    public static class AgendaEntry {
        public static final String TABLE_NAME = "agenda";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_COURSE_ID = "course_id";
    }
}
