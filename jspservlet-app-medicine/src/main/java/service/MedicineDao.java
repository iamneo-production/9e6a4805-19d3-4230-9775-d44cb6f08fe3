package service;
import utility.ConnectionManager;
import model.Medicine;
import java.sql.*;
import java.util.ArrayList;

public class MedicineDao {
    private static final String ADD_MEDICINE_SQL = "INSERT INTO medicines" +" (medicineName, expiryDate, manufacturingDate, medicinePrice, medicineQuality) VALUES"+" (?, ?, ?, ?, ?);";
    private static final String UPDATE_MEDICINE_SQL = "update medicines set medicineName=?, expiryDate=?, manufacturingDate=?, medicinePrice=?, medicineQuality=? where medicineId=?;";
    private static final String VIEW_MEDICINE_BY_ID = "select medicineId, medicineName, expiryDate, manufacturingDate, medicinePrice, medicineQuality from medicines where medicineId =?;";
    private static final String DELETE_MEDICINE_SQL = "delete from medicines where medicineId = ?;";
    private static final String SELECT_ALL_MEDICINE = "select * from medicines;";
    private static final String SELECT_SEARCH_MEDICINE = "select * from medicines WHERE medicineName LIKE ? ;";
    
        //Display all medicines
        public ArrayList<Medicine> selectSearchMedicines(String mName) {
            ArrayList<Medicine> medicines = new ArrayList<>();
            ConnectionManager con = new ConnectionManager();
            try (Connection connection = con.getConnection();
                    PreparedStatement statement = connection.prepareStatement(SELECT_SEARCH_MEDICINE);) {
                statement.setString(1, "%" + mName + "%");
                ResultSet rs = statement.executeQuery();
    
               while (rs.next()) {
                    int id = rs.getInt("medicineId");
                    String medicineName = rs.getString("medicineName");
                    Date expiryDate = rs.getDate("expiryDate");
                    Date manufacturingDate = rs.getDate("manufacturingDate");
                    int medicinePrice = rs.getInt("medicinePrice");
                    String medicineQuality = rs.getString("medicineQuality");
                    medicines.add(new Medicine(id, medicineName, expiryDate, manufacturingDate,medicinePrice,medicineQuality));
                }
             } catch (SQLException e) {
                e.printStackTrace();
            }
            return medicines;
        }

    //Add Medicine
    public void addMedicine(Medicine medicine) throws SQLException{
            ConnectionManager con = new ConnectionManager();
            try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_MEDICINE_SQL);){

                statement.setString(1, medicine.getMedicineName());
                statement.setDate(2, medicine.getExpiryDate());
                statement.setDate(3, medicine.getManufacturingDate());
                statement.setInt(4, medicine.getMedicinePrice());
                statement.setString(5, medicine.getMedicineQuality());
                statement.executeUpdate();

            }catch(Exception e){
                e.printStackTrace();
            }
    }

    //Update Medicine
    public boolean updateMedicine(Medicine medicine) throws SQLException{
        boolean rowUpdated;
        ConnectionManager con = new ConnectionManager();
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_MEDICINE_SQL);){

            statement.setString(1, medicine.getMedicineName());
            statement.setDate(2, medicine.getExpiryDate());
            statement.setDate(3, medicine.getManufacturingDate());
            statement.setInt(4, medicine.getMedicinePrice());
            statement.setString(5, medicine.getMedicineQuality());
            statement.setInt(6, medicine.getMedicineId());
            rowUpdated = statement.executeUpdate() > 0;

        }
        return rowUpdated;
    }
         
    // View Medicine By Id
    public Medicine viewMedicineById(int id){
        Medicine medicine = null;
        ConnectionManager con = new ConnectionManager();
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement(VIEW_MEDICINE_BY_ID);) {
        
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                
                    while (rs.next()) {
                        String medicineName = rs.getString("medicineName");
                        Date expiryDate = rs.getDate("expiryDate");
                        Date manufacturingDate = rs.getDate("manufacturingDate");
                        int medicinePrice = rs.getInt("medicinePrice");
                        String medicineQuality = rs.getString("medicineQuality");
                        medicine = new Medicine(id, medicineName, expiryDate, manufacturingDate,medicinePrice,medicineQuality);
                    }
                                        
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return medicine;

            }

    //Display all medicines
    public ArrayList<Medicine> selectAllMedicines() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        ConnectionManager con = new ConnectionManager();
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MEDICINE);) {
            ResultSet rs = statement.executeQuery();

           while (rs.next()) {
                int id = rs.getInt("medicineId");
                String medicineName = rs.getString("medicineName");
                Date expiryDate = rs.getDate("expiryDate");
                Date manufacturingDate = rs.getDate("manufacturingDate");
                int medicinePrice = rs.getInt("medicinePrice");
                String medicineQuality = rs.getString("medicineQuality");
                medicines.add(new Medicine(id, medicineName, expiryDate, manufacturingDate,medicinePrice,medicineQuality));
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }

    // Delete Medicine
    public boolean deleteMedicine(int medicineId) throws SQLException{
        boolean rowDeleted;
        ConnectionManager con = new ConnectionManager();
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_MEDICINE_SQL);){
            statement.setInt(1, medicineId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}