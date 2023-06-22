package com.etb.eattrainbalance.persistence.entity.enam;

// defines the different roles available in the application
public enum Role {
    ADMIN,      // Represents an administrative user
    OPERATOR,   // Represents an operator user, who probably has more rights than a regular user, but less than an admin
    USER        // Represents a regular user with standard rights
}
