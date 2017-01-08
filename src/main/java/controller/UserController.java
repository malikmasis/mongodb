package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.User;

import com.mysql.jdbc.PreparedStatement;

@Controller
// Controller sayfasi oldugunu belirtiyoruz
public class UserController {

	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<String> orderList = new ArrayList<String>();
	String cekilenVeri = null;

	// yonlendirecegimiz sayfa
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request,
			ModelMap model,
			// Kullanacagmiz parametreler
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname,
			@RequestParam(required = false) String phone) {

		try {
			if (!name.equals("")) {
				// veritabani baglantilari
				// eger siz de veri tabani kullanmak isterseniz kendi veritabani
				// ayarlarinizi yapmaniz lazim
				// ben mysql kullandim. 4 sutun mevcur id(primer
				// key),name,surname,phone //tablo ismi infonal
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/infonal";
				String kullaniciad = "root"; // burada kendi bilgilerinizi girin
				String sifre = ""; // ayni sekilde sifrenizi
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				con = DriverManager.getConnection(url, kullaniciad, sifre);
				st = con.createStatement();

				String sql = "INSERT INTO user (name, surname, phone)"
						+ "VALUES (?, ?, ?)";
				PreparedStatement preparedStatement = (PreparedStatement) con
						.prepareStatement(sql);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, surname);
				preparedStatement.setString(3, phone);
				preparedStatement.executeUpdate();

				// veri tabanina kayit yapiliyor
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Surucu projeye eklenmemis!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Veritabanina baglanti saglanamadi!");
		}

		ModelAndView mvr = new ModelAndView("save");
		mvr.addObject("name", name);
		mvr.addObject("surname", surname);
		mvr.addObject("phone", phone);
		// parametreleri save sayfasindan kullanacagimizi belirtiyoruz
		return mvr;

	}

	String orderShape = "ST";

	@RequestMapping("/list")
	public ModelAndView list() {
		System.out.println("in controller list");
		orderList.clear();
		orderList.add("name");
		orderList.add("surname");
		orderList.add("phone");
		try {
			// veri tabani islemleri
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/infonal";
			String kullaniciad = "root";
			String sifre = "";
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			con = DriverManager.getConnection(url, kullaniciad, sifre);
			st = con.createStatement();

			// verileri sıraladığımız bölüm
			String vericek = "SELECT id,name,surname,phone FROM user";
			rs = st.executeQuery(vericek);
			userList.clear();
			while (rs.next()) {
				System.out.println("Isim= " + rs.getString("name")
						+ "  Soyisim= " + rs.getString("surname")
						+ "  Telefon= " + rs.getString("phone") + "  id= "
						+ rs.getInt("id"));
				cekilenVeri = (rs.getString("name") + "  "
						+ rs.getString("surname") + " " + rs.getString("phone"));
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setPhone(rs.getString("phone"));
				userList.add(user);

			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Surucu projeye eklenmemis!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Veritabanina baglanti saglanamadi!");
		}
		// buralari eski yazilarimizda aciklamistik
		ModelAndView mv = new ModelAndView("list");
		mv.addObject("userList", userList);
		mv.addObject("orderList", orderList);
		return mv;
	}

	// silme isleminde hangi satirini sildigini bilmesi icin bu tanmlama
	// kullanilir
	@RequestMapping("/list/{id}")
	public ModelAndView delete(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Integer id,
			// jsp tarafindan veri alnacaksa pathVariable anatasyonu kullanlr
			@RequestParam(value = "idNew", required = false) Integer idNew
	// jsp tarafindan veri gonderilecekse requestParametre anatasyonu kullanlr
	) {

		ArrayList<User> userList = new ArrayList<User>();
		User user = new User();
		orderList.clear();
		orderList.add("name");
		orderList.add("surname");
		orderList.add("phone");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/infonal";
			String kullaniciad = "root";
			String sifre = "";
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			con = DriverManager.getConnection(url, kullaniciad, sifre);
			st = con.createStatement();

			// hangi bilgiye göre silecegi. id= primer keydi hatrlarsaniz
			String query = "delete from user where id = ?";
			PreparedStatement preparedStmt = (PreparedStatement) con
					.prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.execute();

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Surucu projeye eklenmemis!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Veritabanina baglanti saglanamadi!");
		}

		// silme isleminden sonra listenin tekrar gelmesi icin
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/infonal";
			String kullaniciad = "root";
			String sifre = "";
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			con = DriverManager.getConnection(url, kullaniciad, sifre);
			st = con.createStatement();

			// verileri sirala
			String vericek = "SELECT id,name,surname,phone FROM user";
			rs = st.executeQuery(vericek);
			userList.clear();
			while (rs.next()) {
				System.out.println("Isim= " + rs.getString("name")
						+ "  Soyisim= " + rs.getString("surname") + "ID= "
						+ rs.getInt("id"));
				cekilenVeri = (rs.getInt("id") + " " + rs.getString("name")
						+ "  " + rs.getString("surname") + " " + rs
						.getString("phone"));
				User user1 = new User();
				user1.setId(rs.getInt("id"));
				user1.setName(rs.getString("name"));
				user1.setSurname(rs.getString("surname"));
				user1.setPhone(rs.getString("phone"));
				userList.add(user1);
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Surucu projeye eklenmemis!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Veritabanina baglanti saglanamadi!");
		}

		ModelAndView mvr = new ModelAndView("list");
		mvr.addObject("id", id);
		mvr.addObject("orderShape", orderShape);
		mvr.addObject("orderList", orderList);
		mvr.addObject("userList", userList);

		return mvr;

	}

	// uptade
	// tıkladğımız ilgili linkin bilgilerine gider
	@RequestMapping("/uptade/{name}/{surname}/{phone}/{id}")
	public ModelAndView uptade(
			HttpServletRequest request,
			ModelMap model,
			// PathVariable ve Request Parami daha önce aciklamstik
			@PathVariable("id") Integer id,
			@PathVariable("name") String name,
			@PathVariable("surname") String surname,
			@PathVariable("phone") String phone,
			@RequestParam(value = "idNew", required = false) Integer idNew,
			@RequestParam(value = "name", required = false) String nameNew,
			@RequestParam(value = "surname", required = false) String surnameNew,
			@RequestParam(value = "phone", required = false) String phoneNew) {

		ArrayList<User> userList = new ArrayList<User>();
		User user = new User();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/infonal";
			String kullaniciad = "root";
			String sifre = "";
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			con = DriverManager.getConnection(url, kullaniciad, sifre);
			st = con.createStatement();
			// uptade islemini yapyoruz. id primer key oldugu icin buna g�re
			// yapyoruz
			String query = "update user set   name= ? , surname= ? , phone=?   where id=? ";
			PreparedStatement preparedStmt = (PreparedStatement) con
					.prepareStatement(query);

			preparedStmt.setString(1, nameNew);
			preparedStmt.setString(2, surnameNew);
			preparedStmt.setString(3, phoneNew);
			preparedStmt.setInt(4, id);
			// bu sra �nemli dikkat etmekte fayda. Siradan kasit numaralar onu
			// karstrmayalm
			preparedStmt.executeUpdate();

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Surucu projeye eklenmemis!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Veritabanina baglanti saglanamadi!");
		}
		ModelAndView mvr = new ModelAndView("uptade");

		mvr.addObject("name", name);
		mvr.addObject("id", id);
		mvr.addObject("idNew", idNew);
		mvr.addObject("nameNew", nameNew);
		mvr.addObject("surnameNew", surnameNew);
		mvr.addObject("phoneNew", phoneNew);
		mvr.addObject("surname", surname);
		mvr.addObject("phone", phone);
		mvr.addObject("userList", userList);

		return mvr;

	}

	boolean orderReverse = false;

	@RequestMapping("/list/order/{order}")
	public ModelAndView orderBy(HttpServletRequest request, ModelMap model,
	// PathVariable ve Request Parami daha önce aciklamstik
			@PathVariable("order") String order
	// @RequestParam(value = "orderShape", required = false) String orderShape
	) {

		String aa="name";
		System.out.println("in controller order");
		System.out.println("***********" + order);
		orderShape = "Dikkat";
		try {
			// veri tabani islemleri
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/infonal";
			String kullaniciad = "root";
			String sifre = "";
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			con = DriverManager.getConnection(url, kullaniciad, sifre);
			st = con.createStatement();

			System.out.print(orderReverse + "t/f");

			   if(orderReverse == false){
				// orderShape parametresini sadece yazıyı göstermek amaciyla yazdık
				orderShape = "DUZ";
				// eger orderReserve false ise bize alfabetik sıralama yapiyor
				//order değişkenini yukarıdan alıyoruz. .jsp sayfasından tıklanan link
				//hangisiyle oradaki değer order'a atanır ve ona göre sıralama yapılır.
				String vericek = "SELECT id,name,surname,phone FROM user ORDER BY  " + order + "";
				rs = st.executeQuery(vericek);
				// Burada ise değerimizi true yapıyoruz. Çünkü tıkladığımızda
				// ters sıralama yapabilsin
				orderReverse = true;

			   }
			   else
			   {
				   orderShape = "TERS";
					System.out.print("45");
					// eger orderReserve false ise bize alfabetik sıralama yapiyor
					//aynı işlem order'in tersi için de yapılıyor
					String vericek = "SELECT id,name,surname,phone FROM user ORDER BY  " + order + " DESC";
					rs = st.executeQuery(vericek);
					// Burada ise değerimizi true yapıyoruz. Çünkü tıkladığımızda
					// ters sıralama yapabilsin
					orderReverse = false;
			   }
			
			
			/*
			if (orderReverse == false && order.equals("Sırala")) {
				// orderShape parametresini sadece yazıyı göstermek amaciyla yazdık
				orderShape = "DUZ1";
				System.out.print("1");
				// eger orderReserve false ise bize alfabetik sıralama yapiyor
				String vericek = "SELECT id,name,surname,phone FROM user ORDER BY  " + aa + "";
				String vericek1 = "SELECT id,name,surname,phone FROM user ORDER BY  name";
				rs = st.executeQuery(vericek);
				System.out.println(vericek + "--" + aa+ "-?-" + vericek1 );
				// Burada ise değerimizi true yapıyoruz. Çünkü tıkladığımızda
				// ters sıralama yapabilsin
				orderReverse = true;

			} else if (orderReverse == true && order.equals("Sırala")) {
				// yukarıdaki kodun ters mantığı burada da geçerlidir.
				orderShape = "Ters1";
				System.out.print("2");
				String vericek = "SELECT id,name,surname,phone FROM user ORDER BY name DESC";
				rs = st.executeQuery(vericek);
				orderReverse = false;
			} 
			//soy isim sıralamasını alfabetik yapıyoruz
			else if (orderReverse == false && order.equals("SıralaA-Z")) {
				orderShape = "DUZ2";
				System.out.print("3");
				String vericek = "SELECT id,name,surname,phone FROM user ORDER BY surname ASC";
				rs = st.executeQuery(vericek);
				orderReverse = true;

			}
			//soy isim sıralamasını alfabenin tersine göre yapıyoruz
			else if (orderReverse == true && order.equals("SıralaA-Z")) {
				orderShape = "Ters2";
				System.out.print("4");
				String vericek = "SELECT id,name,surname,phone FROM user ORDER BY surname DESC";
				rs = st.executeQuery(vericek);
				orderReverse = false;
			}
			//telefon sıralamasını küçükten büyüğe yapıyoruz
			else if (orderReverse == false && order.equals("Sırala0-9")) {
				orderShape = "Düz3";
				System.out.print("5");
				String vericek = "SELECT id,name,surname,phone FROM user ORDER BY phone ASC";
				rs = st.executeQuery(vericek);
				orderReverse = true;
			}
			//telefon sıralamasını büyükten küçüğe yapıyoruz
			else if (orderReverse == true && order.equals("Sırala0-9")) {
				orderShape = "Ters3";
				System.out.print("6");
				String vericek = "SELECT id,name,surname,phone FROM user ORDER BY phone DESC";
				rs = st.executeQuery(vericek);
				orderReverse = false;
			}*/

			if (order.equals("Sırala"))
				System.out.print("!!");
			else
				System.out.print("%%");

			userList.clear();
			while (rs.next()) {
				System.out.println("Isim= " + rs.getString("name")
						+ "  Soyisim= " + rs.getString("surname")
						+ "  Telefon= " + rs.getString("phone") +
						"  id= "+ rs.getInt("id"));
				cekilenVeri = (rs.getString("name") + "  "
						+ rs.getString("surname") + " " + rs.getString("phone"));
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setPhone(rs.getString("phone"));
				userList.add(user);
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Surucu projeye eklenmemis!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Veritabanina baglanti saglanamadi!");
		}
		// buralari eski yazilarimizda aciklamistik
		ModelAndView mv = new ModelAndView("list");
		mv.addObject("orderShape", orderShape);
		mv.addObject("userList", userList);
		mv.addObject("orderList", orderList);

		return mv;
	}

}