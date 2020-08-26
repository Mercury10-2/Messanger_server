package msngr_server.msngr_server.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDto {

    private String name;
    private String email;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;
    private boolean verified;
    private String error;

    public UserDto() {
    }

    public UserDto(String name, String email, String gender, LocalDateTime lastVisit) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.lastVisit = lastVisit;
        this.verified = true;
        this.error = null;
    }

    public UserDto(String error) {
        this.name = null;
        this.email = null;
        this.gender = null;
        this.lastVisit = null;
        this.verified = false;
        this.error = error;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getLastVisit() {
        return this.lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public boolean getVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

}