package com.GMMPCI.backend;

import lombok.Data;

// Model class
@Data
public class ComplianceFormData {
    private String jobOrderNo;
    private NameOfDeceased nameOfDeceased;
    private String dateOfInterment;
    private String dayTime;
    private String location;
    private String remarks;
    private String joborderReleasedBy;
    private String nameDeceased;

    // Add other fields as needed
    
    @Data
    public static class NameOfDeceased {
        private String firstName;
        private String middleName;
        private String surname;

        //Manually defined getters

        public String getFirstName(){
            return firstName;
        }

        public String getMiddleName(){
            return middleName;
        }

        public String getSurname(){
            return middleName;
        }
    }
    
    // Getter for nameOfDeceased
    public NameOfDeceased getNameOfDeceased() {
        return nameOfDeceased;
    }

    public String getNameDeceased() {
        return nameDeceased;
    }

    public String getJobOrderNo() {
        return jobOrderNo;
    }

    public String getDateOfInterment() {
        return dateOfInterment;
    }

    public String getDayTime() {
        return dayTime;
    }

    public String getLocation() {
        return location;
    }

    public String getRemarks() {
        return remarks;
    }

    // Setters or a constructor for these fields might also be necessary
    
}
