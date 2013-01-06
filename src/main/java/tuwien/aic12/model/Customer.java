package tuwien.aic12.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bisanov
 */
@Entity(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "registred")
    private Boolean registred = false;
    @Column(name = "registerTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerTime;
    @Column(name = "registerDuration")
    private Long registerDuration;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "accountNo")
    private int accountNo;
    @Column(name = "company", unique = true)
    private String company;
    @Column(name = "token")
    private String token;
    @Column(name = "lastLoginTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ArrayList<Job> jobs = new ArrayList<Job>();

    public Customer() {
    }

    public Long getRegisterDuration() {
        return registerDuration;
    }

    public void setRegisterDuration(Long registerDuration) {
        this.registerDuration = registerDuration;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getRegistred() {
        return registred;
    }

    public void setRegistred(Boolean registred) {
        this.registred = registred;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

}
