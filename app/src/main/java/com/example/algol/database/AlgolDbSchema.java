package com.example.algol.database;

/**
 * Created by Сергей Пинкевич on 16.11.2016.
 */

public class AlgolDbSchema {
    public static final class MainMenuTable {
        public static final String NAME = "menu_items";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String CATEGORY = "category";
            public static final String DESCRIPTION = "description";
            public static final String COMPLEXITY = "complexity";
        }
    }
}
