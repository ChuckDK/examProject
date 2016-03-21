import java.time.LocalDate;
import java.time.LocalTime;

public class CourseCertificate {
    private int certificateID;
    private LocalDate date;
    private LocalTime time;

    public CourseCertificate(int certificateID) {
        this.certificateID = certificateID;
        this.time = LocalTime.now();
        this.date = LocalDate.now();
    }

    public CourseCertificate(int certificateID, LocalDate date, LocalTime time) {
        this.certificateID = certificateID;
        this.date = date;
        this.time = time;
    }

    public int getCertificateID() {
        return certificateID;
    }

    public void setCertificateID(int certificateID) {
        this.certificateID = certificateID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseCertificate)) return false;

        CourseCertificate that = (CourseCertificate) o;

        return getCertificateID() == that.getCertificateID();

    }

    @Override
    public int hashCode() {
        return getCertificateID();
    }
}
