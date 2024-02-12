package com.example.studentsinfo;

public class Students {
//    private int imageProf;
    private String stuname_ID, stuDesc;//username,userDesc

    public Students(String usernameID, String userDESCC) {

        this.stuname_ID = usernameID;
        this.stuDesc = userDESCC;
    }

//    public int getImageProf() {
//        return imageProf;
//    }
//
//    public void setImageProf(int imageProf) {
//        this.imageProf = imageProf;
//    }

    public String getUserName() {
        return stuname_ID;
    }

    public void setStuname_ID(String usernamee) {
        this.stuname_ID = usernamee;
    }

    public String getUserDes() {
        return stuDesc;

    }
}
