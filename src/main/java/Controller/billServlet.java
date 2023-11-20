package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.billDao;
import modelo.bill;

/**
 * Servlet implementation class billServlet
 */
@WebServlet("/billServlet")
public class billServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private billDao billDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public billServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.billDao = new billDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
			switch (action) {

			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertarBill(request, response);
				break;
			case "/delete":
				eliminarBill(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				actualizarBill(request, response);

				break;
			default:
				listBill(request, response);
				break;

			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
	}

	private void insertarBill(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		Date date = request.get;
		String value = request.getParameter("value");
		String type = request.getParameter("type");
		String observation = request.getParameter("observation");

        bill bill = new bill(date, value, type, observation);

		billDao.insert(bill);

		response.sendRedirect("list");

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int id = Integer.parseInt(request.getParameter("id"));

		bill billActual = billDao.select(id);

		request.setAttribute("usuario", billActual);

		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);

	}

	private void actualizarBill(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int id = Integer.parseInt(request.getParameter("id"));

		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");

		Usuario usuario = new Usuario(id, nombre, email, pais);

		usuarioDao.update(usuario);

		response.sendRedirect("list");

	}

	private void eliminarBill(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int id = Integer.parseInt(request.getParameter("id"));

		usuarioDao.delete(id);

		response.sendRedirect("list");
	}

	private void listBill(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		List<Usuario> listUsuarios = usuarioDao.selectAll();
		request.setAttribute("listUsuarios", listUsuarios);

		RequestDispatcher dispatcher = request.getRequestDispatcher("usuariolist.jsp");
		dispatcher.forward(request, response);

	}

}
