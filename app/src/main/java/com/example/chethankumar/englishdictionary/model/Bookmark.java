package com.example.chethankumar.englishdictionary.model;

/**
 * Created by Suneesh on 2/17/2017.
 */
public class Bookmark {
    private int _id;
    private String _name;
    private String _meaning;

    public Bookmark() {

    }

    public Bookmark(int id, String name, String meaning) {
        _id = id;
        _name = name;
        _meaning = meaning;
    }
    public Bookmark(String name, String meaning) {
        _name = name;
        _meaning = meaning;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_meaning() {
        return _meaning;
    }

    public void set_meaning(String _meaning) {
        this._meaning = _meaning;
    }
}
