package ir.parsa2820.terminator.storage;

import java.util.ArrayList;

import ir.parsa2820.terminator.model.Department;

public class InMemoryStorage {
    private static InMemoryStorage instance;
    private ArrayList<Department> departments;

    private InMemoryStorage() {
    }

    public static InMemoryStorage getInstance() {
        if (instance == null) {
            instance = new InMemoryStorage();
        }
        return instance;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }
}
