package Model;

public class Request {
    private String name , job;
    private int salary , numofmonths;

    public Request() {
    }

    public Request(String job , String name, int salary, int numOfmonths) {
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.numofmonths = numOfmonths;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getSalary() {
        return salary;
    }

    public int getNumofmonths() {
        return numofmonths;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setNumofmonths(int numofmonths) {
        this.numofmonths = numofmonths;
    }
}
