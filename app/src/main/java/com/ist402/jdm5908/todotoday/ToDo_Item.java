package com.ist402.jdm5908.todotoday;

public class ToDo_Item {

    // Member Attributes
    private int _id;
    private String description;
    private int is_done;

    public ToDo_Item() {
    }

    public ToDo_Item(int id, String desc, int done) {

        _id = id;
        description = desc;
        is_done = done;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIs_done() {
        return is_done;
    }

    public void setIs_done(int is_done) {
        this.is_done = is_done;
    }


}
