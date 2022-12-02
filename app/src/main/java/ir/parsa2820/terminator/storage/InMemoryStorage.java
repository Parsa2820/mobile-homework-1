package ir.parsa2820.terminator.storage;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

import ir.parsa2820.terminator.ContextProvider;
import ir.parsa2820.terminator.model.Agenda;
import ir.parsa2820.terminator.model.Course;
import ir.parsa2820.terminator.model.Department;

public class InMemoryStorage{
    private static InMemoryStorage instance;
    private ArrayList<Department> departments;
    private ArrayList<Course> courses;
    private AgendaDbHelper agendaDbHelper;

    private InMemoryStorage() {
    }

    public static InMemoryStorage getInstance() {
        if (instance == null) {
            instance = new InMemoryStorage();
        }
        return instance;
    }

    public void setContext(Context context) {
        agendaDbHelper = new AgendaDbHelper(context);
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
        this.courses = new ArrayList<>();
        for (Department d : departments) {
            this.courses.addAll(Arrays.asList(d.getCourses()));
        }
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Course getCourse(String courseId) {
        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                return c;
            }
        }
        return null;
    }

    public void createAgenda(String name) {
        this.addToAgenda(name, "");
    }

    public void addToAgenda(String name, String courseId) {
        SQLiteDatabase db = agendaDbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + AgendaContract.AgendaEntry.TABLE_NAME + " (" +
                AgendaContract.AgendaEntry.COLUMN_NAME_NAME + ", " +
                AgendaContract.AgendaEntry.COLUMN_NAME_COURSE_ID + ") VALUES ('" + name + "', '" + courseId + "')");
    }

    public void deleteFromAgenda(String name, String courseId) {
        SQLiteDatabase db = agendaDbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + AgendaContract.AgendaEntry.TABLE_NAME + " WHERE " +
                AgendaContract.AgendaEntry.COLUMN_NAME_NAME + " = '" + name + "' AND " +
                AgendaContract.AgendaEntry.COLUMN_NAME_COURSE_ID + " = '" + courseId + "'");
    }

    public Agenda getAgenda(String name) {
        SQLiteDatabase db = agendaDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + AgendaContract.AgendaEntry.TABLE_NAME + " WHERE " +
                AgendaContract.AgendaEntry.COLUMN_NAME_NAME + " = '" + name + "'", null);
        if (cursor.getCount() == 0) {
            return null;
        }
        ArrayList<Course> courses = new ArrayList<>();
        while (cursor.moveToNext()) {
            int courseIdIndex = cursor.getColumnIndex(AgendaContract.AgendaEntry.COLUMN_NAME_COURSE_ID);
            String courseId = cursor.getString(courseIdIndex);
            if (!courseId.equals("")) {
                courses.add(getCourse(courseId));
            }
        }
        cursor.close();
        return new Agenda(name, courses);
    }

    public ArrayList<Agenda> getAgendas() {
        SQLiteDatabase db = agendaDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + AgendaContract.AgendaEntry.TABLE_NAME, null);
        ArrayList<Agenda> agendas = new ArrayList<>();
        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(AgendaContract.AgendaEntry.COLUMN_NAME_NAME);
            String name = cursor.getString(nameIndex);
            if (getAgenda(name) != null) {
                agendas.add(getAgenda(name));
            }
        }
        cursor.close();
        return agendas;
    }

    public ArrayList<String> getAgendaNames() {
        SQLiteDatabase db = agendaDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + AgendaContract.AgendaEntry.COLUMN_NAME_NAME + " FROM " + AgendaContract.AgendaEntry.TABLE_NAME, null);
        ArrayList<String> names = new ArrayList<>();
        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(AgendaContract.AgendaEntry.COLUMN_NAME_NAME);
            String name = cursor.getString(nameIndex);
            names.add(name);
        }
        cursor.close();
        return names;
    }
}
