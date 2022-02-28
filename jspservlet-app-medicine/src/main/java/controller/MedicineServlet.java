package controller;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Medicine;
import service.MedicineDao;


@WebServlet("/")
public class MedicineServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private MedicineDao medicineDao;
	
	public MedicineServlet() {
		this.medicineDao = new MedicineDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/":
				listMedicine(request, response);
				break;
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertMedicine(request, response);
				break;
			case "/delete":
				deleteMedicine(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateMedicine(request, response);
				break;
			case "/search":
				searchMedicine(request, response);
				break;
			default:
				listMedicine(request, response);
				break;
			}
		} catch (SQLException | ParseException ex) {
			throw new ServletException(ex);
		}
	}

	private void searchMedicine(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
				String medicineName = request.getParameter("medicineName");
				List<Medicine> listMedicine = medicineDao.selectSearchMedicines(medicineName);
				request.setAttribute("listMedicine", listMedicine);
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
	}

	private void listMedicine(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Medicine> listMedicine = medicineDao.selectAllMedicines();
		request.setAttribute("listMedicine", listMedicine);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Medicine-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int medicineId = Integer.parseInt(request.getParameter("medicineId"));
		Medicine existingMedicine = medicineDao.viewMedicineById(medicineId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Medicine-form.jsp");
		request.setAttribute("medicine", existingMedicine);
		dispatcher.forward(request, response);

	}

	private void insertMedicine(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ParseException {
		String medicineName = request.getParameter("medicineName");
		java.util.Date edate = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("expiryDate"));
		Date expiryDate = new Date(edate.getTime());
		java.util.Date mdate = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("manufacturingDate"));
		Date manufacturingDate = new Date(mdate.getTime());
		int medicinePrice = Integer.parseInt(request.getParameter("medicinePrice"));
		String medicineQuality = request.getParameter("medicineQuality");
		Medicine newMedicine = new Medicine(medicineName, expiryDate, manufacturingDate,medicinePrice,medicineQuality);
		medicineDao.addMedicine(newMedicine);
		response.sendRedirect("list");
	}

	private void updateMedicine(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ParseException {

		int medicineId = Integer.parseInt(request.getParameter("medicineId"));
		String medicineName = request.getParameter("medicineName");
		java.util.Date edate = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("expiryDate"));
		Date expiryDate = new Date(edate.getTime());
		java.util.Date mdate = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("manufacturingDate"));
		Date manufacturingDate = new Date(mdate.getTime());
		int medicinePrice = Integer.parseInt(request.getParameter("medicinePrice"));
		String medicineQuality = request.getParameter("medicineQuality");

		Medicine medicine = new Medicine(medicineId, medicineName, expiryDate, manufacturingDate,medicinePrice,medicineQuality);
		medicineDao.updateMedicine(medicine);
		response.sendRedirect("list");
	}

	private void deleteMedicine(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int medicineId = Integer.parseInt(request.getParameter("medicineId"));
		medicineDao.deleteMedicine(medicineId);
		response.sendRedirect("list");

	}
}