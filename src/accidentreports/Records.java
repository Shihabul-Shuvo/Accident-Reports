/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentreports;

/**
 *
 * @author User
 */
public class Records {

    private Integer id;
    private String upazilaname;
    private String date;
    private String type;
    private String place;
    private Integer injured;
    private Integer death;
    private String cause;
    
    // We used this constructor to show data in MyReports table
    public Records(Integer id, String upazilaname, String date, String type, String place, Integer injured, Integer death, String cause) {
        // Here all the variable are in same position as showReport() on DashboardController.java
        // at line: 301
        this.id = id;
        this.upazilaname = upazilaname;
        this.date = date;
        this.type = type;
        this.place = place;
        this.injured = injured;
        this.death = death;
        this.cause = cause;
    }

    public Integer getId() {
        return id;
    }
    
    public String getUpazilaname() {
        return upazilaname;
    }

    public String getdate(){
        return date;
    }
    
    public String getType() {
        return type;
    }

    public String GetPlace() {
        return place;
    }

    public Integer GetInjured() {
        return injured;
    }

    public Integer GetDeath() {
        return death;
    }

    public String getCause() {
        return cause;
    }

}

