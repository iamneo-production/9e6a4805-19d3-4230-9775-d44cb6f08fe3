package model;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Medicine {

    @Id
	@GeneratedValue
    private int medicineId;
    private String medicineName;
    private Date expiryDate;
    private Date manufacturingDate;
    private int medicinePrice;
    private String medicineQuality;

    public Medicine(int medicineId, String medicineName, Date expiryDate, Date manufacturingDate, int medicinePrice, String medicineQuality){
        super();
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.expiryDate = expiryDate;
        this.manufacturingDate = manufacturingDate;
        this.medicinePrice = medicinePrice;
        this.medicineQuality = medicineQuality;
    }

    public Medicine(String medicineName, Date expiryDate, Date manufacturingDate, int medicinePrice, String medicineQuality){
        super();
        this.medicineName = medicineName;
        this.expiryDate = expiryDate;
        this.manufacturingDate = manufacturingDate;
        this.medicinePrice = medicinePrice;
        this.medicineQuality = medicineQuality;
    }

    public int getMedicineId(){
        return this.medicineId;
    }
    public void setMedicineId(int mi){
        this.medicineId = mi;
    }

    public String getMedicineName(){
        return this.medicineName;
    }
    public void setMedicineName(String mn){
        this.medicineName = mn;
    }

    public Date getExpiryDate(){
        return this.expiryDate;
    }
    public void setExpiryDate(Date ed){
        this.expiryDate = ed;
    }

    public Date getManufacturingDate(){
        return this.manufacturingDate;
    }
    public void setManufacturingDate(Date md){
        this.manufacturingDate = md;
    }

    public int getMedicinePrice(){
        return this.medicinePrice;
    }
    public void setMedicinePrice(int mp){
        this.medicinePrice = mp;
    }

    public String getMedicineQuality(){
        return this.medicineQuality;
    }
    public void setMedicineQuality(String mq){
        this.medicineQuality = mq;
    }

}
